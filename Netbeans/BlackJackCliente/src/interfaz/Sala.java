/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Sala.java
 *
 * Created on 26-ago-2010, 17:36:47
 */
package interfaz;

import blackjackcliente.Principal;
import java.io.IOException;
import java.util.ArrayList;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class Sala extends javax.swing.JFrame {

   // private static Sala sala;
    private ArrayList<MesaInfo> mesainfo;
    private Mesa mesaActual = null;
    private InfoSala infoS;

    /** Creates new form Sala */
    public Sala() {
        initComponents();
        mesainfo = new ArrayList(4);

        
        for (int i = 0; i < 4; i++) {
            MesaInfo infom = new MesaInfo(this);
            mesainfo.add(infom);
            listamesas.add(infom);
            
        }

        this.validate();
    }


    public String getNombreJugador(){

        return infoS.getNombreJugador();
    }


    /*public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }*/



    public componentes.Mesa getComponenteMesa() {//Ya disponible para HiloOyente
        return mesaActual.getMesa();
    }

    public interfaz.Mesa getDialogMesa(){
        return mesaActual;
    }
/*
    public static Sala getInstancia() {
        if (sala == null) {
            sala = new Sala();
        }
        return sala;
    }
*/
    public ObjectSocket getSocket(){
        return this.infoS.getSocket();
    }

    public void setInfoSala(InfoSala info) {
        ArrayList<InfoMesa> infoM = info.getInfoMesas();
        for (int i = 0; i < infoM.size(); i++) {
            mesainfo.get(i).setDatos(infoM.get(i));
        }
        this.infoS = info;

    }

    public void unirMesaDialog(int numMesa) {
        Mensaje men = new Mensaje(Mensaje.TipoMensaje.UnirAMesa, "" + numMesa);
        men.enviarMensaje(infoS.getSocket().getOutput());
        
/*        men = Mensaje.recibirMensaje(infoS.getSocket().getInput());
        Mesa m = new Mesa(null, true, new InfoMesa(men.getDatos()));
        this.mesaActual = m.getMesa();
        m.setVisible(true);*/
    }

    public void iniciaPartida(String dato){
        InfoMesa info = new InfoMesa(dato);
        this.getComponenteMesa().inicializarMesa(info);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listamesas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        desconectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 480));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        listamesas.setSize(new java.awt.Dimension(500, 100));
        listamesas.setLayout(new java.awt.GridLayout(0, 1, 1, 1));
        getContentPane().add(listamesas, java.awt.BorderLayout.CENTER);

        jTextArea1.setColumns(15);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.EAST);

        desconectar.setText("Desconectar");
        desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarActionPerformed(evt);
            }
        });
        getContentPane().add(desconectar, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarActionPerformed
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.AvisoDesconexionCliente);
        m.enviarMensaje(infoS.getSocket().getOutput());
        System.exit(0);
    }//GEN-LAST:event_desconectarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.AvisoDesconexionCliente);
        m.enviarMensaje(infoS.getSocket().getOutput());
        try {
            // TODO add your handling code here:
            this.infoS.getSocket().close();
        } catch (IOException ex) {
        }
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.MostrarRanking);
        m.enviarMensaje(infoS.getSocket().getOutput());
    }//GEN-LAST:event_formWindowActivated
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton desconectar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JPanel listamesas;
    // End of variables declaration//GEN-END:variables

    public void respuestaSentarseEnMesa(String mensaje) {

        mesaActual.muestraMensaje(mensaje);
    }

    public void unirAMesa(String datos) {
        this.mesaActual = new Mesa(null, false, new InfoMesa(datos),this);
        mesaActual.setVisible(true);
        this.setVisible(false);
    }
    public void finPartida(String mensaje) {
        this.mesaActual.avisoFinPartida(mensaje);
    }

    public void escribirRanking(String datos) {
        datos = "Ranking de Jugadores\n--------------------\nNombre\tPuntos\n\n"+datos;
        jTextArea1.setText(datos);
    }
}
