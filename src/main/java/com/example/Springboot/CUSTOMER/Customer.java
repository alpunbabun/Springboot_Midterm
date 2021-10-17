package com.example.Springboot.CUSTOMER;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Objects;@Entity

@Table(name = "customer")
public class Customer {
    private @Id @GeneratedValue long id;
    private @NotBlank String email;
    private @NotBlank String username;
    private @NotBlank String password;
    private @NotBlank boolean loggedIn;

    public Customer() {
    }

    public Customer(@NotBlank String email,
                    @NotBlank String username,
                     @NotBlank String password) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.loggedIn = false;
    }

    public Customer(@NotBlank String username,
                    @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    public long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer= (Customer) o;
        return
//              Objects.equals(email, customer.email) &&
                Objects.equals(username, customer.username) &&
                Objects.equals(password, customer.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, password,
                loggedIn);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", loggedIn=" + loggedIn +
                '}';
    }
}
