package com.example.michael.addressbook;

import java.io.Serializable;

/**
 * Created by Michael on 9/25/2016.
 */

class ContactItem implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String streetAddress;
    private String cityAddress;

    public ContactItem(String name, String phone, String email, String street, String city) {
        setName(name);
        setPhoneNumber(phone);
        setEmailAddress(email);
        setStreetAddress(street);
        setCityAddress(city);
    }

    public ContactItem() {
        setName("");
        setPhoneNumber("");
        setEmailAddress("");
        setStreetAddress("");
        setCityAddress("");
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String newPhone) {
        this.phoneNumber = newPhone;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String newEmail) {
        this.emailAddress = newEmail;
    }

    public String getStreetAddress() {
        return this.streetAddress;
    }

    public void setStreetAddress(String newStreet) {
        this.streetAddress = newStreet;
    }

    public String getCityAddress() {
        return this.cityAddress;
    }

    public void setCityAddress(String cityAddress) {
        this.cityAddress = cityAddress;
    }
}
