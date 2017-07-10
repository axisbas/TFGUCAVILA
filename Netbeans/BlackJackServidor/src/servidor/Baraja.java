/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

import java.util.Vector;
import java.lang.Math.*;
import java.util.Collections;

/**
 *
 * @author alex
 */
public class Baraja {
    private static Baraja instancia = null;
    private Vector<Carta> cartas = new Vector<Carta>(52);
    private int indice =0;
    
    public Baraja(){
        rellenarPalo(Carta.Palo.treboles);
        rellenarPalo(Carta.Palo.picas);
        rellenarPalo(Carta.Palo.diamantes);
        rellenarPalo(Carta.Palo.corazones);
        barajar();

    }

    public  void barajar (){
        Collections.shuffle(this.cartas);
        indice = 0;
    }

    private void rellenarPalo(Carta.Palo palo){
        for(int i = 1;i<13;i++)
        this.cartas.add(new Carta(i,palo));
    }

    public Carta getCarta(){

        if (indice>=cartas.size()){
            barajar();
        }
        return this.cartas.get(indice++);
    }

}
