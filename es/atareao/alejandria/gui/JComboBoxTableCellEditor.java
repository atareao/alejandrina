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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Propietario
 */
public class JComboBoxTableCellEditor extends AbstractCellEditor implements ActionListener, TableCellEditor, Serializable {
    protected JDefaultComboBox comboBox;
    protected int _row;
    protected int _column;
    public JComboBoxTableCellEditor() {
        comboBox = new JDefaultComboBox();
        //AutoCompleteDecorator.decorate(comboBox);
        this.comboBox.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        // hitting enter in the combo box should stop cellediting (see below)
        this.comboBox.addActionListener(this);
        // remove the editor's border - the cell itself already has one
        ((JComponent) comboBox.getEditor().getEditorComponent()).setBorder(null);        
        //comboBox.setHorizontalAlignment(SwingConstants.CENTER);
        comboBox.setBackground( Color.white);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column) {
        //setSelectedItem(value);
        comboBox.setSelectedItem((String)value);
        comboBox.setForeground(table.getSelectionForeground());
        comboBox.setBackground(table.getSelectionBackground());
        this._column=column;
        this._row=row;
        return comboBox;
    }
    @Override
    public Object getCellEditorValue() {
        if(comboBox.getSelectedItem()==null){
            comboBox.setSelectedIndex(0);
        }
        return ((DefaultComboBoxElement)comboBox.getSelectedItem()).getKey();
    }
    public int getRow(){
        return _row;
    }
    public int getColumn(){
        return _column;
    }
    public void setElements(DefaultComboBoxElement[] elements){
        comboBox.setElements(elements);
    }
    public void setElements(Vector<DefaultComboBoxElement> elements){
        comboBox.setElements(elements);
    }
    public DefaultComboBoxElement[] getElements(){
        int size=comboBox.getListSize();
        if(size>0){
            DefaultComboBoxElement[] elements=new DefaultComboBoxElement[size];
            for(int contador=0;contador<size;contador++){
                elements[contador]=comboBox.getElementAt(contador);
            }
            return elements;
        }
        return null;
    }
    private void setValue(DefaultComboBoxElement value) {
        comboBox.setSelectedItem(value);
    }
    public void setSelectedElement(DefaultComboBoxElement selected){
        int size=comboBox.getListSize();
        if(size>0){
            for(int contador=0;contador<size;contador++){
                if(comboBox.getElementAt(contador).equals(selected)){
                    comboBox.setSelectedIndex(contador);
                    return;
                }
            }
        }
    }
    // Implementing ActionListener
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        // Selecting an item results in an actioncommand "comboBoxChanged".
        // We should ignore these ones.
        
        // Hitting enter results in an actioncommand "comboBoxEdited"
        if(e.getActionCommand().equals("comboBoxEdited")) {
            stopCellEditing();
        }
    }
    @Override
    public boolean stopCellEditing() {
        if (comboBox.isEditable()) {
            // Notify the combo box that editing has stopped (e.g. User pressed F2)
            comboBox.actionPerformed(new ActionEvent(this, 0, ""));
        }
        fireEditingStopped();
        return true;
    }
    
    
}