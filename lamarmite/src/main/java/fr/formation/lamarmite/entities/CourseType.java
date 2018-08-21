package fr.formation.lamarmite.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class CourseType implements Serializable {

    private static final long serialVersionUID = -3416208634504083069L;

    public final static String STARTER_CODE = "A";

    public final static String MAIN_COURSE_CODE = "B";

    public final static String DESSERT_CODE = "C";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 11, nullable = false, unique = true)
    private String code;

    @Column(length = 50, nullable = false)
    private String frenchName;

    @Column(length = 50, nullable = false)
    private String englishName;

    public CourseType() {
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

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof CourseType)) {
	    return false;
	}
	CourseType other = (CourseType) obj;
	return code.equals(other.code);
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public String toString() {
	return "{id=" + id + ", code=" + code + ", frenchName=" + frenchName
		+ ", englishName=" + englishName + "}";
    }
}
