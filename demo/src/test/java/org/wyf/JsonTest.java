package org.wyf;

import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.wyf.common.codec.JsonCodec;
import org.wyf.json.entity.Person;

import java.util.ArrayList;
import java.util.List;

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
        List<Person> ps = JsonCodec.decode(str, new TypeToken<List<Person>>(){}.getType());
        for(int i = 0; i < ps.size() ; i++)
        {
            Person p = ps.get(i);
            System.out.println(p.toString());
        }

    }
}
