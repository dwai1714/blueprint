CREATE ROLE tdaruwalla WITH LOGIN PASSWORD 'tdaruwalla'	;
CREATE DATABASE emdm_d;
 \connect emdm_d;
 CREATE SCHEMA emdm;
 grant usage on schema emdm to tdaruwalla;
 GRANT ALL PRIVILEGES ON DATABASE emdm_d TO tdaruwalla;
 CREATE TABLE emdm.t_item (product_id SERIAL NOT NULL, description CHARACTER VARYING(100) NOT NULL, 
	urls JSONB, PRIMARY KEY (product_id));
grant select, insert, update, delete on all tables in schema emdm to tdaruwalla;


GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA emdm TO tdaruwalla;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA emdm TO tdaruwalla;

