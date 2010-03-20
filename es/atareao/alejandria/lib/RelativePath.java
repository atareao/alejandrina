/*
 * RelativePath.java
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
import java.io.*;
import java.util.*;
/**
 * this class provides functions used to generate a relative path
 * from two absolute paths
 * @author David M. Howard
 */
public class RelativePath {
    //
    //********************************CONSTANTES********************************
    //
    
    //
    // *********************************CAMPOS*********************************
    //
    
    //
    //******************************CONSTRUCTORES*******************************
    //
    
    //
    //********************************METODOS***********************************
    //
    /**
     * get relative path of File 'f' with respect to 'home' directory
     * example : home = /a/b/c
     *           f    = /a/d/e/x.txt
     *           s = getRelativePath(home,f) = ../../d/e/x.txt
     * @param home base path, should be a directory, not a file, or it doesn't
     * make sense
     * @param f file to generate path for
     * @return path from home to f as a string
     */
    public static String getRelativePath(String home,String f){
        return getRelativePath(new File(home),new File(f));
    }    
    /**
     * get relative path of File 'f' with respect to 'home' directory
     * example : home = /a/b/c
     *           f    = /a/d/e/x.txt
     *           s = getRelativePath(home,f) = ../../d/e/x.txt
     * @param home base path, should be a directory, not a file, or it doesn't
     * make sense
     * @param f file to generate path for
     * @return path from home to f as a string
     */
    public static String getRelativePath(File home,File f){
        File r;
        List homelist;
        List filelist;
        String s;
        
        homelist = getPathList(new File(home.getAbsolutePath()));
        filelist = getPathList(new File(f.getAbsolutePath()));
        s = matchPathLists(homelist,filelist);
        
        return s;
    }
    //
    //**************************METODOS AUXILIARES******************************
    //
    /**
     * break a path down into individual elements and add to a list.
     * example : if a path is /a/b/c/d.txt, the breakdown will be [d.txt,c,b,a]
     * @param f input file
     * @return a List collection with the individual elements of the path in
     * reverse order
     */
    private static List getPathList(File f) {
        List<String> l = new ArrayList<String>();
        File r;
        try {
            r = f.getCanonicalFile();
            while(r != null) {
                l.add(r.getName());
                r = r.getParentFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            l = null;
        }
        return l;
    }
    
    /**
     * figure out a string representing the relative path of
     * 'f' with respect to 'r'
     * @param r home path
     * @param f path of file
     */
    private static String matchPathLists(List r,List f) {
        int i;
        int j;
        String s;
        // start at the beginning of the lists
        // iterate while both lists are equal
        s = "";
        i = r.size()-1;
        j = f.size()-1;
        
        // first eliminate common root
        while((i >= 0)&&(j >= 0)&&(r.get(i).equals(f.get(j)))) {
            i--;
            j--;
        }
        
        // for each remaining level in the home path, add a ..
        for(;i>=1;i--) {
            s += ".." + File.separator;
        }
        
        // for each level in the file path, add the path
        for(;j>=1;j--) {
            s += f.get(j) + File.separator;
        }
        
        // file name
        s += f.get(j);
        return s;
    }
    //
    //**************************METODOS DE ACCESO*******************************
    //
    
}
