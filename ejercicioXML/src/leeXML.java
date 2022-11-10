import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class leeXML {
    public static void main(String[] args) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
        Document documento = null;
        try {
            documento = builder.parse(new File("libros.xml"));
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NodeList libros = documento.getElementsByTagName("libro"); //en NodeList se guardan tantos elementos como etiquetas libro haya
        for (int i = 0; i < libros.getLength(); i++) //en el NodeList libros habrán 3, por lo tanto 3 pasadas por el for
        {
            Node libro = libros.item(i);
            Element elemento = (Element) libro; //convertimos el nodo a ELement
            //System.out.println(elemento.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getTextContent()); //muestra el documento xml entero, el nodo y sus descendientes
            /*System.out.println(elemento.getElementsByTagName("titulo").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("precio").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("año").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("autor").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println(elemento.getElementsByTagName("editorial").item(0).getChildNodes().item(0).getNodeValue());
            System.out.println("");*/
        }
    }
}
