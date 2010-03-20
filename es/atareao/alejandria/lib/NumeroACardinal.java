/*
 * NumeroACardinal.java
 *
 * Copyright (c) 2010 Lorenzo Carbonell
 * email: lorenzo.carbonell.cerezo@gmail.com
 * website: http://www.atareao.es
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */


package es.atareao.alejandria.lib;
//
//********************************IMPORTACIONES*********************************
//
/**
 * La clase NumeroACardinal convierte enteros long <= 1E18 ( un trillón ) a su
 * expresión cardinal mediante el uso de la recursividad
 *
 * @author Juan José Vidal
 * @version 1.0
 *
 * (Enero 2.004)
 */
public class NumeroACardinal {
    //
    //********************************CONSTANTES********************************
    //
    // cardinales de numeros de cero a quince
    private static String[] ini =
    {"cero ", "uno ", "dos ", "tres ", "cuatro ", "cinco ", "seis ", "siete ",
     "ocho ", "nueve ", "diez ", "once ", "doce ", "trece ", "catorce ",
     "quince "};
     
     // cardinales de decenas de trenta a noventa
     private static String[] med =
     {"trenta ", "cuarenta ", "cincuenta ", "sesenta ", "setenta ", "ochenta ",
      "noventa "};
      
      // cardinales de centenas de doscientoa a novecientos y mil
      private static String[] fin =
      { "doscientos ", "trescientos ", "cuatrocientos ", "quinientos ",
        "seiscientos ", "setecientos ", "ochocientos ", "novecientos ", "mil "};
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Crea una nueva instancia de NumeroACardinal
     */
    public NumeroACardinal() {
    }
    //
    //********************************METODOS***********************************
    //
    /**
     *
     * Convertidor a cardinales de numeros "num" de "0" a "1E18"
     *
     * @param num Entero long a convertir a su expresión cardinal
     * @return Expresión cardinal de "num"
     */
    public static String convierte(long num){
        if ( num / 30 == 0 ) return deCeroAventinueve(num);
        else if ( num / 101 == 0 ) return deTrentaAcien(num);
        else if ( num / 1001 == 0 ) return deCientoUnoAmil(num);
        else if ( num / 1000001 == 0 ) return deNumIniAnumFin(num,
        1000, 1000000, "mil ", "mil ", "un millón ");
        else if ( num / 1000000000001L == 0 ) return deNumIniAnumFin(num,
        1000000L, 1000000000000L, "un millón ", "millones ", "un billón ");
        else if ( num / 1000000000000000001L == 0 ) return deNumIniAnumFin(num,
        1000000000000L, 1000000000000000000L, "un billón ", "billones ",
        "un trillón ");
        else return "Numero demasiado grande";
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    /*
     * Convertidor a cardinales de numeros "num" de "0" a "29"
     */
    private static String deCeroAventinueve(long num){
        if ( num < 16 ) return ini[(int)num];
        else if ( num < 20 ) return "dieci" + convierte(num - 10);
        else if (num == 20) return "veinte ";
        else return "veinti" + convierte(num - 20);
    }
    
    /*
     * Convertidor a cardinales de numeros "num" de "30" a "100"
     */
    private static String deTrentaAcien(long num){
        String res = new String();
        long numAux = num / 10;
        num -= numAux * 10;
        if ( numAux < 10 ) res = med[(int)numAux - 3];
        else if ( numAux == 10 ) return "cien ";
        if ( num == 0 ) return res;
        else  return res += "y " + convierte(num);
    }
    
    /*
     * Convertidor a cardinales de numeros "num" de "101" a "1000"
     */
    private static String deCientoUnoAmil(long num){
        String res = new String();
        long numAux = num / 100;
        num -= numAux * 100;
        if ( numAux == 1 ) return "ciento " + convierte(num);
        else res = fin[(int)numAux - 2];
        if ( num == 0 ) return res;
        else return res += convierte(num);
    }
    
   /*
    * Convertidor a cardinales de numeros "num"  mayores que "1000" de "numIni" 
    * a "numFin"; "carIni" es la expresión cardinal de "numIni", "carFin" es la
    * expresión cardinal de "numFin" y "carMed" es la terminación de la 
    * expresión cardinal de los numeros entre "carIni" y "carFin"
    */
    private static String deNumIniAnumFin(long num, long numIni, long numFin,
    String carIni, String carMed, String carFin){
        String res = new String();
        long numAux = num / numIni;
        num -= numAux * numIni;
        if ( numAux == 1 ) return carIni + convierte(num);
        else if ( numAux < numFin / numIni ) res = convierte(numAux) + carMed;
        else if ( numAux == numFin / numIni ) return carFin;
        if ( num == 0 ) return res;
        else return res += convierte(num);
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
}
