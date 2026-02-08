

package yusvel.schedule;
import Table.Designations;
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
     
        int countE = 3;
      
        ArrayList<Employee> arr = new ArrayList<>();
        out.println(Employee.create());
        /*
        JFrame window = new JFrame("LayOut");
        window.setDefaultCloseOperation(JXFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridBagLayout());
      
        
        JTable table = new JTable(); 
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(400,400));
        window.add(tableScroll,new GridBagConstraints(0,0,1,1,1,1,GridBagConstraints.NORTH,1,new Insets(3,3,3,3),0,0));
        window.setLocationRelativeTo(null);
        window.pack();
        window.setVisible(true);
        */
    }
  
}
