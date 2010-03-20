/*
 * JCheckBoxTableCellEditor.java
 *
 * Created on 16 de agosto de 2007, 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.table.TableCellEditor;
import javax.swing.text.JTextComponent;


/**
 *
 * @author Propietario
 */
public abstract class JBasicFieldTableCellEditor extends AbstractCellEditor implements TableCellEditor,JFieldMov{
    private static final long serialVersionUID=0L;
    protected JTextComponent _component;
    protected int _row;
    protected int _column;
    protected JMTable _table;
    public JBasicFieldTableCellEditor(JTextComponent component) {
        _component=component;
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
                        if(_component.getCaretPosition()==0){
                            _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_LEFT);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_KP_DOWN:
                        _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_DOWN);
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_KP_RIGHT:
                        if(_component.getCaretPosition()==_component.getText().length()){
                            _component.firePropertyChange(EDIT_STOP,PROP_NONE,PROP_RIGHT);
                            return;
                        }
                        break;
                }
            }
            
        });
        _component.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if(!evt.getOppositeComponent().equals(_table)){
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
    public abstract void setHorizontalAlignment(int horizontalAlignment);
    
    public abstract int getHorizontalAlignment();
}