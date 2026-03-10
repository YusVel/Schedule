/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yusvel.schedule.employee;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;
import yusvel.schedule.employee.EmployeeTableModel;

/**
 *
 * @author yusup
 */
public class EmployeeCellRendereAsCombobox extends JComboBox implements TableCellRenderer {

    private int mode;

    EmployeeCellRendereAsCombobox(int mode) {
        this.mode = mode;
        this.setBackground(new Color(252, 252, 217));
        this.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 242), 1, false));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component ret = table.getDefaultRenderer(String.class).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (mode == EmployeeTableModel.EDITABLE) {
            switch (column) {
                case 3 -> {
                    this.removeAllItems();
                    for (var el : Employee.WORKING_RATE) {
                        this.addItem(el);
                    }
                    this.setSelectedIndex((Byte) table.getValueAt(row, column));
                    return this;
                }
                case 4 -> {
                    this.removeAllItems();
                    for (var el : Employee.POSTS) {
                        this.addItem(el);
                    }
                    this.setSelectedIndex((Byte) table.getValueAt(row, column));
                    return this;
                }
                case 5 -> {
                    this.removeAllItems();
                    for (var el : Employee.DEPARTMENTS) {
                        this.addItem(el);
                    }
                    this.setSelectedIndex((Byte) table.getValueAt(row, column));
                    return this;
                }
                case 6 -> {
                    this.removeAllItems();
                    for (var el : Employee.CABINET_NUMBER) {
                        this.addItem(el);
                    }
                    this.setSelectedIndex((Byte) table.getValueAt(row, column) - 1);
                    return this;
                }
            }
        }
        return ret;
    }
}
