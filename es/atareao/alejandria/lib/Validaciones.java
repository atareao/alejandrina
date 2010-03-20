/*
 * ***********************Software description*********************************
 * Validaciones.java
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
public class Validaciones {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private static String NIF_STRING_ASOCIATION = "TRWAGMYFPDXBNJZSQVHLCKET";
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    /**
     * Devuelve un NIF completo a partir de un DNI. Es decir, añade la letra del NIF
     * @param dni dni al que se quiere añadir la letra del NIF
     * @return NIF completo.
     */
    public static String letraNIF(int dni) {
        return ""+NIF_STRING_ASOCIATION.charAt(dni % 23);
    }
    public boolean validateNIF(String nif){
        int dni=Integer.valueOf(nif.substring(0,nif.length()-1));
        String letra=nif.substring(nif.length());
        if(letra.equals(letraNIF(dni))){
            return true;
        }
        return false;
    }
    public boolean validateNIE(String nie){
        int dni=Integer.valueOf(nie.substring(1,nie.length()-1));
        String letra=nie.substring(nie.length());
        if(letra.equals(letraNIF(dni))){
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
