/*
 * ErrorDialog.java
 *
 * Created on 14 de septiembre de 2006, 13:38
 */

package es.atareao.alejandria.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.sql.SQLException;

/**
 *
 * @author  Protactino
 */
public class ErrorDialog extends javax.swing.JDialog {
    
    /** Creates new form ErrorDialog */
    private final static Dimension _dimension=new Dimension(510,140);
    public ErrorDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setSize(_dimension);
        this.setLocationRelativeTo(null);
    }
    public ErrorDialog(java.awt.Frame parent, boolean modal,String error) {
        super(parent, modal);
        initComponents();
        this.setError(error);
        this.setSize(_dimension);
        this.setLocationRelativeTo(null);
    }
    public ErrorDialog(java.awt.Frame parent, boolean modal,String error1,String error2) {
        super(parent, modal);
        initComponents();
        this.setError(error1);
        this.jTextArea2.setText(error2);
        this.setSize(_dimension);
        this.setLocationRelativeTo(null);
    }
    public ErrorDialog(String error){
        super(new Frame(),true);
        initComponents();
        this.setError(error);
        this.setSize(_dimension);
        this.setLocationRelativeTo(null);

    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Error");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFocusable(false);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, -1, -1));

        jTextArea2.setBackground(java.awt.SystemColor.info);
        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setFont(new java.awt.Font("Tahoma", 0, 10));
        jTextArea2.setRows(5);
        jTextArea2.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea2);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 480, 250));

        jButton2.setText("Ampliar");
        jButton2.setFocusable(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, -1, -1));

        jScrollPane1.setBackground(java.awt.SystemColor.info);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setBackground(java.awt.SystemColor.info);
        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Tahoma", 1, 10));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(3);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 480, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
// TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// TODO add your handling code here:
        if(jButton2.getText().equals("Ampliar")){
            jButton2.setText("Contraer");
            this.setSize(510,430);
            this.paintAll(this.getGraphics());
        }else{
            jButton2.setText("Ampliar");
            this.setSize(_dimension);
            this.paintAll(this.getGraphics());
        }
    }//GEN-LAST:event_jButton2ActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ErrorDialog(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    // End of variables declaration//GEN-END:variables
    private String _error;

    public String getError() {
        return _error;
    }

    public void setError(String error) {
        this._error = error;
        this.jTextArea1.setText(error);
    }
    //
    //
    //
    //
    //
    public static void manejaError(Exception error){
        manejaError(error,false);
    }
    public static void manejaError(Exception error,boolean salir){
        StringBuilder sb=new StringBuilder();
        StackTraceElement[] ste=error.getStackTrace();
        for(int i=0;i<ste.length;i++){
            sb.append(ste[i].toString());
            sb.append("\r\n");
        }
        String mensaje="";
        if (error instanceof SQLException){
            mensaje="Error "+((SQLException)error).getErrorCode()+"\r\n";
        }
        mensaje+=error.getMessage();
        ErrorDialog ed=new ErrorDialog(new Frame(), true,mensaje,sb.toString());
        ed.setVisible(true);
        if (salir){
            System.exit(0);
        }    
    }
}