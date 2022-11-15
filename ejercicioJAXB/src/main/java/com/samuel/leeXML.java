package com.samuel;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
/**
 * 4. Igualmente haciendo uso de JAXB haciendo uso de anotaciones, para escritura y lectura.
 */
public class leeXML {
    public static void main(String[] args) {

        try {
            JAXBContext context = JAXBContext.newInstance(Libros.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Libros libros = (Libros) unmarshaller.unmarshal(new File("./src/recursos/librosJAXB.xml"));
            libros.muestraLibro();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
