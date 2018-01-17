package com.example.pc.classwritingtextfiles;

/**
 * Created by pc on 11/15/2017.
 */
public class Person {
    //private properties
    private String name;
    private String surname;

    // constructor
    public Person(String Name, String Surname){
        this.name = Name;
        this.surname = Surname;
    }
    // getter and setter for name property
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    // getter and setter for surname property
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getSurname(){
        return surname;
    }
}
