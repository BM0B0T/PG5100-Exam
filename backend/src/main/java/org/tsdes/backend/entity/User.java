package org.tsdes.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @NotBlank
    @Email
    private String username;

    @NotBlank
    private String password;

    @Max(32)
    @Min(2)
    @NotBlank
    private String firstName;

    @Max(32)
    @Min(2)
    @NotBlank
    private String LastName;


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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String surname) {
        this.LastName = surname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }
}
