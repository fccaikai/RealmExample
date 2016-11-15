package com.kcode.reamlexample.model;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

/**
 * Created by caik on 2016/11/15.
 */

@RealmClass
public class Email implements RealmModel {
    private String address;
    private boolean isActive;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "address:" + address + ",isActive:" + isActive;
    }
}
