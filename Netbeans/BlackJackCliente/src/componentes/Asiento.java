/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Asiento.java
 *
 */
package componentes;

import java.util.ArrayList;

/**
 *
 * @author alex
 */
public class Asiento extends javax.swing.JPanel {

    public ArrayList<Carta> mano;
    private String nombreU;

    /** Creates new form Asiento */
    public Asiento() {
        initComponents();
        this.mano = new ArrayList();
        this.setNombre("<Desocupado>");
    }

    public void setNombreU(String nombreU) {
        this.nombreU = nombreU;
    }



    public void anadeCarta(int numero, String palo) {
        Carta c = new Carta(numero, palo);
        System.out.println("Jugador :"+this.getNombre()+" Carta: "+numero+palo);

        if (mano.isEmpty() && !this.getNombre().equals("crupier") && !this.getNombre().equals(nombreU)) {
            c.setTapada(true);
        }
        mano.add(c);
        panelMano.add(c);
        panelMano.validate();
        panelMano.repaint();
        panelMano.validate();
        this.repaint();
        this.validate();
        this.repaint();
        panelMano.repaint();
        panelMano.validate();
    }
    public void anadeCarta(Carta c){
        anadeCarta(c.getNumero(),c.getPalo());
                this.repaint();
        this.validate();
                panelMano.validate();
        panelMano.repaint();
    }
    public void anadeCartas(ArrayList<Carta> cartas){
        for (Carta c: cartas){
            this.anadeCarta(c);
        }
        this.repaint();
        this.validate();
                panelMano.repaint();
        panelMano.validate();
    }

    public void vaciarMano() {
        this.mano.removeAll(mano);
        this.mano.clear();


        panelMano.removeAll();
        panelMano.setLayout(new java.awt.GridLayout(1, 0));
        panelMano.updateUI();
 //        System.out.println("Se ha vaciad la mano de "+this.getNombre()+" nº cartas="+panelMano.getComponentCount());
    }

    public String getNombre() {
        return nombre.getText();
    }

    public void setNombre(String nombre) {
        if (nombre == null) {
            nombre = "<Desocupado>";
        }
        this.nombre.setText(nombre);
    }

    public void mostrarCartas() {
        if (!mano.isEmpty()) {
            mano.get(0).setTapada(false);
        }
    }

    public void desocuparAsiento(){
        this.setNombre("<Desocupado>");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nombre = new javax.swing.JLabel();
        panelMano = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        nombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nombre.setText("<Desocupado>");
        add(nombre, java.awt.BorderLayout.PAGE_START);

        panelMano.setBackground(new java.awt.Color(29, 142, 57));
        panelMano.setLayout(new java.awt.GridLayout(1, 0));
        add(panelMano, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nombre;
    private javax.swing.JPanel panelMano;
    // End of variables declaration//GEN-END:variables
}
