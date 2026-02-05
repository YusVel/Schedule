

package yusvel.schedule;
import Date.DatePicker;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import yusvel.schedule.employee.WindowEmployeeCreator;
import mainTable.*;

import static java.lang.System.out;
import java.util.*;


public class Schedule {

    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
     
        int countE = 1;
        
        ArrayList<Employee> listOfEmployees = new ArrayList<>(countE);
        
        for(int i = 0;i<countE;i++)
        {
            listOfEmployees.add(Employee.create());
        }
        for(Employee e:listOfEmployees)
        {
            out.println(e);
        }
        out.println("Количество работников до записи в файл: "+ listOfEmployees.size());
        Employee.writeToFile(listOfEmployees);
        
        listOfEmployees = Employee.readFromFile();
        out.println("Количество работников: "+ listOfEmployees.size());
        for(Employee e:listOfEmployees)
        {
            out.println(e.getFullName());
        }
        
    /*
        JFrame window = new JFrame("sdsdfsdfsd");
        window.setLayout(null);
        window.setBounds(500, 500,500,500);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DatePicker piker = new DatePicker();
       
        window.add(piker);
        
        window.setVisible(true);
*/
    }
  
}
