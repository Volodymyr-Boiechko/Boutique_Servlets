package com.boiechko.entity;

import com.boiechko.enums.PersonType;

import java.util.Objects;

public class Person {

    private int idPerson;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String email;
    private String phoneNumber;
    private int idAddress;
    private PersonType personType;

    public Person(){
    }

    public Person
            (int idPerson, String username, String password, String firstName, String lastName,
             String birthDate, String email, String phoneNumber, int idAddress)
    {
        this.idPerson = idPerson;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.idAddress = idAddress;
    }

    public Person(String username, String password, String birthDate, String email) {
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
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

    public String getBirthDate() { return birthDate; }

    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public int getIdAddress() { return idAddress; }

    public void setIdAddress(int idAddress) { this.idAddress = idAddress; }

    public PersonType getPersonType() { return personType; }

    public void setPersonType(PersonType personType) { this.personType = personType; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idPerson == person.idPerson &&
                idAddress == person.idAddress &&
                username.equals(person.username) &&
                password.equals(person.password) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                birthDate.equals(person.birthDate) &&
                email.equals(person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                personType == person.personType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson, username, password, firstName, lastName, birthDate, email, phoneNumber, idAddress, personType);
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", idAddress=" + idAddress +
                ", personType=" + personType +
                '}';
    }
}