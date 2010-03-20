/*
 * ***********************Software description*********************************
 * SimpleNIFValidator.java
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
public class SimpleNotEmptyValidator extends SimpleStringValidator{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public SimpleNotEmptyValidator(String value){
        super(value);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    @Override
    public boolean validate(){
        if((this.getValue()!=null)&&(this.getValue().length()>0)){
            return true;
        }
        return false;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
