package com.boiechko.entity;

import java.util.Objects;

public class Address {

    private int idAddress;
    private int idPerson;
    private String country;
    private String city;
    private String street;
    private String postCode;

    public Address(){
    }

    public Address(int idAddress, int idPerson, String country, String city, String street, String postCode) {
        this.idAddress = idAddress;
        this.idPerson = idPerson;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    public Address(int idPerson, String country, String city, String street, String postCode) {
        this.idPerson = idPerson;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
    }

    public int getIdAddress() { return idAddress; }

    public void setIdAddress(int idAddress) { this.idAddress = idAddress; }

    public int getIdPerson() { return idPerson; }

    public void setIdPerson(int idPerson) { this.idPerson = idPerson; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getPostCode() { return postCode; }

    public void setPostCode(String postCode) { this.postCode = postCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return idAddress == address.idAddress &&
                idPerson == address.idPerson &&
                country.equals(address.country) &&
                city.equals(address.city) &&
                street.equals(address.street) &&
                postCode.equals(address.postCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAddress, idPerson, country, city, street, postCode);
    }

    @Override
    public String toString() {
        return "Address{" +
                "idAddress=" + idAddress +
                ", idPerson=" + idPerson +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postCode='" + postCode + '\'' +
                '}';
    }
}