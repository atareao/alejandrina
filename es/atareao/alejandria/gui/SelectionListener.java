/*
 * SelectionListener
 *
 * File created on 09-mar-2010
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

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author atareao
 */

public class SelectionListener implements ListSelectionListener {

    //
    //********************************CONSTANTES********************************
    //

    //
    // *********************************CAMPOS*********************************
    //
    JTable _table;
    // It is necessary to keep the table since it is not possible
    // to determine the table from the event's source
    //
    //******************************CONSTRUCTORES*******************************
    //
    public SelectionListener(JTable table) {
        this._table = table;
    }
    //
    //********************************METODOS***********************************
    //
    @Override
    public void valueChanged(ListSelectionEvent e) {
    // If cell selection is enabled, both row and column change events are fired
        if (e.getSource() == _table.getSelectionModel() && _table.getRowSelectionAllowed()) {
        //Row selection changed
            int first = e.getFirstIndex();
            int last = e.getLastIndex();
            System.out.println("Row selection changed");
            _table.firePropertyChange("Row selection changed", true, true);
        } else if (e.getSource() == _table.getColumnModel().getSelectionModel() && _table.getColumnSelectionAllowed() ){
            // Column selection changed
            int first = e.getFirstIndex();
            int last = e.getLastIndex();
            System.out.println("Column selection changed");
        }
        if (e.getValueIsAdjusting()) {
            // The mouse button has not yet been released
        }
    }

    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
}
