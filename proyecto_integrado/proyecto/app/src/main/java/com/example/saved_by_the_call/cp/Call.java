package com.example.saved_by_the_call.cp;

public class Call {

    private long id;
    private String name;
    private long contact;
    private String date;

    public Call(long id, String name, long contact, String date) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.date = date;
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

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
