package fr.formation.lamarmite.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Course implements Serializable {

    private static final long serialVersionUID = 6231643022338322473L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 4, nullable = false, unique = true)
    private String code;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CourseType type;

    @NotNull(message = "{error.commons.required}")
    @DecimalMin(value = "5.00", message = "{error.commons.price}")
    @DecimalMax(value = "10.00", message = "{error.commons.price}")
    @Column(nullable = false)
    private BigDecimal price;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 50, nullable = false)
    private String frenchName;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 50, nullable = false)
    private String englishName;

    @Column(length = 500)
    private String frenchDescription;

    @Column(length = 500)
    private String englishDescription;

    public Course() {
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

    public CourseType getType() {
	return type;
    }

    public void setType(CourseType type) {
	this.type = type;
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

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Course)) {
	    return false;
	}
	Course other = (Course) obj;
	return code.equals(other.code);
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public String toString() {
	return "{id=" + id + ", code=" + code + ", type=" + type + ", price="
		+ price + ", frenchName=" + frenchName + ", englishName="
		+ englishName + "}";
    }
}
