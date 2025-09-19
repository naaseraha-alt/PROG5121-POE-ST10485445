
package com.example.PoeChatApp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

class AppTest {

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }
    

public class UserTest {
    @Test
    public void testValidUsername() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testInvalidUsername() {
        User user = new User("Kyle!!!!!!!", "Pass@123", "+27831234567");
        assertFalse(user.checkUserName());
    }

    @Test
    public void testValidPassword() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testInvalidPassword() {
        User user = new User("Yul_r", "Password", "+27831234567");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testValidCellNumber() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testInvalidCellNumber() {
        User user = new User("Yul_r", "Pass@123", "0896532");
        assertFalse(user.checkPasswordComplexity());
    }

    @Test
    public void testLoginSuccess() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.loginUser("Yul_r", "Pass@123"));
    }

    @Test
    public void testLoginFailure() {
        User user = new User("Yul_r", "Pass@123", "+27831234567");
        assertTrue(user.loginUser("Yul_r", "wrongPass"));
    }

}

    /**
     * Test of main method, of class App.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        App.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    }

