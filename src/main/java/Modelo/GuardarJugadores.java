package Modelo;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GuardarJugadores {

    private String mensajeGuardado;
    private final String RUTA_GUARDADO = new InicializadorFicheros().getRuta_Guardados();

    /**
     * Establece el mensaje de guardado.
     *
     * @param mensajeGuardado El mensaje a establecer.
     */
    public void setMensajeGuardado(String mensajeGuardado) {
        this.mensajeGuardado = mensajeGuardado;
    }

    /**
     * Obtiene el mensaje de guardado.
     *
     * @return El mensaje de guardado.
     */
    public String getMensajeGuardado() {
        return mensajeGuardado;
    }

    /**
     * Guarda la lista de jugadores en el tipo de almacenamiento especificado.
     *
     * @param jugadores Lista de jugadores a guardar.
     * @param tipoAlmacenamiento Tipo de almacenamiento (texto, binario, objetos, acceso aleatorio, xml).
     */
    public void guardarJugador(List<JugadorModelo> jugadores, String tipoAlmacenamiento) {
        switch (tipoAlmacenamiento) {
            case "texto":
                guardarJugadorEnTexto(jugadores);
                break;
            case "binario":
                guardarJugadorEnBinario(jugadores);
                break;
            case "objetos":
                guardarJugadorEnObjetos(jugadores);
                break;
            case "accesoAleatorio":
                guardarJugadorEnAccesoAleatorio(jugadores);
                break;
            case "xml":
                guardarJugadorEnXML(jugadores);
                break;
            default:
                setMensajeGuardado("Tipo de almacenamiento no válido.");
                break;
        }
    }

    /**
     * Guarda la lista de jugadores en un archivo de texto.
     *
     * @param jugadoresExistentes Lista de jugadores a guardar.
     */
    private void guardarJugadorEnTexto(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (JugadorModelo jugador : jugadoresExistentes) {
                writer.write(jugador.toString());
                writer.newLine();
            }
            setMensajeGuardado("Jugador guardado en fichero de texto.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en texto: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de jugadores en un archivo binario.
     *
     * @param jugadoresExistentes Lista de jugadores a guardar.
     */
    private void guardarJugadorEnBinario(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.dat";

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaArchivo))) {
            for (JugadorModelo jugador : jugadoresExistentes) {
                dos.writeInt(jugador.getId());
                dos.writeUTF(jugador.getNick_name());
                dos.writeInt(jugador.getExperiencia());
                dos.writeInt(jugador.getNivel_vida());
                dos.writeInt(jugador.getMonedas());
            }
            setMensajeGuardado("Jugador guardado en fichero binario.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en binario: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de jugadores en un archivo de objetos.
     *
     * @param jugadoresExistentes Lista de jugadores a guardar.
     */
    private void guardarJugadorEnObjetos(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores_objetos.dat";

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            for (JugadorModelo jugador : jugadoresExistentes) {
                oos.writeObject(jugador);
            }
            setMensajeGuardado("Jugador guardado en fichero de objetos.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en objetos: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de jugadores en un archivo de acceso aleatorio.
     *
     * @param jugadoresExistentes Lista de jugadores a guardar.
     */
    private void guardarJugadorEnAccesoAleatorio(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores_acceso_aleatorio.dat";

        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            raf.setLength(0); // Limpiar el archivo antes de escribir
            for (JugadorModelo jugador : jugadoresExistentes) {
                raf.seek(raf.length()); // Ir al final del archivo
                raf.writeInt(jugador.getId());
                raf.writeUTF(jugador.getNick_name());
                raf.writeInt(jugador.getExperiencia());
                raf.writeInt(jugador.getNivel_vida());
                raf.writeInt(jugador.getMonedas());
            }
            setMensajeGuardado("Jugador guardado en fichero de acceso aleatorio.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en acceso aleatorio: " + e.getMessage());
        }
    }

    /**
     * Guarda la lista de jugadores en un archivo XML.
     *
     * @param jugadoresExistentes Lista de jugadores a guardar.
     */
    private void guardarJugadorEnXML(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.xml";

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("jugadores");
            doc.appendChild(rootElement);

            for (JugadorModelo jugador : jugadoresExistentes) {
                Element jugadorElement = doc.createElement("jugador");
                rootElement.appendChild(jugadorElement);

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(jugador.getId())));
                jugadorElement.appendChild(id);

                Element nick = doc.createElement("nick_name");
                nick.appendChild(doc.createTextNode(jugador.getNick_name()));
                jugadorElement.appendChild(nick);

                Element experience = doc.createElement("experiencia");
                experience.appendChild(doc.createTextNode(String.valueOf(jugador.getExperiencia())));
                jugadorElement.appendChild(experience);

                Element lifeLevel = doc.createElement("nivel_vida");
                lifeLevel.appendChild(doc.createTextNode(String.valueOf(jugador.getNivel_vida())));
                jugadorElement.appendChild(lifeLevel);

                Element coins = doc.createElement("monedas");
                coins.appendChild(doc.createTextNode(String.valueOf(jugador.getMonedas())));
                jugadorElement.appendChild(coins);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));

            transformer.transform(source, result);
            setMensajeGuardado("Jugador guardado en fichero XML.");
        } catch (Exception e) {
            setMensajeGuardado("Error al guardar el jugador en XML: " + e.getMessage());
        }
    }
}
