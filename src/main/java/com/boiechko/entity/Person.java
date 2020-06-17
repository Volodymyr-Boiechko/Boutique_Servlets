package com.boiechko.entity;

import com.boiechko.enums.PersonType;

public class Person {

    private int idPerson;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String postCode;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private PersonType personType;

    public Person(){
    }

    public Person
            (int idPerson, String username, String password, String firstName, String lastName,
             String country, String city, String street, String postCode, String birthDate, String email,
             String phoneNumber, PersonType personType)
    {
        this.idPerson = idPerson;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personType = personType;
    }

    public Person
            (String username, String password, String firstName, String lastName,
             String country, String city, String street, String postCode, String birthDate, String email,
             String phoneNumber, PersonType personType)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.street = street;
        this.postCode = postCode;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personType = personType;
    }

    public Person
            (int idPerson, String username, String password, String firstName, String lastName,
             String birthDate, String email, String phoneNumber, PersonType personType)
    {
        this.idPerson = idPerson;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personType = personType;
    }

    public Person
            (String username, String password, String firstName, String lastName,
             String birthDate, String email, String phoneNumber, PersonType personType)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.personType = personType;
    }

    public int getIdPerson() { return idPerson; }

    public void setIdPerson(int idPerson) { this.idPerson = idPerson; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCountry() { return country; }

    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getStreet() { return street; }

    public void setStreet(String street) { this.street = street; }

    public String getPostCode() { return postCode; }

    public void setPostCode(String postCode) { this.postCode = postCode; }

    public String getBirthDate() { return birthDate; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public PersonType getPersonType() { return personType; }

    public void setPersonType(PersonType personType) { this.personType = personType; }
}