package com.cevatraining.jsf.todo.service;

import java.util.List;

import com.cevatraining.jsf.todo.model.Todo;

public interface TodoService {
	
	public Todo createTodo(Todo todo);

	public List<Todo> findAllTodos();

	public Todo editTodo(Todo todo);

	public Todo deleteTodo(Todo todo);
}