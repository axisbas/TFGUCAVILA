/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import baseDatos.BaseD;
import controlador.Controlador;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class HiloPeticionConexion extends Thread {

    private ObjectSocket socket = null;
    private Controlador controlador;
    private static int puntosIniciales = 1000;

    public HiloPeticionConexion(Socket sock) throws IOException {
        this.socket = new ObjectSocket(sock);
        controlador = Controlador.getInstancia();
    }

    private Mensaje procesaValidacion(Mensaje m) throws SQLException {
        String[] datos = m.splitDatos();
        Mensaje respuesta = new Mensaje(Mensaje.TipoMensaje.RespuestaLogin);
        if (controlador.existeUsuario(datos[0])) {
            respuesta.setDatos("no");
            respuesta.setMensaje("Usuario ya registrado");
        } else {
            int puntos = Registrador.getInstancia().validarUsuario(datos[0], datos[1]);
            if (puntos>0) {
                respuesta.setDatos("ok"+Mensaje.separador+puntos);
                respuesta.setMensaje(Controlador.getInstancia().getUsuarios().toString());
                controlador.anadirUsuario(datos[0], datos[1], puntos);
                HiloCliente hiloc = new HiloCliente(socket, controlador.getUsuario(datos[0]));
                hiloc.start();
            } else {
                respuesta.setDatos("no");
                respuesta.setMensaje("Usuario o password incorrectos");
            }
        }

        return respuesta;
    }

    private Mensaje procesaAlta(Mensaje m) throws SQLException {
        String[] datos = m.splitDatos();
        Mensaje respuesta = new Mensaje(Mensaje.TipoMensaje.RespuestaAlta);
        if (controlador.existeUsuario(datos[0]) || BaseD.getInstancia().existeUsuario(datos[0])) {
            respuesta.setDatos("no");
            respuesta.setMensaje("Usuario ya registrado");
        }
        else {
        
            if (Registrador.getInstancia().darAlta(datos[0], datos[1], datos[2], puntosIniciales)){
            Controlador.getInstancia().traceTexto("Inicio de registro...");
            respuesta.setDatos("ok"+Mensaje.separador+puntosIniciales);
            respuesta.setMensaje("Se ha completado el registro");
            Controlador.getInstancia().traceTexto("Se ha completado el registro");
            }
            else {
                respuesta.setMensaje("Error en los datos de registro");
            }
        }
        return respuesta;
    }

    @Override
    public void run() {
        try {
            Mensaje m = Mensaje.recibirMensaje(socket.getInput());
            Controlador.getInstancia().traceTexto(m.toString());
            if (m.getTipo() == Mensaje.TipoMensaje.Login) {
                m = procesaValidacion(m);

            } else {
                m = procesaAlta(m);
            }
            m.enviarMensaje(socket.getOutput());
            String [] datos= m.splitDatos();
            if ( datos[0].equals("ok") ) {
                Mensaje men = new Mensaje(Mensaje.TipoMensaje.RespuestaLogin, Sala.getInstancia().infoSala());
                men.enviarMensaje(socket.getOutput());
                Controlador.getInstancia().traceTexto("Enviada info de Sala");
            }


        } catch (Exception ex) {
            Controlador.getInstancia().traceTexto("HiloPeticionConexion.run: " + ex);
        }
    }
}
