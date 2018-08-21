package fr.formation.lamarmite.dto;

import java.math.BigDecimal;

public class MenuDTO {

    private Long id;

    private String code;

    private String name;

    private BigDecimal price;

    public MenuDTO() {
	//
    }

    public MenuDTO(Long id, String code, BigDecimal price, String name) {
	setId(id);
	setCode(code);
	setPrice(price);
	setName(name);
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
