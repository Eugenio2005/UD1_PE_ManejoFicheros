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
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class GuardarJugadores {

    private String mensajeGuardado;
    private final LeerFicheros leeFicheros = new LeerFicheros();
    private final String RUTA_GUARDADO = new InicializadorFicheros().getRuta_Guardados();

    public void setMensajeGuardado(String mensajeGuardado) {
        this.mensajeGuardado = mensajeGuardado;
    }

    public String getMensajeGuardado() {
        return mensajeGuardado;
    }

    public void guardarJugador(JugadorModelo jugador, String tipoAlmacenamiento){
        leeFicheros.cargarJugadores(tipoAlmacenamiento);
        List<JugadorModelo> jugadores = leeFicheros.getJugadores();

        switch (tipoAlmacenamiento) {
            case "texto":
                guardarJugadorEnTexto(jugadores, jugador);
                break;
            case "binario":
                guardarJugadorEnBinario(jugadores, jugador);
                break;
            case "objetos":
                guardarJugadorEnObjetos(jugadores, jugador);
                break;
            case "accesoAleatorio":
                guardarJugadorEnAccesoAleatorio(jugadores, jugador);
                break;
            case "xml":
                guardarJugadorEnXML(jugadores, jugador);
                break;
        }
    }

    private void guardarJugadorEnTexto(List<JugadorModelo> jugadoresExistentes, JugadorModelo jugador) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.txt";

        //if (!jugadoresExistentes.contains(jugador)) {
        
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
                for (JugadorModelo jugador1 : jugadoresExistentes) {
                writer.write(jugador1.toString());
                writer.newLine();
                }
                setMensajeGuardado("Jugador guardado en fichero de texto.");
            } catch (IOException e) {
                setMensajeGuardado("Error al guardar el jugador en texto: " + e.getMessage());
            }
        

        //}
    }

    private void guardarJugadorEnBinario(List<JugadorModelo> jugadoresExistentes, JugadorModelo jugador) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.dat";

        //if (!jugadoresExistentes.contains(jugador)) {
            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaArchivo, true))) {
                dos.writeInt(jugador.getId());
                dos.writeUTF(jugador.getNick_name());
                dos.writeInt(jugador.getExperience());
                dos.writeInt(jugador.getLife_level());
                dos.writeInt(jugador.getCoins());
                setMensajeGuardado("Jugador guardado en fichero binario.");
            } catch (IOException e) {
                setMensajeGuardado("Error al guardar el jugador en binario: " + e.getMessage());
            }
        //}
    }

    private void guardarJugadorEnObjetos(List<JugadorModelo> jugadoresExistentes, JugadorModelo jugador) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores_objetos.dat";

        //if (!jugadoresExistentes.contains(jugador)) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, true))) {
                oos.writeObject(jugador);
                setMensajeGuardado("Jugador guardado en fichero de objetos.");
            } catch (IOException e) {
                setMensajeGuardado("Error al guardar el jugador en objetos: " + e.getMessage());
            }
        //}
    }

    private void guardarJugadorEnAccesoAleatorio(List<JugadorModelo> jugadoresExistentes, JugadorModelo jugador) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores_acceso_aleatorio.dat";

        //if (!jugadoresExistentes.contains(jugador)) {
            try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
                raf.seek(raf.length()); // Ir al final del archivo
                raf.writeInt(jugador.getId());
                raf.writeUTF(jugador.getNick_name());
                raf.writeInt(jugador.getExperience());
                raf.writeInt(jugador.getLife_level());
                raf.writeInt(jugador.getCoins());
                setMensajeGuardado("Jugador guardado en fichero de acceso aleatorio.");
            } catch (IOException e) {
                setMensajeGuardado("Error al guardar el jugador en acceso aleatorio: " + e.getMessage());
            }
        //}
    }

    private void guardarJugadorEnXML(List<JugadorModelo> jugadoresExistentes, JugadorModelo jugador) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.xml";
        
        //if (!jugadoresExistentes.contains(jugador)) {
            try {
                // Creación o actualización del fichero XML
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc;

                File archivoXML = new File(rutaArchivo);
                if (archivoXML.exists()) {
                    doc = dBuilder.parse(archivoXML);
                    doc.getDocumentElement().normalize();
                } else {
                    doc = dBuilder.newDocument();
                    Element rootElement = doc.createElement("jugadores");
                    doc.appendChild(rootElement);
                }

                Element root = doc.getDocumentElement();
                Element jugadorElement = doc.createElement("jugador");

                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(String.valueOf(jugador.getId())));
                jugadorElement.appendChild(id);

                Element nick = doc.createElement("nick_name");
                nick.appendChild(doc.createTextNode(jugador.getNick_name()));
                jugadorElement.appendChild(nick);

                Element experience = doc.createElement("experience");
                experience.appendChild(doc.createTextNode(String.valueOf(jugador.getExperience())));
                jugadorElement.appendChild(experience);

                Element lifeLevel = doc.createElement("life_level");
                lifeLevel.appendChild(doc.createTextNode(String.valueOf(jugador.getLife_level())));
                jugadorElement.appendChild(lifeLevel);

                Element coins = doc.createElement("coins");
                coins.appendChild(doc.createTextNode(String.valueOf(jugador.getCoins())));
                jugadorElement.appendChild(coins);

                root.appendChild(jugadorElement);

                // Guardar el documento
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(archivoXML);
                transformer.transform(source, result);

                //setMensajeGuardado("Jugador guardado en fichero XML.");
            } catch (Exception e) {
                setMensajeGuardado("Error al guardar el jugador en XML: " + e.getMessage());
            }
            setMensajeGuardado("Jugador guardado en fichero XML.");
        //}
    }
}
