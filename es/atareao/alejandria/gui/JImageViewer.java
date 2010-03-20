/*
 * ***********************Software description*********************************
 * JImageViewer.java
 *
 *
 * ***********************Software description*********************************
 *
 * Copyright (C) 23 de febrero de 2008 - Lorenzo Carbonell
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * **************************Software License***********************************
 *
 *
 */
package es.atareao.alejandria.gui;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Date;

/**
 *
 * @author  Lorenzo Carbonell
 */
public class JImageViewer extends javax.swing.JFrame {

    // <editor-fold defaultstate="collapsed" desc=" Constantes  ">
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Constructores  ">
    /** Creates new form JImageViewer */
    public JImageViewer() {
        initComponents();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        this.setSize((int) (toolkit.getScreenSize().getWidth() * 0.9), (int) (toolkit.getScreenSize().getHeight() * 0.9));
        this.setLocationRelativeTo(null);
    }

    public JImageViewer(JThumbnailInterface jti) {
        this();
        this.jImagePanel1.display(jti);
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jImagePanel1 = new es.atareao.alejandria.gui.JImagePanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jImagePanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jImagePanel1MouseDragged(evt);
            }
        });
        jImagePanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jImagePanel1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jImagePanel1MouseReleased(evt);
            }
        });
        jImagePanel1.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jImagePanel1MouseWheelMoved(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jImagePanel1Layout = new org.jdesktop.layout.GroupLayout(jImagePanel1);
        jImagePanel1.setLayout(jImagePanel1Layout);
        jImagePanel1Layout.setHorizontalGroup(
            jImagePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 400, Short.MAX_VALUE)
        );
        jImagePanel1Layout.setVerticalGroup(
            jImagePanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 300, Short.MAX_VALUE)
        );

        getContentPane().add(jImagePanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jImagePanel1MouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jImagePanel1MouseWheelMoved
        if (evt.getWheelRotation() > 0) {
            this.jImagePanel1.scale(-0.05);
        } else {
            this.jImagePanel1.scale(0.05);
        }
    }//GEN-LAST:event_jImagePanel1MouseWheelMoved

    private void jImagePanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jImagePanel1MousePressed
        this.dragStartTime = new Date().getTime();
        this.dragStartPoint = new Point(evt.getX() - this.jImagePanel1.getOriginX(), evt.getY() - this.jImagePanel1.getOriginY());

    }//GEN-LAST:event_jImagePanel1MousePressed

    private void jImagePanel1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jImagePanel1MouseReleased
        if (!this.dragInProgress) {
            this.jImagePanel1MouseReleased(evt);
        } else {
            this.jImagePanel1.setOriginX(evt.getX() - this.dragStartPoint.x);
            this.jImagePanel1.setOriginY(evt.getY() - this.dragStartPoint.y);
            this.jImagePanel1.repaint();

            this.dragStartPoint = null;
            this.dragInProgress = false;
        }
        evt.consume();
    }//GEN-LAST:event_jImagePanel1MouseReleased

    private void jImagePanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jImagePanel1MouseDragged
        this.jImagePanel1.setOriginX(evt.getX() - this.dragStartPoint.x);
        this.jImagePanel1.setOriginY(evt.getY() - this.dragStartPoint.y);
        if (new Date().getTime() - this.dragStartTime  > 250) {
                this.dragInProgress = true;
                this.jImagePanel1.repaint();
        }
    }//GEN-LAST:event_jImagePanel1MouseDragged

// <editor-fold defaultstate="collapsed" desc=" Método Principal  ">
/**
 * @param args the command line arguments
 */
public static void

main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void

run() {
                new JImageViewer().setVisible(true);
            }

});
    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" Métodos  ">
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  ">
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" Campos  ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private es.atareao.alejandria.gui.JImagePanel jImagePanel1;
    // End of variables declaration//GEN-END:variables
    private Image image;
//
private javax.swing

.Timer ssTimer = null;

    private Point dragStartPoint;

private long

dragStartTime;
    private boolean

dragInProgress = false;
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  ">
    
    // </editor-fold>
}
