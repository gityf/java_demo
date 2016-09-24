package org.wyf;

import org.junit.Test;
import org.wyf.annotation.ann.Item;
import org.wyf.annotation.ann.UserInfo;
import org.wyf.annotation.entity.UserA;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.System.out;

/**
 * Created by wyf on 16/9/24.
 */
public class AnnTest {
    @Test
    public void TestAnn() {
        try {
            Class cls = Class.forName("org.wyf.annotation.entity.UserA");
            boolean annotationPresent = cls.isAnnotationPresent(UserInfo.class);
            if (!annotationPresent) {
                return;
            }
            UserInfo info = (UserInfo)cls.getAnnotation(UserInfo.class);
            String userInfo = info.value();
            out.println("annotation is:" + userInfo);
            Field[] fields = cls.getDeclaredFields();
            for (Field f: fields) {
                Item item = (Item)f.getAnnotation(Item.class);
                out.println("field is:"+item.value());
                out.println(f.getName());
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
