/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.util.ArrayList;
/**
 *
 * @author alex
 */
public class Sala{
    private static Sala sala = null;
    private ArrayList<Mesa> mesas = new ArrayList<Mesa>();

    private Sala(int numMesas){
        for (int i=0; i<numMesas;i++){
            Mesa m = new Mesa(i);
            mesas.add(m);
            HiloGestionMesa hilog = new HiloGestionMesa(m);
            hilog.start();
        }
    }

    private Sala (){
        this(4);
    }
    public String infoSala (){
        String infoSala ="";
        for (Mesa m: mesas){
            infoSala += m.infoMesa();
        }
        return infoSala;
    }

    public static Sala getInstancia(){
        if (sala == null)
            sala = new Sala();
        return sala;
    }

    public Mesa getMesa(int numero) {
        return mesas.get(numero);
    }

}
