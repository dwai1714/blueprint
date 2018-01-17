package com.corvesta.keyspring.blueprint.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.corvesta.keyspring.blueprint.model.oracle.Department;
import com.corvesta.keyspring.blueprint.service.DepartmentServiceImpl;

@RestController
@RequestMapping(value = "/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentServiceImpl departmentService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(method = RequestMethod.GET)
	public List<Department> list() {
		logger.debug("Invoking getAll Call in Controller");
		return departmentService.getAll();
	}


}
