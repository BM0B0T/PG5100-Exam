package org.tsdes.frontend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.tsdes.backend.entity.User;
import org.tsdes.backend.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Primarily adapted from https://github.com/arcuri82/testing_security_development_enterprise_systems/
 */
@Named
@RequestScoped
public class UserInfoController {
    @Autowired
    private UserService userService;

    public String getUserName() {
        String userName = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        User user = userService.getUser(userName);
        return user.getFirstName() + " " + user.getLastName();
    }
}
