

package yusvel.schedule;
import Date.DatePicker;
import Table.Designations;
import Table.MainTable;
import Table.ScheduleTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import java.awt.FileDialog;
import java.io.File;


import static java.lang.System.out;
import java.util.*;
import org.jdesktop.swingx.JXFrame;


public class Schedule {
    static JFrame window = new JFrame("Окно");
    static String path;
    static FileDialog file = new FileDialog(window,"Выберете файл",FileDialog.LOAD);
    static JTable table = new JTable();
    
    static void getFullFileName()
    {
        file.setFilenameFilter((File dir, String name) -> name.endsWith(".tbl")&&dir.canRead());
        file.setVisible(true);
        path = file.getDirectory()+file.getFile();
        try
        {
            MainTable tb = MainTable.readTableFromFile(path);
            out.println(tb.getDate().get(Calendar.YEAR)+" "+DatePicker.MONTHS_OF_YEAR[tb.getDate().get(Calendar.MONTH)]);
            table.setModel(new ScheduleTableModel(tb));
             table.getColumnModel().getColumn(0).setPreferredWidth(10);
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
            for(int i = 2;i<table.getColumnCount();i++)
            {
                table.getColumnModel().getColumn(i).setPreferredWidth(15);
            }
        }
        catch(Exception e)
        {
            out.println(e);
            
        }
        
    }
    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
  
        JFrame window = new JFrame("Окно");
        window.setLayout(new BorderLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
      
        int w = 1200;
        int h = 800;
        int y_rel_pos = window.getLocation().y-h/2;
        int x_rel_pos = window.getLocation().x-w/2;
        window.setSize(w, h);
        window.setLocation(x_rel_pos, y_rel_pos);
        JButton button = new JButton("Открыть");
        button.addActionListener(e->{getFullFileName();});
        
      


        JScrollPane scroll = new JScrollPane(table);
        //scroll.setPreferredSize(new Dimension(800,700));

        window.add(scroll,BorderLayout.CENTER);
        window.add(button,BorderLayout.SOUTH);
        window.setVisible(true);
        window.pack();
    }
  
}
