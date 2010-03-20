/*
 * ***********************Software description*********************************
 * JSpinnerMonthChooser.java
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

package es.atareao.alejandria.gui;

//
//********************************IMPORTACIONES*********************************
//

import java.text.DateFormatSymbols;
import java.util.Date;
import java.util.Locale;
import javax.swing.JSpinner;
import es.atareao.alejandria.lib.DateUtils;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JSpinnerMonthChooser extends JSpinner{
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    public static final long serialVersionUID=0L;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JSpinnerMonthChooser(){
        this(DateUtils.Ahora());
    }
    public JSpinnerMonthChooser(Date date){
        super();
        this.setLocal(Locale.getDefault());
        this.initNames();
        this.setFecha(date);
        
        int mes=DateUtils.monthOfYear(date);
        this.setValue(this.getMonthNames()[0]);
        
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 
    public void initNames() {
        //DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(this.getLocal());
        //String[] monthNames = dateFormatSymbols.getMonths();
        String[] monthNames={"Enero","Febrero"};
        this.setMonthNames(monthNames);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private String[] _monthNames;
    private Date _fecha; 
    private Locale _local;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 
    public String[] getMonthNames() {
        return _monthNames;
    }

    public void setMonthNames(String[] monthNames) {
        this._monthNames = monthNames;
    }

    public Date getFecha() {
        return _fecha;
    }

    public void setFecha(Date fecha) {
        this._fecha = fecha;
    }

    public Locale getLocal() {
        return _local;
    }

    public void setLocal(Locale local) {
        this._local = local;
    }
    // </editor-fold> 
}
