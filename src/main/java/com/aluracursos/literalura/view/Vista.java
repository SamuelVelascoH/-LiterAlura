package com.aluracursos.literalura.view;

import com.aluracursos.literalura.controller.Controller;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Component
public class Vista {
    @Autowired
    Controller controller;
    private Scanner teclado = new Scanner(System.in);
    public void searchBookByTitle() {
        System.out.println(MessagesLibro.MessageBookRequest[1]);
        var tituloDeLibro = teclado.nextLine();
        Libro libro = controller.getDataBooks(tituloDeLibro);
        if (libro != null) {
            MessagesLibro.printBookDetails(libro);
        }
    }
    public void viewListBooksRegistred() {
        List<Libro>  libros = controller.listBooksRegistred();
        System.out.println(MessagesLibro.outMessageBook[4]);
        MessagesLibro.printBooksRegistred(libros);
    }
    public void printAuthorsRegistred(List<Autor> autores) {
        System.out.println(MessagesAutor.outMessageAutor[0]);
        for (Autor autor : autores) {
            MessagesAutor.printAutor(autor);
            List<String> writtenBooks = new ArrayList<>();
            for (Libro libro : autor.getLibros()) {
                writtenBooks.add(libro.getTitulo());
            }
            System.out.println(MessagesAutor.outMessageAutor[1] + writtenBooks);
            System.out.println(MessagesAutor.outMessageAutor[4]);
        }
    }
    public void viewListAuthorsRegistred() {
        List<Autor> autores = controller.listAuthorsRegistred();
        this.printAuthorsRegistred(autores);
    }
    public void ShowMenuLanguage(){
        System.out.println(MessagesLibro.MessageBookRequest[0]);
        System.out.println( MessagesLibro.menuLanguages );
    }
    public void viewListByLanguage() {
        this.ShowMenuLanguage();
        Scanner enter = new Scanner(System.in);
        var idioma = enter.nextLine();
        List<Libro> libros = controller.listBooksByLanguage(idioma.toLowerCase());
        if (libros.isEmpty()) {
            System.out.println(MessagesLibro.outMessageBook[5] + idioma + "\n");
        }else {
            System.out.println(MessagesLibro.outMessageBook[7]+idioma + "\n");
            MessagesLibro.printBooksRegistred(libros);
        }
    }
    public void paintMenu(){
        System.out.println(MessagesLibro.menu);
    }
    public void viewSearchAuthorAliveInYear() {
        System.out.println(MessagesAutor.messageAutorRequest[0]);
        var year = teclado.nextInt();
        List<Autor> autoresVivos = controller.listAuthorsAliveInYear(year);
        if (autoresVivos.isEmpty()) {
            System.out.println(MessagesAutor.outMessageAutor[3] + year + "\n");
        } else {
            System.out.println(MessagesAutor.outMessageAutor[5]+MessagesAutor.outMessageAutor[2] + year + MessagesAutor.outMessageAutor[5]);
            this.printAuthorsRegistred(autoresVivos);
        }
    }
    public void executeOption(int opcion){
        switch (opcion) {
            case 1:
                this.searchBookByTitle();
                break;
            case 2:
                this.viewListBooksRegistred();
                break;
            case  3:
                this.viewListAuthorsRegistred();
                break;
            case 4 :
                this.viewSearchAuthorAliveInYear();
                break;
            case 5:
                this.viewListByLanguage();
                break;
            case 0:
                System.out.println("Cerrando la aplicación...");
                break;
            default:
                System.out.println("Opción inválida");
        }
    }
}
