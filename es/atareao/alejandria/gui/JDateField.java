/*
 * ***********************Software description*********************************
 * NumericTextField.java
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

/*  Nota:
 * al modificar el valor dispara la propiedad "value"
 */ 



//
//********************************IMPORTACIONES*********************************
//
import java.awt.Dimension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import es.atareao.alejandria.lib.DateUtils;

/**
 *
 * @author Lorenzo Carbonell
 */
public class JDateField extends JFormattedTextField {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    public static final String EDIT_START="StartEditing";
    public static final String EDIT_STOP="StopEditing";
    //
    public static final int PROP_NONE=-1;
    public static final int PROP_RIGHT=0;
    public static final int PROP_LEFT=1;
    public static final int PROP_UP=2;
    public static final int PROP_DOWN=3;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public JDateField() {
        super(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy"))),0.0);
        this.setFormat("dd/MM/yyyy");
        this.setPreferredSize(new Dimension(50,20));
        addAncestorListener( new AncestorListener(){
            public void ancestorAdded(AncestorEvent e){
                requestFocus();
            }
            public void ancestorMoved(AncestorEvent e){}
            public void ancestorRemoved(AncestorEvent e){}
        });
        /*
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                onFocusGained(evt);
            }
        });
         */ 
        this.getDocument().addDocumentListener(new DocumentEventListener());
        /*
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                OnDecimalsChanged(evt);
            }
        });
        */
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">
    public static String toStringDate(String stringdatesql){
        return toString(toDateSql(stringdatesql));
        
    }
    public static String toStringDateSql(String stringdate){
        return toStringDateSql(toDate(stringdate));
    }
    public static String toStringDateSql(Date valor){
        String converted=null;
        //SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        converted=sdf.format(valor);
        return converted;
    }
    public static String toStringDateSql(Object valor){
        if(valor instanceof String){
            return toStringDateSql((String)valor);
        }
        if(valor instanceof Date){
            return toStringDateSql((Date)valor);
        }
        return toStringDateSql(DateUtils.Ahora());
    }
    public static Date toDateSql(String valor){
        try {
            Date converted = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            converted = sdf.parse(valor);
            return converted;
        } catch (ParseException ex) {
            return DateUtils.Ahora();
        } catch(NullPointerException ex){
            return DateUtils.Ahora();
        }
    }
    
    public static Date toDate(String valor){
        try {
            Date converted = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            converted = sdf.parse(valor);
            return converted;
        } catch (ParseException ex) {
            return DateUtils.Ahora();
        }
    }
    public static Date toDate(Object valor){
        if(valor instanceof Date){
            Date converted=(Date)valor;
            return converted;
        }else{
            return DateUtils.Ahora();
        }
    }
    
    public static String toString(Date valor){
        String converted = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        converted = sdf.format(valor);
        return converted;
    }
    public static String toString(Object valor){
        if(valor instanceof Date){
            return toString((Date)valor);
        }
        if(valor instanceof String){
            return (String)valor;
        }
        return toString(DateUtils.Ahora());
    }
    
    private class DocumentEventListener implements DocumentListener{

        public void insertUpdate(DocumentEvent e) {
            firePropertyChange("value",null,getValue());
        }

        public void removeUpdate(DocumentEvent e) {
            firePropertyChange("value",null,getValue());
        }

        public void changedUpdate(DocumentEvent e) {
            firePropertyChange("value",null,getValue());
        }
    }

    private SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd/MM/yyyy");
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    // Methods to get the field value
    
    private void setFormat(String sformat) {
        SimpleDateFormat format=new SimpleDateFormat(sformat);
        this.setFormat(format);
    }
    private void setFormat(SimpleDateFormat format) {
        if (format == null) {
            format=this.getDateFormat();
        }
        DateFormatter df=new DateFormatter(format);
        df.setAllowsInvalid(false);
        this.setFormatterFactory(new DefaultFormatterFactory(df, df, df));
    }
    @Override
    public void setValue(Object value){
        Date date=toDate(value);
        this.setDate(date);
    }
    public void setDate(Date date){
        super.setValue(date);
        this.setText(this.getDateFormat().format(date));
    }
    public void setDate(String value){
        Date date=toDate(value);
        this.setDate(date);
    }
    public String getString(){
        return this.getText();
    }
    public String getDate(){
        return this.getText();
    }
    // </editor-fold> 
}
