/*
 * ***********************Software description*********************************
 * GenericBeanInfo.java
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

package es.atareao.alejandria.bean;

//
//********************************IMPORTACIONES*********************************
//

import java.beans.PropertyEditorManager;
import java.beans.SimpleBeanInfo;
import java.util.Locale;
/**
 *
 * @author Lorenzo Carbonell
 */
public class GenericBeanInfo extends SimpleBeanInfo {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public GenericBeanInfo(String bean, boolean registerLocaleEditor) {
        if(registerLocaleEditor) {
            PropertyEditorManager.registerEditor(Locale.class,LocaleEditor.class);
            }
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    // </editor-fold> 
}
