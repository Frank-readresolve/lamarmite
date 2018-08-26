package fr.formation.lamarmite;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.formation.lamarmite.entities.User;

public class Principal implements UserDetails {

    private static final long serialVersionUID = 9034216980615315120L;

    private User user;

    public Principal(User user) {
	this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	String roleName = user.getRole().name();
	GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
	Collection<GrantedAuthority> authorities = new ArrayList<>(1);
	authorities.add(authority);
	return authorities;
    }

    @Override
    public String getPassword() {
	return user.getPassword();
    }

    @Override
    public String getUsername() {
	return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
	return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
	return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
	return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
	return user.isEnabled();
    }

    public User getUser() {
	return user;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (!(obj instanceof Principal)) {
	    return false;
	}
	Principal other = (Principal) obj;
	return getUsername().equals(other.getUsername());
    }

    @Override
    public int hashCode() {
	return Objects.hash(getUsername());
    }
}
