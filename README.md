# Introduction

At first, the goal is to use the [Hibernate 6
introduction](https://docs.jboss.org/hibernate/orm/6.3/introduction/html_single/Hibernate_Introduction.html)
to guide some of the experiments.

# Preparation

## Download and Import the `sakila` Database

The `sakila` database is not included in the repository.

To download the `sakila` database SQL script for H2 database, just run the
following command:

```bash
./mvnw com.googlecode.maven-download-plugin:download-maven-plugin:wget@download-h2-database-sakila-script
```
