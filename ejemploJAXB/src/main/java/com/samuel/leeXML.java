package com.samuel;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;

public class leeXML {
    public static void main(String[] args) {

        try {
            JAXBContext context = JAXBContext.newInstance(Libros.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //Libros libros = (Libros) unmarshaller.unmarshal(new File("librosJAXB.xml"));
            Libros libros = (Libros) unmarshaller.unmarshal(new File("librosJAXB.xml"));
            libros.muestraLibro();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
