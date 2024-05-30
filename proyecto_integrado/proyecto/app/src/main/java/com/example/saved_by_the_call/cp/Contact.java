package com.example.saved_by_the_call.cp;

public class Contact {
    private long id;
    private String name;
    private String phone;
    private String img;

    public Contact(long id, String name, String phone, String img){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
