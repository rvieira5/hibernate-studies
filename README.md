# Introduction

At first, the goal is to use the [Hibernate 6 introduction](https://docs.jboss.org/hibernate/orm/6.3/introduction/html_single/Hibernate_Introduction.html) to guide some of the experiments.

# Preparation

## Download and Import the `sakila` Database

The `sakila` database is not included in the repository. Follow the steps below to download and import the database:

### a. Download the `sakila` Database

Run the following command to download the `sakila` database:

```bash
wget https://downloads.mysql.com/docs/sakila-db.tar.gz
```

### b. Extract the Files

Extract the downloaded files:

```bash
tar -xvzf sakila-db.tar.gz
```

This will create a folder named `sakila-db` containing the files `sakila-schema.sql` and `sakila-data.sql`.
