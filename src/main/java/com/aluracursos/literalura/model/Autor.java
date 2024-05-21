package com.aluracursos.literalura.model;
import com.aluracursos.literalura.dto.DatosAutor;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private String añoDeNacimiento;
    private String añoDeFallecimiento;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "autores")
    private Set<Libro> libros;

    public Autor(String nombre, Integer añoDeNacimiento, Integer añoDeFallecimiento) {
        this.nombre = nombre;
        this.añoDeNacimiento = String.valueOf(añoDeNacimiento);
        this.añoDeFallecimiento = String.valueOf(añoDeFallecimiento);
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.añoDeNacimiento = String.valueOf(datosAutor.añoDeNacimiento());
        this.añoDeFallecimiento = String.valueOf(datosAutor.añoDeFallecimiento());

    }

    public Autor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(String añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public String getAñoDeFallecimiento() {
        return añoDeFallecimiento;
    }

    public void setAñoDeFallecimiento(String añoDeFallecimiento) {
        this.añoDeFallecimiento = añoDeFallecimiento;
    }
    public Collection<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
    }
}
