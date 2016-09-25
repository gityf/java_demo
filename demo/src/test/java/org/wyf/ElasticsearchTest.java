package org.wyf;

import org.junit.Before;
import org.junit.Test;
import org.wyf.elasticsearch.ISearch;
import org.wyf.elasticsearch.SearchImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by wyf on 16/9/25.
 */
public class ElasticsearchTest {
    ISearch search;

    @Test
    public void get() {
        Map<String,Object> map = search.get("a", "type1", "123");
        Set<String> set = map.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext();) {
            String key = it.next();
            System.out.println(key + ":" + map.get(key));
        }
    }

    @Test
    public void save() {
        Map<String, Object> values = new HashMap<String, Object>();
        values.put("k1", "v1");
        values.put("k2", "v2");

        Map<String,Object> map = search.save("a", "type1", "123", values);
        Set<String> set = map.keySet();
        for (Iterator<String> it = set.iterator(); it.hasNext();) {
            String key = it.next();
            System.out.println(key + ":" + map.get(key));
        }

    }

    @Before
    public void before() {
        search = new SearchImpl();
    }
}
