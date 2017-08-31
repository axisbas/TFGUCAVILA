/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Mesa.java
 *
 */

package componentes;

import blackjackcliente.Principal;
import interfaz.InfoMesa;
import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Mesa extends javax.swing.JPanel {
    private ArrayList <Asiento> asientos;
    private int numMesa;

    /** Creates new form Mesa */
    public Mesa() {
        initComponents();
        asientos = new ArrayList(4);
        for(int i=0;i<4;i++){
            Asiento a = new Asiento();
            asientos.add(a);
            this.add(a);
            a.setNombreU(Principal.username);
        }
    }


    public String getNumMesa() {
        return ""+numMesa;
    }

    

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }


    public void setNombre(int indice, String nombre){
        asientos.get(indice).setNombre(nombre);
    }

    private Asiento getIndice(String nombre){
        for (Asiento a : asientos){
            if (a.getNombre().equals(nombre)){
                return a;
            }
        }
        return null;
    }

    public void setCarta(String nombre, int numero, String palo){
        this.getIndice(nombre).anadeCarta(numero, palo);
        this.repaint();
        this.validate();
                for(int i=0;i<4;i++){
            asientos.get(i).repaint();
            asientos.get(i).validate();
        }
    }

    public void mostrarCartas(){
        for (Asiento a : asientos){
            a.mostrarCartas();
            a.repaint();
            a.validate();
            this.repaint();
            this.validate();
        }
    }

    public void vaciarManos(){
        for (Asiento a : asientos){
            a.vaciarMano();
            a.repaint();
            a.validate();
        }
        this.repaint();
        this.validate();
    }

    public void inicializarMesa(InfoMesa infomesa){
        this.vaciarManos();
        ArrayList <String> nombres = infomesa.nomJug();
        this.setNumMesa(infomesa.getNumMesa());
        for (int i=0; i<nombres.size();i++){
            this.asientos.get(i).setNombre(nombres.get(i));
            this.asientos.get(i).anadeCartas(infomesa.getCartas(nombres.get(i)));
            this.asientos.get(i).repaint();
            this.asientos.get(i).validate();
        }
        for (int j=nombres.size(); j<asientos.size();j++){
            this.asientos.get(j).desocuparAsiento();
        }
        this.vaciarManos();
        this.repaint();
        this.validate();
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(2, 2, 4, 4));
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
