package com.corvesta.keyspring.blueprint.model.postgres;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.corvesta.keyspring.blueprint.utils.JsonbUserType;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.Serializable;

@Entity
@Table(name = "T_ITEM")
@TypeDef(name = JsonbUserType.JSONB_TYPE, typeClass = JsonbUserType.class)

public class Item implements Serializable{
	public Item(String description, ObjectNode urls) {
		super();
		this.description = description;
		this.urls = urls;
	}

	public Item() {
		super();
	}

	// Although table name is Item and id is product_id we tried to be consistent in
	// our entity definition
	// These will be your Postgres Tables

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	Long itemtId;
	String description;
	@Type(type = JsonbUserType.JSONB_TYPE)
	private ObjectNode urls;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)

	public Long getItemtId() {
		return itemtId;
	}

	public void setItemtId(Long productId) {
		this.itemtId = productId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Type(type = JsonbUserType.JSONB_TYPE)
	public ObjectNode getUrls() {
		return urls;
	}

	public void setUrls(ObjectNode urls) {
		this.urls = urls;
	}

}
