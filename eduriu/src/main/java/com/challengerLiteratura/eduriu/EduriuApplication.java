package com.challengerLiteratura.eduriu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.challengerLiteratura.eduriu.servicios.LibroServicio;

@SpringBootApplication
public class EduriuApplication implements CommandLineRunner {

    @Autowired
    private LibroServicio libroServicio;

    @Override
    public void run(String... args) throws Exception {

        libroServicio.menu();
    }

	public static void main(String[] args) {
		SpringApplication.run(EduriuApplication.class, args);

	}
}
