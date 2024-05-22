package com.aluracursos.literalura.main;
import com.aluracursos.literalura.controller.Controller;
import com.aluracursos.literalura.view.Vista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class Main {
    Controller controller;
    Vista view;
    private Scanner teclado = new Scanner(System.in);

    @Autowired
    public Main(Controller controller, Vista view) {
        this.controller = controller;
        this.view = view;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            view.paintMenu();
            opcion = teclado.nextInt();
            view.executeOption(opcion);
        }
        System.exit(0);
    }
}

