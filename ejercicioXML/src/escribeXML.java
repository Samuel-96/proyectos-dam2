import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class escribeXML {
    public static void main(String[] args) {
        //creamos las referencias a null
        Element nodoLibro = null, nodoDatos = null; Text texto = null;

        //creamos el document
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        DOMImplementation dom = db.getDOMImplementation();
        Document document = dom.createDocument(null,"xml",null);

        //creamos la raiz
        Element raiz = document.createElement("libros"); //creamos la etiqueta raiz
        document.getDocumentElement().appendChild(raiz); //la conectamos

        //creamos la lista de libros y los objetos libro
        ArrayList<Libro> libros = new ArrayList<>();
        Libro l1 = new Libro(1,"El camino de los reyes",29.95,"Brandon Sanderson",2011,"Nova");
        Libro l2 = new Libro(2,"Dune",19.95,"Frank Herbert",1965,"Casa del libro");
        Libro l3 = new Libro(3,"Espejismo",12.95,"Hugh Howey",2013,"minotauro");

        //añadimos libros a la lista
        libros.add(l1);
        libros.add(l2);
        libros.add(l3);

        //construimos documento con los elementos empleado
        for (Libro libro: libros)
        {
            /*
                <libros>
                    <libro>
                        <id>texto</id>
             */
            nodoLibro = document.createElement("libro"); //creamos la etiqueta libro en nodoEmpleado
            raiz.appendChild(nodoLibro); //lo conectamos al nodo raiz, es decir libros
            nodoDatos = document.createElement("id"); //creo la etiqueta id
            nodoLibro.appendChild(nodoDatos); //la conecto al nodo padre, libro
            texto = document.createTextNode(String.valueOf(libro.getId())); //guardo en texto el id en forma de string a la etiqueta
            nodoDatos.appendChild(texto); //lo inserto en la etiqueta id

            /*
                <libros>
                    <libro>
                        <id>texto</id>
                        <titulo>texto</titulo>
             */
            nodoDatos = document.createElement("titulo");
            nodoLibro.appendChild(nodoDatos);
            texto = document.createTextNode(libro.getTitulo());
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("precio");
            nodoLibro.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(libro.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("autor");
            nodoLibro.appendChild(nodoDatos);
            texto = document.createTextNode(libro.getAutor());
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("año");
            nodoLibro.appendChild(nodoDatos);
            texto = document.createTextNode(String.valueOf(libro.getAnyo()));
            nodoDatos.appendChild(texto);

            nodoDatos = document.createElement("editorial");
            nodoLibro.appendChild(nodoDatos);
            texto = document.createTextNode(libro.getEditorial());
            nodoDatos.appendChild(texto);
        }

        Source source = new DOMSource(document); //el documento, el origen
        Result resultado = new StreamResult(new File("libros.xml"));
        Transformer transformer = null;
        try {
            transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        }
        try {
            transformer.transform(source,resultado);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }

    }
}