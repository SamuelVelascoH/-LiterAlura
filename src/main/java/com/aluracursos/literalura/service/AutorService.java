package com.aluracursos.literalura.service;

import com.aluracursos.literalura.dto.DatosAutor;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AutorService {
    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public List<Autor> getAuthorsAliveInYear(int año) {
        //Obtener todos los autores de la base de datos
        List<Autor> autores = autorRepository.findAll();
        //filtrar la lista de autores para incluir solo los autores vivos en el año ingresado
        return autores.stream()
                .filter(autor -> Integer.parseInt(autor.getAñoDeNacimiento()) <= año && (autor.getAñoDeFallecimiento() == null || Integer.parseInt(autor.getAñoDeFallecimiento()) >= año))
                .collect(Collectors.toList());
    }
    public Set<Autor> processAuthors(List<Map<String, Object>> authorsData) {
        Set<Autor> autores = new HashSet<>();
        for (Map<String, Object> author : authorsData) {
            autores.add(processAuthorData(author));
        }
        return autores;
    }
    private Autor processAuthorData(Map<String, Object> authorData) {
        String nombre = (String) authorData.get("name");
        Integer añoDeNacimiento = ((Double) authorData.get("birth_year")).intValue();
        Integer añoDeFallecimiento = ((Double) authorData.get("death_year")).intValue();

        DatosAutor datosAutor = new DatosAutor(nombre, añoDeNacimiento, añoDeFallecimiento);
        Autor autor = new Autor(datosAutor);
        // Si el autor no existe, guardarlo en la base de datos
        Optional<Autor> existingAutor = autorRepository.findByNombreContainsIgnoreCase(nombre);
        if (existingAutor.isEmpty()){
            autorRepository.save(autor);
        } else {
            autor = existingAutor.get();
        }
        return autor;
    }
}
