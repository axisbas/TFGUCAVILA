/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Mesa.java
 *
 */
package interfaz;

import blackjackcliente.Principal;
import javax.swing.JOptionPane;
import mensaje.Mensaje;

/**
 *
 * @author alex
 */
public class Mesa extends javax.swing.JDialog {

    private HiloEspera hiloe = null;
    private Sala s;
    /** Creates new form Mesa */
    public Mesa(java.awt.Frame parent, boolean modal, InfoMesa datos, Sala sala) {
        super(parent, modal);
        initComponents();
        mesa1.inicializarMesa(datos);
        this.s=sala;
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
    }

    public componentes.Mesa getMesa() {
        return this.mesa1;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mesa1 = new componentes.Mesa();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        sentarse = new javax.swing.JButton();
        salirtab1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        pasartab2 = new javax.swing.JButton();
        salirtab2 = new javax.swing.JButton();
        apostar = new javax.swing.JButton();
        apuesta = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        pasartab3 = new javax.swing.JButton();
        pedircarta = new javax.swing.JButton();
        mensajes = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 700));
        setPreferredSize(new java.awt.Dimension(800, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        mesa1.setMinimumSize(new java.awt.Dimension(640, 480));
        mesa1.setPreferredSize(new java.awt.Dimension(640, 480));
        getContentPane().add(mesa1, java.awt.BorderLayout.CENTER);

        sentarse.setText("Sentarse");
        sentarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sentarseActionPerformed(evt);
            }
        });
        jPanel1.add(sentarse);

        salirtab1.setText("Salir");
        salirtab1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirtab1ActionPerformed(evt);
            }
        });
        jPanel1.add(salirtab1);

        jTabbedPane1.addTab("tab1", jPanel1);

        pasartab2.setText("Pasar");
        pasartab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasartab2ActionPerformed(evt);
            }
        });
        jPanel2.add(pasartab2);

        salirtab2.setText("Salir");
        salirtab2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirtab2ActionPerformed(evt);
            }
        });
        jPanel2.add(salirtab2);

        apostar.setText("Apostar");
        apostar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apostarActionPerformed(evt);
            }
        });
        jPanel2.add(apostar);

        apuesta.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        apuesta.setMinimumSize(new java.awt.Dimension(140, 28));
        apuesta.setPreferredSize(new java.awt.Dimension(140, 28));
        jPanel2.add(apuesta);

        jTabbedPane1.addTab("tab2", jPanel2);

        pasartab3.setText("Pasar");
        pasartab3.setEnabled(false);
        pasartab3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasartab3ActionPerformed(evt);
            }
        });
        jPanel3.add(pasartab3);

        pedircarta.setText("Pedir Carta");
        pedircarta.setEnabled(false);
        pedircarta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pedircartaActionPerformed(evt);
            }
        });
        jPanel3.add(pedircarta);

        jTabbedPane1.addTab("tab3", jPanel3);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.PAGE_END);

        mensajes.setBackground(new java.awt.Color(255, 255, 51));
        getContentPane().add(mensajes, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sentarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sentarseActionPerformed
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.SentarseEnMesa, mesa1.getNumMesa());
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setEnabledAt(2, false);
    }//GEN-LAST:event_sentarseActionPerformed

    private void salirtab1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirtab1ActionPerformed
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.SalirDeMesa, mesa1.getNumMesa());
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        this.setVisible(false);
        //Sala.getInstancia().setVisible(true);
        s.setVisible(true);
    }//GEN-LAST:event_salirtab1ActionPerformed

    private void pasartab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasartab2ActionPerformed
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.PasoApuesta, mesa1.getNumMesa());
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(0, true);

    }//GEN-LAST:event_pasartab2ActionPerformed

    private void salirtab2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirtab2ActionPerformed
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.SalirDeMesa, mesa1.getNumMesa());
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        this.setVisible(false);
        //Sala.getInstancia().setVisible(true);
        s.setVisible(true);
    }//GEN-LAST:event_salirtab2ActionPerformed

    private void apostarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apostarActionPerformed
        // TODO add your handling code here:

        hiloe = null;
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.Apuesta, mesa1.getNumMesa() + Mensaje.separador + apuesta.getText());
        if (Integer.parseInt(apuesta.getText())>Principal.puntos){
            JOptionPane.showMessageDialog(this,"No puede apostar más de "+Principal.puntos);
        }
        else{
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        jTabbedPane1.setSelectedIndex(2);
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setEnabledAt(0, false);
        }
    }//GEN-LAST:event_apostarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.SalirDeMesa);
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        this.setVisible(false);
        //Sala.getInstancia().setVisible(true);
        s.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void pasartab3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasartab3ActionPerformed
        // TODO add your handling code here:
        hiloe = null;
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.TerminarMano, mesa1.getNumMesa());
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
        jTabbedPane1.setEnabledAt(2, false);
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setEnabledAt(0, false);
    }//GEN-LAST:event_pasartab3ActionPerformed

    private void pedircartaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pedircartaActionPerformed
        // TODO add your handling code here:
        hiloe = null;
        Mensaje m = new Mensaje(Mensaje.TipoMensaje.PedirCarta, mesa1.getNumMesa());
        //m.enviarMensaje(Sala.getInstancia().getSocket().getOutput());
        m.enviarMensaje(s.getSocket().getOutput());
    }//GEN-LAST:event_pedircartaActionPerformed

    public void muestraMensaje(String mensaje) {
        mensajes.setText(mensaje);
    }

    public void avisarApuesta() {
        jTabbedPane1.setSelectedIndex(1);
        hiloe = new HiloEspera(Principal.timeOut, 0);
        hiloe.start();
    }

    public void avisoTurno() {
        jTabbedPane1.setSelectedIndex(2);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(2, true);
        pasartab3.setEnabled(true);

        pedircarta.setEnabled(true);
        hiloe = new HiloEspera(Principal.timeOut, 1);
        hiloe.start();
    }

    public void avisoFinTurno() {
        pasartab3.setEnabled(false);
        pedircarta.setEnabled(false);
    }

    void avisoFinPartida(String mensaje) {
        mensajes.setText(mensaje);
        mesa1.mostrarCartas();
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(1, true);
    }

    class HiloEspera extends Thread {

        private int timeout;
        private int estado;// 0 -> pasarApuesta, 1-> pasarCarta

        public HiloEspera(int timeout, int estado) {
            this.timeout = timeout;
            this.estado = estado;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException ex) {
            }
            if (hiloe != null) {
                if (estado == 0) {
                    pasartab2ActionPerformed(null);
                } else if (estado == 1) {
                    pasartab3ActionPerformed(null);
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton apostar;
    private javax.swing.JFormattedTextField apuesta;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel mensajes;
    private componentes.Mesa mesa1;
    private javax.swing.JButton pasartab2;
    private javax.swing.JButton pasartab3;
    private javax.swing.JButton pedircarta;
    private javax.swing.JButton salirtab1;
    private javax.swing.JButton salirtab2;
    private javax.swing.JButton sentarse;
    // End of variables declaration//GEN-END:variables
}
