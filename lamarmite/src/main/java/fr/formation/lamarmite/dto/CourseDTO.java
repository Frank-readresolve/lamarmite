package fr.formation.lamarmite.dto;

import java.math.BigDecimal;

public class CourseDTO {

    private Long id;

    private String code;

    private String typeName;

    private String name;

    private BigDecimal price;

    public CourseDTO() {
	//
    }

    public CourseDTO(Long id, String code, BigDecimal price, String name,
	    String typeName) {
	setId(id);
	setCode(code);
	setPrice(price);
	setName(name);
	setTypeName(typeName);
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

    public String getTypeName() {
	return typeName;
    }

    public void setTypeName(String typeName) {
	this.typeName = typeName;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public BigDecimal getPrice() {
	return price;
    }

    public void setPrice(BigDecimal price) {
	this.price = price;
    }
}
