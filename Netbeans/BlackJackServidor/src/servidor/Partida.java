/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import baseDatos.BaseD;
import controlador.Controlador;
import java.util.ArrayList;
import java.util.Collections;
import mensaje.Mensaje;

/**
 *
 * @author alex
 */
public class Partida {

    private Mesa mesa;
    private Baraja baraja;
    private ArrayList<Jugador> jugadores;
    private ArrayList<Jugador> ganadores=new ArrayList();
    

    public Partida(Mesa mesa) {
        baraja = new Baraja();
        this.mesa = mesa;
    }

    public ArrayList<Jugador> getGanadores() {
        return ganadores;
    }





    private void avisarRepartoCarta(Jugador j, Carta c) {
        String nombre;
        if (j.getUsuarioRegistrado() == null) {
            nombre = "crupier";
        } else {
            nombre = j.getUsuarioRegistrado().getNombre();
        }
        Mensaje men = new Mensaje(Mensaje.TipoMensaje.MostrarCarta);
        men.setDatos(nombre + Mensaje.separador + c.getNumero() + Mensaje.separador + c.getPalo().name());
        enviarMensajeJugadoresMesa(men);

    }

    private void resetearManos() {
        for (Jugador j : jugadores) {
            j.resetearMano();

        }
    }

    public void recibirApuestas() {
        int count = 0;
        while (count < jugadores.size()) {

            Mensaje m = this.mesa.popMensaje();
            if (m != null) {
                String dato[] = m.splitDatos();
                Jugador j = mesa.getJugador(dato[0]);
                switch (m.getTipo()) {
                    case AvisoDesconexionCliente:
                        if (j != null) {
                            if (j.isMiron() != true) {
                                jugadores.remove(j);
                            }
                            mesa.quitarJugador(j);
                        }
                        
                        break;
                    case Apuesta:
                        //Restar los puntos de su total de puntos.
                        int apuesta = Integer.parseInt(dato[2]);
                        
                        Controlador.getInstancia().traceTexto("El jugador: " + j.getUsuarioRegistrado().getNombre() + " apuesta " + apuesta);
                        j.setApuesta(apuesta);
                        j.getUsuarioRegistrado().setPuntos(j.getUsuarioRegistrado().getPuntos() - apuesta);
                        Controlador.getInstancia().traceTexto("Apuesta ="+j.getApuesta()+" Puntos "+j.getUsuarioRegistrado().getPuntos());
                        BaseD.getInstancia().modificarPuntos(j.getUsuarioRegistrado().getPuntos(), j.getUsuarioRegistrado().getNombre());
                        count++;
                        break;
                    case PasoApuesta:
                        //Terminar su mano y esperar a mano siguiente.
                        j.setPlantado(true);
                        j.setApuesta(0);
                        count++;
                        break;
                    default:
                }
            }

            Controlador.espera();
        }
        Controlador.getInstancia().traceTexto("Todas las apuestas han sido realizadas");
    }

    private int getTotalApuestas() {
        int total = 0;
        for (Jugador j : jugadores) {
            total += j.getApuesta();
        }
        return total;
    }

