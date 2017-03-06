package com.barexas.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Length(min = 3)
    @Pattern(regexp="\\w+")
    private String login;
    @NotNull
    @Length(min = 5)
    private String password;
    @NotNull
    @Length(min = 5)
    private String fio;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PhoneInfo> book;

    public CustomUser(String login, String password, String fio) {
        this.login = login;
        this.password = password;
        this.fio = fio;
        this.book  = new HashSet<>();
    }

    public CustomUser() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Set<PhoneInfo> getBook() {
        return book;
    }

    public void setBook(Set<PhoneInfo> book) {
        this.book = book;
    }

    public void addInfo(PhoneInfo info) {
        this.book.add(info);
    }

    public void deleteInfo(PhoneInfo info) {
        this.book.remove(info);
    }
}
