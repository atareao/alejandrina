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
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import es.atareao.alejandria.lib.Convert;
import es.atareao.alejandria.lib.Tapadera;
import java.io.File;

/**
 *
 * @author Propietario
 */
public class JImageTapaderaListCellRenderer extends JImage implements ListCellRenderer {
    final Border blueBorder=BorderFactory.createLineBorder(Color.BLUE);
    final Border emptyBorder=BorderFactory.createEmptyBorder();
    /**
     * Creates a new instance of JCheckBoxTableCellRenderer
     */
    public JImageTapaderaListCellRenderer() {
        setOpaque(true);
    }

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (isSelected) {
            setForeground(list.getSelectionForeground());
            setBackground(list.getSelectionBackground());
        }else{
            setForeground(list.getForeground());
            setBackground(list.getBackground());
        }
        if(cellHasFocus){
            setBorder(BorderFactory.createLineBorder(new Color(63,145,192,255),1));
        }else{
            setBorder(BorderFactory.createLineBorder(new Color(128,128,128,255),0));
        }
        if(value!=null){
            this.setImage((String)value);
        }
        return this;
    }
}
