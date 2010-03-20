/*
 * JCheckBoxTableCellRenderer.java
 *
 * Created on 16 de agosto de 2007, 22:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Propietario
 */
public class JImageFromResourceTableCellRenderer extends JLabel implements TableCellRenderer{
    final Border blueBorder=BorderFactory.createLineBorder(Color.BLUE);
    final Border emptyBorder=BorderFactory.createEmptyBorder();
    /**
     * Creates a new instance of JCheckBoxTableCellRenderer
     */
    public JImageFromResourceTableCellRenderer() {
        //setOpaque(false);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (isSelected) {
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        }else{
            setForeground(table.getForeground());
            setBackground(table.getBackground());
        }
        if(hasFocus){
            setBorder(BorderFactory.createLineBorder(new Color(63,145,192,255),1));
        }else{
            setBorder(BorderFactory.createLineBorder(new Color(128,128,128,255),0));
        }
        if(value!=null){
            
            String fileName=(String)value;
            this.setIcon(new javax.swing.ImageIcon(getClass().getResource(fileName)));
        }
        return this;
    }    
}
