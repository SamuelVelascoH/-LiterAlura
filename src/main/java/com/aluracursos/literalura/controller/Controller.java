package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.AutorService;
import com.aluracursos.literalura.service.LibroService;
import com.aluracursos.literalura.view.Vista;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {
    private AutorRepository autorRepository;
    private LibroRepository libroRepository;
    private Vista vista;
    private LibroService libroService;
    private AutorService autorService;
    @Autowired
    public Controller(AutorRepository autorRepository, LibroRepository libroRepository, Vista vista, LibroService service, AutorService autorService) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        this.vista = vista;
        this.libroService = service;
        this.autorService = autorService;
    }
    public void getDataBooks(String tituloDeLibro) {
        Optional<Libro> libro = libroService.processBookData(tituloDeLibro);
        if (libro.isPresent()) {
            vista.printBookDetails(libro.get());
        }
    }
    public void listBooksRegistred() {
        List<Libro> libros = libroRepository.findAll();
        vista.printBooksRegistred(libros);
    }
    public void listAuthorsRegistred() {
        List<Autor> autores = autorRepository.findAll();
        vista.printAuthorsRegistred(autores);
    }
    public void listAuthorsAliveInYear(int año) {
        List<Autor> autoresVivos = autorService.getAuthorsAliveInYear(año);
        if (autoresVivos.isEmpty()) {
            System.out.println("\n"+"No hay autores vivos registrados en el año " + año + "\n");
        } else {
            System.out.println("----------REGISTRO DEL AÑO " + año + "----------");
            vista.printAuthorsRegistred(autoresVivos);
        }
    }
    public void listBooksByLanguage() {
        vista.ShowMenuLanguage();
        Scanner enter = new Scanner(System.in);
        var idioma = enter.nextLine();
        List<Libro> librosPorIdioma = libroService.getBooksByLanguage(idioma);
            if (librosPorIdioma.isEmpty()) {
                System.out.println("\n"+"No hay libros registrados en el idioma " + idioma + "\n");
            }else {
                vista.printBooksRegistred(librosPorIdioma);
            }
    }
    public void printMenu(){
        System.out.println(vista.paintMenu());
    }

}
