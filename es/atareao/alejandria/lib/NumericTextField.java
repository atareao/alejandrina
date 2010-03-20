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

package es.atareao.alejandria.lib;

//

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import javax.swing.JFormattedTextField;

//********************************IMPORTACIONES*********************************
import javax.swing.text.AttributeSet;
//
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.Document;
import javax.swing.text.NumberFormatter;
/**
 *
 * @author Lorenzo Carbonell
 */
public class NumericTextField extends JFormattedTextField implements InsertErrorListener {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    
  public NumericTextField() {
    this(null, null);
  }
    
  public NumericTextField(String text, DecimalFormat format) {
    this.setFormat(format);
    this.setValue(text);
    NumericPlainDocument numericDoc = (NumericPlainDocument) getDocument();
    if (format != null) {
      numericDoc.setFormat(format);
    }
    numericDoc.addInsertErrorListener(this);
    this.setDocument(numericDoc);

  }

  public NumericTextField(DecimalFormat format) {
    this(null,format);
  }

  public NumericTextField(String text) {
    this(text, null);
  }

  public void setFormat(DecimalFormat format) {
    if(format!=null){
        NumberFormatter nf=new NumberFormatter(format);
        nf.setAllowsInvalid(false);
        this.setFormatterFactory(new DefaultFormatterFactory(nf));
        this.setFormatter(nf);
        ((NumericPlainDocument) getDocument()).setFormat(format);
    }
  }

  public DecimalFormat getFormat() {
    return ((NumericPlainDocument) getDocument()).getFormat();
  }

  public void formatChanged() {
    // Notify change of format attributes.
    setFormat(getFormat());
  }

  // Methods to get the field value
  public Long getLongValue() throws ParseException {
    return ((NumericPlainDocument) getDocument()).getLongValue();
  }

  public Double getDoubleValue() throws ParseException {
    return ((NumericPlainDocument) getDocument()).getDoubleValue();
  }
    public void setMinimumFractionDigits(int newValue){
        this.getFormat().setMinimumFractionDigits(newValue);
    }
  public Number getNumberValue() throws ParseException {
    return ((NumericPlainDocument) getDocument()).getNumberValue();
  }

  // Methods to install numeric values
  public void setValue(Number number) {
    setText(getFormat().format(number));
  }

  public void setValue(long l) {
    setText(getFormat().format(l));
  }

  public void setValue(double d) {
    setText(getFormat().format(d));
  }

  public void normalize() throws ParseException {
    // format the value according to the format string
    setText(getFormat().format(getNumberValue()));
  }

  // Override to handle insertion error
  public void insertFailed(NumericPlainDocument doc, int offset, String str,
      AttributeSet a) {
    // By default, just beep
    Toolkit.getDefaultToolkit().beep();
  }

  // Method to create default model
  @Override
  protected Document createDefaultModel() {
    return new NumericPlainDocument();
  }
protected InsertErrorListener errorListener;

  protected DecimalFormat format;

  protected char decimalSeparator;

  protected char groupingSeparator;

  protected String positivePrefix;

  protected String negativePrefix;

  protected int positivePrefixLen;

  protected int negativePrefixLen;

  protected String positiveSuffix;

  protected String negativeSuffix;

  protected int positiveSuffixLen;

  protected int negativeSuffixLen;

  protected ParsePosition parsePos = new ParsePosition(0);

  protected static DecimalFormat defaultFormat = new DecimalFormat();


    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
