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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lorenzo Carbonell
 */
public class SimpleEmailValidator extends SimpleStringValidator{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public SimpleEmailValidator(String value){
        super(value);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public boolean validate(){
        Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
        String cadena=this.getValue();
        if((cadena!=null)&&(cadena.length()>0)){
            Matcher m = p.matcher(cadena);
            if(m.matches()){
                return true;
            }else{
                return false;
            }
        }else{
            return true;
        }        
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
