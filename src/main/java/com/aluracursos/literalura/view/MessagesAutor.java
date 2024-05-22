package com.aluracursos.literalura.view;

import com.aluracursos.literalura.model.Autor;

public class MessagesAutor {
    public static void printAutor(Autor autor){

        System.out.println("Nombre: " + autor.getNombre());
        System.out.println("Año de nacimiento: " + autor.getAñoDeNacimiento());
        System.out.println("Año de fallecimiento: " + autor.getAñoDeFallecimiento());
    }
    public static String [] outMessageAutor = {
        "----------AUTORES REGISTRADOS----------:",//index[0]
            "Libros escritos: ",//index[1]
            "REGISTRO DEL AÑO ",//index[2]
            "\n No hay autores vivos registrados en el año ",//index[3]
            "-------------------------",//index[4]
            "----------",//index[5]
    };
    public  static String [] messageAutorRequest = {
            "Ingrese el año"//index [0]
    };
}
