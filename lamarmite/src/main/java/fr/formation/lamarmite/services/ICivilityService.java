package fr.formation.lamarmite.services;

import java.util.List;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CivilityDTO;

public interface ICivilityService {

    public List<CivilityDTO> findAllAsDTO(AppLanguage lang);
}
