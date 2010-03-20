/*
 * JTextFieldExtended
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

import java.awt.Component;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

    //
    //********************************METODOS***********************************
    //

    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
/**
 *
 * @author atareao
 */

public class JTextFieldExtended extends JTextField implements JFieldMov{
    //
    //********************************CONSTANTES********************************
    //
    private static final long serialVersionUID=0L;
    //
    // *********************************CAMPOS*********************************
    //
    private boolean _onFocusSelectAll=true;
    //
    //******************************CONSTRUCTORES*******************************
    //
    public JTextFieldExtended(){
    }
    @Override
    public void processFocusEvent(FocusEvent fe) {
        super.processFocusEvent(fe);
        Component focusOwner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        if (isDisplayable() && fe.getID()==FocusEvent.FOCUS_GAINED && focusOwner==this) {
            if(_onFocusSelectAll){
                this.setSelectionStart(0);
                this.setSelectionEnd(getText().length());
            }
        }
    }
    @Override
    protected boolean processKeyBinding(final KeyStroke ks,final KeyEvent e, final int condition, final boolean pressed) {
        if (hasFocus()) {
            return super.processKeyBinding(ks, e, condition, pressed);
        } else {
            requestFocus();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    processKeyBinding(ks, e, condition, pressed);
                }
            });
        return true;
        }
    }
    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  ">

    // Methods to get the field value
    public boolean isOnFocusSelectAll() {
        return _onFocusSelectAll;
    }

    public void setOnFocusSelectAll(boolean onFocusSelectAll) {
        this._onFocusSelectAll = onFocusSelectAll;
    }
    // </editor-fold>
}
