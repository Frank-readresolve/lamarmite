package fr.formation.lamarmite.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
public class Civility implements Serializable {

    private static final long serialVersionUID = 1806950331274992220L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1, nullable = false, unique = true)
    private String code;

    @Column(length = 5, nullable = false, unique = true)
    private String abbreviationFr;

    @Column(length = 5, nullable = false, unique = true)
    private String abbreviationEn;

    public Civility() {
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

    public String getAbbreviationFr() {
	return abbreviationFr;
    }

    public void setAbbreviationFr(String abbr) {
	abbreviationFr = abbr;
    }

    public String getAbbreviationEn() {
	return abbreviationEn;
    }

    public void setAbbreviationEn(String abbr) {
	abbreviationEn = abbr;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Civility)) {
	    return false;
	}
	Civility other = (Civility) obj;
	return code.equals(other.code);
    }

    @Override
    public int hashCode() {
	return Objects.hash(code);
    }

    @Override
    public String toString() {
	return "{id=" + id + ", code=" + code + ", abbreviationFr="
		+ abbreviationFr + ", abbreviationEn=" + abbreviationEn + "}";
    }
}
