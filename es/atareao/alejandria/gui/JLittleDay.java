/*
 * JLittleDay
 *
 * File created on 07-mar-2010
 * Copyright (c) 2009 Lorenzo Carbonell
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

package es.atareao.alejandria.gui;

import es.atareao.alejandria.lib.Convert;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 *
 * @author atareao
 */

public class JLittleDay extends JLabel{
    //
    //********************************CONSTANTES********************************
    //
    private final static Color BG_OTRO=new Color(231,238,236);
    private final static Color BG_NORMAL=Color.WHITE;
    private final static Color BG_SELECCIONADO=new Color(255,231,156,255);
    private final static Color BG_FESTIVO=new Color(255,249,231,255);
    //
    private final static Color LB_NORMAL=new Color(63,145,192,255);
    private final static Color LB_HOY=Color.RED;
    //
    private final static Border BORDER_SELECTED=BorderFactory.createLineBorder(new Color(63,145,192,255),2);
    private final static Border BORDER_NORMAL=BorderFactory.createLineBorder(new Color(63,145,192,255),1);
    //
    // *********************************CAMPOS*********************************
    //
    private boolean _selected=false;
    private boolean _thisMonth=true;
    private boolean _today=false;
    private boolean _holiday=false;
    private int _position=0;
    private Color _colorOtro=BG_OTRO;
    private Color _colorNormal=BG_NORMAL;
    private Color _colorSeleccionado=BG_SELECCIONADO;
    private Color _colorFestivo=BG_FESTIVO;


    //
    //******************************CONSTRUCTORES*******************************
    //
    public JLittleDay(){
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        setForeground(LB_HOY);
        setBackground(BG_NORMAL);
        setBorder(BORDER_NORMAL);
        setFont(new java.awt.Font("Tahoma", 0, 10));
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        setText("1");
        setMaximumSize(new java.awt.Dimension(10, 10));
        setMinimumSize(new java.awt.Dimension(10, 10));
        setPreferredSize(new java.awt.Dimension(10, 10));
        setOpaque(true);
    }
    //
    //********************************METODOS***********************************
    //
    private void formMouseClicked(java.awt.event.MouseEvent evt) {
        if(this._thisMonth){
            this._selected=true;
            this.paint();
            this.firePropertyChange("SelectedDay",0,this._position);
        }
    }
    private void paint(){
        if(this._today){
            this.setForeground(LB_HOY);
        }else{
            this.setForeground(LB_NORMAL);
        }
        if(this.isThisMonth()){
            if(this.isSelected()){
                this.setBackground(this.getColorSeleccionado());
                this.setBorder(BORDER_SELECTED);
            }else{
                if(this.isHoliday()){
                    this.setBackground(this.getColorFestivo());
                }else{
                    this.setBackground(this.getColorNormal());
                }
                this.setBorder(BORDER_NORMAL);
            }
        }else{
            this.setBackground(this.getColorOtro());
            this.setBorder(BORDER_NORMAL);
        }                
    }
    @Override
    public void paint(Graphics g){
        super.paint(g);
        paint();
    }

    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
    /**
     * @return the _day
     */
    public int getDay() {
        return Convert.toInt(this.getText());
    }

    /**
     * @param day the _day to set
     */
    public void setDay(int day) {
        this.setText(Convert.toString(day));
    }

    /**
     * @return the _selected
     */
    public boolean isSelected() {
        return _selected;
    }

    /**
     * @param selected the _selected to set
     */
    public void setSelected(boolean selected) {
        this._selected = selected;
        this.paint();
    }

    /**
     * @return the _thisMonth
     */
    public boolean isThisMonth() {
        return _thisMonth;
    }

    /**
     * @param thisMonth the _thisMonth to set
     */
    public void setThisMonth(boolean thisMonth) {
        this._thisMonth = thisMonth;
    }

    /**
     * @return the _today
     */
    public boolean isToday() {
        return _today;
    }

    /**
     * @param today the _today to set
     */
    public void setToday(boolean today) {
        this._today = today;
    }

    /**
     * @return the _holiday
     */
    public boolean isHoliday() {
        return _holiday;
    }

    /**
     * @param holiday the _holiday to set
     */
    public void setHoliday(boolean holiday) {
        this._holiday = holiday;
    }

    /**
     * @return the _colorOtro
     */
    public Color getColorOtro() {
        return _colorOtro;
    }

    /**
     * @param colorOtro the _colorOtro to set
     */
    public void setColorOtro(Color colorOtro) {
        this._colorOtro = colorOtro;
    }

    /**
     * @return the _colorNormal
     */
    public Color getColorNormal() {
        return _colorNormal;
    }

    /**
     * @param colorNormal the _colorNormal to set
     */
    public void setColorNormal(Color colorNormal) {
        this._colorNormal = colorNormal;
    }

    /**
     * @return the _colorSeleccionado
     */
    public Color getColorSeleccionado() {
        return _colorSeleccionado;
    }

    /**
     * @param colorSeleccionado the _colorSeleccionado to set
     */
    public void setColorSeleccionado(Color colorSeleccionado) {
        this._colorSeleccionado = colorSeleccionado;
    }

    /**
     * @return the _colorFestivo
     */
    public Color getColorFestivo() {
        return _colorFestivo;
    }

    /**
     * @param colorFestivo the _colorFestivo to set
     */
    public void setColorFestivo(Color colorFestivo) {
        this._colorFestivo = colorFestivo;
    }

    /**
     * @return the _position
     */
    public int getPosition() {
        return _position;
    }

    /**
     * @param position the _position to set
     */
    public void setPosition(int position) {
        this._position = position;
    }
}
