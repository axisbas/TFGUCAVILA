/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaz;

import java.util.ArrayList;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class InfoSala {
    private ObjectSocket socket;
    private Mensaje mensaje;
    private String infoSala;
    private String nombreJugador;

    
    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }



    public ObjectSocket getSocket() {
        return socket;
    }

    public void setSocket(ObjectSocket socket) {
        this.socket = socket;
    }

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public String getInfoSala() {
        return infoSala;
    }

    public void setInfoSala(String infoSala) {
        this.infoSala = infoSala;
    }

    public ArrayList <InfoMesa> getInfoMesas(){
        ArrayList <InfoMesa> im = new ArrayList();
        String datos [] = infoSala.split(Mensaje.separador);
        System.out.println("El array datos es: "+infoSala);
        for (int i=0; i<datos.length; i++){
            System.out.println(datos[i]);
            InfoMesa  info = new InfoMesa(datos[i]);
            im.add(info);
        }
        return im;
    }    
}
