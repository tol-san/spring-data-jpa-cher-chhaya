-- Runs once, only when the postgres data volume is empty
-- (Docker auto-executes any .sql file in /docker-entrypoint-initdb.d/)

CREATE DATABASE keycloak_db;
GRANT ALL PRIVILEGES ON DATABASE keycloak_db TO CURRENT_USER;

CREATE DATABASE springboot_db;
GRANT ALL PRIVILEGES ON DATABASE springboot_db TO CURRENT_USER;