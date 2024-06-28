package com.gabriel.backend_challenge.entity.enuns;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.fail;

class RoleEnumTest {

    @Test
    @DisplayName("Test fromString method with a valid role")
    void testFromStringWithValidRole() {
        String input = "Admin";
        RoleEnum expected = RoleEnum.ADMIN;
        RoleEnum actual = RoleEnum.fromString(input);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test fromString method with a valid role USER")
    void testFromStringWithValidRoleExternal() {
        String input = "External";
        RoleEnum expected = RoleEnum.EXTERNAL;
        RoleEnum actual = RoleEnum.fromString(input);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test fromString method with a valid role MEMBER")
    void testFromStringWithValidRoleMember() {
        String input = "Member";
        RoleEnum expected = RoleEnum.MEMBER;
        RoleEnum actual = RoleEnum.fromString(input);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test fromString method with an invalid role")
    void testFromStringWithInvalidRole() {
        String input = "InvalidRole";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> RoleEnum.fromString(input));
        String expectedMessage = "Unknown enum value: " + input;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        try {
            RoleEnum.fromString(input);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}