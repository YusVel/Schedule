

package yusvel.schedule;
import Table.DesignationInTheMainTable;
import Date.DatePicker;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import yusvel.schedule.employee.WindowEmployeeCreator;

import static java.lang.System.out;
import java.util.*;


public class Schedule {

    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
     
        int countE = 3;
      
        ArrayList<Employee> arr = new ArrayList<>();
        /*
        for(int i = 0;i<countE;i++)
        {
            if(arr.add(Employee.create())){out.println(arr.getLast());}
            else{out.println("Данный сотрудник быд РАНЕЕ ДОБАВЛЕН");}
        }
        out.println("Колическтво сотрудников перед записью: "+arr.size());
        Employee.writeToFile(arr);
        for(Employee e:arr){out.println(e);}
        */
        arr = Employee.readFromFile();
        out.println("Колическтво сотрудников: "+arr.size());
        for(Employee e:arr){out.println(e);}
        var iter = arr.iterator();iter = arr.iterator();
        
        for(int i = 0;i<arr.size();i++)
        {
          arr.get(i).chandge();
        }
        Employee.writeToFile(arr);
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
