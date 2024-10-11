
package Modelo;

import java.io.Serializable;
import java.util.Objects;

public class JugadorModelo implements Serializable {
    private int id;
    private String nick_name;
    private int experiencia;
    private int nivel_vida;
    private int monedas;
    
    public JugadorModelo() {
    }

    public JugadorModelo(int id, String nick_name, int experiencia, int nivel_vida, int monedas) {
        this.id = id;
        this.nick_name = nick_name;
        this.experiencia = experiencia;
        this.nivel_vida = nivel_vida;
        this.monedas = monedas;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public int getNivel_vida() {
        return nivel_vida;
    }

    public void setNivel_vida(int nivel_vida) {
        this.nivel_vida = nivel_vida;
    }

    public int getMonedas() {
        return monedas;
    }

    public void setMonedas(int monedas) {
        this.monedas = monedas;
    }

    @Override
    public String toString() {
        return "[USER_ID = " + id + ", NOMBRE = " + nick_name + ", EXPERIENCIA = " + experiencia
                + ", NIVEL_VIDA = " + nivel_vida + ", MONEDAS = " + monedas + "]";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.nick_name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JugadorModelo other = (JugadorModelo) obj;
        return this.id == other.id;
    }

   
    
}

