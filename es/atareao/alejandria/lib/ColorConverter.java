/*
 * ColorConverter.java
 * 
 * Created on 24-oct-2007, 21:12:35
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//http://www.easyrgb.com/math.php?MATH=M2#text2
package es.atareao.alejandria.lib;

/**
 *
 * @author Propietario
 */
public class ColorConverter {
    public static int[] fromXYZtoRGB(double[] XYZ){
        double var_X = XYZ[0] / 100;       //Where X = 0 ÷  95.047
        double var_Y = XYZ[1] / 100;        //Where Y = 0 ÷ 100.000
        double var_Z = XYZ[2] / 100;        //Where Z = 0 ÷ 108.883

        double var_R = var_X *  3.2406 + var_Y * -1.5372 + var_Z * -0.4986;
        double var_G = var_X * -0.9689 + var_Y *  1.8758 + var_Z *  0.0415;
        double var_B = var_X *  0.0557 + var_Y * -0.2040 + var_Z *  1.0570;

        if ( var_R > 0.0031308 ){
            var_R = 1.055*(Math.pow(var_R,( 1 / 2.4 ))) - 0.055;
        }else{
            var_R = 12.92 * var_R;
        }
        if ( var_G > 0.0031308 ){
            var_G = 1.055*(Math.pow(var_G,( 1 / 2.4 ))) - 0.055;
        }else{
            var_G = 12.92 * var_G;
        }
        if ( var_B > 0.0031308 ){
            var_B = 1.055*(Math.pow(var_B,( 1 / 2.4 ))) - 0.055;
        }else{
            var_B = 12.92* var_B;
        }
        int R = (int)var_R * 255;
        int G = (int)var_G * 255;
        int B = (int)var_B * 255 ;       
        return new int[]{R,G,B};
    }
    public static double[] fromRGBtoXYX(int[] RGB){
        double var_R = ( RGB[0] / 255 );        //Where R = 0 ÷ 255
        double var_G = ( RGB[1] / 255 );        //Where G = 0 ÷ 255
        double var_B = ( RGB[2] / 255 );        //Where B = 0 ÷ 255
        if ( var_R > 0.04045 ){
            var_R = Math.pow(( var_R + 0.055 ) / 1.055 , 2.4);
        }else{
            var_R = var_R / 12.92;
        }
        if ( var_G > 0.04045 ){
            var_G = Math.pow( ( var_G + 0.055 ) / 1.055 , 2.4);
        }else{
            var_G = var_G / 12.92;
        }
        if ( var_B > 0.04045 ){
            var_B = Math.pow(( var_B + 0.055 ) / 1.055,2.4);
        }else{
            var_B = var_B / 12.92;
        }
        var_R = var_R * 100;
        var_G = var_G * 100;
        var_B = var_B * 100;
        //Observer. = 2°, Illuminant = D65
        double X = var_R * 0.4124 + var_G * 0.3576 + var_B * 0.1805;
        double Y = var_R * 0.2126 + var_G * 0.7152 + var_B * 0.0722;
        double Z = var_R * 0.0193 + var_G * 0.1192 + var_B * 0.9505;       
        return new double[]{X,Y,Z};
    }
    public static double[] fromXYZtoYxy(double[]XYZ){
        //Where X = 0 ÷  95.047       Observer. = 2°, Illuminant = D65
        //Where Y = 0 ÷ 100.000
        //Where Z = 0 ÷ 108.883
        double Y = XYZ[1];
        double x = XYZ[0] / ( XYZ[0] + XYZ[1] + XYZ[2] );
        double y = XYZ[1] /  ( XYZ[0] + XYZ[1] + XYZ[2] );
        return new double[]{Y,x,y};
    }
}
