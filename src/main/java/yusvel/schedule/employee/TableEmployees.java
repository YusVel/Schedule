/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yusvel.schedule.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JCheckBox;
import javax.swing.table.TableCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.CellEditor;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import org.jdesktop.swingx.JXTextField;

public class TableEmployees extends JTable {

    public TableEmployees(ArrayList<Employee> Employees, int mode) {

        this.setDefaultRenderer(JComboBox.class, new EmployeeCellRendereAsCombobox(mode));
        CellEditor editor = this.getDefaultEditor(JComboBox.class);
        editor.addCellEditorListener(new CellEditorListener(){
            @Override
            public void editingStopped(ChangeEvent e) {
            System.out.println("Закончили редактировать ячейку"+e.getSource());}

            @Override
            public void editingCanceled(ChangeEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        try {
            this.setModel(new EmployeeTableModel(Employees, mode));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        this.getColumnModel().getColumn(0).setPreferredWidth(100);//выставляем ширину колонки с фамилиями
        this.getColumnModel().getColumn(1).setPreferredWidth(60);//с именем
        this.getColumnModel().getColumn(2).setPreferredWidth(100);//с отчеством
        this.getColumnModel().getColumn(3).setPreferredWidth(5);//савка
        this.getColumnModel().getColumn(4).setPreferredWidth(30);//должность
        this.getColumnModel().getColumn(5).setPreferredWidth(30);//отделение
        this.getColumnModel().getColumn(6).setPreferredWidth(10);//кабинет
        this.setFont(new Font("Verdena", Font.PLAIN, 18));
        if (mode == EmployeeTableModel.SELECTABLE) {
            this.getColumnModel().getColumn(0).setPreferredWidth(60);//выставляем ширину колонки с фамилиями
            this.getColumnModel().getColumn(1).setPreferredWidth(8);//с именем
            this.getColumnModel().getColumn(2).setPreferredWidth(8);//с отчеством
            this.getColumnModel().getColumn(3).setPreferredWidth(2);//савка
            this.getColumnModel().getColumn(4).setPreferredWidth(30);//должность
            this.getColumnModel().getColumn(5).setPreferredWidth(140);//отделение
            this.getColumnModel().getColumn(6).setPreferredWidth(2);//кабинет
            this.getColumnModel().getColumn(7).setPreferredWidth(2);//кабинет
            this.setFont(new Font("Verdena", Font.PLAIN, 12));
        }

        this.setBackground(new Color(252, 252, 217));
        this.setRowHeight(25);
        this.setGridColor(new Color(212, 210, 210));

        this.setVisible(true);
    }
        public void chekBoxfilter(String postOrDepartment, boolean value)
    {
        ArrayList<Employee> employees =((EmployeeTableModel)this.getModel()).getEmployees();
        ArrayList<Boolean> ArrayBooleans = ((EmployeeTableModel)this.getModel()).getArrayBooleans();
        
        Byte index = indexOfArray(postOrDepartment, Employee.POSTS);
        if (index != (-1)||postOrDepartment.equals("Все")) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getPost().equals(index)) {
                    ArrayBooleans.set(i, value);
                }
                if (postOrDepartment.equals("Все")) {
                    ArrayBooleans.set(i, value);
                }
            }
        }
        index = indexOfArray(postOrDepartment, Employee.DEPARTMENTS);
        if (index != (-1)||postOrDepartment.equals("Все")) {
            for (int i = 0; i < employees.size(); i++) {
                if (employees.get(i).getDepartment().equals(index)) {
                    ArrayBooleans.set(i, value);
                }
                if (postOrDepartment.equals("Все")) {
                    ArrayBooleans.set(i, value);
                }
            }
        }
    }
    private Byte indexOfArray(String postOrDepartment, String[] array) {
        for (Byte i = 0; i < array.length; i++) {
            if (postOrDepartment.equals(array[i.intValue()])) {
                return i;
            }
        }
        return -1;
    }
   
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if (this.getColumnClass(column).equals(String.class)) {
            return new DefaultCellEditor(new JXTextField(String.valueOf( this.getValueAt(row, column))));
        } else if (this.getColumnClass(column).equals(JComboBox.class)) {
            JComboBox comboBox = new JComboBox();
            comboBox.setBackground(new Color(252, 252, 217));
            switch (column) {
                case 3 -> {
                    for (var el : Employee.WORKING_RATE) {
                        comboBox.addItem(el);
                    }
                    comboBox.setSelectedIndex((Byte) this.getValueAt(row, column));
                }
                case 4 -> {
                    comboBox.removeAllItems();
                    for (var el : Employee.POSTS) {
                        comboBox.addItem(el);
                    }
                    comboBox.setSelectedIndex((Byte) this.getValueAt(row, column));
                }
                case 5 -> {
                    comboBox.removeAllItems();
                    for (var el : Employee.DEPARTMENTS) {
                        comboBox.addItem(el);
                    }
                    comboBox.setSelectedIndex((Byte) this.getValueAt(row, column));
                }
                case 6 -> {
                    comboBox.removeAllItems();
                    for (var el : Employee.CABINET_NUMBER) {
                        comboBox.addItem(el);
                    }
                    comboBox.setSelectedIndex((Byte) this.getValueAt(row, column));
                    comboBox.setName(" ");
                   
                }
            }
            return new DefaultCellEditor(comboBox) { //пришлось переопределить данный метот, как как он возвращал в стандартном поведении сам элемент, а не индекс
                @Override
                public Object getCellEditorValue() {
                    //System.out.println("Возвращаем значение  " + comboBox.getSelectedIndex());
                    return comboBox.getName()!=null?(byte)(comboBox.getSelectedIndex()+1):(byte)comboBox.getSelectedIndex();
                }
            };
        } else {
            JCheckBox checkBox = new JCheckBox();
            if(column==7)
            {
                checkBox.setSelected((Boolean)this.getValueAt(row, column));
            }   
            return new DefaultCellEditor(checkBox);
        }
    }
}
