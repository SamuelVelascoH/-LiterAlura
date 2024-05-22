package com.aluracursos.literalura.controller;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.AutorService;
import com.aluracursos.literalura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Controller
public class Controller {
    private AutorRepository autorRepository;
    private LibroRepository libroRepository;
    private LibroService libroService;
    private AutorService autorService;
    @Autowired
    public Controller(AutorRepository autorRepository, LibroRepository libroRepository, LibroService service, AutorService autorService) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        this.libroService = service;
        this.autorService = autorService;
    }
    public Libro getDataBooks(String tituloDeLibro) {
        Optional<Libro> libro = libroService.recoverBook(tituloDeLibro);
        return libro.get();
    }
    public List<Libro> listBooksRegistred() {
        return libroRepository.findAll();
    }
    public List<Autor> listAuthorsRegistred() {
        return autorRepository.findAll();
    }
    public List<Autor>  listAuthorsAliveInYear(int año) {
        return autorService.getAuthorsAliveInYear(año);
    }
    public List<Libro> listBooksByLanguage(String idioma) {
        return libroService.getBooksByLanguage(idioma);
    }

}
