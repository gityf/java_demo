package org.wyf;

import com.alibaba.fastjson.JSON;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.wyf.common.codec.JsonCodec;
import org.wyf.common.codec.JsonUtil;
import org.wyf.json.entity.Person;

import java.util.*;

/**
 * Created by wyf on 16/9/24.
 */
public class JsonTest {
    @Test
    public void GsonDemo() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 10; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setAge(i * 5);
            persons.add(p);
        }
        String str = JsonCodec.encode(persons);
        System.out.println(str);
        String str1 = JsonUtil.toJson(persons);
        System.out.println(str1);
        List<Person> ps = JsonCodec.decode(str, new TypeToken<List<Person>>(){}.getType());
        for(int i = 0; i < ps.size() ; i++)
        {
            Person p = ps.get(i);
            System.out.println(p.toString());
        }
    }

    @Test
    public void JsonUtilDemo() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 3; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setAge(i * 5);
            persons.add(p);
        }
        String str = JsonUtil.toJson(persons);
        System.out.println(str);
        List<LinkedHashMap<String, Object>> list = JsonUtil.toObject(str, List.class);
        System.out.println(list.size());
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            Set<String> set = map.keySet();
            for (Iterator<String> it = set.iterator();it.hasNext();) {
                String key = it.next();
                System.out.println(key + ":" + map.get(key));
            }
        }
    }

    @Test
    public void FastJsonDemo() {
        List<Person> persons = new ArrayList<Person>();
        for (int i = 0; i < 3; i++) {
            Person p = new Person();
            p.setName("name" + i);
            p.setAge(i * 5);
            persons.add(p);
        }
        String str = JSON.toJSONString(persons);
        System.out.println(str);

    }
}
