

package yusvel.schedule;
import Date.DatePicker;
import java.util.NoSuchElementException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import yusvel.schedule.employee.WindowEmployeeCreator;
import mainTable.*;

import static java.lang.System.out;
import java.util.*;


public class Schedule {

    public static void main(String[] args) throws InterruptedException  {
     
        int countE = 3;
        WindowEmployeeCreator w = new WindowEmployeeCreator();
        ArrayList<Employee> listOfEmployees = new ArrayList<>(countE);
        for(int i = 0;i<countE;i++)
        {
            listOfEmployees.add(w.createNewEmployee());
        }
        for(Employee e:listOfEmployees)
        {
            out.println(e);
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
