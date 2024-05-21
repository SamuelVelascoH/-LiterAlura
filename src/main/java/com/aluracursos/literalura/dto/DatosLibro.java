package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Autor;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") Set<Autor> autores,
        @JsonAlias("languages")Set<String> idiomas,
        @JsonAlias("download_count")String descargas

){
}
