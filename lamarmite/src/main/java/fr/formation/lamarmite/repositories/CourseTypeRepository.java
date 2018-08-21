package fr.formation.lamarmite.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.CourseTypeDTO;

@Repository
public class CourseTypeRepository extends BaseRepository
	implements ICourseTypeRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseTypeDTO> findAllAsDTO(AppLanguage lang) {
	StringBuilder queryBuilder = new StringBuilder(
		"select new fr.formation.lamarmite.dto.CourseTypeDTO(c.id, c.");
	String nameCol = "frenchName";
	if (lang.isEnglish()) {
	    nameCol = "englishName";
	}
	queryBuilder.append(nameCol);
	queryBuilder.append(") from CourseType c order by c.code");
	Query query = em.createQuery(queryBuilder.toString());
	return query.getResultList();
    }
}
