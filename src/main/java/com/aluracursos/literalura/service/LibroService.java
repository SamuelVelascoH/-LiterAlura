package com.aluracursos.literalura.service;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.dto.DatosLibro;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.view.MessagesLibro;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibroService {
    private LibroRepository libroRepository;
    private AutorService autorService;
    private final String URL_BASE = "https://gutendex.com/books?search=";

    public LibroService(LibroRepository libroRepository, AutorService autorService) {

        this.libroRepository = libroRepository;
        this.autorService = autorService;
    }

    private Optional<Libro> processBookData(String tituloDeLibro) {
        //Deserializar el JSON
        Map<String, Object> bookData = ApiServices.getJsonData(URL_BASE, tituloDeLibro);
        // Asignar los datos del JSON a la entidad DatosLibro
        List<Map<String, Object>> results = (List<Map<String, Object>>) bookData.get("results");
        if (!results.isEmpty()) {
            Map<String, Object> result = results.get(0);
            Libro libro = processSingleBookData(result);
            return Optional.ofNullable(libro);
        } else {
            System.out.println(MessagesLibro.outMessageBook[0]);
            return Optional.empty();
        }
    }

    /**
     * Agiliza la respuesta buscando primero en la base de datos local, para evitar perder tiempo inesesario
     * consultando primero a la api, sin saber si ya existe
     */
    public Optional<Libro> recoverBook(String titulo){
        Optional<Libro> existingLibro = libroRepository.findByTituloContainsIgnoreCase(titulo.toLowerCase());
        if(existingLibro.isEmpty()){
            return processBookData(titulo);
        }
        System.out.println(MessagesLibro.outMessageBook[1]);
        return existingLibro;
    }

    private Libro processSingleBookData(Map<String, Object> bookData) {
        String titulo = (String) bookData.get("title");
        Set<Autor> autores = autorService.processAuthors((List<Map<String, Object>>) bookData.get("authors"));
        Set<String> idiomas = new HashSet<>((List<String>) bookData.get("languages"));
        String descargas = bookData.get("download_count").toString();

        DatosLibro datosLibro = new DatosLibro(titulo, autores, idiomas, descargas);
        Libro libro = new Libro(datosLibro);
        // Guardar el libro en la base de datos
        saveBookInDatabase(libro);
        return libro;
    }
    private Optional<Libro> saveBookInDatabase(Libro libro) {
        libroRepository.save(libro);
        System.out.println(MessagesLibro.outMessageBook[2]);
        return Optional.ofNullable(libro);
    }
    public List<Libro> getBooksByLanguage(String idioma) {
        //Obtener todos los libros de la base de datos
        List<Libro> libros = libroRepository.findAll();
        //filtrar la lista de libros para incluir solo los libros con el idioma ingresado
        return libros.stream()
                .filter(libro -> libro.getIdiomas().contains(idioma))
                .collect(Collectors.toList());
    }
}
