/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blackjackcliente;

import interfaz.Sala;
import javax.swing.JOptionPane;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
class HiloOyente extends Thread {

    private ObjectSocket socket;
    private Sala sala;

    public HiloOyente(ObjectSocket socket, Sala sala) {
        this.socket = socket;
        this.sala=sala;
    }

    private void procesarMensaje() throws Exception {
        System.out.println("Esperando mensaje de servidor");
        Mensaje men = Mensaje.recibirMensaje(socket.getInput());
        System.out.println("Recibido: " + men.getTipo().toString());
        //Sala s = new Sala();
        switch (men.getTipo()) {

            case AvisoDesconexionCliente:
                break;
            case MostrarCarta:
                System.out.println(men.getDatos());
                String[] datos = men.splitDatos();
                sala.getComponenteMesa().setCarta(datos[0], Integer.parseInt(datos[1]), datos[2]);
                //Sala.getInstancia().getComponenteMesa().setCarta(datos[0], Integer.parseInt(datos[1]), datos[2]);
                break;
            case RespuestaUnirAMesa:
                sala.unirAMesa(men.getDatos());
                //Sala.getInstancia().unirAMesa(men.getDatos());
                break;
            case FinPartida:
                sala.finPartida(men.getMensaje());
                sala.getDialogMesa().muestraMensaje("La partida ha finalizado");
                //Sala.getInstancia().finPartida(men.getMensaje());
                break;
            case AvisoApuesta:
                sala.getDialogMesa().avisarApuesta();
                sala.getDialogMesa().muestraMensaje("APUESTA!!. El límite es: "+Principal.puntos);
                //Sala.getInstancia().getDialogMesa().avisarApuesta();
                break;
            case AvisoVictoria:
                //Sala.getInstancia().getDialogMesa().muestraMensaje(men.getMensaje());
                sala.getDialogMesa().muestraMensaje(men.getMensaje());
                break;
            case AvisoTurno:
                if (men.getDatos().equals(sala.getNombreJugador())){
                sala.getDialogMesa().avisoTurno();
                }
                sala.getDialogMesa().muestraMensaje(men.getMensaje());

                //Sala.getInstancia().getDialogMesa().avisoTurno();
                break;
            case AvisoFinTurno:
                sala.getDialogMesa().avisoFinTurno();
                sala.getDialogMesa().muestraMensaje("Turno finalizado");
                //Sala.getInstancia().getDialogMesa().avisoFinTurno();
                break;
            case RespuestaSentarseEnMesa:
                sala.respuestaSentarseEnMesa(men.getMensaje());
                //Sala.getInstancia().respuestaSentarseEnMesa(men.getMensaje());
                break;
            case InicioPartida:
                sala.iniciaPartida(men.getDatos());
                sala.getDialogMesa().muestraMensaje("Comienza la partida...");
                //Sala.getInstancia().iniciaPartida(men.getDatos());
                break;
            case RespuestaMostrarRanking:
                sala.escribirRanking(men.getDatos());
                break;
            case EnviarPuntuaciones:
                Principal.puntos = Integer.parseInt(men.getDatos());
                break;
            default:
        }

    }

    @Override
    public void run() {
        while (socket != null) {
            try {
                System.out.println("Ejecutando HiloOyente");
                procesarMensaje();
                System.out.println(socket == null ? "SI" : "NO");
                /*
                 * Todos los mensajes que puede recibir del servidor desde que está en la Sala
                 */

            } catch (Exception ex) {
                socket = null;
                JOptionPane.showMessageDialog(null, "Se ha desconectado del servidor: " + ex.toString());
            }
        }
    }
}
