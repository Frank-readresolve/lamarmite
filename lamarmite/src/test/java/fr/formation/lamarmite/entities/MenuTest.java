package fr.formation.lamarmite.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class MenuTest {

    @Test
    public void shouldConstruct() {
	Menu expected = new Menu();
	assertNotNull(expected);
    }

    @Test
    public void shouldBeEqual() {
	Menu expected = new Menu();
	expected.setCode("CD1-CD2");
	Menu actual = new Menu();
	actual.setCode("CD1-CD2");
	assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBeEqual() {
	Menu expected = new Menu();
	expected.setCode("CD1-CD2");
	Menu actual = new Menu();
	actual.setCode("CD1-CD2-CD3");
	assertNotEquals(expected, actual);
    }
}
