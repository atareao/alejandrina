/*
 * ***********************Software description*********************************
 * GuiNotEmptyValidator.java 
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
import javax.swing.JTextField;
import javax.swing.JDialog;
import es.atareao.alejandria.val.SimpleNotEmptyValidator;
/**
 *
 * @author Lorenzo Carbonell
 */
public class GuiNotEmptyValidator extends AbstractValidator {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public GuiNotEmptyValidator(JDialog parent, JTextField c) {
        super(parent, c, "El valor introducido no es v�lido, no puede estar vac�o");
    }
    public GuiNotEmptyValidator(JDialog parent, JTextField c, String message) {
        super(parent, c, message);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" M�todos  ">
    
    @Override
    protected boolean validationCriteria(JComponent c) {
        String value=((JTextField)c).getText();
        SimpleNotEmptyValidator snv=new SimpleNotEmptyValidator(value);
        if(snv.validate()){
            return true;
        }
        return false;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" M�todos auxiliares  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" M�todos de acceso  ">

    // </editor-fold> 
}
