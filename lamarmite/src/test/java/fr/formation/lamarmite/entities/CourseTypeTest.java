package fr.formation.lamarmite.entities;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseTypeTest {

    @Test
    public void shouldConstruct() {
	CourseType expected = new CourseType();
	assertNotNull(expected);
    }

    @Test
    public void shouldBeEqual() {
	CourseType expected = new CourseType();
	expected.setCode("A");
	CourseType actual = new CourseType();
	actual.setCode("A");
	assertEquals(expected, actual);
    }

    @Test
    public void shouldNotBeEqual() {
	CourseType expected = new CourseType();
	expected.setCode("A");
	CourseType actual = new CourseType();
	actual.setCode("B");
	assertNotEquals(expected, actual);
    }
}
