
package yusvel.schedule.employee;

import java.util.ArrayList;
import java.util.Comparator;
import javax.swing.table.AbstractTableModel;


public class EmloyeeTableModel extends AbstractTableModel{

    ArrayList<Employee> employees;
    ArrayList<Boolean> arrayBooleans;
    
    public static final int EDITABLE = 0;
    public static final int SELECTABLE = 1;
    private int mode = 0;
    public EmloyeeTableModel(ArrayList<Employee> Employees, int mode)
    {
        if (mode < 0 || mode > 1) {
            this.mode = 0;
        } else {
            this.mode = mode;
        }

        this.employees = Employees;
        Employees.sort(new Comparator(){
                                            @Override
                                            public int compare(Object o1, Object o2) {
                                                return ((Employee)o1).compareTo((Employee)o2);
                                            }
                                        });
        if(this.mode==SELECTABLE)
        {
           arrayBooleans = new ArrayList<Boolean>(employees.size());
           for(int i = 0;i<employees.size();i++)
           {
               arrayBooleans.add(Boolean.FALSE);
           }
        }
    }
    @Override
    public int getRowCount() {
        return employees.size();
    }

    @Override
    public int getColumnCount() {
        if(mode==SELECTABLE){
            return 8; 
        }
        return 7; 
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = new Object();
        switch(columnIndex)
        {
            case 0 -> ret = employees.get(rowIndex).getSurname();
            case 1 -> ret = employees.get(rowIndex).getName();
            case 2 -> ret = employees.get(rowIndex).getPatronomic();
            case 3 -> ret = employees.get(rowIndex).getWorkingRate();
            case 4 -> ret = Employee.POSTS[employees.get(rowIndex).getPost()];
            case 5 -> ret = Employee.DEPARTMENTS[employees.get(rowIndex).getDepartment()].replaceAll("Лечебное отделение", "");
            case 6 -> ret = employees.get(rowIndex).getCabineNumber(); 
            case 7 -> ret = arrayBooleans.get(rowIndex); 
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
            case 7 -> ret = ""; 
        }
        return ret;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> ret = Object.class;
        switch(columnIndex)
        {
            case 0 -> ret = String.class;
            case 1 -> ret = String.class;
            case 2 -> ret = String.class;
            case 3 -> ret = Float.class;
            case 4 -> ret = String.class;
            case 5 -> ret = String.class;
            case 6 -> ret = Byte.class;
            case 7 -> ret = Boolean.class; 
        }
        return ret;
    }
    @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            boolean ret = false;
        switch(mode)
        {
            case EDITABLE:ret=true; break;
            case SELECTABLE:if(columnIndex==7){ret=true;}break;
        }
        return ret;
    }
    @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            if(mode==EDITABLE)
            {
                switch(columnIndex)
                {
                    case 0: employees.get(rowIndex).setSurname((String)aValue);break;
                    case 1: employees.get(rowIndex).setName((String)aValue);break;
                    case 2: employees.get(rowIndex).setPatronomic((String)aValue);break;
                    case 3: employees.get(rowIndex).setWorkingRate((Float)aValue);break;
                    case 4: employees.get(rowIndex).setPost((Byte)aValue);break;
                    case 5: employees.get(rowIndex).setDepartment((Byte)aValue);break;
                    case 6: employees.get(rowIndex).setCabinetNumber((Byte)aValue);break;
                }
            }else if(mode==SELECTABLE)
            {
                arrayBooleans.set(rowIndex, (Boolean)aValue);
            }
    }
}
