package com.bse.restaurant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OrderTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Order order = new Order();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void GivenNothing_WhenDisplayMenus_ThenShouldDisplayText() {
        order.displayMenus();
        assertFalse(outContent.toString().isEmpty());
    }

    @Test
    public void GivenNumber2_WhenDisplaySelectedMenu_ThenShouldDisplayBeef() {
        String actual = order.displaySelectedMenu(2);
        assertEquals("Boeuf", actual);

    }
}
