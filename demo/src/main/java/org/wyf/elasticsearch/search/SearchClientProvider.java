package org.wyf.elasticsearch.search;

/**
 * Created by wyf on 16/9/25.
 */
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wyf.common.property.XmlProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static java.lang.System.out;

public class SearchClientProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchClientProvider.class);

    private TransportClient client = null;
    private volatile boolean inited = false;

    public TransportClient get() {
        return this.client;
    }

    @PreDestroy
    public synchronized void close() {
        if (this.client != null) {
            this.client.close();
        }
    }

    @PostConstruct
    public synchronized void init() {
        if (!inited) {
            try {
                Map<String, String> config = XmlProperties.loadFromXml("properties/elasticsearch.xml");
                if (config == null) {
                    out.println("load xml err");
                    return;
                }
                Iterator<Map.Entry<String, String>> iterator = config.entrySet().iterator();
                Map<String, String> settingConfig = new HashMap<String, String>();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> next = iterator.next();
                    if (!next.getKey().equals("transport.addresses")) {
                        settingConfig.put(next.getKey(), next.getValue());
                    }
                }
                Settings settings = Settings.builder().put(settingConfig).build();
                TransportClient client = TransportClient.builder().settings(settings).build();
                this.client = client;

                String[] addresses = config.get("transport.addresses").split(",");
                for (String address : addresses) {
                    String[] hostAndPort = address.split(":");
                    client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(hostAndPort[0]), Integer.valueOf(hostAndPort[1])));

                }
                this.inited = true;
            } catch (UnknownHostException e) {
                LOGGER.error(String.format("init search client err:=>msg:[%s]", e.getMessage()), e);
                if (client != null) {
                    this.client.close();
                }
            }
        }
    }
}