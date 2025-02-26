package com.example.myapplication;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    private static GlobalData instance;
    private List<User> userList;

    private GlobalData() {
        userList = new ArrayList<>();
        userList.add(new User("Admin", "Admin", "Admin", "Admin", true));
        userList.add(new User("test", "test", "test", "test", true));
        userList.add(new User("test2", "test2", "test2", "test2", false));
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

    public void removeUserByLogin(String login){
        if(!login.equals(userList.get(0).GetLogin())){
            userList.removeIf(user -> user.GetLogin().equals(login));
        }
    }

    public List<User> getUsers(){
        return new ArrayList<>(userList);
    }
    public User getUserByIndex(int index){
        if (index >= 0 && index < userList.size()){
            return userList.get(index);
        }
        return null;
    }

    public User getUserByLogin(String login){
        for(User user : userList){
            if (user.GetLogin().equals(login)){
                return user;
            }
        }
        return null;
    }

    public void replaceUserByLogin(String login, User newUser) {
        for (int i = 0; i < userList.size(); i++) {
            User currentUser = userList.get(i);
            if (currentUser.GetLogin().equals(login)) {
                userList.set(i, newUser);
                break;
            }
        }
    }
}
