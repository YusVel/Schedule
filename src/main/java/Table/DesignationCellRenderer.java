/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import com.jhlabs.image.BorderFilter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import yusvel.schedule.employee.Employee;

public class DesignationCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JButton component = new JButton(); //основной компонент для рентера
        ArrayList<Employee> employees = ((ScheduleTableModel) (table.getModel())).mainTable.getEmployees(); //массив сотрудников в таблице
        Calendar date = ((ScheduleTableModel) (table.getModel())).mainTable.getDate(); // иесяц текущего графика
        int thicknessLine = 2;

        component.setFont(new Font("Verdena", Font.PLAIN, 14));

        if (column > 1) {
            if (value.getClass().equals(String.class)) {
                System.out.println("Рисуем");
                return new DefaultTableCellRenderer().getTableCellRendererComponent(table, "", isSelected, hasFocus, row, column);
            }
            component.setText(Designations.ACCEPTABLE[((Designations) value).getValueInt()]);

        }
        if (column == 1) {
            component.setText((String) value);
            component.setHorizontalAlignment(SwingConstants.LEFT);

        }
        if (column == 0) {
            component.setText(Byte.toString((Byte) value));
        }

        if (!(row < table.getRowCount() - 1 && employees.get(row).getCabineNumber().equals(employees.get(row + 1).getCabineNumber()))) {
            component.setBorder(BorderFactory.createMatteBorder(0, 0, thicknessLine, 0, Color.BLACK));
            if ((employees.get(row).getDepartment().intValue() == 0 && employees.get(row + 1).getDepartment().intValue() == 0)) {
                component.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
            }
        }
        if (row == 0) {
            component.setBorder(BorderFactory.createMatteBorder(thicknessLine, 0, 0, 0, Color.BLACK)); //граница сверху
        }

        if (isSelected) {
            component.setBackground(new Color(117, 184, 255));
        } else {
            component.setBackground(new Color(252, 252, 217));
            Calendar tempDate = Calendar.getInstance();
            tempDate.set(date.get(Calendar.YEAR), date.get(Calendar.MONTH), column - 2);
            tempDate.setFirstDayOfWeek(Calendar.MONDAY);
            if ((tempDate.get(Calendar.DAY_OF_WEEK) == 6 || tempDate.get(Calendar.DAY_OF_WEEK) == 7) && column > 1)// если выходной день то ячейка серая
            {
                component.setBackground(new Color(212, 210, 210));
            }
        }
        if (hasFocus) {
            component.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 242), thicknessLine, false));
        } else {
            component.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 242), 0, false));

        }

        return component;

    }
}
