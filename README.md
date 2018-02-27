# blueprint
Spring Boot Blueprint
Before you compile:
a) Need to start Consul. If not started at default port change bootstrap.yml to point to right address
b) Need to start Vault in dev mode with key 00000000-0000-0000-0000-000000000000
c) Need to start Postgres and create table T_ITEM in your database and schema. Make sure you change the JDBC Url in application.yml to point to your DB and your schema 
	drop table T_ITEM;

        CREATE TABLE T_ITEM (
          product_id             SERIAL PRIMARY KEY,
          description           	VARCHAR(100) NOT NULL,
          urls 					jsonb
        );
d) Need to get Oracle container from https://hub.docker.com/r/sath89/oracle-12c/
e) Download ojdbc8 from Oracle site then do a maven build (https://www.mkyong.com/maven/how-to-add-oracle-jdbc-driver-in-your-maven-local-repository/)


