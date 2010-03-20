/*  swing1.1 */
 
package es.atareao.alejandria.gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.plaf.ColorUIResource;


/**
 * @version 1.0 01/11/99
 */
public class CheckRenderer extends JPanel implements TreeCellRenderer {
    protected static final long serialVersionUID=0L;
    protected JCheckBox check;
    protected JTreeLabel label;
  
  public CheckRenderer() {
        //super.setBackground(new Color(255,255,255));
        this.setBackground(UIManager.getColor("Tree.textBackground"));
        setLayout(null);
        add(check = new JCheckBox());
        add(label = new JTreeLabel());
        check.setBackground(UIManager.getColor("Tree.textBackground"));
        label.setBackground(UIManager.getColor("Tree.textBackground"));
        this.setBackground(UIManager.getColor("Tree.textBackground"));
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value,
               boolean isSelected, boolean expanded,
               boolean leaf, int row, boolean hasFocus) {
    String  stringValue = tree.convertValueToText(value, isSelected,
			expanded, leaf, row, hasFocus);
    setEnabled(tree.isEnabled());
    check.setSelected(((CheckNode)value).isSelected());
    label.setFont(tree.getFont());
    label.setText(stringValue);
    label.setSelected(isSelected);
    label.setFocus(hasFocus);
    if (leaf) {
      label.setIcon(UIManager.getIcon("Tree.leafIcon"));
    } else if (expanded) {
      label.setIcon(UIManager.getIcon("Tree.openIcon"));
    } else {
      label.setIcon(UIManager.getIcon("Tree.closedIcon"));
    }	    
    return this;
  }
  
    @Override
  public Dimension getPreferredSize() {
    Dimension d_check = check.getPreferredSize();
    Dimension d_label = label.getPreferredSize();
    return new Dimension(d_check.width  + d_label.width,
      (d_check.height < d_label.height ?
       d_label.height : d_check.height));
  }
  
    @Override
  public void doLayout() {
    Dimension d_check = check.getPreferredSize();
    Dimension d_label = label.getPreferredSize();
    int y_check = 0;
    int y_label = 0;
    if (d_check.height < d_label.height) {
      y_check = (d_label.height - d_check.height)/2;
    } else {
      y_label = (d_check.height - d_label.height)/2;
    }
    check.setLocation(0,y_check);
    check.setBounds(0,y_check,d_check.width,d_check.height);
    label.setLocation(d_check.width,y_label);    
    label.setBounds(d_check.width,y_label,d_label.width,d_label.height);    
  }
   
    @Override
    public void setBackground(Color color) {
        if (color instanceof ColorUIResource) {
            color = null;
        }
        super.setBackground(color);
  }
}    
