

package yusvel.schedule;
import Date.DatePicker;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import yusvel.schedule.employee.WindowEmployeeCreator;


public class Schedule {

    public static void main(String[] args) throws InterruptedException  {
        System.out.println("Hello World!");
  
     
        
       Employee e = new Employee();
       e.createNewEmployee();
       
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
