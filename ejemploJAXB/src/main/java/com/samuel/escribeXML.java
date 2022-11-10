package com.samuel;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;

import java.io.File;

/**
 * Hello world!
 *
 */
public class escribeXML
{
    public static void main( String[] args)
    {
        //ArrayList<Libro> libros = new ArrayList<Libro>();
        Libro l1 = new Libro(1,"Nacidos de la bruma",29.95,"Brandon Sanderson",2010,"Nova");
        Libro l2 = new Libro(2,"Dune",29.95,"Frank Herbert",1965,"Nova");
        Libros libros = new Libros();
        libros.addLibro(l1);
        libros.addLibro(l2);

        try {
            JAXBContext contexto = JAXBContext.newInstance(
                    libros.getClass() );
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE);
            marshaller.marshal(libros, new File("librosJAXB.xml"));
        } catch (PropertyException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JAXBException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
