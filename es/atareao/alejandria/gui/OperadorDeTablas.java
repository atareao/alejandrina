/*
 * OperadorDeTablas
 *
 * File created on 11-mar-2010
 * Copyright (c) 2009 Lorenzo Carbonell
 * email: lorenzo.carbonell.cerezo@gmail.com
 * website: http://www.atareao.es
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package es.atareao.alejandria.gui;

/**
 *
 * @author atareao
 */

public abstract class OperadorDeTablas {
    //
    //********************************CONSTANTES********************************
    //
    public OperadorDeTablas(JMTable table){
        _table=table;
    }

    //
    // *********************************CAMPOS*********************************
    //
    private JMTable _table;
    //
    //******************************CONSTRUCTORES*******************************
    //

    //
    //********************************METODOS***********************************
    //
    public abstract void refresca();

    public abstract boolean saveRow(int row,int columna);

    public abstract boolean newRow();

    public boolean newRow(Object[] filedata){
        int fila=getTable().getRowCount()-1;
        int filaAnterior=fila-1;
        if (fila<0){
            fila=0;
        }
        if(filaAnterior>0){
            Object valorAnterior=getTable().getDefaultTableModel().getValueAt(filaAnterior,1);
            if((valorAnterior==null)||(((String)valorAnterior).length()==0)){
                return false;
            }
        }
        getTable().getDefaultTableModel().addRow(filedata);
        getTable().setFilaModificada(getTable().getRowCount());
        /*
        int selectedRow=getTable().getSelectedRow();
        int selectedColumn=getTable().getSelectedColumn();
        if(!saveRow(fila,selectedColumn)){
            refresca();
            getTable().setColumnSelectionInterval(selectedColumn, selectedColumn);
            getTable().setRowSelectionInterval(selectedRow-1, selectedRow-1);
            return false;
        }
         * 
         */
        return true;
    }

    public abstract boolean deleteRow(int Row);
    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
    /**
     * @return the _table
     */
    public JMTable getTable() {
        return _table;
    }
}
