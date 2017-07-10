/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import controlador.Controlador;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author alex
 */
public class HiloEscucha extends Thread{

    private ServerSocket socket;

    public HiloEscucha(ServerSocket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        if (socket!=null){
        Controlador.getInstancia().introducirTexto("El servidor está a la escucha........");
        }
        else{
            Controlador.getInstancia().introducirTexto("El servidor no está a la escucha");
        }
        while (socket!=null) {
            try {
                Socket sock = socket.accept();
                HiloPeticionConexion peticion = new HiloPeticionConexion(sock);
                Controlador.getInstancia().introducirTexto("Servidor acepta la conexion");
                peticion.start();
            } catch (IOException ex) {
                socket=null;
                Controlador.getInstancia().introducirTexto("**"+ex);
            }
        }
    }
}
