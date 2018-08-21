package fr.formation.lamarmite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.lamarmite.entities.Menu;

public interface IMenuJpaRepository extends JpaRepository<Menu, Long> {
    //
}
