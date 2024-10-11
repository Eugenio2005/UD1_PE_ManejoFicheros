package Modelo;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

public class LeerFicheros {

    private final String RUTA_GUARDADO = new InicializadorFicheros().getRuta_Guardados();
    private ArrayList<JugadorModelo> jugadores;

    /**
     * Constructor que inicializa la lista de jugadores.
     */
    public LeerFicheros() {
        jugadores = new ArrayList<>();
    }

    /**
     * Carga los jugadores desde un archivo basado en el tipo de almacenamiento especificado.
     *
     * @param tipoAlmacenamiento El tipo de archivo desde el cual se cargarán los jugadores.
     */
    public void cargarJugadores(String tipoAlmacenamiento) {
        switch (tipoAlmacenamiento) {
            case "texto":
                leerFicheroTexto();
                break;
            case "binario":
                leerFicheroBinario();
                break;
            case "objetos":
                leerFicheroObjetos();
                break;
            case "accesoAleatorio":
                leerFicheroAccesoAleatorio();
                break;
            case "xml":
                leerFicheroXML();
                break;
            default:
                System.out.println("No se ha seleccionado un tipo de archivo válido.");
        }
    }

    /**
     * Lee los jugadores desde un archivo de texto y los agrega a la lista de jugadores.
     */
    private void leerFicheroTexto() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_GUARDADO + "/jugadores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.replace("[", "").replace("]", "");
                String[] partes = linea.split(", ");
                String[] datos = new String[5];

                for (int i = 0; i < partes.length; i++) {
                    String[] keyValue = partes[i].split("= ");
                    datos[i] = keyValue[1].trim();
                }

                JugadorModelo jugador = new JugadorModelo(
                        Integer.parseInt(datos[0]), // id
                        datos[1], // nick_name
                        Integer.parseInt(datos[2]), // experiencia
                        Integer.parseInt(datos[3]), // nivelVida
                        Integer.parseInt(datos[4]) // monedas
                );

                jugadores.add(jugador);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de texto: " + e.getMessage());
        }
    }

    /**
     * Lee los jugadores desde un archivo binario y los agrega a la lista de jugadores.
     */
    private void leerFicheroBinario() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(RUTA_GUARDADO + "/jugadores.dat"))) {
            while (true) {
                int id = dis.readInt();
                String nickName = dis.readUTF();
                int experiencia = dis.readInt();
                int nivelVida = dis.readInt();
                int monedas = dis.readInt();
                JugadorModelo jugador = new JugadorModelo(id, nickName, experiencia, nivelVida, monedas);
                jugadores.add(jugador);
            }
        } catch (EOFException e) {
            // Fin del archivo alcanzado
        } catch (IOException e) {
            System.err.println("Error al leer el fichero binario: " + e.getMessage());
        }
    }

    /**
     * Lee los jugadores desde un archivo de objetos y los agrega a la lista de jugadores.
     */
    private void leerFicheroObjetos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_GUARDADO + "/jugadores_objetos.dat"))) {
            while (true) {
                JugadorModelo jugador = (JugadorModelo) ois.readObject();
                jugadores.add(jugador);
            }
        } catch (EOFException e) {
            // Fin del archivo alcanzado
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al leer el fichero de objetos: " + e.getMessage());
        }
    }

    /**
     * Lee los jugadores desde un archivo de acceso aleatorio y los agrega a la lista de jugadores.
     */
    private void leerFicheroAccesoAleatorio() {
        try (RandomAccessFile raf = new RandomAccessFile(RUTA_GUARDADO + "/jugadores_acceso_aleatorio.dat", "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                String nickName = raf.readUTF();
                int experiencia = raf.readInt();
                int nivelVida = raf.readInt();
                int monedas = raf.readInt();
                JugadorModelo jugador = new JugadorModelo(id, nickName, experiencia, nivelVida, monedas);
                jugadores.add(jugador);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de acceso aleatorio: " + e.getMessage());
        }
    }

    /**
     * Lee los jugadores desde un archivo XML y los agrega a la lista de jugadores.
     */
    private void leerFicheroXML() {
        try {
            File archivo = new File(RUTA_GUARDADO + "/jugadores.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("jugador");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;
                    int id = Integer.parseInt(elem.getElementsByTagName("id").item(0).getTextContent());
                    String nickName = elem.getElementsByTagName("nick_name").item(0).getTextContent();
                    int experiencia = Integer.parseInt(elem.getElementsByTagName("experiencia").item(0).getTextContent());
                    int nivelVida = Integer.parseInt(elem.getElementsByTagName("nivel_vida").item(0).getTextContent());
                    int monedas = Integer.parseInt(elem.getElementsByTagName("monedas").item(0).getTextContent());
                    JugadorModelo jugador = new JugadorModelo(id, nickName, experiencia, nivelVida, monedas);
                    jugadores.add(jugador);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el fichero XML: " + e.getMessage());
        }
    }

    /**
     * Devuelve la lista de jugadores cargados.
     *
     * @return La lista de jugadores.
     */
    public ArrayList<JugadorModelo> getJugadores() {
        return jugadores;
    }
}
