/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import baseDatos.BaseD;
import java.sql.SQLException;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class UsuarioRegistrado {

    private String nombre;
    private String password;
    private String email;
    private int puntos;
    private Jugador jugador = null;
    private ObjectSocket socket;

    public UsuarioRegistrado(String nombre, String password, int puntos) {
        this.nombre = nombre;
        this.password = password;
        this.puntos = puntos;

    }

    public ObjectSocket getSocket() {
        return socket;
    }

    public void setSocket(ObjectSocket socket) {
        this.socket = socket;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (this.nombre != null ? this.nombre.hashCode() : 0);
        hash = 19 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object usu) {
        return this.hashCode() == usu.hashCode();
    }

}
