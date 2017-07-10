/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componentes;

import beanPictureBox.PictureBox;
import blackjackcliente.Principal;

/**
 *
 * @author alex
 */
public class Carta extends PictureBox {

    public enum Palo {

        diamantes, treboles, picas, corazones
    };
    private int numero;
    private Palo palo;
    private boolean tapada = false;

    public Carta() {
        this(0, null);
    }

    public Carta(int numero, String palo) {
        setNumero(numero);
        setPalo(palo);
        //super.setModo(Ajuste.AJUSTAR);
        this.setRutaImagen(getImage(numero, this.palo));
    }

    public void setTapada(boolean tapada) {
        this.tapada = tapada;
        this.setRutaImagen(getImage(numero, this.palo));
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
        if (palo != null) {
            this.setRutaImagen(getImage(numero, this.palo));
        }
    }

    public String getPalo() {
        if (palo == null) {
            return "nulo";

        } else {
            return palo.name();
        }
    }

    public void setPalo(String palo) {
        if (palo != null) {
            this.palo = Palo.valueOf(palo);
            this.setRutaImagen(getImage(numero, this.palo));
        } else {
            this.palo = null;
        }
    }

    private String getImage(int num, Palo palo) {
        String s ;
        if (tapada) {
            s="../imagenesc/b1fv.png";//Reverso
        } else {
            s="../imagenesc/" + num + palo.name().charAt(0) + ".png";
        }
        return Principal.class.getResource(s).getPath();

    }
}
