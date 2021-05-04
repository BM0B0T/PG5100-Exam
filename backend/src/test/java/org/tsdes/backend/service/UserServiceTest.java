package org.tsdes.backend.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.tsdes.backend.StubApplication;
import org.tsdes.backend.entity.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = StubApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest extends ServiceTestBase {

    @Autowired
    private UserService userService;

    @Test
    @DisplayName("Test create user")
    void testCreateUser() {
        assertTrue(userService.createUser("user@gmail.com", "password", "test", "test"));
        User user = userService.getUser("user@gmail.com");
        assertEquals("user@gmail.com", user.getUsername());
        assertEquals("test", user.getFirstName());
        assertEquals("test", user.getLastName());
        assertNotNull(user.getPassword());
    }

    @Test
    @DisplayName("Testing that you cant create more then one user on same email")
    void TestingMultipleUsers() {
        assertTrue(userService.createUser("user@gmail.com", "password", "test", "test"));
        assertFalse(userService.createUser("user@gmail.com", "password1", "test1", "test2"));
    }

    @Test
    @DisplayName("password length")
    void passwordLength() {
        assertFalse(userService.createUser("user@gmail.com", "p", "test1", "test2"));
    }


}
