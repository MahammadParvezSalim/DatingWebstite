/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ashish
 */
public class Profile 

{
    private String fname;
    private String lname;
    private String gender;
    private int age;
    private String city;
    private String intrst1;
    private String intrst2;
    private String intrst3;
    private String timeStamp;

    

    public Profile(String fname, String lname, String gender, int age, String city, String intrst1, String intrst2, String intrst3,String timeStamp) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.age = age;
        this.city = city;
        this.intrst1 = intrst1;
        this.intrst2 = intrst2;
        this.intrst3 = intrst3;
        this.timeStamp = timeStamp;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIntrst1() {
        return intrst1;
    }

    public void setIntrst1(String intrst1) {
        this.intrst1 = intrst1;
    }

    public String getIntrst2() {
        return intrst2;
    }

    public void setIntrst2(String intrst2) {
        this.intrst2 = intrst2;
    }

    public String getIntrst3() {
        return intrst3;
    }

    public void setIntrst3(String intrst3) {
        this.intrst3 = intrst3;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    
}
