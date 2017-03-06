package com.barexas.entities;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class PhoneInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Length(min = 4)
    private String lastName;
    @NotNull
    @Length(min = 4)
    private String firstName;
    @NotNull
    @Length(min = 4)
    private String middleName;
    @NotNull
    @Pattern(regexp = "\\+380\\(\\d{2}\\)\\d{7}")
    private String mobileNumb;
    @Pattern(regexp = "\\+380\\(\\d{2}\\)\\d{7}")
    private String homeNumb;
    private String address;
    @Email
    private String email;

    public PhoneInfo() {
    }

    public PhoneInfo(String lastName, String firstName, String middleName, String mobileNumb, String homeNumb, String address, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.mobileNumb = mobileNumb;
        this.homeNumb = homeNumb;
        this.address = address;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getMobileNumb() {
        return mobileNumb;
    }

    public void setMobileNumb(String mobileNumb) {
        this.mobileNumb = mobileNumb;
    }

    public String getHomeNumb() {
        return homeNumb;
    }

    public void setHomeNumb(String homeNumb) {
        this.homeNumb = homeNumb;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
