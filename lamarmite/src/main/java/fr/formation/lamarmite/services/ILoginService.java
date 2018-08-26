package fr.formation.lamarmite.services;

import org.springframework.security.core.userdetails.*;

public interface ILoginService extends UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username)
	    throws UsernameNotFoundException;
}
