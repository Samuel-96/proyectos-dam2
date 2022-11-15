package com.samuel;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Libros {
    private ArrayList<Libro> libros = null;


    public Libros() {
        libros = new ArrayList<>();
    }

    public void muestraLibro()
    {
        for(Libro libro : libros)
        {
            System.out.println(libro);
        }
    }

    public void addLibro(Libro l)
    {
        libros.add(l);
    }

}
