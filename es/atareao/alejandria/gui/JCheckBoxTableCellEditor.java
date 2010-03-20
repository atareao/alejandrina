/*
 * JCheckBoxTableCellEditor.java
 *
 * Created on 16 de agosto de 2007, 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.TableCellEditor;
import es.atareao.alejandria.lib.Convert;

/**
 *
 * @author Propietario
 */
public class JCheckBoxTableCellEditor extends AbstractCellEditor implements TableCellEditor {
    final Border blueBorder=BorderFactory.createLineBorder(Color.BLUE);
    protected JCheckBox checkBox;
    protected int _row;
    protected int _column;
    public JCheckBoxTableCellEditor() {
        
        checkBox = new JCheckBox();
        checkBox.setBackground( Color.white);
        checkBox.setBorder(blueBorder);
        checkBox.setHorizontalAlignment(SwingConstants.CENTER);
        checkBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxItemStateChanged(evt);
            }
        });        
        
    }
    private void checkBoxItemStateChanged(java.awt.event.ItemEvent evt) {
        this.stopCellEditing();
    }
    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column) {
        try {
            checkBox.setSelected(Convert.toBoolean(value));
        } catch (Exception ex) {
            checkBox.setSelected(false);
        }
        
        checkBox.setForeground(table.getSelectionForeground());
        checkBox.setBackground(table.getSelectionBackground());
        checkBox.setBorder(blueBorder);
        this._column=column;
        this._row=row;
        return checkBox;
    }
    public Object getCellEditorValue() {
        return Boolean.valueOf(checkBox.isSelected());
    }
    public boolean isMarked(){
        return checkBox.isSelected();
    }
    public int getRow(){
        return _row;
    }
    public int getColumn(){
        return _column;
    }
}