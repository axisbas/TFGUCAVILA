/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;

/**
 *
 * @author alex
 */
public class Hilo extends Thread {

    private Socket socket;
    private UsuarioRegistrado usuario;
    private Jugador jugador;
    private Mesa mesa;
    private Partida partida;
    private boolean br = true;
    private BufferedReader canalEntrada;
    private DataOutputStream canalSalida;

    public Hilo(Socket _socket) {
        this.socket = _socket;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Partida getPartida() {
        return partida;
    }

    public Socket getSocket() {
        return socket;
    }

    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public void setUsuario(UsuarioRegistrado usuario) {
        this.usuario = usuario;
    }
}


