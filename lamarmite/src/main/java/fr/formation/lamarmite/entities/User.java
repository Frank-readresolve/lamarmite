package fr.formation.lamarmite.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1800900843909976847L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Civility civility;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 50, nullable = false)
    private String lastname;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 50, nullable = false)
    private String firstname;

    @NotNull(message = "{error.commons.required}")
    @Email(message = "{error.commons.email}")
    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @NotNull(message = "{error.commons.required}")
    @Column(length = 100, nullable = false)
    private String password;

    @NotNull(message = "{error.commons.required}")
    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role role = Role.ROLE_USER;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    public boolean accountNonExpired = true;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    public boolean accountNonLocked = true;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    public boolean credentialsNonExpired = true;

    @Convert(converter = BooleanConverter.class)
    @Column(length = 1, nullable = false)
    public boolean enabled = true;

    public User() {
	//
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Civility getCivility() {
	return civility;
    }

    public void setCivility(Civility civility) {
	this.civility = civility;
    }

    public String getLastname() {
	return lastname;
    }

    public void setLastname(String lastname) {
	this.lastname = lastname;
    }

    public String getFirstname() {
	return firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    public void setAccountNonExpired(boolean flag) {
	accountNonExpired = flag;
    }

    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    public void setAccountNonLocked(boolean flag) {
	accountNonLocked = flag;
    }

    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean flag) {
	credentialsNonExpired = flag;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean flag) {
	enabled = flag;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof User)) {
	    return false;
	}
	User other = (User) obj;
	return email.equals(other.email);
    }

    @Override
    public int hashCode() {
	return Objects.hash(email);
    }

    @Override
    public String toString() {
	return "{id=" + id + ", civility=" + civility + ", lastname=" + lastname
		+ ", firstname=" + firstname + ", email=" + email
		+ ", password=[SECRET], role=" + role + ", accountNonExpired="
		+ accountNonExpired + ", accountNonLocked=" + accountNonLocked
		+ ", credentialsNonExpired=" + credentialsNonExpired
		+ ", enabled=" + enabled + "}";
    }

    public static enum Role {
	ROLE_USER, ROLE_ADMIN;

	public String getName() {
	    return name();
	}
    }
}
