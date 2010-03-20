/*
 * JCheckBoxTableCellEditor.java
 *
 * Created on 16 de agosto de 2007, 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.table.TableCellEditor;


/**
 *
 * @author Propietario
 */
public abstract class JBasicComboBoxTableCellEditor extends AbstractCellEditor implements ActionListener, TableCellEditor, Serializable, JFieldMov {
    private static final long serialVersionUID=0L;
    protected JComboBox _component;
    protected int _row;
    protected int _column;
    protected JMTable _table;
    public JBasicComboBoxTableCellEditor(JComboBox component) {
        _component=component;
        _component.putClientProperty("JComboBox.isTableCellEditor", Boolean.TRUE);
        // hitting enter in the combo box should stop cellediting (see below)
        _component.addActionListener(this);
        _component.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                stopCellEditing();
            }
        });
        // remove the editor's border - the cell itself already has one
        ((JComponent) _component.getEditor().getEditorComponent()).setBorder(null);
        _component.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evt) {
                switch (evt.getKeyCode()){
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_KP_UP:
                        _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_UP);
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_KP_LEFT:
                        _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_LEFT);
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_KP_DOWN:
                        _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_DOWN);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_KP_RIGHT:
                        _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_RIGHT);
                        break;
                }
            }
            
        });
        _component.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if(_component.isEditable()){
                    _component.showPopup();
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if((evt.getOppositeComponent()!=null)&&(!evt.getOppositeComponent().equals(_table))){
                    stopCellEditing();
                    _table.saveRow();
                }
            }
        });
        _component.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals(EDIT_STOP)){
                    stopCellEditing();
                    switch(((Integer)evt.getNewValue()).intValue()){
                        case PROP_LEFT:
                            if(_column>0){
                                _table.setColumnSelectionInterval(_column-1,_column-1);
                                _table.setRowSelectionInterval(_table.getSelectedRow(),_table.getSelectedRow());
                            }                             
                            break;
                        case PROP_RIGHT:
                            if(_column<_table.getColumnCount()-1){
                                _table.setColumnSelectionInterval(_column+1,_column+1);
                                _table.setRowSelectionInterval(_table.getSelectedRow(),_table.getSelectedRow());
                            }                             
                            break;
                        case PROP_UP:
                            if(_table.getSelectedRow()>1){
                                _table.setColumnSelectionInterval(_column,_column);
                                _table.setRowSelectionInterval(_table.getSelectedRow()-1,_table.getSelectedRow()-1);
                            }
                            break;
                        case PROP_DOWN:
                            if(_table.getSelectedRow()<_table.getRowCount()-1){
                                _table.setColumnSelectionInterval(_column,_column);
                                _table.setRowSelectionInterval(_table.getSelectedRow()+1,_table.getSelectedRow()+1);
                            }
                            break;
                    }
                    fireEditingStopped();
                }
            }
        });
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
        if (_component.isEditable()) {
            // Notify the combo box that editing has stopped (e.g. User pressed F2)
            _component.actionPerformed(new ActionEvent(this, 0, ""));
        }
        fireEditingStopped();
        return true;
    }
    /**
     * The default method returns true for the {@link MouseEvent} and false 
     * for any other events.
     * 
     * @param event the event to check
     *
     * @return true if the passed event is the mouse event and false otherwise.
     */
    @Override
    public boolean isCellEditable(EventObject event){
        return true;
    }

    /**
     * Returns true to indicate that the editing cell can be selected.
     * 
     * The default method returns true without action but may be overridden
     * in derived classes for more specific behavior.
     * 
     */
    @Override
    public boolean shouldSelectCell(EventObject anEvent){
        return false;
    }
    public int getRow(){
        return _row;
    }
    public int getColumn(){
        return _column;
    }
    public Object[] getElements(){
        if(this._component.getItemCount()>0){
            Object[] elements=new Object[this._component.getItemCount()];
            for(int contador=0;contador<this._component.getItemCount();contador++){
                elements[contador]=this._component.getItemAt(contador);
            }
            return elements;            
        }
        return null;
    }
    public void setSelectedElement(Object selected){
        this._component.setSelectedItem(selected);
    }
    public abstract void setHorizontalAlignment(int horizontalAlignment);
    
    public abstract int getHorizontalAlignment();
}