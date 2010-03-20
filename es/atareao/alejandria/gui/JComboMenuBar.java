/*
 * JComboMenuBar
 *
 * File created on 26-dic-2009
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
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicArrowButton;

/**
 *
 * @author atareao
 */

public class JComboMenuBar extends JMenuBar{
    //
    //********************************CONSTANTES********************************
    //
    JMenu menu;
    Dimension preferredSize;

    public JComboMenuBar(JMenu menu) {
        this.menu = menu;
        Color color = UIManager.getColor("Menu.selectionBackground");
        UIManager.put("Menu.selectionBackground", UIManager.getColor("Menu.background"));
        menu.updateUI();
        UIManager.put("Menu.selectionBackground", color);
        MenuItemListener listener = new MenuItemListener();
        setListener(menu, listener);
        add(menu);
    }
    class MenuItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            menu.setText(item.getText());
            menu.requestFocus();
        }
    }

    private void setListener(JMenuItem item, ActionListener listener) {
        if (item instanceof JMenu) {
            JMenu emenu = (JMenu) item;
            int n = emenu.getItemCount();
            for (int i = 0; i < n; i++) {
                setListener(emenu.getItem(i), listener);
            }
        } else if (item != null) { // null means separator
            item.addActionListener(listener);
        }
    }
    public String getSelectedItem() {
        return menu.getText();
    }
    @Override
    public void setPreferredSize(Dimension size) {
        preferredSize = size;
    }
    @Override
    public Dimension getPreferredSize() {
        if (preferredSize == null) {
            Dimension sd = super.getPreferredSize();
            Dimension menuD = getItemSize(menu);
            Insets margin = menu.getMargin();
            Dimension retD = new Dimension(menuD.width, margin.top + margin.bottom + menuD.height);
      menu.setPreferredSize(retD);
      preferredSize = retD;
    }
    return preferredSize;
  }

  private Dimension getItemSize(JMenu menu) {
    Dimension d = new Dimension(0, 0);
    int n = menu.getItemCount();
    for (int i = 0; i < n; i++) {
      Dimension itemD;
      JMenuItem item = menu.getItem(i);
      if (item instanceof JMenu) {
        itemD = getItemSize((JMenu) item);
      } else if (item != null) {
        itemD = item.getPreferredSize();
      } else {
        itemD = new Dimension(0, 0); // separator
      }
      d.width = Math.max(d.width, itemD.width);
      d.height = Math.max(d.height, itemD.height);
    }
    return d;
  }

    public static class ComboMenu extends JMenu {
        ArrowIcon iconRenderer;
        public ComboMenu(String label) {
            super(label);
            iconRenderer = new ArrowIcon(SwingConstants.SOUTH, true);
            setBorder(new EtchedBorder());
            setIcon(new BlankIcon(null, 11));
            setHorizontalTextPosition(JButton.LEFT);
            setFocusPainted(true);
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension d = this.getPreferredSize();
            int x = Math.max(0, d.width - iconRenderer.getIconWidth() - 3);
            int y = Math.max(0,(d.height - iconRenderer.getIconHeight()) / 2 - 2);
            iconRenderer.paintIcon(this, g, x, y);
        }
    }

  public static JMenu createMenu(String label) {
    return new ComboMenu(label);
  }

}

class ArrowIcon implements Icon, SwingConstants {
  private static final int DEFAULT_SIZE = 11;

  //private static final int DEFAULT_SIZE = 5;

  private int size;

  private int iconSize;

  private int direction;

  private boolean isEnabled;

  private BasicArrowButton iconRenderer;

  public ArrowIcon(int direction, boolean isPressedView) {
    this(DEFAULT_SIZE, direction, isPressedView);
  }

  public ArrowIcon(int iconSize, int direction, boolean isEnabled) {
    this.size = iconSize / 2;
    this.iconSize = iconSize;
    this.direction = direction;
    this.isEnabled = isEnabled;
    iconRenderer = new BasicArrowButton(direction);
  }

    @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    iconRenderer.paintTriangle(g, x, y, size, direction, isEnabled);
  }

    @Override
  public int getIconWidth() {
    //int retCode;
    switch (direction) {
    case NORTH:
    case SOUTH:
      return iconSize;
    case EAST:
    case WEST:
      return size;
    }
    return iconSize;
  }

    @Override
  public int getIconHeight() {
    switch (direction) {
    case NORTH:
    case SOUTH:
      return size;
    case EAST:
    case WEST:
      return iconSize;
    }
    return size;
  }
}

class BlankIcon implements Icon {
  private Color fillColor;

  private int size;

  public BlankIcon() {
    this(null, 11);
  }

  public BlankIcon(Color color, int size) {
    //UIManager.getColor("control")
    //UIManager.getColor("controlShadow")
    fillColor = color;

    this.size = size;
  }

    @Override
  public void paintIcon(Component c, Graphics g, int x, int y) {
    if (fillColor != null) {
      g.setColor(fillColor);
      g.drawRect(x, y, size - 1, size - 1);
    }
  }

    @Override
  public int getIconWidth() {
    return size;
  }

    @Override
  public int getIconHeight() {
    return size;
  }
    //
    // *********************************CAMPOS*********************************
    //

    //
    //******************************CONSTRUCTORES*******************************
    //

    //
    //********************************METODOS***********************************
    //

    //
    //**************************METODOS AUXILIARES******************************
    //

    //
    //**************************METODOS DE ACCESO*******************************
    //
}
