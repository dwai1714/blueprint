package com.corvesta.keyspring.blueprint;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.corvesta.keyspring.blueprint.dao.postgres.ItemRepository;
import com.corvesta.keyspring.blueprint.model.postgres.Item;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ItemRepositoryTest {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ItemRepository itemRepository;

	@Test
	public void whenFindByDesc() throws Exception {
		// given
		Item blackDog = new Item();
		blackDog.setDescription("Black Dog");
		entityManager.persist(blackDog);
		entityManager.flush();

		// when
		List<Item> found = itemRepository.findByDescriptionIgnoreCaseContaining("Black");
		logger.debug("Number of Items Returned " + found.size());

		// then
		assertThat(found.get(0).getDescription().toString().equals(blackDog.getDescription()));
	}

}
