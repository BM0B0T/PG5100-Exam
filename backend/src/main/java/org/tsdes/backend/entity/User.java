package org.tsdes.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @NotBlank
    @Size(min = 1, max = 1024)
    @Email
    private String username;

    @NotBlank
    private String password;

    @Size(min = 1, max = 1024)
    @NotBlank
    private String firstName;

    @Size(min = 1, max = 1024)
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
