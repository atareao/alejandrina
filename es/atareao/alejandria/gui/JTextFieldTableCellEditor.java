/*
 * JTextFieldTableCellEditor.java
 *
 * Created on 9 de marzo de 2010, 22:45
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellEditor;
import es.atareao.alejandria.lib.Convert;


/**
 *
 * @author Propietario
 */
public class JTextFieldTableCellEditor extends JBasicFieldTableCellEditor implements TableCellEditor,JFieldMov{
    private static final long serialVersionUID=0L;
    public JTextFieldTableCellEditor() {
        super(new JTextFieldExtended());
        ((JTextFieldExtended)this._component).setHorizontalAlignment(SwingConstants.CENTER);
    }


    @Override
    public Component getTableCellEditorComponent(JTable table,Object value,boolean isSelected,int row,int column) {
        this._table=(JMTable)table;
        this._column=column;
        this._row=row;
        table.setRowSelectionInterval(row,row);
        ((JTextFieldExtended)this._component).setText(Convert.toString(value));
        ((JTextFieldExtended)this._component).setForeground(table.getSelectionForeground());
        ((JTextFieldExtended)this._component).setBackground(table.getSelectionBackground());
        return ((JTextFieldExtended)this._component);
    }

    @Override
    public Object getCellEditorValue() {
        return ((JTextFieldExtended)this._component).getText();
    }

    @Override
    public void setHorizontalAlignment(int horizontalAlignment) {
        ((JTextFieldExtended)this._component).setHorizontalAlignment(horizontalAlignment);
    }

    @Override
    public int getHorizontalAlignment() {
        return ((JTextFieldExtended)this._component).getHorizontalAlignment();
    }
}