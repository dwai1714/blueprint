package com.corvesta.keyspring.blueprint.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.corvesta.keyspring.blueprint.dao.oracle.DepartmentJdbcRepository;
import com.corvesta.keyspring.blueprint.model.oracle.Department;

@Service
@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
public class DepartmentServiceImpl  {

	@Autowired
	private DepartmentJdbcRepository departmentJdbcRepository; // Usage of JDBC Template
																

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
// has Permission
	public List<Department> getAll() {
		logger.debug("Getting All Departments  service is invoked");
		List<Department> products = departmentJdbcRepository.findAll();
		return products;
	}


	public Department findDepartmentByDepartmentId(Long departmentId) {
		logger.debug("Getting one Product with Id service is invoked");
		return departmentJdbcRepository.findDepartmentByDepartmentId(departmentId);
	}

}
