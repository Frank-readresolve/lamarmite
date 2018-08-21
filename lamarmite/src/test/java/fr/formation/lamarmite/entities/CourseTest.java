package fr.formation.lamarmite.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseTest {

    @Test
    public void shouldConstruct() {
	Course expected = new Course();
	assertNotNull(expected);
    }

    @Test
    public void shouldBeEqual() {
	Course expected = new Course();
	expected.setCode("CD1");
	Course actual = new Course();
	actual.setCode("CD1");
	assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBeEqual() {
	Course expected = new Course();
	expected.setCode("CD1");
	Course actual = new Course();
	actual.setCode("CD2");
	assertNotEquals(expected, actual);
    }
}
