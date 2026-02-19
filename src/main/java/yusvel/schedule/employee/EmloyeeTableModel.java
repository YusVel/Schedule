
package yusvel.schedule.employee;

import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;


public class EmloyeeTableModel extends AbstractTableModel{

    ArrayList<Employee> Employees;
    public EmloyeeTableModel(ArrayList<Employee> Employees)
    {
        //System.out.println(Employees.size());
        this.Employees = Employees;
        Employees.sort(new Comparator(){
                                            @Override
                                            public int compare(Object o1, Object o2) {
                                                return ((Employee)o1).compareTo((Employee)o2);
                                            }
                                        });
    }
    @Override
    public int getRowCount() {
        return Employees.size();
    }

    @Override
    public int getColumnCount() {
        return 7; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = new Object();
        switch(columnIndex)
        {
            case 0 -> ret = Employees.get(rowIndex).getSurname();
            case 1 -> ret = Employees.get(rowIndex).getName();
            case 2 -> ret = Employees.get(rowIndex).getPatronomic();
            case 3 -> ret = Employees.get(rowIndex).getWorkingRate();
            case 4 -> ret = Employee.POSTS[Employees.get(rowIndex).getPost()];
            case 5 -> ret = Employee.DEPARTMENTS[Employees.get(rowIndex).getDepartment()].replaceAll("Лечебное отделение", "");
            case 6 -> ret = Employees.get(rowIndex).getCabineNumber(); 
        }
        return ret;
    }
        @Override
    public String getColumnName(int column) {
        String ret = "";
        switch(column)
        {
            case 0 -> ret = "ФАМИЛИЯ";
            case 1 -> ret = "ИМЯ";
            case 2 -> ret = "ОТЧЕСТВО";
            case 3 -> ret = "СТАВКА";
            case 4 -> ret = "ДОЛЖНОСТЬ";
            case 5 -> ret = "ОТДЕЛЕНИЕ";
            case 6 -> ret = "№ КАБИНЕТА"; 
        }
        return ret;
    }
    
}
