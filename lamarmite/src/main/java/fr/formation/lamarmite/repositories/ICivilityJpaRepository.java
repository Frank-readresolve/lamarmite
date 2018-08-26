package fr.formation.lamarmite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.lamarmite.entities.Civility;

public interface ICivilityJpaRepository extends JpaRepository<Civility, Long> {

    public Civility findByCode(String code);
}
