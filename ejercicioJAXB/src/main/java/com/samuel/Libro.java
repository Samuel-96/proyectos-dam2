package com.samuel;

/**
 * Clase Libro --
 * <p>
 * Un Libro contiene las propiedades básicas de un libro: id, titulo, precio, autor, anyo, editorial
 *
 * @author Samuel Amat Enguidanos
 * @version 1.0.0 -- 28/09/2022
 */

public class Libro {

    //atributos
    private int id;
    private String titulo;
    private double precio;
    private int anyo;
    private String autor;
    private String editorial;

    /**
     *
     * @param id - el identificador del libro (como un ISBN)
     * @param titulo - título del libro
     * @param precio - precio del libro, número real
     * @param autor - autor del libro
     * @param anyo - año de publicación del libro
     * @param editorial - editorial encargada de publicar el libro
     */
    public Libro(int id, String titulo, double precio, String autor, int anyo, String editorial) {
        this.id = id;
        this.titulo = titulo;
        this.precio = precio;
        this.autor = autor;
        this.anyo = anyo;
        this.editorial = editorial;
    }

    public Libro()
    {

    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @return titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @return precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @return autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * @return año
     */
    public int getAnyo() {
        return anyo;
    }

    /**
     * @return editorial
     */
    public String getEditorial() {
        return editorial;
    }

    /**
     * @param id - número entero
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param titulo - titulo del libro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param precio - precio del libro
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @param autor - autor del libro
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     * @param anyo - año de publicación
     */
    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    /**
     * @param editorial - editoril encargada de publicar el libro
     */
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        return "\t\tLibro" +
                "\n id: " + id +
                "\n titulo: " + titulo  +
                "\n precio: " + precio +
                "\n autor: " + autor  +
                "\n anyo: " + anyo +
                "\n editorial: " + editorial +
                "\n----------------------------";
    }
}
