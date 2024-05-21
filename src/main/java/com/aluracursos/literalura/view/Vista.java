package com.aluracursos.literalura.view;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Vista {
     public void printBookDetails(Libro libro){
        System.out.println("----------LIBRO----------");
        System.out.println("Titulo: " + libro.getTitulo());
         libro.getAutores().stream()
                 .map(Autor::getNombre)
                 .forEach(nombre -> System.out.println("Autor:  " + nombre));
         libro.getIdiomas().stream()
                    .forEach(idioma -> System.out.println("Idiomas:  " + idioma));
         System.out.println("Descargas: " + libro.getDescargas());
         System.out.println("-------------------------");
    }

    public void printBooksRegistred(List<Libro> libros) {
        System.out.println("----------LIBROS REGISTRADOS----------");
        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            // Imprimir los nombres de los autores
            libro.getAutores().stream()
                    .map(Autor::getNombre)
                    .forEach(nombre -> System.out.println("Autor:  " + nombre));
            System.out.println("Idiomas: " + String.join(", ", libro.getIdiomas()));
            System.out.println("Descargas: " + libro.getDescargas());
            System.out.println("-------------------------");
        }
    }

    public void printAuthorsRegistred(List<Autor> autores) {
        System.out.println("----------AUTORES REGISTRADOS----------:");
        for (Autor autor : autores) {
            System.out.println("Nombre: " + autor.getNombre());
            System.out.println("Año de nacimiento: " + autor.getAñoDeNacimiento());
            System.out.println("Año de fallecimiento: " + autor.getAñoDeFallecimiento());
            // Crea una lista para almacenar los títulos de los libros
            List<String> titulosDeLibros = new ArrayList<>();
            // Agrega los títulos de los libros a la lista
            for (Libro libro : autor.getLibros()) {
                titulosDeLibros.add(libro.getTitulo());
            }
            // Imprime la lista de títulos de libros
            System.out.println("Libros escritos: " + titulosDeLibros);

            System.out.println("-------------------------");
        }
    }
    public void ShowMenuLanguage(){
        System.out.println("Ingrese el idioma");
        System.out.println(
                "es - Español \n" +
                        "en - Inglés \n" +
                        "fr - Francés \n" +
                        "pt - Portugués \n"
        );
    }
    public String paintMenu(){
        var menu = """
                    1 - Buscar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                    0 - Salir""";
        return menu ;
    }
}
