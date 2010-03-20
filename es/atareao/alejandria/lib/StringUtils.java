/*
 * ***********************Software description*********************************
 * StringUtils.java
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
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.StringTokenizer;

/**
 *
 * @author Lorenzo Carbonell
 */
public class StringUtils {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    public static String toCompare(String cadena){
        String temporal=cadena.trim().toLowerCase().replaceAll(" ","");
        temporal=temporal.replace('á','a');
        temporal=temporal.replace('é','e');
        temporal=temporal.replace('í','i');
        temporal=temporal.replace('ó','o');
        temporal=temporal.replace('ú','u');   
        return temporal;
    }
    public static String sinAcentos(String cadena){
        String temporal=cadena.trim().toLowerCase();
        temporal=temporal.replace('á','a');
        temporal=temporal.replace('é','e');
        temporal=temporal.replace('í','i');
        temporal=temporal.replace('ó','o');
        temporal=temporal.replace('ú','u');
        return temporal;
    }    
    public static String normalizaCadena(String cadena){
        if(cadena==null){
            cadena=new String("");
            return cadena;
        }
        if(cadena.equals("")){
            return "";
        }
        StringTokenizer st=new StringTokenizer(cadena," ");
        if(st.countTokens()>1){
            StringBuffer sb=new StringBuffer();
            while(st.hasMoreTokens()){
                sb.append(primeraMayusculas(st.nextToken()));
                sb.append(" ");
            }        
            return sb.toString().trim();
        }else{
            return(primeraMayusculas(cadena));
        }
    }
    public static String primeraMayusculas(String cadena){
        cadena=cadena.trim();
        if(cadena.length()>1){
            StringBuffer sb=new StringBuffer();
            String izquierda=cadena.substring(0,1);
            String derecha=cadena.substring(1,cadena.length());
            sb.append(izquierda.toUpperCase());
            sb.append(derecha.toLowerCase());
            return sb.toString();
        }else{
            return cadena.toUpperCase();
        }
    }
    public static String formatPk(String number){
        return formatPk(Convert.toDouble(number));
    }
    public static String formatPk(double number){
        DecimalFormat dc=new DecimalFormat("0,000.000");
        DecimalFormatSymbols dfs=dc.getDecimalFormatSymbols();
        dfs.setGroupingSeparator('+');
        dc.setDecimalFormatSymbols(dfs);
        return dc.format(number);

    }
    public static String formatCod36(long numero,String cadena){
        String codigo;
        String resultado;
        codigo=cod36(numero);
        if(codigo.length()<cadena.length()){
            resultado=cadena.substring(0,cadena.length()-codigo.length())+codigo;
        }else{
            resultado=codigo;
        }
        return resultado;
    }
    public static String formatTelefono(String cadena){
        if((cadena!=null)&&(cadena.length()>3)){
            String derecha=cadena.substring(cadena.length()-3,cadena.length());
            String izquierda=cadena.substring(0,cadena.length()-3);
            return formatTelefono(izquierda)+" "+derecha;
        }else{
            return cadena;
        }
    }
    public static String rellena(String cadena,String relleno){
        if(cadena.length()<relleno.length()){
            return relleno.substring(0,relleno.length()-cadena.length())+cadena;
        }
        return cadena;
    }
    public static String repite(String cadena, int veces){
        StringBuffer sb=new StringBuffer();
        for(int contador=0;contador<veces;contador++){
            sb.append(cadena);
        }
        return sb.toString();
    }    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 
    private static String cod36(long numero){
        long ParteEntera;
        String TParteEntera;
        long ParteDecimal;
        String TParteDecimal;
        if(numero<10){
            return chr(numero+48);
        }else{
            if(numero<36){
                return chr(numero-10+65);
            }else{
                ParteEntera=numero/36;
                TParteEntera=cod36(ParteEntera);
                ParteDecimal=numero-ParteEntera*36;
                TParteDecimal=cod36(ParteDecimal);
                return TParteEntera+TParteDecimal;
            }
        }
    }
    private static String chr(long i){
        return Convert.toString((char)i);
    }
    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
