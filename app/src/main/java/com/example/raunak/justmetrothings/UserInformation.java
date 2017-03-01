package com.example.raunak.justmetrothings;

/**
 * Created by RAUNAK on 01-03-2017.
 */

public class UserInformation {
    public String name;
    public String gender;
    public String relationship;
    public String location;

    public UserInformation(){}

    public UserInformation(String username, String usergender, String userrelation, String userlocation){
        name = username;
        gender = usergender;
        relationship = userrelation;
        location = userlocation;
    }
}
