package com.example.onlinedirectoryprovider.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserData {

    @SerializedName("type")
    @Expose
    public String type;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("email")
    @Expose
    public String email;

    @SerializedName("mobile")
    @Expose
    public String mobile;

    @SerializedName("location")
    @Expose
    public String location;

    @SerializedName("zipcode")
    @Expose
    public String zipcode;

    @SerializedName("device_type")
    @Expose
    public String device_type;

    @SerializedName("device_token")
    @Expose
    public String device_token;

    public UserData(String type, String name, String email, String mobile, String location, String zipcode, String device_type, String device_token) {
        this.type = type;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.location = location;
        this.zipcode = zipcode;
        this.device_type = device_type;
        this.device_token = device_token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}