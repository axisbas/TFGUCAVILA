/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import controlador.Controlador;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alex
 */
public class HiloGestionMesa extends Thread{
    private Mesa mesa;



    public HiloGestionMesa (Mesa m){
        mesa = m;
        Controlador.getInstancia().traceTexto("Se ha creado el Hilo correspondiente a la Mesa: "+m.getNumeroMesa());
    }

    @Override
    public void run(){
         while (mesa!=null){
            try {
                mesa.crearPartida();
            } catch (SQLException ex) {
                Logger.getLogger(HiloGestionMesa.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
             Controlador.espera();

         }
    }

}
