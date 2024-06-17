package com.challengerLiteratura.eduriu;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.challengerLiteratura.eduriu.servicios.LibroServicio;

@SpringBootApplication
public class EduriuApplication implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        LibroServicio libroServicio = new LibroServicio();
        var libro = "Romeo and Juliet";
        libroServicio.busquedaLibro(libro);

    }

	public static void main(String[] args) {
		SpringApplication.run(EduriuApplication.class, args);



	}
}
