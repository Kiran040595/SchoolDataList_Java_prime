package com.cevatraining.jsf.todo.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(schema = "public.todo")
@NamedQueries({ @NamedQuery(name = Todo.FIND_ALL, query = "SELECT t FROM Todo t order by t.id") })
@XmlRootElement
public class Todo {
	@Id
	@GeneratedValue
	private int id;

	@Column
	private String title;

	@Column
	private String description;

	@Column
	private int priority;

	@Column
	private Calendar dueDate;

	@Transient
	private boolean edit = true;

	public static final String FIND_ALL = "TODO.findAll";

	public Todo(String title, String description, int priority) {
		this.title = title;
		this.description = description;
		this.priority = priority;
	}

	public Todo() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isEdit() {
		return edit;
	}

	public void setEdit(boolean edit) {
		this.edit = edit;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", title=" + title + ", description=" + description + ", priority=" + priority
				+ ", dueDate=" + dueDate + ", edit=" + edit + "]";
	}
}