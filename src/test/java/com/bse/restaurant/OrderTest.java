package com.bse.restaurant;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

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
    public void GivenNumber1_WhenGetSelectedMenu_ThenShouldDisplayChicken() {
        String actual = order.getSelectedMenu(1);
        assertEquals("Poulet", actual);

    }

    @Test
    public void GivenNumber2_WhenGetSelectedMenu_ThenShouldDisplayBeef() {
        String actual = order.getSelectedMenu(2);
        assertEquals("Boeuf", actual);

    }

    @Test
    public void GivenNumber3_WhenGetSelectedMenu_ThenShouldDisplayVegetarian() {
        String actualResult = order.getSelectedMenu(3);
        assertEquals("Végétarien", actualResult);
    }

    @Test
    public void GivenVegetarianInStandardInput_WhenMenuIsRun_ThenDisplayCorrectProcess() {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        order = new Order();
        order.runMenu();
        String output = outContent.toString().replace("\r\n", "\n");
        assertEquals(output.endsWith("Vous avez choisi comme menu : Végétarien\n"), true);
        assertEquals(output.length() > "Vous avez choisi comme menu : Végétarien\n".length(), true);
    }

    @Test
    public void GivenNumber1_WhenGetSelectedSide_ThenShouldDisplayRice() {
        String actualResult = order.getSelectedSide(1);
        assertEquals("Riz", actualResult);
    }

    @Test
    public void GivenNumber2_WhenGetSelectedSide_ThenShouldDisplayChips() {
        String actualResult = order.getSelectedSide(2);
        assertEquals("Frites", actualResult);
    }

    @Test
    public void GivenNumber3_WhenGetSelectedSide_ThenShouldDisplayVegetables() {
        String actualResult = order.getSelectedSide(3);
        assertEquals("Légumes frais", actualResult);
    }

    @Test
    public void GivenNumber0_WhenGetSelectedSide_ThenShouldDisplayNoSide() {
        String actualResult = order.getSelectedSide(0);
        assertEquals("Rien", actualResult);
    }

    @Test
    public void GivenNumber1_WhenGetSelectedDrink_ThenShouldDisplayStillWater() {
        String actualResult = order.getSelectedDrink(1);
        assertEquals("Eau plate", actualResult);
    }

    @Test
    public void GivenNumber2_WhenGetSelectedDrink_ThenShouldDisplaySparklingWater() {
        String actualResult = order.getSelectedDrink(2);
        assertEquals("Eau gazeuse", actualResult);
    }

    @Test
    public void GivenNumber3_WhenGetSelectedDrink_ThenShouldDisplaySoda() {
        String actualResult = order.getSelectedDrink(3);
        assertEquals("Soda", actualResult);
    }

    @Test
    public void GivenNumber0_WhenGetSelectedDrink_ThenShouldDisplayNoDrink() {
        String actualResult = order.getSelectedSide(0);
        assertEquals("Rien", actualResult);
    }


}
