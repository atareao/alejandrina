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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.TableCellEditor;
import es.atareao.alejandria.lib.Convert;


/**
 *
 * @author Propietario
 */
public class JDateFieldTableCellEditor extends AbstractCellEditor implements TableCellEditor{
    protected JDateChooser dateField;
    protected int _row;
    protected int _column;
    protected JTable _table;
    public JDateFieldTableCellEditor(JTable table, int column) {
        this._column=column;
        this._table=table;
        dateField=new JDateChooser();
        //dateField.setHorizontalAlignment(SwingConstants.CENTER);
        dateField.setEditable(false);
        dateField.setBackground( Color.white);
        dateField.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("DateChanged)")){
                    stopCellEditing();
                    fireEditingStopped();
                }
            }
            
        });
        dateField.addPropertyChangeListener(new PropertyChangeListener(){

            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals(JDateField.EDIT_STOP)){
                    stopCellEditing();
                    switch(((Integer)evt.getNewValue()).intValue()){
                        case JDateField.PROP_LEFT:
                            if(_column>0){
                                _table.setColumnSelectionInterval(_column-1,_column-1);
                                _table.setRowSelectionInterval(_table.getSelectedRow(),_table.getSelectedRow());
                            }                             
                            break;
                        case JDateField.PROP_RIGHT:
                            if(_column<_table.getColumnCount()-1){
                                _table.setColumnSelectionInterval(_column+1,_column+1);
                                _table.setRowSelectionInterval(_table.getSelectedRow(),_table.getSelectedRow());
                            }                             
                            break;
                        case JDateField.PROP_UP:
                            if(_table.getSelectedRow()>1){
                                _table.setColumnSelectionInterval(_column,_column);
                                _table.setRowSelectionInterval(_table.getSelectedRow()-1,_table.getSelectedRow()-1);
                            }
                            break;
                        case JDateField.PROP_DOWN:
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

    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column) {
        dateField.setDate(Convert.toDate(value));
        dateField.setForeground(table.getSelectionForeground());
        dateField.setBackground(table.getSelectionBackground());
        this._column=column;
        this._row=row;
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                dateField.requestFocus();
            }
	});
        
        return dateField;
    }
    public Object getCellEditorValue() {
        return dateField.getDate();
        
    }
    public void setEditable(boolean editable){
        dateField.setEditable(editable);
    }
    public void setEnabled(boolean enabled){
        dateField.setEnabled(enabled);
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
}