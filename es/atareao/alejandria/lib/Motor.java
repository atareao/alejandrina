/*
 * Motor.java
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
import java.util.Random;
/**
 *
 * @author Protactino
 */
public class Motor {
    //
    //********************************CONSTANTES********************************
    //
    private static char[] MAYUSCULAS={'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    private static char[] MINUSCULAS={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    private static char[] NUMEROS={'0','1','2','3','4','5','6','7','8','9'};
    private static char[] SIGNOS={' ','¡','!','+','-','*','/','!','@','#','$','%','&','(',')','=','?','¿',';',':',',','.','<','>'};
    //
    // *********************************CAMPOS*********************************
    //
    private Random _generator;
    private int _numMayusculas=0;
    private int _numMinusculas=0;
    private int _numNumeros=0;
    private int _numSignos=0;
    private String _password;
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    private void inicializa(int mayusculas,int minusculas,int numeros, int signos)
    {
        this.setGenerator(new Random());
        this.setNumMayusculas(mayusculas);
        this.setNumMinusculas(minusculas);
        this.setNumNumeros(numeros);
        this.setNumSignos(signos);
        this.setPassword(this.genera().toString());
    }
    /**
     * Crea una nueva instancia de Motor
     */
    public Motor(int mayusculas) {
        inicializa(mayusculas,0,0,0);
    }
    public Motor(int mayusculas,int minusculas) {
        inicializa(mayusculas,minusculas,0,0);
    }
    public Motor(int mayusculas,int minusculas,int numeros) {
        inicializa(mayusculas,minusculas,numeros,0);
    }
    public Motor(int mayusculas,int minusculas,int numeros, int signos) {
        inicializa(mayusculas,minusculas,numeros,signos);
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    private Random getGenerator() {
        return _generator;
    }

    private void setGenerator(Random generator) {
        this._generator = generator;
    }

    public int getNumMayusculas() {
        return _numMayusculas;
    }

    public void setNumMayusculas(int numMayusculas) {
        this._numMayusculas = numMayusculas;
    }

    public int getNumMinusculas() {
        return _numMinusculas;
    }

    public void setNumMinusculas(int numMinusculas) {
        this._numMinusculas = numMinusculas;
    }

    public int getNumNumeros() {
        return _numNumeros;
    }

    public void setNumNumeros(int numNumeros) {
        this._numNumeros = numNumeros;
    }

    public int getNumSignos() {
        return _numSignos;
    }

    public void setNumSignos(int numSignos) {
        this._numSignos = numSignos;
    }

    public String getPassword() {
        return _password;
    }

    private void setPassword( String password) {
        this._password = password;
    }
    
    //
    //********************************METODOS***********************************
    //
    public String toString(){
        return desordena(this.getGenerator(),new StringBuffer(this.getPassword())).toString();
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    private StringBuffer genera(){
        StringBuffer sb=new StringBuffer();
        if(this.getNumMayusculas()>0){
            sb.append(this.generaMayusculas());
        }
        if(this.getNumMinusculas()>0){
            sb.append(this.generaMinusculas());
        }
        if(this.getNumNumeros()>0){
            sb.append(this.generaNumeros());
        }
        if(this.getNumSignos()>0){
            sb.append(this.generaSignos());
        }
        return sb;
    }
    private static String quitaCaracter(String cadena,int caracter){
        String izquierda=cadena.substring(0,caracter);
        String derecha=cadena.substring(caracter+1);
        return izquierda+derecha;
    }
    
    public String desordena(){
        return desordena(this.getGenerator(),new StringBuffer(this.getPassword())).toString();
    }
    
    private static StringBuffer desordena(Random generador,StringBuffer ordenado){
        int valorGenerado;
        String temporal=new String(ordenado.toString());
        StringBuffer desordenado=new StringBuffer();
        for(int contador=0;contador<ordenado.length();contador++){
            char[] valores=temporal.toCharArray();
            valorGenerado=generador.nextInt(valores.length);
            desordenado.append(valores[valorGenerado]);
            temporal=quitaCaracter(temporal,valorGenerado);
        }
        return desordenado;
    }
    private static StringBuffer generator(Random generador,char[] valores,int cuantos){
        int valorGenerado;
        StringBuffer sb=new StringBuffer();
        for(int contador=0;contador<cuantos;contador++){
            valorGenerado=generador.nextInt(valores.length);
            sb.append(valores[valorGenerado]);
        }
        return sb;
    }
    private StringBuffer generaMayusculas(){
        return generator(this.getGenerator(),MAYUSCULAS,this.getNumMayusculas());
    }
    
    private StringBuffer generaMinusculas(){
        return generator(this.getGenerator(),MINUSCULAS,this.getNumMinusculas());
    }
    
    private StringBuffer generaNumeros(){
        return generator(this.getGenerator(),NUMEROS,this.getNumNumeros());
    }
    private StringBuffer generaSignos(){
        return generator(this.getGenerator(),SIGNOS,this.getNumSignos());
    }
}