    public void evaluarGanador() {
        int valorMaximo = 0;
        int valorMano = 0;
        ganadores.clear();
        // Se calcula el tanteo máximo
        for (Jugador j : jugadores) {
            if (!j.isPlantado()) {
                valorMano = j.getMano().valorMano();
                Controlador.getInstancia().traceTexto("El jugador: " + j.getUsuarioRegistrado().getNombre() + " tiene " + valorMano);
                if (valorMano >= valorMaximo && valorMano < Mano.TanteoMaximo) {
                    valorMaximo = valorMano;
                }
            }
        }
        if ((mesa.getCrupier().getMano().valorMano() == Mano.TanteoMaximo)) {
            Controlador.getInstancia().traceTexto("El Crupier ha obtenido BlackJack");
        } else {
            ArrayList<Jugador> blackJack = new ArrayList();
            for (Jugador j : jugadores) {
                if (j.getMano().valorMano() == Mano.TanteoMaximo) {
                    blackJack.add(j);
                }
            }
            if (!blackJack.isEmpty()) {
                int puntos = (getTotalApuestas()+getTotalApuestas()/jugadores.size()+200) / blackJack.size();
                for (Jugador j : blackJack) {
                    Mensaje mens = new Mensaje(Mensaje.TipoMensaje.AvisoVictoria);
                    mens.setMensaje("El jugador " + j.getUsuarioRegistrado().getNombre() + " gana");
                    mens.setDatos(j.getUsuarioRegistrado().getNombre());
                    enviarMensajeJugadoresMesa(mens);
                    j.getUsuarioRegistrado().setPuntos(puntos + j.getUsuarioRegistrado().getPuntos());
                    int puntosJug = j.getUsuarioRegistrado().getPuntos();
                    BaseD.getInstancia().modificarPuntos(puntosJug, j.getUsuarioRegistrado().getNombre());
                }
            } else {
                if (mesa.getCrupier().getMano().valorMano() >= valorMaximo && mesa.getCrupier().getMano().valorMano() < Mano.TanteoMaximo) {
                    Controlador.getInstancia().traceTexto("El Crupier gana con un " + mesa.getCrupier().getMano().valorMano());
                } else {
                    for (Jugador j : jugadores) {
                        if (j.getMano().valorMano() == valorMaximo) {
                            ganadores.add(j);
                        }
                    }
                    if (ganadores.size()==0){
                        Controlador.getInstancia().traceTexto("El Crupier gana con un " + mesa.getCrupier().getMano().valorMano());
                    }
                    else{
                    int punt = (getTotalApuestas()+getTotalApuestas()/jugadores.size()+100) / ganadores.size();
                    for (Jugador j : ganadores) {
                        Mensaje mens = new Mensaje(Mensaje.TipoMensaje.AvisoVictoria);
                        Controlador.getInstancia().traceTexto("Ganadores = "+ganadores.size()+" Puntos: "+getTotalApuestas());
                        mens.setMensaje("El jugador " + j.getUsuarioRegistrado().getNombre() + " gana " + punt + " puntos");
                        mens.setDatos(j.getUsuarioRegistrado().getNombre());
                        enviarMensajeJugadoresMesa(mens);
                        Controlador.getInstancia().traceTexto("El jugador gana "+punt+" puntos");
                        Controlador.getInstancia().traceTexto("Total apuestas: "+getTotalApuestas());
                        j.getUsuarioRegistrado().setPuntos(punt + j.getUsuarioRegistrado().getPuntos());
                        int puntosJugador = j.getUsuarioRegistrado().getPuntos();
                        BaseD.getInstancia().modificarPuntos(puntosJugador, j.getUsuarioRegistrado().getNombre());
                        }
                    }
                }
            }
        }

    }

    private void enviarMensajeJugadoresMesa(Mensaje m) {
        for (Jugador j : mesa.getJugadores()) {
            m.enviarMensaje(j.getUsuarioRegistrado().getSocket().getOutput());
        }
    }

    private void enviarMensajeJugadoresActivos(Mensaje m) {
        for (Jugador j : jugadores) {
            m.enviarMensaje(j.getUsuarioRegistrado().getSocket().getOutput());
        }
    }

