package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    private static GlobalData instance;
    private List<User> userList;

    private GlobalData() {
        userList = new ArrayList<>();
        userList.add(new User("Admin", "Admin", "Admin", "Admin", true));
        userList.add(new User("test", "test", "test", "test", false));
    }
    public static GlobalData getInstance() {
        if (instance == null){
            instance = new GlobalData();
        }
        return instance;
    }

    public void addUser(User user){
        userList.add(user);
    }

    public void removeUserByIndex(int index){
        if (index > 0 && index < userList.size()) {
            userList.remove(index);
        }
    }

    public void removeUserByLogin(String login){
        if(!login.equals(userList.get(0).GetLogin())){
            userList.removeIf(user -> user.GetLogin().equals(login));
        }
    }

    public List<User> getUsers(){
        return new ArrayList<>(userList);
    }

    public int getSize(){
        return userList.size();
    }

    public User getUserByIndex(int index){
        if (index >= 0 && index < userList.size()){
            return userList.get(index);
        }
        return null;
    }

    public int getIndexByLogin(String login){
        for (int i = 0; i < userList.size(); i++){
            User user = userList.get(i);
            if(user.GetLogin().equals(login)){
                return i;
            }
        }
        return -1;
    }

    public User getUserByLogin(String login){
        for(User user : userList){
            if (user.GetLogin().equals(login)){
                return user;
            }
        }
        return null;
    }
}
