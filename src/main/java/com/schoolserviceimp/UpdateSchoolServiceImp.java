




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
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.deltaspike.jpa.api.transaction.Transactional;
import org.primefaces.model.SortOrder;

import com.schoolmodel.UpdateSchoolModel;

@Stateful
public class UpdateSchoolServiceImp {

    

	@PersistenceContext(unitName = "updateschoolDataModel", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

   

    private EntityManager getEntityManager() {
        try {
            EntityManagerFactory factory = Persistence.createEntityManagerFactory("updateschoolDataModelLocalPU");
            em = factory.createEntityManager();
            return em;
        } catch (Exception e) {
            System.out.println("Exception in getEntityManager() " + e);
            return null;
        }
    }   
    
    

    public UpdateSchoolModel createSchool(UpdateSchoolModel school) {
        em.persist(school);
        em.flush();
        return school;
    }

    public List<UpdateSchoolModel> findAll() {
        try {
            TypedQuery<UpdateSchoolModel> typedQuery = em.createNamedQuery(UpdateSchoolModel.FIND_ALL, UpdateSchoolModel.class);
            return typedQuery.getResultList();
        } catch (Exception e) {
            System.out.println("Exception in findAll() " + e);
            return null;
        }
    }
    
    public void deleteSchool(UpdateSchoolModel school) {
        try {
            em.remove(school);
            em.flush();
        } catch (Exception e) {
            System.out.println("Exception in deleteSchool() " + e);
        }
    }
    
    public UpdateSchoolModel updateSchool(UpdateSchoolModel school) {
        try {
            UpdateSchoolModel mergedSchool = em.merge(school);
            em.flush();
            return mergedSchool;
        } catch (Exception e) {
            System.out.println("Exception in updateSchool() " + e);
            return null;
        }
    }
    
    public UpdateSchoolModel findSchoolById(int id) {
        try {
            return em.find(UpdateSchoolModel.class, id);
        } catch (Exception e) {
            System.out.println("Exception in findSchoolById() " + e);
            return null;
        }
    }
    
    public UpdateSchoolModel editSchool(UpdateSchoolModel school) {
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
        Query query = em.createQuery("SELECT COUNT(e.id) FROM UpdateSchoolModel e");
        return ((Number) query.getSingleResult()).intValue();
    }

    public List<UpdateSchoolModel> getSchoolList(int start, int size, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<UpdateSchoolModel> q = cb.createQuery(UpdateSchoolModel.class);
    Root<UpdateSchoolModel> r = q.from(UpdateSchoolModel.class);
    CriteriaQuery<UpdateSchoolModel> select = q.select(r);

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
            Expression<String> expr1 =  r.get(field).as(String.class);
              
            
            
            
            Predicate p = cb.like(cb.lower(expr1),
                    "%" + value.toString().toLowerCase() + "%");
            predicates.add(p);
            
        }
        if (predicates.size() > 0) {
            q.where(cb.and(predicates.toArray
                    (new Predicate[predicates.size()])));
    }}
    
    
    

    TypedQuery<UpdateSchoolModel> query = em.createQuery(select);
    query.setFirstResult(start);
    query.setMaxResults(size);
    List<UpdateSchoolModel> list = query.getResultList();
    return list;

    }
    
    
    @Transactional
    public void deleteSchoolsByIds(List<String> ids) {
        if (ids != null && !ids.isEmpty()) {
            try {
                for (String id : ids) {
                    UpdateSchoolModel school = em.find(UpdateSchoolModel.class, id);
                    if (school != null) {
                        em.remove(school);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    
    
    public int getFilteredRowCount(Map<String, Object> filters) {
        
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<UpdateSchoolModel> r = criteriaQuery.from(UpdateSchoolModel.class);
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