    public void mostrarOrdenJugadores() {
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.InicioPartida, mesa.infoMesa());
        enviarMensajeJugadoresMesa(m);
    }

    public void inicia() {
        mesa.procesarMensajesCola();
        jugadores = mesa.getJugadoresActivos();
        Collections.shuffle(jugadores);
        resetearManos();
        mostrarOrdenJugadores();
        repartirPrimeraCarta();
        Controlador.getInstancia().traceTexto("Jugadores activos para esta partida: " + jugadores.size());
        for (Jugador j : jugadores) {
            System.out.println("Nombre : " + j.getNombre() + " Cartas:" + j.getMano().toString());
        }
        avisarApuesta();
        recibirApuestas();
        tratamientoJugadores();
        tratamientoCrupier();
        finPartida();
        evaluarGanador();
        enviarPuntuaciones();

    }

    public void avisarApuesta() {
        Mensaje men = new Mensaje(Mensaje.TipoMensaje.AvisoApuesta);
        men.setMensaje("Turno de apuesta");
        enviarMensajeJugadoresActivos(men);
    }

    public void finPartida() {
        Mensaje men = new Mensaje(Mensaje.TipoMensaje.FinPartida);
        men.setMensaje("La partida ha finalizado");
        enviarMensajeJugadoresMesa(men);
    }

    public void tratamientoCrupier() {
        boolean fin = false;
        Crupier crupier = mesa.getCrupier();
        crupier.getMano().resetear();
        while (!fin) {
            Controlador.espera(2000);
            Carta c = baraja.getCarta();
            crupier.getMano().anadeCarta(c);
            avisarRepartoCarta(crupier, c);
            Controlador.getInstancia().traceTexto("El valor de la mano del Crupier es: " + (crupier.getMano().valorMano()) + " y tiene : " + crupier.getMano().getCartas().size() + " cartas");
            if (crupier.getMano().valorMano() > 15) {
                fin = true;
            }
        }
    }

    public void tratamientoJugadores() {
        for (Jugador j : jugadores) {
            if (j.isPlantado() != true) {
                Controlador.getInstancia().traceTexto("Turno de: " + j.getUsuarioRegistrado().getNombre());
                Mensaje men = new Mensaje(Mensaje.TipoMensaje.AvisoTurno);
                men.setDatos(j.getNombre());
                men.setMensaje("Turno de jugada para:  " + j.getUsuarioRegistrado().getNombre());
                enviarMensajeJugadoresMesa(men);
                jugarMano(j);
            }
        }
    }

    //Del mismo modo que el anterior
    public void jugarMano(Jugador j) {
        boolean fin = false;

        while (!fin) {
            if (this.mesa.popMensaje(Mensaje.TipoMensaje.AvisoDesconexionCliente) != null) {
                mesa.quitarJugador(j);
                j.setPlantado(true);
                fin = true;
            } else {
                Mensaje m = this.mesa.popMensaje();
                if (m != null) {
                    switch (m.getTipo()) {
                        case PedirCarta:
                            Controlador.getInstancia().traceTexto("Pedir Carta dentro de partida");
                            Carta c = baraja.getCarta();
                            j.getMano().anadeCarta(c);
                            avisarRepartoCarta(j, c);
                            Controlador.getInstancia().traceTexto("Aviso reparto carta");
                            if (j.getMano().valorMano() >= Mano.TanteoMaximo) {
                                fin = true;
                            }
                            break;
                        case TerminarMano:
                            //Se pasa el turno del jugador
                            fin = true;
                            break;
                        default:
                    }
                }
            }
            Controlador.espera(5000);

        }
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.AvisoFinTurno);
        m.enviarMensaje(j.getUsuarioRegistrado().getSocket().getOutput());
    }

    public void repartirPrimeraCarta() {
        for (Jugador j : jugadores) {
            if (!j.getNombre().equals("crupier")) {
                Carta c = baraja.getCarta();
                j.getMano().anadeCarta(c);
                avisarRepartoCarta(j, c);
            }
        }
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    private void enviarPuntuaciones() {
        Mensaje men = new Mensaje(Mensaje.TipoMensaje.EnviarPuntuaciones);
        for(Jugador j: jugadores){
            men.setDatos(""+j.getUsuarioRegistrado().getPuntos());
            Controlador.getInstancia().traceTexto("Los puntos del usuario son: "+j.getUsuarioRegistrado().getPuntos());
            men.enviarMensaje(j.getUsuarioRegistrado().getSocket().getOutput());
        }
    }
}






