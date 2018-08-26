package fr.formation.lamarmite.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CivilityDTO;
import fr.formation.lamarmite.repositories.ICivilityRepository;

@Service
public class CivilityService implements ICivilityService {

    private final ICivilityRepository civilityRepository;

    @Autowired
    protected CivilityService(ICivilityRepository civilityRepository) {
	this.civilityRepository = civilityRepository;
    }

    @Override
    public List<CivilityDTO> findAllAsDTO(AppLanguage lang) {
	return civilityRepository.findAllAsDTO(lang);
    }
}
