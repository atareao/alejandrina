/*
 * ***********************Software description*********************************
 * AbstractValidator.java 
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
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
/**
 *
 * @author Lorenzo Carbonell
 */
public abstract class AbstractValidator  extends InputVerifier implements KeyListener {

    // <editor-fold defaultstate="collapsed" desc=" Constantes  "> 

    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Constructores  "> 
    private AbstractValidator() {
        color = new Color(243, 255, 159);
    }
	
    private AbstractValidator(JComponent c, String message) {
        this();
        c.addKeyListener(this);
        messageLabel = new JLabel(message + " ");
        image = new JLabel(new ImageIcon(getClass().getResource("/es/atareao/alejandria/val/gui/error.png")));
    }
	
    /**
     * @param parent A JDialog that implements the ValidationCapable interface.
     * @param c The JComponent to be validated.
     * @param message A message to be displayed in the popup help tip if validation fails.
     */
	
    public AbstractValidator (JDialog parent, JComponent c, String message) {		
        this(c, message);
        this.parent = parent;
        popup = new JDialog(parent);
        initComponents();
    }
	
    /**
     * @param parent A JFrame that implements the ValidationCapable interface.
     * @param c The JComponent to be validated.
     * @param message A message to be displayed in the popup help tip if validation fails.
     */
	
    public AbstractValidator (JFrame parent, JComponent c, String message) {
        this(c, message);
        this.parent = parent;
        popup = new JDialog(parent);
        initComponents();
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos  ">
    /**
     * Implement the actual validation logic in this method. The method should
     * return false if data is invalid and true if it is valid. It is also possible
     * to set the popup message text with setMessage() before returning, and thus
     * customize the message text for different types of validation problems.
     * 
     * @param c The JComponent to be validated.
     * @return false if data is invalid. true if it is valid.
     */
	
    protected abstract boolean validationCriteria(JComponent c);
/**
     * This method is called by Java when a component needs to be validated.
     * It should not be called directly. Do not override this method unless
     * you really want to change validation behavior. Implement
     * validationCriteria() instead.
     */
    @Override
    public boolean verify(JComponent c) {
        if((c.isEnabled())&&(c.isVisible())){
            if (!validationCriteria(c)) {
                if(parent instanceof GuiWantsValidationStatus)
                    ((GuiWantsValidationStatus)parent).validateFailed();
                c.setBackground(Color.PINK);
                popup.setSize(0, 0);
                popup.setLocationRelativeTo(c);
                point = popup.getLocation();
                cDim = c.getSize();
                popup.setLocation(point.x-(int)cDim.getWidth()/2,
                    point.y+(int)cDim.getHeight()/2);
                popup.pack();
                popup.setVisible(true);
                return false;
            }
            c.setBackground(Color.WHITE);
            if(parent instanceof GuiWantsValidationStatus)
                ((GuiWantsValidationStatus)parent).validatePassed();
        }
        return true;
    }	

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        popup.setVisible(false);
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    /**
     * Changes the message that appears in the popup help tip when a component's
     * data is invalid. Subclasses can use this to provide context sensitive help
     * depending on what the user did wrong.
     * 
     * @param message
     */
	
    protected void setMessage(String message) {
       messageLabel.setText(message);
    }    
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos auxiliares  ">
    private void initComponents() {
        popup.getContentPane().setLayout(new FlowLayout());
        popup.setUndecorated(true);
        popup.getContentPane().setBackground(color);
        popup.getContentPane().add(image);
        popup.getContentPane().add(messageLabel);
        popup.setFocusableWindowState(false);
    }
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Campos  "> 
    private JDialog popup;
    private Object parent;
    private JLabel messageLabel;
    private JLabel image;
    private Point point;
    private Dimension cDim;
    private Color color;
    // </editor-fold> 

    // <editor-fold defaultstate="collapsed" desc=" Metodos de acceso  ">

    // </editor-fold> 
}
