/*
 * ***********************Software description*********************************
 * SimpleStringValidator.java
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

package es.atareao.alejandria.val;

//
//********************************IMPORTACIONES*********************************
//
/**
 *
 * @author Lorenzo Carbonell
 */
public abstract class SimpleStringValidator implements SimpleValidator{


    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public SimpleStringValidator(String value){
        this.setValue(value);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public abstract boolean validate();
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private String _value;

     // </editor-fold>
    

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public String getValue() {
        return _value;
    }

    public void setValue(String value) {
        this._value = value;
    }
    // </editor-fold> 
}
