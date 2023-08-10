package com.schoolmodel;



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
@Table(schema="school")
@NamedQueries({ @NamedQuery(name = SchoolModel.FIND_ALL, query = "SELECT t FROM SchoolModel t order by t.id") })
@XmlRootElement

public class SchoolModel {
	
	
	
	public static final String FIND_ALL = "SchoolModel.findAll";
	@Id
	@GeneratedValue
	private String id;
	@Column
	private String name;
	@Column
	private String className;
	@Column
	private String marks;
	@Transient
	private boolean edit = false;
	
	
	public SchoolModel(String name, String className, String marks) {
		
		this.name = name;
		this.className = className;
		this.marks = marks;
	}
	public SchoolModel() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMarks() {
		return marks;
	}
	public void setMarks(String marks) {
		this.marks = marks;
	}
	
	
	
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	@Override
	public String toString() {
		return "School [id=" + id + ", name=" + name + ", className=" + className + ", marks=" + marks+
				", edit = "+ edit + "]";
	}
	
	
	
	
	
	
	
	

}
