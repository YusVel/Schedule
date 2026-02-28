package Table;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import yusvel.schedule.employee.Employee;

public class CreationNewTableDialog extends JDialog {

    static final Font FONT = new Font("Verdena", Font.PLAIN, 16);
    Calendar date = Calendar.getInstance();
    Float workingHour;
    JLabel workingHourlabel = new JLabel("Количество часов \nв текущем месяце: ");
    JTextField workingHourTextField = new JTextField();
    JLabel monthLabel = new JLabel("Месяц: ");
    JComboBox monthCombobox = new JComboBox();
    JLabel yearLabel = new JLabel("Год: ");
    JComboBox yearCombobox = new JComboBox();
    JLabel employeesLabel = new JLabel("Список сотрудников");
    JLabel filters = new JLabel("Фильтры");
    JScrollPane scrollTable;

    public CreationNewTableDialog(JFrame owner, ArrayList<Employee> employees) {
        super(owner, "Создание новой таблицы");
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout());

        workingHourlabel.setFont(FONT);
        scrollTable = new JScrollPane(new CheckBoxEmployeesTable(employees));

        this.add(scrollTable);

        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

}

class CheckBoxEmployeesTable extends JTable {

    static final Font FONT = new Font("Verdena", Font.PLAIN, 16);

    public CheckBoxEmployeesTable(ArrayList<Employee> employees) {
        super(new CheckBoxEmployeesTableModel(employees));
        this.setFont(FONT);
        this.getColumnModel().getColumn(0).setPreferredWidth(80);
        this.getColumnModel().getColumn(1).setPreferredWidth(30);
        this.getColumnModel().getColumn(2).setPreferredWidth(10);
        this.getColumnModel().getColumn(3).setPreferredWidth(10);
        this.setRowHeight(25);
        this.setBackground(new Color(252, 252, 217));
    }

}

class CheckBoxEmployeesTableModel extends AbstractTableModel //модель таблицы с двумя колонками
{

    ArrayList<Employee> employees;
    ArrayList<Boolean> ArrayBooleans = new ArrayList<Boolean>();

    CheckBoxEmployeesTableModel(ArrayList<Employee> employees) {
        this.employees = employees;
        for (var e : employees) {
            this.ArrayBooleans.add(Boolean.TRUE);
        }
    }

    @Override
    public String getColumnName(int column) {
        String ret = "";
        switch (column) {
            case 0 ->
                ret = "ФИО";
            case 1 ->
                ret = "Должность";
            case 2 ->
                ret = "Отделение";
            case 3 ->
                ret = "Добавление";

        }
        return ret;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> ret = Object.class;
        switch (columnIndex) {
            case 0, 1, 2 ->
                ret = String.class;
            case 3 ->
                ret = Boolean.class;

        }
        return ret;
    }

    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {//можно галочки ставить только в колонке с чекбоксами
        return columnIndex == 3;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (columnIndex == 3) {
            ArrayBooleans.set(rowIndex, (Boolean) aValue);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {//Возвращаем строки, если это первые 3 колонки,а четвертой колонкой будет чекбокс
        if (columnIndex < 3) {
            Employee emp = employees.get(rowIndex);
            String ret = "";
            switch (columnIndex) {
                case 0 ->
                    ret = emp.getSurname() + " " + emp.getName().substring(0, 1) + ". " + emp.getPatronomic().substring(0, 1) + '.';
                case 1 ->
                    ret = Employee.POSTS[emp.getPost()];
                case 2 ->
                    ret = Employee.DEPARTMENTS[emp.getDepartment()].startsWith("Лечебное")?Employee.DEPARTMENTS[emp.getPost()].substring(19):Employee.DEPARTMENTS[emp.getPost()];
            }
            return ret;
        } else {
            return ArrayBooleans.get(rowIndex);
        }
    }

}
