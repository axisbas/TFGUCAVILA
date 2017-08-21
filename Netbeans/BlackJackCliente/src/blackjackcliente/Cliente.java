/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Cliente.java
 *
 */

package blackjackcliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class Cliente extends javax.swing.JFrame {

    /** Creates new form Cliente */
    public Cliente() {
            initComponents();
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for(Mensaje.TipoMensaje tm : Mensaje.TipoMensaje.values()){
            m.addElement(tm);
        }
        this.tipo.setModel(m);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tipo = new javax.swing.JComboBox();
        datos = new javax.swing.JTextField();
        mensaje = new javax.swing.JTextField();
        enviarmensaje = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        servidor = new javax.swing.JTextArea();
        conectar = new javax.swing.JButton();
        desconectar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        datos.setText("Datos...");

        mensaje.setText("Mensaje...");

        enviarmensaje.setText("Enviar");
        enviarmensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarmensajeActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(90, 90, 90)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, mensaje)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, datos, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(tipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 238, Short.MAX_VALUE)
                        .add(enviarmensaje)
                        .add(88, 88, 88))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(tipo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(enviarmensaje))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(datos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(mensaje, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        servidor.setColumns(20);
        servidor.setRows(5);
        jScrollPane1.setViewportView(servidor);

        conectar.setText("Conectar");
        conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarActionPerformed(evt);
            }
        });

        desconectar.setText("Desconectar");
        desconectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconectarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE)
            .add(layout.createSequentialGroup()
                .add(139, 139, 139)
                .add(conectar)
                .add(123, 123, 123)
                .add(desconectar)
                .addContainerGap(229, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(18, 18, 18)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 165, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 27, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(conectar)
                    .add(desconectar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoActionPerformed

    private void conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarActionPerformed
        try {
            // TODO add your handling code here:
            socket = new ObjectSocket(new Socket("localhost", 7777));
            Mensaje men = new Mensaje(Mensaje.TipoMensaje.Login, "usuario1"+Mensaje.separador+"password1"+"");
            //Mensaje men = new Mensaje((Mensaje.TipoMensaje)tipo.getSelectedItem(), datos.getText(), mensaje.getText());
            men.enviarMensaje(socket.getOutput());
            men = new Mensaje(Mensaje.TipoMensaje.UnirAMesa, "1"+"");
            men.enviarMensaje(socket.getOutput());
            men = new Mensaje(Mensaje.TipoMensaje.SentarseEnMesa, "1"+"");
            men.enviarMensaje(socket.getOutput());
            HiloOyente hiloo = new HiloOyente();
            hiloo.start();
        } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.toString());
        }
        conectar.setEnabled(false);

    }//GEN-LAST:event_conectarActionPerformed

    private void desconectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconectarActionPerformed
        // TODO add your handling code here:
        if (socket!=null){
            try {
                socket.close();
                socket = null;
            } catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_desconectarActionPerformed

    private void enviarmensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarmensajeActionPerformed
        // TODO add your handling code here:
        Mensaje m = new Mensaje((Mensaje.TipoMensaje)tipo.getSelectedItem(),datos.getText(),mensaje.getText());
        try {
            socket.getOutput().writeObject(m);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_enviarmensajeActionPerformed

    /**
    * @param args the command line arguments
    */
 /*   public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton conectar;
    private javax.swing.JTextField datos;
    private javax.swing.JButton desconectar;
    private javax.swing.JButton enviarmensaje;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mensaje;
    private javax.swing.JTextArea servidor;
    private javax.swing.JComboBox tipo;
    // End of variables declaration//GEN-END:variables
    private ObjectSocket socket=null;


    class HiloOyente extends Thread{
        @Override
        public void run(){
            try {
                while (socket != null){
                    Mensaje m = (Mensaje) socket.getInput().readObject();
                    servidor.setText(servidor.getText()+m.toString()+"\n");
                    
                }
            } catch (Exception ex) {
                socket = null;
                JOptionPane.showMessageDialog(null, "Se ha desconectado del servidor");
                conectar.setEnabled(true);
            }


        }
    }
}
