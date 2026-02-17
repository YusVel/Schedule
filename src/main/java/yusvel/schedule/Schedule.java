

package yusvel.schedule;
import Date.DatePicker;
import Table.Designations;
import Table.MainTable;
import Table.ScheduleTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


import static java.lang.System.out;
import java.util.*;
import org.jdesktop.swingx.JXFrame;


public class Schedule extends JFrame implements ActionListener, MouseListener{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("Файл");
    JMenu menuOpen = new JMenu("Открыть");
    JMenu menuHelp = new JMenu("Help");
    Schedule()
    {
        ////////////////////////////////Настройка главного окна//////////////////////////////////////////
        super("Schedule");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setBounds(this.getLocation().x-width/2+70, this.getLocation().y-height/2+40, width, height);
        ////////////////////////////////////////////////Настройки меню Файл/////////////////////////////////////////////////////
        menuFile.add(new JMenu("Открыть"));
            ((JMenu)menuFile.getMenuComponent(0)).add(new JMenuItem("График"));
            ((JMenu)menuFile.getMenuComponent(0)).add(new JMenuItem("Список сотрудников"));
        
        ///////////////////////////////////////////Настройки элементов главного MENU/////////////////////////////////////////////////////
        menuBar.add(menuFile);
        menuBar.add(new JSeparator(JSeparator.VERTICAL));
        menuBar.add(menuOpen);
        menuBar.add(new JSeparator(JSeparator.VERTICAL));
        menuBar.add(menuHelp);
        
        ////////////////////////////////////Добавляем все элементы в главное окно/////////////////////////////////
        this.setJMenuBar(menuBar);
        ///////////////////////////////////////////////////////////////////////////////////////////////
        this.setVisible(true);
    }
    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
        new Schedule();   
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
         out.println("Something CLICKED");
        if(e.getSource() instanceof JButton b )
        {
           if (b.getText().equals("OPEN"))
           {
               out.println("OPEN button CLICKED");
           }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource() instanceof JButton b)
        {
            if(b.getText().equals("OPEN"))
            {  
              ((JButton)e.getSource()).setForeground(Color.red);
            }
           
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource() instanceof JButton b)
        {
            if(b.getText().equals("OPEN"))
            {  
              ((JButton)e.getSource()).setForeground(Color.BLACK);
            }
           
        }
    }
  
}
