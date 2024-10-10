/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Controlador.JugadorControlador;
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

public class GuardarJugadores2 {

    private String mensajeGuardado;
    private final String RUTA_GUARDADO = new InicializadorFicheros().getRuta_Guardados();

    public void setMensajeGuardado(String mensajeGuardado) {
        this.mensajeGuardado = mensajeGuardado;
    }

    public String getMensajeGuardado() {
        return mensajeGuardado;
    }

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
        }
    }

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

    private void guardarJugadorEnBinario(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores.dat";

        //if (!jugadoresExistentes.contains(jugador)) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(rutaArchivo))) {
            for (JugadorModelo jugador : jugadoresExistentes) {
                dos.writeInt(jugador.getId());
                dos.writeUTF(jugador.getNick_name());
                dos.writeInt(jugador.getExperience());
                dos.writeInt(jugador.getLife_level());
                dos.writeInt(jugador.getCoins());
            }
            setMensajeGuardado("Jugador guardado en fichero binario.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en binario: " + e.getMessage());
        }
        //}
    }

    private void guardarJugadorEnObjetos(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores_objetos.dat";

        //if (!jugadoresExistentes.contains(jugador)) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            for (JugadorModelo jugador : jugadoresExistentes) {
                oos.writeObject(jugador);
            }
            setMensajeGuardado("Jugador guardado en fichero de objetos.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en objetos: " + e.getMessage());
        }
        //}
    }

    private void guardarJugadorEnAccesoAleatorio(List<JugadorModelo> jugadoresExistentes) {
        String rutaArchivo = RUTA_GUARDADO + "/jugadores_acceso_aleatorio.dat";

        //if (!jugadoresExistentes.contains(jugador)) {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "rw")) {
            raf.setLength(0);
            for (JugadorModelo jugador : jugadoresExistentes) {
                raf.seek(raf.length()); // Ir al final del archivo
                raf.writeInt(jugador.getId());
                raf.writeUTF(jugador.getNick_name());
                raf.writeInt(jugador.getExperience());
                raf.writeInt(jugador.getLife_level());
                raf.writeInt(jugador.getCoins());
            }
            setMensajeGuardado("Jugador guardado en fichero de acceso aleatorio.");
        } catch (IOException e) {
            setMensajeGuardado("Error al guardar el jugador en acceso aleatorio: " + e.getMessage());
        }
        //}
    }

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

                Element experience = doc.createElement("experience");
                experience.appendChild(doc.createTextNode(String.valueOf(jugador.getExperience())));
                jugadorElement.appendChild(experience);

                Element lifeLevel = doc.createElement("life_level");
                lifeLevel.appendChild(doc.createTextNode(String.valueOf(jugador.getLife_level())));
                jugadorElement.appendChild(lifeLevel);

                Element coins = doc.createElement("coins");
                coins.appendChild(doc.createTextNode(String.valueOf(jugador.getCoins())));
                jugadorElement.appendChild(coins);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(rutaArchivo));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setMensajeGuardado("Jugador guardado en fichero XML.");
    }

}
