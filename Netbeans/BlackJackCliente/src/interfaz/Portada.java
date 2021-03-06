/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Portada.java
 *
 */
package interfaz;

import blackjackcliente.Principal;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import mensaje.Mensaje;
import mensaje.ObjectSocket;

/**
 *
 * @author alex
 */
public class Portada extends javax.swing.JDialog {

    private ObjectSocket socket;
    private InfoSala infoS=null;

    /** Creates new form Portada */
    private Portada(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setBackground(new java.awt.Color(126, 44, 56));
    }



    /**
     *
     * @return null si no se ha logueado.
     */
    public static InfoSala muestraPortada() {
        Portada portada = new Portada(null, true);
        portada.setVisible(true);
        return portada.infoS;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        login = new javax.swing.JLabel();
        password = new javax.swing.JLabel();
        contrasena = new javax.swing.JPasswordField();
        nombre = new javax.swing.JFormattedTextField();
        conectar = new javax.swing.JButton();
        registrarse = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        archivo = new javax.swing.JMenu();
        itemIP = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();

        org.jdesktop.layout.GroupLayout jDialog1Layout = new org.jdesktop.layout.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(126, 44, 56));
        setPreferredSize(new java.awt.Dimension(450, 550));

        login.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        login.setText("Login");

        password.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        password.setText("Contraseña");

        contrasena.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        contrasena.setText("password1");

        nombre.setText("usuario1");
        nombre.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        nombre.setPreferredSize(new java.awt.Dimension(134, 28));
        nombre.setSelectionEnd(9);
        nombre.setSelectionStart(9);
        nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreActionPerformed(evt);
            }
        });

        conectar.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        conectar.setText("Conectar");
        conectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conectarActionPerformed(evt);
            }
        });

        registrarse.setFont(new java.awt.Font("Lucida Grande", 0, 16)); // NOI18N
        registrarse.setText("Registrarse");
        registrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registrarseActionPerformed(evt);
            }
        });

        archivo.setText("Archivo");

        itemIP.setText("IP");
        itemIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemIPActionPerformed(evt);
            }
        });
        archivo.add(itemIP);
        archivo.add(jSeparator1);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        archivo.add(itemSalir);

        jMenuBar1.add(archivo);

        setJMenuBar(jMenuBar1);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(0, 111, Short.MAX_VALUE)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(90, 90, 90)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(conectar, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(registrarse, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 109, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(login, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 100, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 162, Short.MAX_VALUE)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(nombre, 0, 0, Short.MAX_VALUE)
                            .add(contrasena))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(26, 26, 26)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(nombre, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(login, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(16, 16, 16)
                        .add(password, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(contrasena, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 32, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(151, 151, 151)
                .add(conectar)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(registrarse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 30, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(289, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void conectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conectarActionPerformed
        try {
            // TODO add your handling code here:
            socket = new ObjectSocket(new Socket(Principal.host, Principal.puerto));
            Mensaje men = new Mensaje(Mensaje.TipoMensaje.Login, nombre.getText() + Mensaje.separador + new String(contrasena.getPassword()) + "");
            men.enviarMensaje(socket.getOutput());
            men = Mensaje.recibirMensaje(socket.getInput());
            System.out.println("Peticion de login recibida");
            String nombres = men.getMensaje();
            infoS = new InfoSala();
            infoS.setSocket(socket);
            infoS.setMensaje(men);
            infoS.setNombreJugador(nombre.getText());
            String [] datos = men.splitDatos();
            if (datos[0].equals("ok")) {
                System.out.println("Entra por Ok");
                Principal.username = nombre.getText();
                Principal.puntos = Integer.parseInt(datos[1]);
                Mensaje mensaje = Mensaje.recibirMensaje(socket.getInput());
                System.out.println("Recibida información sala");
                infoS.setInfoSala(mensaje.getDatos());

                this.setVisible(false);
            } else {
                //Mostrar mensaje con lo que ha fallado en el intento de Login
                JOptionPane.showMessageDialog(this, men.getMensaje());
                infoS = null;
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Portada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Portada.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_conectarActionPerformed

    private void registrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registrarseActionPerformed
        // TODO add your handling code here:
        infoS = Registro.muestraRegistro();
        if (infoS!=null){
            this.setVisible(false);
        }
    }//GEN-LAST:event_registrarseActionPerformed



    private void itemIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemIPActionPerformed
        // TODO add your handling code here:
        String ip=JOptionPane.showInputDialog("Introduzca la IP: ", Principal.getHost());
        if (ip != null){
            Principal.setHost(ip);
        }
        
    }//GEN-LAST:event_itemIPActionPerformed

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu archivo;
    private javax.swing.JButton conectar;
    private javax.swing.JPasswordField contrasena;
    private javax.swing.JMenuItem itemIP;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel login;
    private javax.swing.JFormattedTextField nombre;
    private javax.swing.JLabel password;
    private javax.swing.JButton registrarse;
    // End of variables declaration//GEN-END:variables
}
