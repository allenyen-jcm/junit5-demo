package com.rga.junit5;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class Assertions {

    @Test
    void assertWithComparison() {
        List<String> expected = new LinkedList<String>();
        expected.add("element");
        List<String> actual = new LinkedList<>(expected);

        assertEquals(expected, actual);
        assertEquals(expected, actual, "Should be equal.");
        assertEquals(expected, actual, () -> "Should " + "be " + "equal.");

        assertNotSame(expected, actual, "Obviously not the same instance.");
    }

    @Test
    void groupedAssertions() {

        Address address = new Address();
        address.setFirstName("John");
        address.setLastName("Wu");

        assertEquals("Coco", address.getFirstName());
        assertEquals("Yen", address.getLastName());

    }

    @Test
    void groupedAssertionsWithAssertAll() {

        Address address = new Address();
        address.setFirstName("John");
        address.setLastName("Yen");

        assertAll("address", () -> assertEquals("Coco", address.getFirstName()), () -> assertEquals("Wu", address.getLastName()));
    }

    @Test
    @DisplayName("throws EmptyStackException when popped")
    void throwsExceptionWhenPopped() {
        Stack<Object> stack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }

    @Test
    @DisplayName("throws EmptyStackException when peeked")
    void throwsExceptionWhenPeeked() {
        Stack<Object> stack = new Stack<>();
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    public class Address {
        private String firstName;
        private String lastName;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }

}
