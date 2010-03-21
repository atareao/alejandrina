/*
 * AutonumerciRowHeaderRenderer
 *
 * File created on 21-mar-2010
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

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author atareao
 */

public class AutonumericRowHeaderRenderer extends JLabel implements TableCellRenderer{
    //
    //********************************CONSTANTES********************************
    //
    private Color alternateColor = new Color(245, 245, 220);
    //
    // *********************************CAMPOS*********************************
    //

    //
    //******************************CONSTRUCTORES*******************************
    //
    /**
     * Creates a new instance of JCheckBoxTableCellRenderer
     */
    public AutonumericRowHeaderRenderer(JMTable table) {
        super();
        JTableHeader header = table.getTableHeader();
        //setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
        setHorizontalAlignment(SwingConstants.CENTER);
        Dimension dimension=new Dimension(50,20);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);

    }

    //
    //********************************METODOS***********************************
    //
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Border mEmptyBorder = BorderFactory.createEmptyBorder();
        Border mHighLightBorder = UIManager.getBorder("Table.focusCellHighlightBorder");
        Color mFocusCellForeground=table.getSelectionForeground();
        Color mFocusCellBackground=table.getSelectionBackground();
        Color mCellForeground=table.getForeground();
        Color mCellBackground=table.getBackground();
        if (isSelected) {
            setForeground(mFocusCellForeground);
            setBackground(mFocusCellBackground);
        }else{
            setForeground(mCellForeground);
            setBackground((row % 2 == 0 ? alternateColor : mCellBackground));
        }
        if(hasFocus){
            setBorder(mHighLightBorder);
        }else{
            setBorder(mEmptyBorder);
        }
        this.setText(Integer.toString(row));
        return this;
    }
    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
}
