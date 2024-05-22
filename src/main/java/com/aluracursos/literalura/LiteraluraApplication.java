package com.aluracursos.literalura;

import com.aluracursos.literalura.controller.Controller;
import com.aluracursos.literalura.main.Main;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.view.Vista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {
	@Autowired
	Controller controller;
	@Autowired
	Vista vista;
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	public void run(String... args) throws Exception {
		Main main = new Main(controller, vista);
		main.mostrarMenu();
	}

}
