

package yusvel.schedule;
import Date.DatePicker;
import java.util.NoSuchElementException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import yusvel.schedule.employee.WindowEmployeeCreator;
import mainTable.*;

import static java.lang.System.out;



public class Schedule {

    public static void main(String[] args) throws InterruptedException  {
    try
    {
        Designation a = new Designation("У/В");
    }
    catch(IllegalArgumentException e)
    {
       out.println(e.getLocalizedMessage());
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
