/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import baseDatos.BaseD;
import controlador.Controlador;
import java.sql.SQLException;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class HiloCliente extends Thread{

private ObjectSocket socket = null;
private UsuarioRegistrado usuario = null;

    public HiloCliente(ObjectSocket sock, UsuarioRegistrado user) throws SQLException{
        this.socket = sock;
        this.usuario = user;
        user.setSocket(sock);
        Controlador.getInstancia().traceTexto("Se ha creado el Hilo Cliente para: "+user.getNombre());
    }

    @Override
    public void run(){
        while(socket!=null){
            procesarMensaje();
            
        }

    }

    public UsuarioRegistrado getUsuario() {
        return usuario;
    }

    public ObjectSocket getSocket() {
        return socket;
    }

    private void procesarMensaje(){
        try {
            Mensaje men = Mensaje.recibirMensaje(socket.getInput());
            Controlador.getInstancia().traceTexto("He recibido... "+men.toString());
            switch(men.getTipo()){
                
                case UnirAMesa ://mensajes de la partida
                    Sala.getInstancia().getMesa(Integer.parseInt(men.getDatos())).aceptarEnMesa(this);
                    break;
 
                case SentarseEnMesa :
                case SalirDeMesa :
                case SalirDePartida :
                case PasoApuesta:
                case PedirCarta:
                case TerminarMano:
                    Sala.getInstancia().getMesa(Integer.parseInt(men.getDatos())).pushMensaje(men, usuario.getNombre());
                    break;
                case Apuesta:
                    Controlador.getInstancia().traceTexto("Mesa: "+men.splitDatos()[0]+" Apuesta: "+men.splitDatos()[1]);
                    Sala.getInstancia().getMesa(Integer.parseInt(men.splitDatos()[0])).pushMensaje(men, usuario.getNombre());
                case AvisoDesconexionCliente:
                    Controlador.getInstancia().desconectaUsuarioRegistrado(usuario);
                    break;
                case MostrarRanking:
                    String ranking = BaseD.getInstancia().mostrarRanking();
                    Mensaje m = new Mensaje(Mensaje.TipoMensaje.RespuestaMostrarRanking, ranking);
                    m.enviarMensaje(socket.getOutput());
                default :
            }
        } catch (Exception ex) {
            Controlador.getInstancia().traceTexto("Error: "+ex);
            Controlador.getInstancia().traceTexto("Ha sido desconectado del servidor "+usuario.getNombre());
            Controlador.getInstancia().desconectaUsuarioRegistrado(usuario);
            socket = null;
        }
    }

}
