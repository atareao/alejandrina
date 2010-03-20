/*
 * JComboBoxHorizontalSelection
 *
 * File created on 26-dic-2009
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

import javax.swing.JComboBox;
import javax.swing.JPopupMenu;

/**
 *
 * @author atareao
 */

public class JComboBoxHorizontalSelection extends JComboBox{
    //
    //********************************CONSTANTES********************************
    //

    //
    //******************************CONSTRUCTORES*******************************
    //

    //
    //********************************METODOS***********************************
    //

    //
    //**************************METODOS AUXILIARES******************************
    //
    private void getOut(){
        int x = 0;
        int y = this.getY();
	this.gethSelection().show(this, x, y);
        this.setOut(!this.isOut());
    }

    //
    // *********************************CAMPOS*********************************
    //
    private JPopupMenu _hSelection;
    private boolean _out;
    //
    //**************************METODOS DE ACCESO*******************************
    //

    /**
     * @return the _hSelection
     */
    public JPopupMenu gethSelection() {
        return _hSelection;
    }

    /**
     * @param hSelection the _hSelection to set
     */
    public void sethSelection(JPopupMenu hSelection) {
        this._hSelection = hSelection;
    }

    /**
     * @return the _out
     */
    public boolean isOut() {
        return _out;
    }

    /**
     * @param out the _out to set
     */
    public void setOut(boolean out) {
        this._out = out;
    }
}
