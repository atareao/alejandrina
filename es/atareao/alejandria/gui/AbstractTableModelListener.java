/*
 * AbstractTableModelListener.java
 *
 * Created on 17 de agosto de 2007, 10:25
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package es.atareao.alejandria.gui;

/**
 *
 * @author Propietario
 */
import javax.swing.table.TableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class AbstractTableModelListener
    implements TableModelListener
{
    public final void tableChanged(TableModelEvent e)
    {
        TableModel source = (TableModel)e.getSource();
        int first = e.getFirstRow(), last = e.getLastRow();

        if (first == TableModelEvent.HEADER_ROW)
        {
            structureChanged(source);
            return;
        }
        if (first == 0 && last == Integer.MAX_VALUE)
        {
            dataChanged(source);
            return;
        }

        switch (e.getType())
        {
            case TableModelEvent.DELETE:
                rowsRemoved(source, first, last); break;
            case TableModelEvent.UPDATE:
                if (e.getColumn() == TableModelEvent.ALL_COLUMNS)
                    rowsChanged(source, first, last);
                else
                    if(first==last){
                        cellChanged(source, first, e.getColumn());
                    }else{
                        cellsChanged(source, first, last, e.getColumn());
                    }
                break;
            case TableModelEvent.INSERT: rowsAdded(source, first, last);break;
            default: unknownEvent(e);
         }
    }

    protected void unknownEvent(TableModelEvent e)
    {
    }

    protected void cellsChanged(TableModel source, int first, int last, int column)
    {
        rowsChanged(source, first, last);
    }

    protected void cellChanged(TableModel source,int fila,int columna){};
    protected void structureChanged(TableModel source){};
    protected void dataChanged(TableModel source){};
    protected void rowsRemoved(TableModel source, int first, int last){};
    protected void rowsChanged(TableModel source, int first, int last){};
    protected void rowsAdded(TableModel source, int first, int last){};
}