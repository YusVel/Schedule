package Table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
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

public class CreationNewTableDialog extends JDialog implements ActionListener {

    static final Font FONT = new Font("Verdena", Font.PLAIN, 14);
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
    JLabel postsLabel = new JLabel("по должностям:");
    JLabel departmentsLabel = new JLabel("по отделениям:");
    JPanel filtersPanel = new JPanel();
    JPanel filtersPanelPosts = new JPanel(new GridLayout(6, 2));
    
    JPanel filtersPanelDepartments = new JPanel(new GridLayout(5, 1));

    ArrayList<JCheckBox> arrayCheckBoxesOfPosts = new ArrayList<JCheckBox>();
    ArrayList<JCheckBox> arrayCheckBoxesOfDepartments = new ArrayList<JCheckBox>();

    JScrollPane scrollTable;

    Insets insets = new Insets(3, 3, 3, 3);

    public CreationNewTableDialog(JFrame owner, ArrayList<Employee> employees) {
        super(owner, "Создание новой таблицы");
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridBagLayout());

        scrollTable = new JScrollPane(new CheckBoxEmployeesTable(employees));

        workingHourlabel.setFont(FONT);
        monthLabel.setFont(FONT);
        monthCombobox.setPreferredSize(new Dimension(80, 20));
        yearLabel.setFont(FONT);
        yearCombobox.setPreferredSize(new Dimension(80, 20));
        employeesLabel.setFont(FONT);
        filters.setFont(new Font("Verdena", Font.BOLD, 14));
        filtersPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < Employee.POSTS.length; i++) {
            JCheckBox checbox = new JCheckBox(Employee.POSTS[i]);
            checbox.addActionListener(this);
            filtersPanelPosts.add(checbox, BorderLayout.CENTER);
            arrayCheckBoxesOfPosts.add(checbox);
        }
        for (int i = 0; i < Employee.DEPARTMENTS.length; i++) {
            JCheckBox checbox = new JCheckBox(Employee.DEPARTMENTS[i]);
            checbox.addActionListener(this);
            filtersPanelDepartments.add(checbox, BorderLayout.CENTER);
            arrayCheckBoxesOfDepartments.add(checbox);
        }

        filtersPanel.add(filtersPanelPosts, new GridBagConstraints(0, 0, 2, 5, 0, 0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets, 0, 0));
        filtersPanel.add(new JSeparator(JSeparator.VERTICAL), new GridBagConstraints(2, 0, 1, 5, 0, 0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(3, 3, 3, 10), 0, 0));
        filtersPanel.add(filtersPanelDepartments, new GridBagConstraints(3, 0, 1, 5, 0, 0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets, 0, 0));

        this.add(monthLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(yearLabel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(workingHourlabel, new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(monthCombobox, new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(yearCombobox, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(workingHourTextField, new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(new JSeparator(JSeparator.HORIZONTAL),new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(filters, new GridBagConstraints(0, 4, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(postsLabel,new GridBagConstraints(0,5,2,1,1.0,1.0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets,0,0));
        this.add(departmentsLabel,new GridBagConstraints(2,5,1,1,1.0,1.0,GridBagConstraints.CENTER,GridBagConstraints.NONE,insets,0,0));
        this.add(filtersPanel, new GridBagConstraints(0, 6, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(scrollTable,new GridBagConstraints(0,7,3,1,1.0,1.0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets,0,0));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
                    ret = Employee.DEPARTMENTS[emp.getDepartment()].startsWith("Лечебное") ? Employee.DEPARTMENTS[emp.getPost()].substring(19) : Employee.DEPARTMENTS[emp.getPost()];
            }
            return ret;
        } else {
            return ArrayBooleans.get(rowIndex);
        }
    }

}
