package com.kinoin.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cinema {
    private String name;
    private String address;
    private List<Session> sessions;

    public Cinema(String name, String address){
        this.name = name;
        this.address = address;
        this.sessions = new ArrayList<>();
    }

    public void addSession(Session session) {
        sessions.add(session);
    }

    public List<Movie> getMoviesInCartel() {
        return sessions.stream().map(Session::getMovie).distinct().collect(Collectors.toList());
    }

    public String getName() { return name; }

    public String getAddress() { return address; }

    public List<Session> getSessions() { return sessions; }
}
