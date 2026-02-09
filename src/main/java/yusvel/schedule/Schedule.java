

package yusvel.schedule;
import Table.Designations;
import Table.MainTable;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.FileNotFoundException;
import java.io.IOException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;


import static java.lang.System.out;
import java.util.*;
import org.jdesktop.swingx.JXFrame;


public class Schedule {

    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
        
       
        
        
        int countE = 24;
        ArrayList<Employee> arr = new ArrayList<>(24);
        /*
        for(int i = 0;i<countE;i++)
        {
            arr.add(Employee.create());
        }
        Employee.writeToFile(arr);
        */
        
        arr=Employee.readFromFile();
        MainTable table = new MainTable(arr,Calendar.getInstance(),150.0);
        for(var e:arr)
        {
            out.println(e.getFullName()+" \t"+e.getWorkSchedule()+" "+e.getWorkSchedule().size());
        }
        
    }
  
}
