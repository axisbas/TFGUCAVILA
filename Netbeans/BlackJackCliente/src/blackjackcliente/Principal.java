/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blackjackcliente;

import interfaz.InfoSala;
import interfaz.Portada;
import interfaz.Sala;

/**
 *
 * @author alex
 */
public class Principal {
    public static String host = "localhost";
    public static int puerto = 7777;
    public static String username = "";
    public static int puntos = 0;
    public static int timeOut = 30000;


    public static void setHost(String host) {
        Principal.host = host;
    }

    public static String getHost() {
        return host;
    }




    public static void main(String []args){
        InfoSala info = Portada.muestraPortada();
        if (info != null){

            Sala s = new Sala();
            s.setInfoSala(info);//Pasar s en todos los Sala.getInstancia()
            //Sala.getInstancia().setInfoSala(info);
            /*
                                            * Al no ser en modo modal continua ejecutando el código
                                            * que va después de s.setVisible(true)
             */
            new HiloOyente(info.getSocket(),s).start();
            //Sala.getInstancia().setVisible(true);
            s.setVisible(true);
        }        
    }

}
