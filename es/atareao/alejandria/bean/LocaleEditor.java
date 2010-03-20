/*
 * ***********************Software description*********************************
 * LocaleEditor.java
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
import java.beans.PropertyEditorSupport;
import java.util.Calendar;
import java.util.Locale;

/**
 *
 * @author Lorenzo Carbonell
 */
public class LocaleEditor extends PropertyEditorSupport{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    /**
     * Default LocaleEditor constructor.
     */
    public LocaleEditor() {
        locale = Locale.getDefault();
        locales = Calendar.getAvailableLocales();
        length = locales.length;
        localeStrings = new String[length];
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
    /**
     * Returns the locale strings.
     * 
     * @return the locale strings
     */
    @Override
    public String[] getTags() {
        for (int i = 0; i < length; i++) {
            localeStrings[i] = locales[i].getDisplayName();
        }
	return localeStrings;
    }
    /**
     * Sets the locale strings as text and invokes setValue( locale ).
     * 
     * @param text
     *            the locale string text
     * 
     * @throws IllegalArgumentException
     *             not used
     */
    @Override	
    public void setAsText(String text) throws IllegalArgumentException {
        for (int i = 0; i < length; i++) {
            if (text.equals(locales[i].getDisplayName())) {
                locale = locales[i];
                setValue(locale);
                break;
            }
        }
    }

    /**
     * Returns the locale string as text.
     * 
     * @return the locale string
     */

    @Override
    public String getAsText() {
        return locale.getDisplayName();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" M�todos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private Locale[] locales;
    private String[] localeStrings;
    private Locale locale;
    private int length;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    // </editor-fold> 
}
