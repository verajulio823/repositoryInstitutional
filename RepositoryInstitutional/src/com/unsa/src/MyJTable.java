package com.unsa.src;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MyJTable extends JTable {
	 @Override
	    public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
	        Component comp = super.prepareRenderer(renderer, row, col);
	        Object value = getModel().getValueAt(row, col);
	        if (getSelectedRow() == row) {
	            if (value.equals("")) {
	                comp.setBackground(Color.red);
	            } else {
	                comp.setBackground(Color.white);
	            }
	        } else {
	            comp.setBackground(Color.white);
	        }
	        return comp;
	    }
}
