package com.example.dania.ecom.Classes;

public class AddWorkClass {





    public String name;
    public String email;
    public String phone;
    public String location;
    public String payment;
    public String type;
    public float rating;
    public int rateCounter;
    public String id;




    public AddWorkClass(){

    }

    public AddWorkClass(String name, String email, String phone, String location, String payment, String type, float rating, int rateCounter, String id) {



        this.name = name;
        this.email = email;
        this.phone = phone;
        this.location = location;
        this.payment = payment;
        this.type = type;
        this.rating = rating;
        this.rateCounter = rateCounter;
        this.id = id;
    }


    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getRateCounter() {
        return rateCounter;
    }

    public void setRateCounter(int rateCounter) {
        this.rateCounter = rateCounter;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}


}
