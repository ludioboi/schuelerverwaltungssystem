package de.wild.school;

import java.util.Date;

public class Person {

    private String firstname, midname, lastname, address, tel;
    private Date birthdate;

    public Person(){
        firstname ="";
        midname = "";
        lastname = "";
        address = "";
        tel = "";
        birthdate = new Date();
    }

    public Person(String forename, String lastname, String address, String tel, Date birthdate) {
        this.firstname = forename;
        this.midname = "";
        this.lastname = lastname;
        this.address = address;
        this.tel = tel;
        this.birthdate = birthdate;
    }

    public Person(String forename, String midname, String lastname, String address, String tel, Date birthdate) {
        this.firstname = forename;
        this.midname = midname;
        this.lastname = lastname;
        this.address = address;
        this.tel = tel;
        this.birthdate = birthdate;
    }

    public String getFullName(){
        return firstname + (midname.isEmpty() ? " " : " " + midname + " ") + lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMidname() {
        return midname;
    }

    public void setMidname(String midname) {
        this.midname = midname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
