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
import es.atareao.alejandria.val.SimpleLengthValidator;

/**
 *
 * @author Lorenzo Carbonell
 */
public class GuiLengthValidator extends AbstractValidator {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public GuiLengthValidator(JDialog parent, JTextField c,boolean biggerThan,int length) {
        super(parent, c, createMessage(biggerThan,length));
        this.setSlv(new SimpleLengthValidator(c.getText(),length,biggerThan));
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    
    protected boolean validationCriteria(JComponent c) {
        String value=((JTextField)c).getText();
        this.getSlv().setValue(value);
        return this.getSlv().validate();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 
    private static String createMessage(boolean biggerThan,int length){
        String message="";
        if(biggerThan){
            message="El valor introducido no es válido, tiene que ser mayor que "+Integer.toString(length)+".";
        }else{
            message="El valor introducido no es válido, tiene que ser menor que "+Integer.toString(length)+".";
        }
        return message;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private SimpleLengthValidator _slv;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    public SimpleLengthValidator getSlv() {
        return _slv;
    }

    public void setSlv(SimpleLengthValidator slv) {
        this._slv = slv;
    }

    // </editor-fold> 
}
