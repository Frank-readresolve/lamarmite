package fr.formation.lamarmite.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Menu implements Serializable {

    private static final long serialVersionUID = -9073958230143153598L;

    public static final String CODE_DELIMITER = "-";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 14, nullable = false, unique = true)
    private String code;

    @NotNull(message = "{error.commons.required}")
    @DecimalMin(value = "10.00", message = "{error.commons.price}")
    @DecimalMax(value = "30.00", message = "{error.commons.price}")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 50, nullable = false)
    private String frenchName;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 50, nullable = false)
    private String englishName;

    @NotNull(message = "{error.commons.required}")
    @Column(nullable = false)
    private LocalDate startDate;

    @NotNull(message = "{error.commons.required}")
    @Column(nullable = false)
    private LocalDate endDate;

    @ManyToOne
    private Course starter;

    @ManyToOne
    private Course mainCourse;

    @ManyToOne
    private Course dessert;

    @Column(length = 500)
    private String frenchDescription;

    @Column(length = 500)
    private String englishDescription;

    public Menu() {
	//
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }

    public String getFrenchName() {
	return frenchName;
    }

    public void setFrenchName(String frenchName) {
	this.frenchName = frenchName;
    }

    public String getEnglishName() {
	return englishName;
    }

    public void setEnglishName(String englishName) {
	this.englishName = englishName;
    }

    public LocalDate getStartDate() {
	return startDate;
    }

    public void setStartDate(LocalDate startDate) {
	this.startDate = startDate;
    }

    public LocalDate getEndDate() {
	return endDate;
    }

    public void setEndDate(LocalDate endDate) {
	this.endDate = endDate;
    }

    public void setFrenchDescription(String text) {
	frenchDescription = text;
    }

    public String getFrenchDescription() {
	return frenchDescription;
    }

    public void setEnglishDescription(String text) {
	englishDescription = text;
    }

    public String getEnglishDescription() {
	return englishDescription;
    }

    public void setStarter(Course starter) {
	this.starter = starter;
    }

    public Course getStarter() {
	return starter;
    }

    public void setMainCourse(Course mainCourse) {
	this.mainCourse = mainCourse;
    }

    public Course getMainCourse() {
	return mainCourse;
    }

    public void setDessert(Course dessert) {
	this.dessert = dessert;
    }

    public Course getDessert() {
	return dessert;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Menu)) {
	    return false;
	}
	Menu other = (Menu) obj;
	return code.equals(other.code);
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }
}
