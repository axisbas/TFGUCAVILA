/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.util.Iterator;
import java.util.Vector;
import mensaje.Mensaje;

/**
 *
 * @author alex
 */
public class Mano {

    private Vector<Carta> cartas = new Vector<Carta>();
    private boolean paso = false; //Indica si un jugador ha abandonado su mano
    public static final int TanteoMaximo = 21;

    public Mano(Vector<Carta> cartas) {
        this.cartas = cartas;
    }

    public Mano() {
    }

    public Vector<Carta> getCartas() {
        return cartas;
    }

    public void setCartas(Vector<Carta> cartas) {
        this.cartas = cartas;
    }

    public Carta getCarta(int pos) {
        return cartas.get(pos);
    }

    public void resetear() {
        cartas.removeAllElements();
    }

    public void anadeCarta(Carta carta) {
        cartas.add(carta);
    }

    public int valorMano() {
        int valorTotal = 0;
        int contadorAses = 0;
        int valor1=0;
        int valor2=0;

        for (Carta c : cartas) {
            if (c.getNumero() == 1) {
                contadorAses++;
            } else {
                valorTotal += c.getValor();
            }

        }
        if (contadorAses>0){
            valor1 = valorTotal + contadorAses;
            valor2 = valorTotal + 11 + contadorAses - 1;
        if (valor2 <= TanteoMaximo) {
            return valor2;
        }
        return valor1;
        }
        else return valorTotal;
    }

    @Override
    public String toString() {
        String salida = "";
        for (Carta c : cartas) {
            salida = salida + c.toString() + "-";
        }
        return salida;// Numero-Palo-Numero2-Palo2-....-

    }
}
