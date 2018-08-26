package fr.formation.lamarmite.repositories;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CivilityDTO;

public interface ICivilityRepository {

    public List<CivilityDTO> findAllAsDTO(AppLanguage lang);
}
