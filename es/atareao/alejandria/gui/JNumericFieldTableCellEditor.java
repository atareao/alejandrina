/*
 * JNumericFieldTableCellEditor.java
 *
 * Created on 14 de marzo de 2010, 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import es.atareao.alejandria.lib.Convert;


/**
 *
 * @author Propietario
 */
public class JNumericFieldTableCellEditor extends JBasicFieldTableCellEditor implements TableCellEditor,JFieldMov{
    private static final long serialVersionUID=0L;
    public JNumericFieldTableCellEditor(int decimals) {
        super(new JNumericField());
        ((JNumericField)this._component).setDecimals(decimals);
    }
    @Override
    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column) {
        this._table=(JMTable)table;
        this._column=column;
        this._row=row;
        table.setRowSelectionInterval(row,row);
        ((JNumericField)this._component).setValue(Convert.toDouble(value));
        ((JNumericField)this._component).setForeground(table.getSelectionForeground());
        ((JNumericField)this._component).setBackground(table.getSelectionBackground());
        this._column=column;
        return ((JNumericField)this._component);
    }
    @Override
    public Object getCellEditorValue() {
        return ((JNumericField)this._component).getDouble();
    }
    public void setTimesTen(int timesTen){
        ((JNumericField)this._component).setTimesTen(timesTen);
    }

    @Override
    public void setHorizontalAlignment(int horizontalAlignment) {
        ((JNumericField)this._component).setHorizontalAlignment(horizontalAlignment);
    }

    @Override
    public int getHorizontalAlignment() {
        return ((JNumericField)this._component).getHorizontalAlignment();
    }}