package fr.formation.lamarmite.dto;

public class CivilityDTO {

    private Long id;

    private String name;

    public CivilityDTO() {
	//
    }

    public CivilityDTO(Long id, String name) {
	setId(id);
	setName(name);
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String toString() {
	return "{id=" + id + ", name=" + name + "}";
    }
}
