package com.aluracursos.literalura.view;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;

import java.util.List;

public final class MessagesLibro {

    public static void printBookDetails(Libro libro){

        System.out.println(outMessageBook[3]);
        System.out.println("Titulo: " + libro.getTitulo());
        libro.getAutores().stream()
                .map(Autor::getNombre)
                .forEach(nombre -> System.out.println("Autor:  " + nombre));
        libro.getIdiomas().stream()
                .forEach(idioma -> System.out.println("Idiomas:  " + idioma));
        System.out.println("Descargas: " + libro.getDescargas());
        System.out.println(outMessageBook[6]);
    }
    public static void printBooksRegistred(List<Libro> libros) {
        for (Libro libro : libros) {
            System.out.println("Título: " + libro.getTitulo());
            // Imprimir los nombres de los autores
            libro.getAutores().stream()
                    .map(Autor::getNombre)
                    .forEach(nombre -> System.out.println("Autor:  " + nombre));
            System.out.println("Idiomas: " + String.join(", ", libro.getIdiomas()));
            System.out.println("Descargas: " + libro.getDescargas());
            System.out.println(MessagesLibro.outMessageBook[6]);
        }
    }
    public static String menu = """
                    1 - Buscar libros por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año
                    5 - Listar libros por idioma
                    0 - Salir""";
    public static String menuLanguages = "es - Español \n" +
            "en - Inglés \n" +
            "fr - Francés \n" +
            "pt - Portugués \n";

    public static String [] outMessageBook = {
            "\n----------LIBRO NO ENCONTRADO----------\n",//index[0]

            "...\nEl libro ya existe en la base de datos\n----------XD----------",//index[1]

            "Libro guardado en la base de datos...\n----------XD----------", //index[2]

            "----------LIBRO----------",//index[3]

            "----------LIBROS REGISTRADOS----------",//index[4]

            "\nNo hay libros registrados en el idioma ",//index[5]

            "-------------------------",//index[6]
            "\n-----LIBROS REGISTRADOS EN EL IDIOMA:-----"//index[7]
    };
    public static String [] MessageBookRequest = {
            "Ingrese el idioma",//index[0]
            "Ingrese el titulo del libro",//INDEX[1]
    };
}
