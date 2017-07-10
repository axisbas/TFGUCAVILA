/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servidor;

/**
 *
 * @author alex
 */
public class Carta {
    public enum Palo {diamantes, treboles, picas, corazones};
    private int numero;
    private Palo palo;

    public int getValor (){
        if (this.numero > 10){
        return 10;
        }
        else return this.numero;
    }


    public Carta(int numero, Palo palo) {
        this.numero = numero;
        this.palo = palo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Palo getPalo() {
        return palo;
    }

    public void setPalo(Palo palo) {
        this.palo = palo;
    }



   public boolean equals(Carta obj) {
        if(this.palo == obj.getPalo() && this.numero == obj.getNumero()) return true;
        return false;
    }

    @Override
    public String toString (){
        return this.getNumero()+"-"+this.getPalo().toString();
    }


    
    
    
}
