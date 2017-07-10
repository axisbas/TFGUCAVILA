/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

/**
 *
 * @author alex
 */
public class Asiento {
    private int estado;
    private int turno;
    private Jugador jugador;
    private Mesa mesa;
    
    public Asiento(int _posicion){
        this.estado = 1;
        this.turno = _posicion;
        this.jugador = null;
        this.mesa = null;
    }

    public int isEstado() {
        return estado;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    public Mesa getMesa() {
        return this.mesa;
    }

    public int getTurno() {
        return this.turno;
    }

    public void setEstado(int _estado) {
        this.estado = _estado;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
    
    
}
