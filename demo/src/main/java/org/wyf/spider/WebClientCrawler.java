package org.wyf.spider;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangyaofu on 2018/5/7.
 */
public class WebClientCrawler extends Thread {
    public boolean isRunning = true;
    @Override
    public void run() {
        while(isRunning){
            List<String> urlList = webParseHtml(targetUrl);
            System.out.println(urlList);
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    long sleepMs = 200;
    boolean useJs = false;
    String targetUrl = "";
    int timeOut = 5000;

    public WebClientCrawler(long sleepMs, String targetUrl, boolean useJs, int timeOut) {
        this.sleepMs = sleepMs;
        this.targetUrl = targetUrl;
        this.useJs = useJs;
        this.timeOut = timeOut;
    }

    final String DIV_ID = "body";
    final String TAG_IMG = "img";
    final String IMG_SRC = "src";

    public List<String> webParseHtml(String url) {
        String html = "";
        List<String> imgUrlList = new ArrayList<String>();
        BrowserVersion[] versions = {BrowserVersion.INTERNET_EXPLORER_11, BrowserVersion.CHROME, BrowserVersion.FIREFOX_38, BrowserVersion.INTERNET_EXPLORER_8};
        WebClient client = new WebClient(versions[(int)(versions.length * Math.random())]);
        try {
            client.getOptions().setThrowExceptionOnFailingStatusCode(false);
            client.getOptions().setJavaScriptEnabled(useJs);
            client.getOptions().setCssEnabled(false);
            client.getOptions().setThrowExceptionOnScriptError(false);
            client.getOptions().setTimeout(timeOut);
            client.getOptions().setAppletEnabled(true);
            client.getOptions().setGeolocationEnabled(true);
            client.getOptions().setRedirectEnabled(true);

            String ipport = null;
            if (ipport != null) {
                // to set http proxy
                ProxyConfig proxyConfig = new ProxyConfig(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]));
                client.getOptions().setProxyConfig(proxyConfig);
            }else {
                System.out.print(".");
                //return "";
            }

            HtmlPage page = client.getPage(url);

            if (page != null && page.isHtmlPage()) {

                //Thread.sleep(10);  // 等待页面加载完成。

                DomNodeList<DomElement> elementsByTagName = page.getElementsByTagName(TAG_IMG);


                for (DomElement hele : elementsByTagName) {

                    String imgUrl = hele.getAttribute(IMG_SRC);

                    if (StringUtils.isNotBlank(imgUrl)) {
                        imgUrlList.add(imgUrl);
                    }
                }

                return imgUrlList;
            }

            System.out.println(getName() + " 使用代理 " + ipport + "请求目标网址返回HTML：" + html);

        } catch (Exception e) {
            return webParseHtml(url);
        } finally {
            client.close();
        }
        return imgUrlList;
    }
}
