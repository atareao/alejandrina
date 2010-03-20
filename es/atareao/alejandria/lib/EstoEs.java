/*
 * ***********************Software description*********************************
 * EstoEs.java
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

package es.atareao.alejandria.lib;

//
//********************************IMPORTACIONES*********************************
//
/**
 *
 * @author Lorenzo Carbonell
 */
public class EstoEs {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public static boolean unEntero(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
    public static boolean unDecimal(String cadena){
        try{
            Float.parseFloat(cadena);
            return true;
        }catch(Exception ex){
            return false;
        }        
    }
    public static boolean unDoble(String cadena){
        try{
            Double.parseDouble(cadena);
            return true;
        }catch(Exception ex){
            return false;
        }        
    }    
    public static boolean unCaracter(String cadena){
        if(cadena.length()==1){
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
