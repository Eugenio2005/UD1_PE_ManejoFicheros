package Modelo;

import java.io.*;
import java.util.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LeerFicheros {
    
    private final String RUTA_GUARDADO = new InicializadorFicheros().getRuta_Guardados();
    private ArrayList<JugadorModelo> jugadores;

    public LeerFicheros() {
        jugadores = new ArrayList<>();
    }

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

    private void leerFicheroTexto() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_GUARDADO +"/jugadores.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Eliminar los corchetes
                linea = linea.replace("[", "").replace("]", "");

                // Dividimos la línea por comas para obtener cada clave-valor
                String[] partes = linea.split(", ");

                // Array para almacenar los valores extraídos
                String[] datos = new String[5];

                // Recorremos cada parte y extraemos el valor después del "="
                for (int i = 0; i < partes.length; i++) {
                    String[] keyValue = partes[i].split("= ");
                    datos[i] = keyValue[1].trim(); // Guardamos solo el valor
                }

                // Creamos el objeto JugadorModelo con los valores extraídos
                JugadorModelo jugador = new JugadorModelo(
                        Integer.parseInt(datos[0]), // id
                        datos[1], // nick_name
                        Integer.parseInt(datos[2]), // experience
                        Integer.parseInt(datos[3]), // life_level
                        Integer.parseInt(datos[4]) // coins
                );

                jugadores.add(jugador);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de texto: " + e.getMessage());
        }
    }

    private void leerFicheroBinario() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(RUTA_GUARDADO +"/jugadores.bin"))) {
            while (true) {
                int id = dis.readInt();
                String nickName = dis.readUTF();
                int experience = dis.readInt();
                int lifeLevel = dis.readInt();
                int coins = dis.readInt();
                JugadorModelo jugador = new JugadorModelo(id, nickName, experience, lifeLevel, coins);
                jugadores.add(jugador);
            }
        } catch (EOFException e) {
            // Fin del archivo alcanzado
        } catch (IOException e) {
            System.err.println("Error al leer el fichero binario: " + e.getMessage());
        }
    }

    private void leerFicheroObjetos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RUTA_GUARDADO +"/jugadores.obj"))) {
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

    private void leerFicheroAccesoAleatorio() {
        try (RandomAccessFile raf = new RandomAccessFile(RUTA_GUARDADO +"/jugadores.dat", "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int id = raf.readInt();
                String nickName = raf.readUTF();
                int experience = raf.readInt();
                int lifeLevel = raf.readInt();
                int coins = raf.readInt();
                JugadorModelo jugador = new JugadorModelo(id, nickName, experience, lifeLevel, coins);
                jugadores.add(jugador);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el fichero de acceso aleatorio: " + e.getMessage());
        }
    }

    private void leerFicheroXML() {
        try {
            File archivo = new File(RUTA_GUARDADO +"/jugadores.xml");
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
                    int experience = Integer.parseInt(elem.getElementsByTagName("experience").item(0).getTextContent());
                    int lifeLevel = Integer.parseInt(elem.getElementsByTagName("life_level").item(0).getTextContent());
                    int coins = Integer.parseInt(elem.getElementsByTagName("coins").item(0).getTextContent());
                    JugadorModelo jugador = new JugadorModelo(id, nickName, experience, lifeLevel, coins);
                    jugadores.add(jugador);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer el fichero XML: " + e.getMessage());
        }
    }

    public ArrayList<JugadorModelo> getJugadores() {
        return jugadores;
    }
}
