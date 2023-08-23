



package com.schoolmodel;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(schema="updateschool")
@NamedQueries({ @NamedQuery(name = UpdateSchoolModel.FIND_ALL, query = "SELECT t FROM UpdateSchoolModel t order by t.id") })


public class UpdateSchoolModel {
	
	public static final String FIND_ALL = "UpdateSchoolModel.findAll";
	@Id
	@GeneratedValue
	private String id;
	@Column
	private String name;
	@Column
	private String className;
	@Column
	private String marks;
	@Column
	private Date joinDate;
	@Column
	private String status;
	@Column 
	private String district;
	
	@Column 
	private String city;
	
	
	@Transient
	private boolean edit = false;
	
	
	public UpdateSchoolModel(String name, String className, String marks ,Date joinDate,String status,String district,String city) {
		
		this.name = name;
		this.className = className;
		this.marks = marks;
		this.joinDate=joinDate;
		this.status=status;
		this.district=district;
		this.city = city;
		
		
	}
	public UpdateSchoolModel() {
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
	
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}	
	
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "UpdateSchoolModel [id=" + id + ", name=" + name + ", className=" + className + ", marks=" + marks
				+ ", joinDate=" + joinDate + ", status=" + status + ", district=" + district + ", city=" + city
				+ ", edit=" + edit + "]";
	}
}

