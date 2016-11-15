package com.kcode.reamlexample.model;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by caik on 2016/11/15.
 */

@RealmClass
public class User implements RealmModel {
    private String name;
    private int age;

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

    @Override
    public String toString() {
        return "姓名:" + name + "，年龄：" + age;
    }
}
