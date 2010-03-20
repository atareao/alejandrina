/*
 * JCheckBoxTableCellEditor.java
 *
 * Created on 16 de agosto de 2007, 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import es.atareao.alejandria.lib.Convert;
import es.atareao.alejandria.lib.DateUtils;
import java.util.Date;


/**
 *
 * @author Propietario
 */
public class JDateChooserTableCellEditor extends AbstractCellEditor implements TableCellEditor{
    private JDateChooser dateField;
    private JTable _table;
    private int _row=-1;
    private int _column=-1;

    public JDateChooserTableCellEditor() {
        dateField=new JDateChooser();
        dateField.setEditable(true);        
        dateField.addPropertyChangeListener(new PropertyChangeListener(){
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("DateChanged")){
                    if((_table!=null)&&(_row>-1)&&(_column>-1)){
                        _table.setValueAt(Convert.toStringDateSql(dateField.getDate()), _row, _column);
                    }
                }
            }
            
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column) {
        this._table=table;
        this._row=row;
        this._column=column;
        if(value!=null){
            if(value instanceof Date){
                dateField.setDate((Date)value);
            }else{
                if(value instanceof String){
                    dateField.setDate(Convert.toDateSql((String)value));
                }else{
                    dateField.setDate(DateUtils.Ahora());
                }
            }
        }
        dateField.setForeground(table.getSelectionForeground());
        dateField.setBackground(table.getSelectionBackground());
        return dateField;
    }
    @Override
    public Object getCellEditorValue() {
        return Convert.toStringDateSql(dateField.getDate());
        
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
}