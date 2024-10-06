/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.Serializable;

public class JugadorModelo implements Serializable {
    private int id;
    private String nick_name;
    private int experience;
    private int life_level;
    private int coins;
    
    public JugadorModelo() {
    }

    public JugadorModelo(int id, String nick_name, int experience, int life_level, int coins) {
        this.id = id;
        this.nick_name = nick_name;
        this.experience = experience;
        this.life_level = life_level;
        this.coins = coins;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLife_level() {
        return life_level;
    }

    public void setLife_level(int life_level) {
        this.life_level = life_level;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    @Override
    public String toString() {
        return "[USER_ID = " + id + ", NICK_NAME = " + nick_name + ", EXPERIENCE = " + experience
                + ", LIFE_LEVEL = " + life_level + ", COINS = " + coins + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + this.id;
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

