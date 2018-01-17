package com.corvesta.keyspring.blueprint.dao.oracle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corvesta.keyspring.blueprint.model.oracle.Department;


@Repository
public class DepartmentJdbcRepository {
	
	private JdbcTemplate jdbcTemplate;
	
    private DataSource oracleDataSource;
    
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	// This is to show how to use JDBC template for Health edge database. Dont change his part
	
    @Autowired
    @Qualifier("oracleDataSource")
    public void setDataSource(DataSource dataSource) {
        this.oracleDataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(this.oracleDataSource);
    }
	
	// Write your own logic starting from here


	@Transactional(readOnly = true)
	public List<Department> findAll() {
		return jdbcTemplate.query("select deptno, dname from dept", new DepartmentRowMapper());
	}

	@Transactional(readOnly = true)
	public Department findDepartmentByDepartmentId(Long departmentId) {
		Department department = new Department();
		// This is a bad code. We should do a query first to see if the data exist
		// rather than a try catch block
		try {
			logger.debug("ItemJdbcRepo calling dept with ID " + departmentId);

			department = jdbcTemplate.queryForObject("select deptno, dname from dept where deptno = ?",
					new Object[] { departmentId }, new DepartmentRowMapper());
			return department;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	class DepartmentRowMapper implements RowMapper<Department> {
		@Override
		public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
			Department department = new Department();
			department.setDepartmentId(rs.getLong("deptno"));
			department.setName(rs.getString("dname"));
			return department;
		}
	}


}
