package com.schoolserviceimp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Expression;

import org.primefaces.model.SortOrder;

import com.schoolmodel.SchoolModel;

@Stateful
public class SchoolServiceImp {

    

	@PersistenceContext(unitName = "schoolDataModel", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

   

	private Query query;

    private EntityManager getEntityManager() {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("schoolDataModelLocalPU");
            em = factory.createEntityManager();
            return em;
        } catch (Exception e) {
            System.out.println("Exception in getEntityManager() " + e);
            return null;
        }
    }
    
    
    
    
    
    

    public SchoolModel createSchool(SchoolModel school) {
        em.persist(school);
        em.flush();
        return school;
    }

    public List<SchoolModel> findAll() {
        try {
            TypedQuery<SchoolModel> typedQuery = em.createNamedQuery(SchoolModel.FIND_ALL, SchoolModel.class);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Exception in findAll() " + e);
            return null;
        }
    }
    
    public void deleteSchool(SchoolModel school) {
        try {
            SchoolModel mergedSchool = em.merge(school);
            em.remove(mergedSchool);
            em.flush();
        } catch (Exception e) {
            System.out.println("Exception in deleteSchool() " + e);
        }
    }
    
    public SchoolModel updateSchool(SchoolModel school) {
        try {
            SchoolModel mergedSchool = em.merge(school);
            em.flush();
            return mergedSchool;
        } catch (Exception e) {
            System.out.println("Exception in updateSchool() " + e);
            return null;
        }
    }
    
    public SchoolModel findSchoolById(int id) {
        try {
            return em.find(SchoolModel.class, id);
        } catch (Exception e) {
            System.out.println("Exception in findSchoolById() " + e);
            return null;
        }
    }
    
    public SchoolModel editSchool(SchoolModel school) {
		try {
			if (school != null) {
				em.merge(school);
				em.flush();
				return school;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Exception in editTodo()" + e);
			return null;
		}
	}
    
    public int getTotalSchoolList() {
        Query query = em.createQuery("SELECT COUNT(e.id) FROM SchoolModel e");
        return ((Number) query.getSingleResult()).intValue();
    }

    public List<SchoolModel> getSchoolList(int start, int size, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<SchoolModel> q = cb.createQuery(SchoolModel.class);
    Root<SchoolModel> r = q.from(SchoolModel.class);
    CriteriaQuery<SchoolModel> select = q.select(r);

    if (sortField != null) {
        q.orderBy(sortOrder == SortOrder.DESCENDING ? cb.asc(r.get(sortField)) : cb.desc(r.get(sortField)));
    }

    if (filters != null && filters.size()>0) {
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String field = entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                continue;
            }
            Expression<String> expr =  r.get(field).as(String.class);
            
            
            
            
            
            
            Predicate p = cb.like(cb.lower(expr),
                    "%" + value.toString().toLowerCase() + "%");
            predicates.add(p);
            
        }
        if (predicates.size() > 0) {
            q.where(cb.and(predicates.toArray
                    (new Predicate[predicates.size()])));
    }}
    
    
    

    TypedQuery<SchoolModel> query = em.createQuery(select);
    query.setFirstResult(start);
    query.setMaxResults(size);
    List<SchoolModel> list = query.getResultList();
    return list;

    }
    
    
    
    
         
    	
    	
//        Query query = em.createQuery("SELECT e FROM SchoolModel e");
//        query.setFirstResult(start);
//        query.setMaxResults(size);
//        List<SchoolModel> list = query.getResultList();
//        return list;
    
    
    public int getFilteredRowCount(Map<String, Object> filters) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<SchoolModel> r = criteriaQuery.from(SchoolModel.class);
        CriteriaQuery<Long> select = criteriaQuery.select(cb.count(r));

        if (filters != null && filters.size() > 0) {
            List<Predicate> predicates = new ArrayList<>();
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String field = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }

                Expression<String> expr =  r.get(field).as(String.class);
                Predicate p = cb.like(cb.lower(expr),
                        "%" + value.toString().toLowerCase() + "%");
                predicates.add(p);
            }
            if (predicates.size() > 0) {
                criteriaQuery.where(cb.and(predicates.toArray
                        (new Predicate[0])));
            }
        }
     


    
    Long count = em.createQuery(select).getSingleResult();
    return count.intValue();
    
}}
