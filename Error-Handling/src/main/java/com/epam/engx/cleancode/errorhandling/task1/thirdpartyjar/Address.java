package com.epam.engx.cleancode.errorhandling.task1.thirdpartyjar;

import java.util.Map;

public class Address {

    private Map<String, String> data;

    public Address(Map data) {
        this.data = data;
    }

    public String getCity(){
        return data.get("city");
    }

    public String getStreet(){
        return data.get("street");
    }

    public String getHouse(){
        return data.get("house");
    }

}
