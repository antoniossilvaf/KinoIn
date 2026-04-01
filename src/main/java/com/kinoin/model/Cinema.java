package com.kinoin.model;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    private String name;
    private String address;
    private List<Session> sessions;

    public Cinema(String name, String address){
        this.name = name;
        this.address = address;
        this.sessions = new ArrayList<>();
    }

}
