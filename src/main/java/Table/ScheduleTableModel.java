package Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.AbstractTableModel;
import yusvel.schedule.employee.Employee;
import javax.swing.JLabel;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author yusup
 */
public class ScheduleTableModel extends AbstractTableModel{

    MainTable mainTable;
    private Integer ID = -1;

    public ScheduleTableModel(MainTable table) {
        mainTable = table;
    }

    public ScheduleTableModel(ScheduleTableModel tableModel) {
        mainTable = new MainTable(tableModel.getMainTable());
    }
    public void setNewMainTable(MainTable newOne)
    {
        mainTable = newOne;
    }
    public MainTable getMainTable() {
        return mainTable;
    }

    @Override
    public int getRowCount() {
        return mainTable.getEmployees().size();
    }

    @Override
    public int getColumnCount() {
        return mainTable.getDate().getActualMaximum(Calendar.DAY_OF_MONTH) + 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return mainTable.getEmployees().get(rowIndex).getCabineNumber();
        }
        if (columnIndex == 1) {
            return mainTable.getEmployees().get(rowIndex).getFullName();
        }
        return mainTable.getEmployees().get(rowIndex)
                .getWorkSchedule().get(columnIndex - 2);// из злавной таблицы получаем массив сотрудников по индексу строки определяем
        //нужного сотрудника и получаем у него массив обозначений(рабочий график) а оттуда
        //получаем по индексу колонки нужное обохзначение(У,В,у/В, ОТ и тд...
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "Каб.";
        }
        if (column == 1) {
            return "ФИО";
        }
        return String.valueOf(column - 1);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex > 1;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        mainTable.getEmployees().get(rowIndex).getWorkSchedule().set(columnIndex - 2, (Designations) aValue);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Designations.class;
    }

    public Calendar getDate() {
        return mainTable.getDate();
    }

    public Double getWorkingHour() {
        return mainTable.getWorkingHours();
    }

    public ArrayList<Employee> getEmployees() {
        return mainTable.getEmployees();
    }

    public void addEmployee(Employee newOne) {
        mainTable.addEmployee(newOne);
    }

    public void removeEmployee(int index) {
        mainTable.removeEmployee(index);
    }
    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public boolean equalsForSaveContext(Object obj) {
        return this.getClass().equals(obj.getClass()) && this.ID.equals(((ScheduleTableModel) obj).ID) && this.ID >= 0 && ((ScheduleTableModel) obj).ID >= 0;
    }
}
