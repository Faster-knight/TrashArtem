package com.example.demo.app;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

// User object class
@Getter
@Setter
public class User {
    private String name;
    private HashMap<String, String> save;
    private final Integer id;
    public User(Integer id, String name) {this.id = id;this.name = name;}
    public HashMap<String, Object> UserToJSON() {
        HashMap<String, Object> temp = new HashMap<>();
        temp.put("name", getName());
        temp.put("id", getId());
        temp.put("contacts", getSave());
        return temp;
    }
}
