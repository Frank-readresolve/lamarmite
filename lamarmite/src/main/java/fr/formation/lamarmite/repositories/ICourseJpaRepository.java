package fr.formation.lamarmite.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.formation.lamarmite.entities.Course;

public interface ICourseJpaRepository extends JpaRepository<Course, Long> {

    public boolean existsByCodeIgnoreCase(String code);

    public boolean existsByCodeIgnoreCaseAndIdNot(String code, Long id);
}
