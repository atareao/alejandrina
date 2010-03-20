/*
 * ***********************Software description*********************************
 * FormatoFecha.java
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Lorenzo Carbonell
 */
public class FormatoFecha extends MaskFormatter{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    private final static long serialVersionUID=0L;
    public final static int SHORT=0;
    public final static int MEDIUM=1;
    public final static int LONG=2;
    private final static String S_SHORT="dd/MM/yyyy";
    private final static String S_MEDIUM="dd 'de' MMMM 'de' yyyy";
    private final static String S_LONG="EEEE, dd 'de' MMMM 'de' yyyy";
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public FormatoFecha() throws ParseException{
        this(SHORT);
    }
    public FormatoFecha(int dateFormat) throws ParseException{
        this.setDateFormat(dateFormat);
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    /** Convierte el texto del editor en un Date
     * @throws java.text.ParseException 
     */
    @Override
    public Object stringToValue(String text) throws ParseException{
        return this.getFormat().parseObject(text);
    }

    /** Redibe un Date o null y debe convertirlo a texto que cumpla el patrón indicado anteriormente
     * @throws java.text.ParseException 
     */
    @Override
    public String valueToString(Object value) throws ParseException
    {
        if (value instanceof Date) {
            return this.getFormat().format((Date) value);
        }
        return this.getFormat().format(new Date());
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private SimpleDateFormat _format = new SimpleDateFormat("dd/MM/yyyy");
    private int _dateFormat;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    public SimpleDateFormat getFormat() {
        return _format;
    }

    public void setFormat(SimpleDateFormat format) {
        this._format = format;
    }

    public int getDateFormat() {
        return _dateFormat;
    }

    public void setDateFormat(int dateFormat) {
        this._dateFormat = dateFormat;
        switch(dateFormat){
            case SHORT:
                this.setFormat(new SimpleDateFormat(S_SHORT));
                break;
            case MEDIUM:
                this.setFormat(new SimpleDateFormat(S_MEDIUM));
                break;
            case LONG:
                this.setFormat(new SimpleDateFormat(S_LONG));
                break;
        }
    }
    // </editor-fold> 
}
