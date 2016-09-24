package org.wyf.annotation.entity;

import org.wyf.annotation.ann.Item;
import org.wyf.annotation.ann.UserInfo;

/**
 * Created by wyf on 16/9/24.
 */
@UserInfo("user_a")
public class UserA {
    @Item("user_name")
    String name;
    @Item("age")
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
