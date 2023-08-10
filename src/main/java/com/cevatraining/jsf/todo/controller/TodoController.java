package com.cevatraining.jsf.todo.controller;

import java.util.ArrayList;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UICommand;
import javax.faces.component.UIForm;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

import com.cevatraining.jsf.todo.model.Todo;
import com.cevatraining.jsf.todo.service.impl.TodoServiceImpl;

@ManagedBean
@ViewScoped
public class TodoController {

	// confirmation dialog variables
	private String message = "";
	private boolean editDisabled = true;

	// domain model related variables
	private List<Todo> todos;
	private Todo todo;
	private Todo todoEdit;
	private Todo todoNew;

	// JavaServerFaces related variables
	private UIForm form;
	private UIForm tableForm;
	private UICommand addCommand;

	@Inject
	private TodoServiceImpl todoService;

	public TodoController() {
		todos = new ArrayList<Todo>();
	}

	@PostConstruct
	public void init() {
		todos = todoService.findAllTodos();
	}

	public String addNew() {
		todoNew = new Todo("", "", 3);
		form.setRendered(true);
		addCommand.setRendered(false);
		return null;
	}

	public String save() {
		todoService.createTodo(todoNew);
		todos = todoService.findAllTodos();
		setGlobalMessage("add");
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public String cancel() {
		todoNew = null;
		form.setRendered(false);
		addCommand.setRendered(true);
		return null;
	}

	public String delete() {
		todoService.deleteTodo(todo);
		todos = todoService.findAllTodos();
		setGlobalMessage("delete");
		return null;
	}

	public String edit() {
		setEditDisabled(true);
		todo.setEdit(true);
		todoEdit = new Todo();
		todoEdit.setId(todo.getId());
		todoEdit.setPriority(todo.getPriority());
		todoEdit.setTitle(todo.getTitle());
		todoEdit.setEdit(false);
		todoEdit.setDescription(todo.getDescription());
		System.out.println(todo);
		return null;
	}

	public String editSave() {
		setEditDisabled(false);
		todo.setEdit(false);
		todoService.editTodo(todo);
		todos = todoService.findAllTodos();
		setGlobalMessage("edit");
		return null;
	}

	public String editReset() {
		setEditDisabled(false);
		todo.setEdit(false);
		todo.setId(todoEdit.getId());
		todo.setPriority(todoEdit.getPriority());
		todo.setTitle(todoEdit.getTitle());
		todo.setDescription(todoEdit.getDescription());
		return null;
	}

	public void displayTable(ActionEvent event) {
		if (event.getComponent().getId().equalsIgnoreCase("hide")) {
			tableForm.setRendered(false);
		} else {
			tableForm.setRendered(true);
		}
	}

	public List<SelectItem> getPriorities() {
		List<SelectItem> list = new ArrayList<SelectItem>();
		list.add(new SelectItem(1, "High"));
		list.add(new SelectItem(2, "Medium"));
		list.add(new SelectItem(3, "Low"));
		return list;
	}

	public void setGlobalMessage(String type) {

		FacesContext context = FacesContext.getCurrentInstance();
		if (type.equals("add")) {
			context.addMessage(null, new FacesMessage("Added the new record successfully"));
		} else if (type.equals("edit")) {
			context.addMessage(null, new FacesMessage("Edited the selected record successfully"));
		} else if (type.equals("delete")) {
			context.addMessage(null, new FacesMessage("Deleted the selected record successfully"));
		}
	}

	public void confirm(ActionEvent actionEvent) {
		addMessage("You just clicked Button", "Record has been deleted.");
	}

	public void addMessage(String summary, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isEditDisabled() {
		return editDisabled;
	}

	public void setEditDisabled(boolean editDisabled) {
		this.editDisabled = editDisabled;
	}

	public List<Todo> getTodos() {
		return todos;
	}

	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public Todo getTodoNew() {
		return todoNew;
	}

	public void setTodoNew(Todo todoNew) {
		this.todoNew = todoNew;
	}

	public UIForm getForm() {
		return form;
	}

	public void setForm(UIForm form) {
		this.form = form;
	}

	public UICommand getAddCommand() {
		return addCommand;
	}

	public void setAddCommand(UICommand addCommand) {
		this.addCommand = addCommand;
	}

	public UIForm getTableForm() {
		return tableForm;
	}

	public void setTableForm(UIForm tableForm) {
		this.tableForm = tableForm;
	}

}