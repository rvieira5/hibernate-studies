# Database and Docker Setup

This guide explains how to set up the MySQL database and Docker for the project.

## Prerequisites

- Docker installed (already available in GitHub Codespaces).

## Setup Steps

### 1. Start MySQL with Docker

In the root directory of the project, run the following command to start MySQL in a Docker container:

```
docker-compose up -d
```

This will download the MySQL image and start the container.

### 2. Download and Import the `sakila` Database

The `sakila` database is not included in the repository. Follow the steps below to download and import the database:

#### a. Download the `sakila` Database

Run the following command to download the `sakila` database:

```bash
wget https://downloads.mysql.com/docs/sakila-db.tar.gz
```

#### b. Extract the Files

Extract the downloaded files:

```bash
tar -xvzf sakila-db.tar.gz
```

This will create a folder named `sakila-db` containing the files `sakila-schema.sql` and `sakila-data.sql`.

#### c. Import the Schema and Data

Import the schema and data into the MySQL container:

```bash
docker exec -i mysql_db mysql -u root -proot -e "CREATE DATABASE IF NOT EXISTS sakila;"
docker exec -i mysql_db mysql -u root -proot sakila < sakila-db/sakila-schema.sql
docker exec -i mysql_db mysql -u root -proot sakila < sakila-db/sakila-data.sql
```

When prompted, enter the root password (`root`).

### 3. Verify the Database

To verify that the database was imported correctly, access MySQL:

```bash
docker exec -it mysql_db mysql -u root -proot
```

In the MySQL prompt, run:

```bash
USE sakila;
SHOW TABLES;
```

You should see a list of tables from the `sakila` database.

### Test Database Connection

To test the connection with the MySQL database, run the following command:

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.andcelsode.DatabaseConnectionTest"
```
If the connection is successful, you will see the message:

```bash
Database connection successfully established!
```