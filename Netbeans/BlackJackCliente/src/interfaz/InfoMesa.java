/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import componentes.Carta;
import java.util.ArrayList;
import mensaje.Mensaje;

/**
 *
 * @author alex
 */
public class InfoMesa {

    private int numMesa;
    private ArrayList<String> infoJug = new ArrayList();//Nombre de jugador y sus cartas separadas con guiones

    public InfoMesa(String datos) {
        setDatos(datos);
    }
    /*
    public InfoMesa (){

    }
     */

    public ArrayList<String> getInfoJug() {
        return infoJug;
    }

    public void setInfoJug(ArrayList<String> infoJug) {
        this.infoJug = infoJug;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public void setDatos(String datos) {//nº mesa=nºjugadores=nombre-nºcarta-palo-nºcarta2-palo2-...=nombre2...-.../
        this.infoJug.clear();
        String[] cadena = datos.split("=");
        this.numMesa = Integer.parseInt(cadena[0]);
        int n = Integer.parseInt(cadena[1]);
        for (int i = 0; i < n; i++) {
            this.infoJug.add(cadena[i + 2]);
        }

    }

    public ArrayList<String> nomJug() {
        ArrayList<String> nombres = new ArrayList();
        for (String s : infoJug) {
            nombres.add(s.split("-")[0]);
        }
        return nombres;
    }

    public ArrayList<Carta> getCartas(String nomJug) {
        ArrayList<Carta> cartas = new ArrayList();
        for (String s : this.infoJug) {
            String[] datos = s.split("-");
            if (datos[0].equals(nomJug)) {
                for (int i = 1; i < datos.length; i += 2) {
                    cartas.add(new Carta(Integer.parseInt(datos[i]), datos[i + 1]));
                }
                break;
            }
        }
        return cartas;
    }
}
