package com.cevatraining.jsf.todo.service.impl;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.cevatraining.jsf.todo.model.Todo;

@Stateful
public class TodoServiceImpl {

	@PersistenceContext(unitName = "applicationTodoPU", type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	private EntityManager emLocal;

	private EntityManager getEntityManager() {

		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("applicationTodoLocalPU");
			emLocal = factory.createEntityManager();
			return emLocal;
		} catch (Exception e) {
			System.out.println("Exception in getEntityManager() " + e);
			return null;
		}
	}

	public Todo createTodo(Todo todo) {
		if (todo != null) {
			em.persist(todo);
		}
		return todo;
	}

	public List<Todo> findAllTodos() {
		try {
			TypedQuery<Todo> typedQuery = em.createNamedQuery(Todo.FIND_ALL, Todo.class);
			return typedQuery.getResultList();
		} catch (Exception e) {
			System.out.println("Exception in findAllTodos()" + e);
			return null;
		}

	}

	public Todo editTodo(Todo todo) {
		try {
			if (todo != null) {
				em.merge(todo);
				em.flush();
				return todo;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Exception in editTodo()" + e);
			return null;
		}
	}

	public Todo deleteTodo(Todo todo) {
		try {
			if (todo != null) {
				em.remove(em.merge(todo));
				em.flush();
				return todo;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("Exception in deleteTodo()" + e);
			return null;
		}
	}
}