package com.boiechko.entity;

import com.boiechko.enums.PersonType;

import java.sql.Date;
import java.util.Objects;

public class Person {

    private int idPerson;
    private String username;
    private String password;
    private String firstName;
    private String surname;
    private String lastName;
    private Date birthDate;
    private String email;
    private String phoneNumber;
    private PersonType personType;
    private String activationCode;

    public Person() {
    }

    public Person
            (int idPerson, String username, String password, String firstName, String surname,
             String lastName, Date birthDate, String email, String phoneNumber, String activationCode) {
        this.idPerson = idPerson;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.activationCode = activationCode;
    }

    public Person(String username, String password, Date birthDate, String email) {
        this.username = username;
        this.password = password;
        this.birthDate = birthDate;
        this.email = email;
    }

    public Person(String firstName, String surname, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getIdPerson() { return idPerson; }

    public void setIdPerson(int idPerson) { this.idPerson = idPerson; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public Date getBirthDate() { return birthDate; }

    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }


    public PersonType getPersonType() { return personType; }

    public void setPersonType(String personType) { this.personType = PersonType.valueOf(personType); }

    public String getActivationCode() { return activationCode; }

    public void setActivationCode(String activationCode) { this.activationCode = activationCode; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return idPerson == person.idPerson &&
                username.equals(person.username) &&
                password.equals(person.password) &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(lastName, person.lastName) &&
                birthDate.equals(person.birthDate) &&
                email.equals(person.email) &&
                Objects.equals(phoneNumber, person.phoneNumber) &&
                personType == person.personType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPerson, username, password, firstName, surname, lastName, birthDate, email, phoneNumber, personType);
    }

    @Override
    public String toString() {
        return "Person{" +
                "idPerson=" + idPerson +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", personType=" + personType +
                '}';
    }
}