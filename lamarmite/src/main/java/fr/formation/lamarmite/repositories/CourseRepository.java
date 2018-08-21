package fr.formation.lamarmite.repositories;

import java.util.*;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.*;
import fr.formation.lamarmite.entities.*;

@Repository
public class CourseRepository extends BaseRepository
	implements ICourseRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseDTO> findAllAsDTO(AppLanguage lang) {
	StringBuilder queryBuilder = new StringBuilder(
		"select new fr.formation.lamarmite.dto.CourseDTO(c.id, c.code, c.price, c.");
	String nameCol = "frenchName as courseName, t.";
	String typeNameCol = "frenchName";
	if (lang.isEnglish()) {
	    nameCol = "englishName as courseName, t.";
	    typeNameCol = "englishName";
	}
	queryBuilder.append(nameCol);
	queryBuilder.append(typeNameCol);
	queryBuilder.append(") from Course c join c.type t");
	queryBuilder.append(" order by t.code, courseName");
	Query query = em.createQuery(queryBuilder.toString());
	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CourseItemDTO> findAllAsDTO(AppLanguage lang, String typeCode) {
	StringBuilder queryBuilder = new StringBuilder(
		"select new fr.formation.lamarmite.dto.CourseItemDTO(c.id, c.");
	String nameCol = "frenchName";
	if (lang.isEnglish()) {
	    nameCol = "englishName";
	}
	queryBuilder.append(nameCol);
	queryBuilder.append(" as courseName) from Course c join c.type t");
	queryBuilder.append(" where t.code = :typeCode order by courseName");
	Query query = em.createQuery(queryBuilder.toString());
	query.setParameter("typeCode", typeCode);
	return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public String computeMenuCode(Menu menu) {
	List<Long> ids = new ArrayList<>(3);
	Course starter = menu.getStarter();
	if (!Long.valueOf(0L).equals(starter.getId())) {
	    ids.add(starter.getId());
	}
	Course mainCourse = menu.getMainCourse();
	if (!Long.valueOf(0L).equals(mainCourse.getId())) {
	    ids.add(mainCourse.getId());
	}
	Course dessert = menu.getDessert();
	if (!Long.valueOf(0L).equals(dessert.getId())) {
	    ids.add(dessert.getId());
	}
	Query query = em
		.createQuery("select code from Course where id in :ids");
	query.setParameter("ids", ids);
	List<String> codes = query.getResultList();
	return String.join(Menu.CODE_DELIMITER, codes);
    }
}
