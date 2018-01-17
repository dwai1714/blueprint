package com.corvesta.keyspring.blueprint.dao.postgres;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.corvesta.keyspring.blueprint.model.postgres.Item;

// This is what should be used when working within Postgres. You can write your queries if required.

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	List<Item> findByDescriptionIgnoreCaseContaining(String description);


// I will write a actual query for demo
	
//	@Query("select i from com.msa.ims.emdm.items.entities.Item i where (i.upc = :upc or i.shortDescription = :shortDescription) and i.status = :status and i.itemSkey <> :itemSkey")
//	List<Item> findByUpcOrShortDescriptionAndStatusWithoutSpecifiedItemSKey(@Param("upc") String upc, 
//			@Param("shortDescription") String shortDescription, 
//			@Param("status") String status, @Param("itemSkey") int itemSkey);

}
