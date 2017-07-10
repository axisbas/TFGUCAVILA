/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



package vista;

import controlador.Controlador;

/**
 *
 * @author alex
 */
public class Vista extends javax.swing.JFrame {

    private static Vista v;
    /** Creates new form Vista */
    public static Vista getInstance(){
        if (v==null){
            v = new Vista();

        }
        return v;
    }

    public Vista() {
        initComponents();
        
    }

    public void iniciar (){
        setVisible(true);
    }
    public void introducirTexto(String men){
        jTextArea1.setText(jTextArea1.getText() + men + "\n\n");
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        desconexion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(1280, 20));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        desconexion.setText("Desconexion");
        desconexion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desconexionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(desconexion)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(desconexion)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void desconexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desconexionActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_desconexionActionPerformed

    /**
    * @param args the command line arguments
    */


    public static void main(String[] args) {
        // TODO code application logic here
        Vista.getInstance().iniciar();
        Controlador.getInstancia().iniciar();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton desconexion;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables

}