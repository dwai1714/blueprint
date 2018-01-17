package com.corvesta.keyspring.blueprint.dao.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.corvesta.keyspring.blueprint.model.postgres.Item;

// Use JDBC template in Postgres in dire situation. Stick to JPA and if required hibernate queries

@Repository
public class ItemJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	


	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	// The arbitrary table name and column names is intentional as this happens all the time in Real life. This is to be avoided

	@Transactional(readOnly = true)
	public List<Item> findAll() {
		return jdbcTemplate.query("select product_id, description from T_ITEM", new ItemRowMapper());
	}

	@Transactional(readOnly = true)
	public Item findItemByProductId(Long productId) {
		Item item = new Item();
		// This is a bad code. We should do a query first to see if the data exist
		// rather than a try catch block
		try {
			logger.debug("ItemJdbcRepo calling item with ID " + productId);

			item = jdbcTemplate.queryForObject("select product_id, description from T_ITEM where product_id = ?",
					new Object[] { productId }, new ItemRowMapper());
			return item;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	class ItemRowMapper implements RowMapper<Item> {
		@Override
		public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
			Item Item = new Item();
			Item.setItemtId(rs.getLong("product_id"));
			Item.setDescription(rs.getString("description"));
			return Item;
		}
	}

}
