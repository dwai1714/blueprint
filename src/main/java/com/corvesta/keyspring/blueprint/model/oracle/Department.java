package com.corvesta.keyspring.blueprint.model.oracle;

import java.io.Serializable;

import lombok.Data;

// This is the class that will be created for Healthedge. This may not be a table but a view or just a DTO 
@Data
public class Department implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private Long departmentId;
	private String name;

}