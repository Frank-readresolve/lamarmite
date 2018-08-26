package fr.formation.lamarmite.services;

import fr.formation.lamarmite.entities.User;

public interface IUserService {

    public void save(User user);

    public boolean validateEmail(User user);

    public User findById(Long id);
}
