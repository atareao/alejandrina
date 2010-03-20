/*
 * ***********************Software description*********************************
 * GuiCreditCardValidator.java 
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

package es.atareao.alejandria.val.gui;

//
//********************************IMPORTACIONES*********************************
//
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JTextField;

/**
 *
 * @author Lorenzo Carbonell
 */
public class GuiCreditCardValidator  extends AbstractValidator {
    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 
    public static final int MASTERCARD = 0, VISA = 1;
    public static final int AMEX = 2, DISCOVER = 3, DINERS = 4;
    private static final String[] messages = {
        "Not a valid number for MasterCard.",
        "Not a valid number for Visa.",
        "Not a valid number for American Express.",
        "Not a valid number for Discover.",
        "Not a valid number for Diner's Club"
    };    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    public GuiCreditCardValidator(JDialog parent, Object form, JComponent c,String message){
        super(parent, c, message);
        this.parent = parent;
        this.form = form;
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos  "> 
    protected boolean validationCriteria(JComponent c) {
        String number = ((JTextField)c).getText();
		
        if (number.equals("")) {
            setMessage("Field cannnot be blank.");
            return false;
        }
		
        Matcher m = Pattern.compile("[^\\d\\s.-]").matcher(number);
        
        if (m.find()) {
            setMessage("Credit card number can only contain numbers, spaces, \"-\", and \".\"");
            return false;
        }
		
        int type = ((GuiCreditCardValidation)form).validateCreditCardType();
        setMessage(messages[type]);
        Matcher matcher = Pattern.compile("[\\s.-]").matcher(number);
        number = matcher.replaceAll("");
        return validate(number, type);
    }
 
    // Check that cards start with proper digits for
    // selected card type and are also the right length.    
 
    private boolean validate(String number, int type) {
        switch(type) {
		
        case MASTERCARD:
            if (number.length() != 16 ||
                Integer.parseInt(number.substring(0, 2)) < 51 ||
                Integer.parseInt(number.substring(0, 2)) > 55)
            {
                return false;
            }
            break;
			
        case VISA:
            if ((number.length() != 13 && number.length() != 16) ||
                    Integer.parseInt(number.substring(0, 1)) != 4)
            {
                return false;
            }
            break;
			
        case AMEX:
            if (number.length() != 15 ||
                (Integer.parseInt(number.substring(0, 2)) != 34 &&
                    Integer.parseInt(number.substring(0, 2)) != 37))
            {
                return false;
            }
            break;
			
        case DISCOVER:
            if (number.length() != 16 ||
                Integer.parseInt(number.substring(0, 5)) != 6011)
            {
                return false;
            }
            break;
			
        case DINERS:
            if (number.length() != 14 ||
                ((Integer.parseInt(number.substring(0, 2)) != 36 &&
                    Integer.parseInt(number.substring(0, 2)) != 38) &&
                    Integer.parseInt(number.substring(0, 3)) < 300 ||
                        Integer.parseInt(number.substring(0, 3)) > 305))
            {
                return false;
            }
            break;
        }
        return luhnValidate(number);
    }
 
    // The Luhn algorithm is basically a CRC type
    // system for checking the validity of an entry.
    // All major credit cards use numbers that will
    // pass the Luhn check. Also, all of them are based
    // on MOD 10.
	
    private boolean luhnValidate(String numberString) {
        char[] charArray = numberString.toCharArray();
        int[] number = new int[charArray.length];
        int total = 0;
		
        for (int i=0; i < charArray.length; i++) {
            number[i] = Character.getNumericValue(charArray[i]);
        }
		
        for (int i = number.length-2; i > -1; i-=2) {
            number[i] *= 2;
			
            if (number[i] > 9)
                number[i] -= 9;
        }
		
        for (int i=0; i < number.length; i++)
            total += number[i];
		
            if (total % 10 != 0)
                return false;
		
        return true;
    }

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos auxiliares  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private JDialog parent;
    private Object form;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Métodos de acceso  "> 

    // </editor-fold> 
}
