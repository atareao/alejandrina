/*
 * JThumbnailPanel.java
 *
 * Created on 22 de febrero de 2008, 18:24
 */

package es.atareao.alejandria.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import es.atareao.alejandria.lib.FileUtils;
import es.atareao.alejandria.lib.ImageUtils;
import es.atareao.alejandria.lib.URIUtils;

/**
 *
 * @author  Propietario
 */
public class JThumbnailPanel extends javax.swing.JPanel {
    
    /** Creates new form JThumbnailPanel */
    public JThumbnailPanel() {
        initComponents();
        this.jList1.setModel(new DefaultListModel());
        this.jList1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.jList1.setVisibleRowCount(5);
        this.jList1.setCellRenderer(new JImageListCellRenderer());
    }
    
    private void resizeRows(){
        int filas=1;
        if(this.getNumberOfThumbnails()>0){
            int columnas=(this.getWidth()/getThumbnailWidth());
            filas=this.getNumberOfThumbnails()/columnas+1;
        }
    this.jList1.setVisibleRowCount(filas);
    }
    public JThumbnailInterface getThumbnail(int position){
        return (JThumbnailInterface)((DefaultListModel)this.jList1.getModel()).getElementAt(position);
    }
    public int getNumberOfThumbnails(){
        return ((DefaultListModel)this.jList1.getModel()).size();
    }
    public void addThumbnail(JThumbnailInterface thumbnail) {
        ((DefaultListModel)this.jList1.getModel()).addElement(thumbnail);
        this.resizeRows();
    }
    public void addThumbnails(Vector<JThumbnailInterface> thumbnails) {
        for(JThumbnailInterface thumbnail : thumbnails){
            this.addThumbnail(thumbnail);
            
        }
    }
    public void refresh(){
        this.jList1.repaint();
    }
    public void clearThumbnails(){
        ((DefaultListModel)this.jList1.getModel()).removeAllElements();
    }
    public void setThumbnails(Vector<JThumbnailInterface> thumbnails){
        this.clearThumbnails();
        this.addThumbnails(thumbnails);
    }
    public boolean isSelectedThumbnails(){
        if(this.getNumOfSelectedThumbnails()>0){
            return true;
        }
        return false;
    }
    public int getNumOfSelectedThumbnails(){
        return this.jList1.getSelectedIndices().length;
    }
    public Vector<JThumbnailInterface> getSelectedThumbnails(){
        Vector<JThumbnailInterface> selected=new Vector<JThumbnailInterface>();
        for(int contador=0;contador<this.jList1.getSelectedIndices().length;contador++){
            selected.add((JThumbnailInterface)jList1.getSelectedValues()[contador]);
        }
        return selected;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();

        jMenuItem1.setText("Guardar como ...");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);
        jPopupMenu1.add(jSeparator1);

        jMenuItem2.setText("Girar 90� horario");
        jMenuItem2.setEnabled(false);
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem2);

        jMenuItem3.setText("Girar 90� antihorario");
        jMenuItem3.setEnabled(false);
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem3);

        setMaximumSize(new java.awt.Dimension(200, 200));
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(200, 200));
        addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                formAncestorResized(evt);
            }
        });
        setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jList1.setComponentPopupMenu(jPopupMenu1);
        jList1.setLayoutOrientation(javax.swing.JList.HORIZONTAL_WRAP);
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_formAncestorResized
        this.resizeRows();
    }//GEN-LAST:event_formAncestorResized

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if(evt.getClickCount()>1){
            JThumbnailInterface selected=(JThumbnailInterface) this.jList1.getSelectedValue();
            if(selected!=null){
                JImageViewer jiv = new JImageViewer(selected);
                jiv.setVisible(true);
            }
        }
    }//GEN-LAST:event_jList1MouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JThumbnailInterface selected=(JThumbnailInterface) this.jList1.getSelectedValue();
        if(selected!=null){
            JFileChooser jfc=new JFileChooser();
            File source=new File(selected.getURI());
            File dest=new File(source.getName());
            dest =FileUtils.addPathFile(FileUtils.getApplicationPath(),dest);
            jfc.setSelectedFile(dest);
            int status=jfc.showSaveDialog(this);
            if(status==JDirectoryChooser.APPROVE_OPTION){
                try {
                    dest = jfc.getSelectedFile();
                    FileUtils.copy(source, dest);
                } catch (FileNotFoundException ex) {
                    ErrorDialog.manejaError(ex,false);
                } catch (IOException ex) {
                    ErrorDialog.manejaError(ex,false);
                }
            }
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JThumbnailInterface selected=(JThumbnailInterface) this.jList1.getSelectedValue();
        if(selected!=null){
            try {
                BufferedImage image = ImageUtils.toBufferedImage(selected.getImage());
                BufferedImage girada = ImageUtils.rotate(image, 90);
                String name = (new File(selected.getURI())).toString();
                ImageUtils.save(girada, name, URIUtils.getSuffix(selected.getURI()));
                //
                File thumbnailFile = new File(selected.getThumbnailURI());
                BufferedImage thumbnail = ImageUtils.load(thumbnailFile);
                BufferedImage thumbnailGirado = ImageUtils.rotate(thumbnail, 90);
                String thumbnailName = (new File(selected.getURI())).toString();
                ImageUtils.save(thumbnailGirado, thumbnailName, URIUtils.getSuffix(selected.getThumbnailURI()));
                this.refresh();
            } catch (IOException ex) {
                ErrorDialog.manejaError(ex,false);
            }
            
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JThumbnailInterface selected=(JThumbnailInterface) this.jList1.getSelectedValue();
        if(selected!=null){
            try {
                BufferedImage image = ImageUtils.toBufferedImage(selected.getImage());
                BufferedImage girada = ImageUtils.rotate(image, -90);
                String name = (new File(selected.getURI())).toString();
                ImageUtils.save(girada, name, URIUtils.getSuffix(selected.getURI()));
                //
                File thumbnailFile = new File(selected.getThumbnailURI());
                BufferedImage thumbnail = ImageUtils.load(thumbnailFile);
                BufferedImage thumbnailGirado = ImageUtils.rotate(thumbnail, -90);
                String thumbnailName = (new File(selected.getURI())).toString();
                ImageUtils.save(thumbnailGirado, thumbnailName, URIUtils.getSuffix(selected.getThumbnailURI()));
                this.refresh();
            } catch (IOException ex) {
                ErrorDialog.manejaError(ex,false);
            }
            
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList jList1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    private int _thumbnailWidth=150;

    public int getThumbnailWidth() {
        return _thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this._thumbnailWidth = thumbnailWidth;
    }

}
