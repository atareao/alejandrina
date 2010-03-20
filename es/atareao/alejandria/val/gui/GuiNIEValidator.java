/*
 * ***********************Software description*********************************
 * GuiNIFValidator.java 
 * 
 * 
 * ***********************Software description*********************************
 * 
 * Copyright (C) 2008 - Lorenzo Carbonell
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * **************************Software License***********************************
 * 
 */

package es.atareao.alejandria.val.gui;
//
//********************************IMPORTACIONES*********************************
//
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import es.atareao.alejandria.val.SimpleNIEValidator;



/**
 *
 * @author Lorenzo Carbonell
 */
public class GuiNIEValidator  extends AbstractValidator {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public GuiNIEValidator(JDialog parent, JTextField c) {
        super(parent, c, "El valor introducido no es válido, tiene que ser un NIF");
    }
    public GuiNIEValidator(JDialog parent, JTextField c, String message) {
        super(parent, c, message);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    
    protected boolean validationCriteria(JComponent c) {
        String nif=((JFormattedTextField)c).getText();
        SimpleNIEValidator snv=new SimpleNIEValidator(nif);
        return snv.validate();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
