
package Modelo;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class InicializadorFicheros {

    private static final String CARPETA_FICHEROS = "ficheros_jugadores"; // Nombre de la carpeta

    /**
     * Inicializa la carpeta y los ficheros necesarios en el directorio del proyecto.
     * Crea la carpeta "ficheros_jugadores" y los diferentes tipos de ficheros.
     */
    public static void inicializarFicheros() {
        // Crea la carpeta "ficheros_jugadores" en el directorio raíz del proyecto si no existe
        File carpeta = new File(CARPETA_FICHEROS);
        if (!carpeta.exists()) {
            carpeta.mkdir(); // Crear la carpeta
            System.out.println("Carpeta creada: " + CARPETA_FICHEROS);
        } else {
            System.out.println("La carpeta ya existe.");
        }

        // Inicializa cada tipo de fichero
        try {
            // Fichero secuencial de texto
            File ficheroTexto = new File(CARPETA_FICHEROS + "/jugadores.txt");
            if (ficheroTexto.createNewFile()) {
                System.out.println("Fichero de texto creado.");
            }

            // Fichero secuencial binario
            File ficheroBinario = new File(CARPETA_FICHEROS + "/jugadores.dat");
            if (ficheroBinario.createNewFile()) {
                System.out.println("Fichero binario creado.");
            }

            // Fichero de objetos binario
            File ficheroObjetos = new File(CARPETA_FICHEROS + "/jugadores_objetos.dat");
            if (ficheroObjetos.createNewFile()) {
                System.out.println("Fichero de objetos binario creado.");
            }

            // Fichero de acceso aleatorio binario
            File ficheroAccesoAleatorio = new File(CARPETA_FICHEROS + "/jugadores_acceso_aleatorio.dat");
            if (ficheroAccesoAleatorio.createNewFile()) {
                System.out.println("Fichero de acceso aleatorio binario creado.");
            }

            // Fichero de texto XML
            File ficheroXML = new File(CARPETA_FICHEROS + "/jugadores.xml");
            if (ficheroXML.createNewFile()) {
                crearArchivoXmlVacio();
                System.out.println("Fichero de texto XML creado.");
            }

        } catch (IOException e) {
            System.err.println("Error al crear los ficheros: " + e.getMessage());
        }
    }
    
    /**
     * Crea un archivo XML vacío con la estructura básica.
     */
    private static void crearArchivoXmlVacio() {
        try {
            // Crear la instancia del DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Crear un nuevo documento XML
            Document doc = dBuilder.newDocument();

            // Crear el elemento raíz
            Element rootElement = doc.createElement("jugadores");
            doc.appendChild(rootElement);

            // Guardar el documento XML en un archivo
            guardarXmlEnArchivo(doc, "jugadores.xml");

            System.out.println("Archivo XML vacío creado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Guarda el documento XML en un archivo.
     *
     * @param doc El documento XML a guardar.
     * @param nombreArchivo El nombre del archivo donde se guardará el XML.
     */
    private static void guardarXmlEnArchivo(Document doc, String nombreArchivo) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.VERSION, "1.0");

            // Crear el archivo
            File archivo = new File(CARPETA_FICHEROS + "/jugadores.xml");
            StreamResult result = new StreamResult(archivo);
            DOMSource source = new DOMSource(doc);

            // Transformar el documento XML y guardarlo en el archivo
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Devuelve la ruta donde se guardan los ficheros.
     *
     * @return La ruta de la carpeta donde se guardan los ficheros.
     */
    public String getRuta_Guardados() {
        return CARPETA_FICHEROS;
    }
}
