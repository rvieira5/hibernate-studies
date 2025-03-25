package com.andcelsode.hql.noparameters;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hibernate.cfg.JdbcSettings.FORMAT_SQL;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;
import static org.hibernate.cfg.JdbcSettings.PASS;
import static org.hibernate.cfg.JdbcSettings.SHOW_SQL;
import static org.hibernate.cfg.JdbcSettings.URL;
import static org.hibernate.cfg.JdbcSettings.USER;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CountryIT {
	private SessionFactory sessionFactory;

	@BeforeEach
	public void setUp() {
		sessionFactory = new Configuration() //
				.addAnnotatedClass(Country.class) //
				.setProperty(URL, "jdbc:h2:mem:sakila;INIT=RUNSCRIPT FROM './sakila.sql'") //
				.setProperty(USER, "sa") //
				.setProperty(PASS, "") //
				.setProperty("hibernate.agroal.maxSize", "20") //
				.setProperty(SHOW_SQL, TRUE.toString()) //
				.setProperty(FORMAT_SQL, TRUE.toString()) //
				.setProperty(HIGHLIGHT_SQL, TRUE.toString()) //
				.buildSessionFactory();
	}

	@Test
	public void simpleSearch() {
		var result = sessionFactory.fromTransaction(session -> {
			String hqlQuery = """
					select
					    id
					from
					    Country
					where
					    country = 'France'
					""";
			return session.createSelectionQuery(hqlQuery) //
					.getSingleResult();
		});

		assertThat(result) //
				.isInstanceOf(Integer.class) //
				.isEqualTo(34);
	}
}
