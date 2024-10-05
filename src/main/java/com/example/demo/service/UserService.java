package com.example.demo.service;

import com.example.demo.app.User;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UserService {
    private TreeMap<Integer, User> appUsers;
    public UserService() {
        appUsers = new TreeMap<>();
    }
    public User getUserByID(@NonNull Integer id) {
        return appUsers.get(id);
    }
    public boolean addUserInAppData(@NonNull User newUser) {
        if (newUser.getId() == null || newUser.getName() == null) return false;
        if (getUserByID(newUser.getId()) == null) {
            appUsers.put(newUser.getId(), newUser);
            return true;
        }
        return false;
    }
    public boolean delUserInAppDataByID(Integer id) {
        appUsers.put(id, null);
        return true;
    }
    public boolean setUserName(@NonNull Integer id, @NonNull String name) {
        User temp = getUserByID(id);
        if (temp == null) {return false;}
        temp.setName(name);
        appUsers.put(id, temp);
        return true;
    }
    public boolean addContactInUserByID(
            @NonNull Integer id,
            @NonNull String name,
            @NonNull String contact
    ) {
        User temp = appUsers.get(id);
        if (temp == null) return false;
        temp.getSave().put(name, contact);
        appUsers.put(id, temp);
        return true;
    }
    public boolean delContactInUserBuID(
            Integer id,
            String name
    ) {
        User temp = appUsers.get(id);
        temp.getSave().put(name, null);
        return true;
    }
    public HashMap<String, String> getContactsByUserID(
            Integer id
    ) {
        User temp = appUsers.get(id);
        return temp.getSave();
    }
    public HashMap<String, Object> getUsersApp() {
        HashMap<String, Object> temp = new HashMap<>();
        int size = appUsers.size();
        for (int i = 0; i < size; i++) {
            String t = "";
            t.indent(i);
            temp.put(t, appUsers.get(i).UserToJSON());
        }
        return temp;
    }
}
