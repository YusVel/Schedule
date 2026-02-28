/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package yusvel.schedule.employee;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableEmployees extends JTable {
    public TableEmployees(ArrayList<Employee> Employees){
        this.setModel(new EmloyeeTableModel(Employees));
        this.getColumnModel().getColumn(0).setPreferredWidth(100);//выставляем ширину колонки с фамилиями
        this.getColumnModel().getColumn(1).setPreferredWidth(60);//с именем
        this.getColumnModel().getColumn(2).setPreferredWidth(100);//с отчеством
        this.getColumnModel().getColumn(3).setPreferredWidth(5);//савка
        this.getColumnModel().getColumn(4).setPreferredWidth(30);//должность
        this.getColumnModel().getColumn(5).setPreferredWidth(30);//отделение
        this.getColumnModel().getColumn(6).setPreferredWidth(10);//кабинет
        
        this.setBackground(new Color(252, 252, 217));
        this.setFont(new Font("Verdena", Font.PLAIN, 18));
        this.setRowHeight(25);
        this.setGridColor(new Color(212, 210, 210));
        
        this.setVisible(true);
    }
}
