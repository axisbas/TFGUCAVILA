/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Registro.java
 *
 */
package interfaz;

import blackjackcliente.Principal;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class Registro extends javax.swing.JDialog {

    private ObjectSocket socket;
    private InfoSala infoS = null;

    /** Creates new form Registro */
    private Registro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * Si no se ha registrado se retorna null, en otro caso se retornan los datos de la Sala.
     * @return
     */
    public static InfoSala muestraRegistro() {
        Registro registro = new Registro(null, true);
        registro.setVisible(true);
        return registro.infoS;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        email = new javax.swing.JFormattedTextField();
        username = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        registrar = new javax.swing.JButton();
        repassword = new javax.swing.JPasswordField();
        password = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(126, 44, 56));

        jLabel4.setText("Repetir Contraseña");

        jLabel1.setText("Nombre");

        registrar.setText("Registrar");
        registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarActionPerformed(evt);
            }
        });

        jLabel3.setText("Contraseña");

        jLabel2.setText("E-Mail");

        cancelar.setText("Cancelar");
        cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jLabel1)
                    .add(jLabel2)
                    .add(jLabel3)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                            .add(21, 21, 21)
                            .add(registrar))
                        .add(jLabel4)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(username, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(repassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(cancelar)))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(username, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(email, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel2))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel3))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(repassword, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 27, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(registrar)
                    .add(cancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
    private void registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarActionPerformed
        try {
            // TODO add your handling code here:
            socket = new ObjectSocket(new Socket(Principal.host, Principal.puerto));
            if (new String(password.getPassword()).equals(new String(repassword.getPassword()))) {
                Mensaje men = new Mensaje(Mensaje.TipoMensaje.Alta, username.getText() + Mensaje.separador + new String(password.getPassword()) + Mensaje.separador + email.getText() + Mensaje.separador);
                men.enviarMensaje(socket.getOutput());
                men = Mensaje.recibirMensaje(socket.getInput());
                infoS = new InfoSala();
                infoS.setSocket(socket);
                infoS.setMensaje(men);
                infoS.setNombreJugador(username.getText());
                String[] datos = men.splitDatos();
                if (datos[0].equals("ok") ) {
                    JOptionPane.showMessageDialog(this, "Registro completo");
                    this.setVisible(false);

                } else {
                    //Mostrar mensaje con lo que ha fallado en el intento de Login
                    JOptionPane.showMessageDialog(this, men.getMensaje());
                    infoS = null;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña y confirmar contraseña distintas");
                password.setText("");
                repassword.setText("");
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_registrarActionPerformed
*/
        private void registrarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // TODO add your handling code here:
            socket = new ObjectSocket(new Socket(Principal.host, Principal.puerto));
            if (new String(password.getPassword()).equals(new String(repassword.getPassword()))) {
                Mensaje men = new Mensaje(Mensaje.TipoMensaje.Alta, username.getText() + Mensaje.separador + new String(password.getPassword()) +Mensaje.separador+ email.getText());
                men.enviarMensaje(socket.getOutput());
                men = Mensaje.recibirMensaje(socket.getInput());
                infoS = new InfoSala();
                infoS.setSocket(socket);
                infoS.setMensaje(men);
                infoS.setNombreJugador(username.getText());
                String [] datos = men.splitDatos();
                if (datos[0].equals("ok")) {
                    Principal.username = username.getText();
                    Principal.puntos = Integer.parseInt(datos[1]);
                    Mensaje mensaje = Mensaje.recibirMensaje(socket.getInput());
                    infoS.setInfoSala(mensaje.getDatos());
                    this.setVisible(false);
                } else {
                    //Mostrar mensaje con lo que ha fallado en el intento de Login
                    JOptionPane.showMessageDialog(this, men.getMensaje());
                    infoS = null;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Contraseña y confirmar contraseña distintas");
                password.setText("");
                repassword.setText("");
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_cancelarActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelar;
    private javax.swing.JFormattedTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField password;
    private javax.swing.JButton registrar;
    private javax.swing.JPasswordField repassword;
    private javax.swing.JFormattedTextField username;
    // End of variables declaration//GEN-END:variables
}
