package com.medilabo.patients;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Connection;

@SpringBootApplication
public class PatientsApplication {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private DataSource dataSource;

	public static void main(String[] args) {
		SpringApplication.run(PatientsApplication.class, args);
	}

	@PostConstruct
	public void initializeDatabase() throws SQLException, IOException {
		Resource resource = applicationContext.getResource("classpath:data.sql");
		try (InputStream inputStream = resource.getInputStream(); Connection connection = dataSource.getConnection()) {
			ScriptUtils.executeSqlScript(connection, new InputStreamResource(inputStream));
		}
		System.out.println("Les requêtes SQL ont été exécutées avec succès !");
	}

}
