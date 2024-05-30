package com.example.saved_by_the_call.cp;

public class Contact {
    private long id;
    private String name;
    private String phone;
    private String picture;

    public Contact(long id, String name, String phone, String picture){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
