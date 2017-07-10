/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import baseDatos.BaseD;
import controlador.Controlador;
import java.sql.SQLException;
import java.util.ArrayList;
import mensaje.Mensaje;

/**
 *
 * @author alex
 */
public class Mesa {

    public static int maxJugadores = 3;
    private int numeroMesa;
    private ArrayList<Jugador> jugadores = new ArrayList();
    private Crupier crupier = new Crupier();
    private Partida partida;
    private int numJugadores = 0;
    private ArrayList<Mensaje> mensajes = new ArrayList();
    private int contadorPartida=0;

    public Jugador getJugador(String nombre) {
        for (Jugador j : jugadores) {
            if (j.getUsuarioRegistrado().getNombre().equals(nombre)) {
                return j;
            }
        }
        return null;
    }

    Mesa(int numeroMesa) {
        partida = new Partida(this);
        this.numeroMesa = numeroMesa;

    }

    private String nombresJugadores() {
        String jugadoresActivos = "";
        for (Jugador j : this.getJugadoresActivos()) {
            jugadoresActivos += j.getUsuarioRegistrado().getNombre() + Mensaje.separador;
        }
        return jugadoresActivos;
    }

    public void pushMensaje(Mensaje men, String nombre) {
        men.setDatos(nombre + Mensaje.separador + men.getDatos());
        mensajes.add(men);
    }

    public void quitarJugador(Jugador j) {
        jugadores.remove(j);
        j.getUsuarioRegistrado().setJugador(null);
    }

    /**
     * @return Jugadores activos de la mesa.
     */
    public ArrayList<Jugador> getJugadoresActivos() {
        ArrayList<Jugador> jugadoresactivos = new ArrayList();
        for (Jugador j : jugadores) {
            if (!j.isMiron()) {
                jugadoresactivos.add(j);
            }
        }
        return jugadoresactivos;
    }

    public Crupier getCrupier() {
        return crupier;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public boolean hayMensajes() {
        return !this.mensajes.isEmpty();
    }

    public Mensaje peekMensaje(Mensaje.TipoMensaje tipo) {
        if (!hayMensajes()) {//No hay ningún mensaje en la cola de mensajes
            return null;
        } else {
            for (Mensaje men : mensajes) {
                if (men.getTipo() == tipo) {
                    return men;
                }
            }
            return null;//No hay ningún mensaje de ese tipo
        }
    }

    public Mensaje popMensaje() {
        if (mensajes.isEmpty()) {
            return null;
        } else {
            return mensajes.remove(0);
        }
    }

    public Mensaje popMensaje(Mensaje.TipoMensaje tipo) {
        for (Mensaje m : mensajes) {
            if (m.getTipo() == tipo) {
                mensajes.remove(m);
                return m;
            }
        }
        return null;
    }

    public ArrayList<Mano> getManos() {
        ArrayList<Mano> manos = new ArrayList();
        for (Jugador j : jugadores) {
            if (!j.isMiron()) {
                manos.add(j.getMano());
            }
        }
        return manos;
    }

    public int apuestasAcumuladas() {
        int sumaApuesta = 0;
        for (Jugador j : jugadores) {
            sumaApuesta += j.getApuesta();
        }
        return sumaApuesta;
    }

    public void aceptarEnMesa(HiloCliente hiloc) {
        Jugador j = new Jugador(this, hiloc.getUsuario());
        hiloc.getUsuario().setJugador(j);
        this.getJugadores().add(j);
        j.setMiron(true);
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.RespuestaUnirAMesa, infoMesa());
        m.enviarMensaje(hiloc.getSocket().getOutput());
    }

    public String infoMesa() {//    nº mesa=nºjugadores=nombre-nºcarta-palo-nºcarta2-palo2-...=nombre2...-.../
        ArrayList <Jugador>j = new ArrayList();
        j.add(0,crupier);
        j.addAll(this.getJugadoresActivos());       
        String estado = this.getNumeroMesa()+"="+j.size()+"=";
        for (Jugador ju: j){
            estado += ju.getNombre()+"-"+ju.getMano().toString()+"=";
        }
        estado+=Mensaje.separador;
        return estado;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setPartida(Partida partida) {
        this.partida = partida;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public int getNumeroMesa() {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa) {
        this.numeroMesa = numeroMesa;
    }

    public void procesarMensajesCola() {
        Mensaje men = null;
        while ((men = this.popMensaje()) != null) {
            switch (men.getTipo()) {
                case SentarseEnMesa:
                    Jugador j = getJugador(men.splitDatos()[0]);
                    if (this.getJugadoresActivos().size() < 3) {
                        
                        j.setMiron(false);
                        Controlador.getInstancia().traceTexto("Jugador " + j.getUsuarioRegistrado().getNombre() + " aceptado en la mesa: " + this.getNumeroMesa());
                        men.setMensaje("Aceptado en la mesa");
                    }
                    else{
                        men.setMensaje("Actualmente la mesa esta completa");
                    }
                    men.setTipo(Mensaje.TipoMensaje.RespuestaSentarseEnMesa);
                    men.enviarMensaje(j.getUsuarioRegistrado().getSocket().getOutput());

                    Controlador.getInstancia().traceTexto("Enviando mensaje de respuesta para sentarse a mesa");
                    break;
                case SalirDeMesa:
                    Jugador ju = getJugador(men.splitDatos()[0]);
                    this.quitarJugador(ju);

                    break;
                default:
            }
        }
    }

    public void crearPartida() throws SQLException  {
        procesarMensajesCola();
        if (this.getJugadoresActivos().size() != 0) {
            this.contadorPartida++;
            Controlador.getInstancia().traceTexto("Se ha iniciado la partida correspondiente a la mesa: " + this.numeroMesa);
            partida.inicia(); //Desarrollo de toda la partida.
            BaseD.getInstancia().registrarPartida(this.numeroMesa, this.contadorPartida, this.getJugadoresActivos(), partida.getGanadores());
            Controlador.espera(Controlador.TiempoEsperaPartidas);
            
        }

    }

    public void reiniciar() {
        numJugadores = 0;
        partida = null;
    }
}
