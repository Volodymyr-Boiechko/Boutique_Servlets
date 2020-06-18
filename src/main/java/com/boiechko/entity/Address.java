package com.boiechko.entity;

public class Address {

    private int idAddress;
    private String country;
    private String city;
    private String street;
    private String postCode;

    public Address(int idAddress, String country, String city, String street, String postCode) {
        this.idAddress = idAddress;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    public Address(String country, String city, String street, String postCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    public int getIdAddress() { return idAddress; }

    public void setIdAddress(int idAddress) { this.idAddress = idAddress; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getPostCode() { return postCode; }

    public void setPostCode(String postCode) { this.postCode = postCode; }
}