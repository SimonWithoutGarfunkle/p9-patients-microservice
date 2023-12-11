package com.medilabo.patients;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class PatientsApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(PatientsApplication.class, args);
	}

	/**
	 * Inject the data.sql file in the patient database to get some examples for the demo
	 * The SQL file delete all data from the base and add 4 sample patients
	 *
	 * @throws SQLException
	 * @throws IOException
	 */
	@PostConstruct
	public void initializeDatabase() throws SQLException, IOException {
		Resource resource = applicationContext.getResource("classpath:data.sql");
		try (InputStream inputStream = resource.getInputStream(); Connection connection = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(connection, new InputStreamResource(inputStream));
		}
		System.out.println("Les requêtes SQL ont été exécutées avec succès !");
	}

}
