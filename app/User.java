package com.sharetechidea.srms;

public class User {
    public static String name;
    public static boolean isAdmin;


    public static void findUser(String email){
        String[] split = email.split("@");
        name= split[0];
        isUserAdmin();

    }

    private static void isUserAdmin(){
FirebaseServices.getUserAdmin(name);


    }




}
