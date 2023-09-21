package com.app.urlshortener;

import java.util.List;

public class User {
    public String name;
    public int age;

    public User(String name,int age){
        this.name = name;
        this.age = age;
    }   

    public void sayHello(){
        System.out.println("bonjour, je m'appelle "+this.name+" et j'ai "+this.age);
    }

    public static void startUsersConversation(List<User> users ){
        for (User user : users) {
            user.sayHello();
        }
    }
}
