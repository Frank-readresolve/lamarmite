package fr.formation.lamarmite.repositories;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import fr.formation.lamarmite.AppLanguage;
import fr.formation.lamarmite.dto.MenuDTO;

@Repository
public class MenuRepository extends BaseRepository implements IMenuRepository {

    @SuppressWarnings("unchecked")
    @Override
    public List<MenuDTO> findAllAsDTO(AppLanguage lang) {
	StringBuilder queryBuilder = new StringBuilder(
		"select new fr.formation.lamarmite.dto.MenuDTO(m.id, m.code, m.price, m.");
	String nameCol = "frenchName";
	if (lang.isEnglish()) {
	    nameCol = "englishName";
	}
	queryBuilder.append(nameCol);
	queryBuilder.append(" as menuName) from Menu m order by menuName");
	Query query = em.createQuery(queryBuilder.toString());
	return query.getResultList();
    }

    @Override
    public boolean validateCourseCombination(Long menuId, Long starterId,
	    Long mainCourseId, Long dessertId) {
	StringBuilder queryBuilder = new StringBuilder(
		"select count(m) from Menu m where 1=1");
	// Build where clause:
	if (null != menuId) {
	    queryBuilder.append(" and id != :menuId");
	}
	boolean starterSet = !Long.valueOf(0L).equals(starterId);
	if (starterSet) {
	    queryBuilder.append(" and m.starter.id = :starterId");
	} else {
	    queryBuilder.append(" and m.starter is null");
	}
	boolean mainCourseSet = !Long.valueOf(0L).equals(mainCourseId);
	if (mainCourseSet) {
	    queryBuilder.append(" and m.mainCourse.id = :mainCourseId");
	} else {
	    queryBuilder.append(" and m.mainCourse is null");
	}
	boolean dessertSet = !Long.valueOf(0L).equals(dessertId);
	if (dessertSet) {
	    queryBuilder.append(" and m.dessert.id = :dessertId");
	} else {
	    queryBuilder.append(" and m.dessert is null");
	}
	Query query = em.createQuery(queryBuilder.toString());
	// Set parameters:
	if (null != menuId) {
	    query.setParameter("menuId", menuId);
	}
	if (starterSet) {
	    query.setParameter("starterId", starterId);
	}
	if (mainCourseSet) {
	    query.setParameter("mainCourseId", mainCourseId);
	}
	if (dessertSet) {
	    query.setParameter("dessertId", dessertId);
	}
	return 0L == (long) query.getSingleResult();
    }
}
