/*
 * JScrollTable
 *
 * File created on 21-mar-2010
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

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;

/**
 *
 * @author atareao
 */

public class JScrollTable extends JScrollPane{
    //
    //********************************CONSTANTES********************************
    //
    private JMTable _rowHeaderTable;
    private JMTable _hidedTable;
    private JMTable _fixedTable;
    private JMTable _mainTable;
    private AutonumericRowHeaderRenderer _rowHeader;
    //
    // *********************************CAMPOS*********************************
    //
    public JScrollTable(){
        super();
        _fixedTable=new JMTable();
        _mainTable=new JMTable();
        //
        _fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        _mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //
        _fixedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        _mainTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //
        JViewport fixedTableViewport = new JViewport();
        fixedTableViewport.setView(_fixedTable);
        fixedTableViewport.setPreferredSize(_fixedTable.getPreferredSize());
        setRowHeaderView(fixedTableViewport);
        setCorner(JScrollPane.UPPER_LEFT_CORNER, _fixedTable.getTableHeader());
        this.setViewportView(_mainTable);
    }

    /**
     * @return the _table
     */

    /**
     * @param table the _table to set
     */
    public void setTable(JMTable table) {
        this._mainTable = table;
    }
    //
    //******************************CONSTRUCTORES*******************************
    //

    //
    //********************************METODOS***********************************
    //

    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
}
