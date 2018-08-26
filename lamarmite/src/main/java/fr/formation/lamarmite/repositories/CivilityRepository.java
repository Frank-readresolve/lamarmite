package fr.formation.lamarmite.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CivilityDTO;

@Repository
public class CivilityRepository extends BaseRepository
	implements ICivilityRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<CivilityDTO> findAllAsDTO(AppLanguage lang) {
	StringBuilder queryBuilder = new StringBuilder(
		"select new fr.formation.lamarmite.dto.CivilityDTO(c.id, c.");
	String nameCol = "abbreviationFr";
	if (lang.isEnglish()) {
	    nameCol = "abbreviationEn";
	}
	queryBuilder.append(nameCol);
	queryBuilder.append(") from Civility c order by c.code");
	Query query = em.createQuery(queryBuilder.toString());
	return query.getResultList();
    }
}
