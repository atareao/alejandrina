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
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Propietario
 */
public class JImageTableCellRenderer extends JImage implements TableCellRenderer{
    //
    private Color alternateColor = new Color(245, 245, 220);
    /**
     * Creates a new instance of JCheckBoxTableCellRenderer
     */
    public JImageTableCellRenderer() {
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Border mEmptyBorder = BorderFactory.createEmptyBorder();
        Border mHighLightBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
        Color mFocusCellForeground=table.getSelectionForeground();
        Color mFocusCellBackground=table.getSelectionBackground();
        Color mCellForeground=table.getForeground();
        Color mCellBackground=table.getBackground();
        if (isSelected) {
            setForeground(mFocusCellForeground);
            setBackground(mFocusCellBackground);
        }else{
            setForeground(mCellForeground);
            setBackground((row % 2 == 0 ? alternateColor : mCellBackground));
        }
        if(hasFocus){
            setBorder(mHighLightBorder);
        }else{
            setBorder(mEmptyBorder);
        }
        if(value!=null){
            this.setImage((String)value);
        }
        return this;
    }    
}
