FROM mysql:8.0

COPY sakila-db /docker-entrypoint-initdb.d/