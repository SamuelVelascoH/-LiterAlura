package com.aluracursos.literalura.main;
import com.aluracursos.literalura.controller.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Main {
    Controller controller;
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    public Main(Controller controller) {
        this.controller = controller;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            controller.printMenu();
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case  3:
                    listarAutoresRegistrados();
                    break;
                case 4 :
                    listarAutoresVivosEnDeterminadoAño();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
        System.exit(0);
    }


    public void buscarLibroPorTitulo() {
        System.out.println("Ingrese el titulo del libro");
        var tituloDeLibro = teclado.nextLine();
        controller.getDataBooks(tituloDeLibro);
    }

    private void listarLibrosRegistrados() {
        controller.listBooksRegistred();

    }
    public void listarAutoresRegistrados() {
        controller.listAuthorsRegistred();
    }
    public void listarAutoresVivosEnDeterminadoAño() {
        System.out.println("Ingrese el año");
        var año = teclado.nextInt();
        controller.listAuthorsAliveInYear(año);
    }
    private void listarLibrosPorIdioma() {
        controller.listBooksByLanguage();
    }
}

