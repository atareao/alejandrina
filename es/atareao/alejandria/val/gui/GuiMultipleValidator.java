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
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.JDialog;
/**
 *
 * @author Lorenzo Carbonell
 */
public class GuiMultipleValidator extends AbstractValidator {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public GuiMultipleValidator(JDialog parent, JTextField c,Vector<AbstractValidator> validators) {
        super(parent, c, "El valor introducido no es válido, no puede estar vacío");
        this.setValidators(validators);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    
    protected boolean validationCriteria(JComponent c) {
        for(AbstractValidator validator:this.getValidators()){
            if(!validator.validationCriteria(c)){
                return false;
            }
        }
        return true;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private Vector<AbstractValidator>_validators;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public Vector<AbstractValidator> getValidators() {
        return _validators;
    }

    public void setValidators(Vector<AbstractValidator> validators) {
        this._validators = validators;
    }

    // </editor-fold> 
}
