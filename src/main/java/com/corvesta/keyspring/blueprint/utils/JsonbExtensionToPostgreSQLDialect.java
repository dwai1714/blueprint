package com.corvesta.keyspring.blueprint.utils;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL94Dialect;

public class JsonbExtensionToPostgreSQLDialect extends PostgreSQL94Dialect {

	public JsonbExtensionToPostgreSQLDialect() {
		super();
		registerColumnType(Types.JAVA_OBJECT, JsonbUserType.JSONB_TYPE);
	}
}