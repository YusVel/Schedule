

package yusvel.schedule;
import Date.DatePicker;
import Table.CreationNewTableDialog;
import Table.Designations;
import Table.MainJTable;
import Table.MainTable;
import Table.ScheduleTableModel;
import Table.TblFileFilter;
import com.jhlabs.image.BorderFilter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.IOException;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.Toolkit;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;


import static java.lang.System.out;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTextField;
import yusvel.schedule.employee.EmloyeeTableModel;
import yusvel.schedule.employee.EmpFileFilter;
import yusvel.schedule.employee.TableEmployees;


public class Schedule extends JFrame implements ActionListener, MouseListener{
    int width = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    ArrayList<MainTable> arrayTableModels = new ArrayList<MainTable>();
    
    
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("Файл");
        JMenu menuOpen = new JMenu("Открыть");
            JMenuItem openSchedule = new JMenuItem("График");
            JMenuItem openEmployees = new JMenuItem("Список сотрудников");
    JToolBar menuTools = new JToolBar ();
            JButton back = new JButton(new ImageIcon(Paths.get("icons","arrow_back.png").toAbsolutePath().toString()));
            JButton forward = new JButton(new ImageIcon(Paths.get("icons","arrow_forward.png").toAbsolutePath().toString()));
            JButton save = new JButton(new ImageIcon(Paths.get("icons","save.png").toAbsolutePath().toString()));
            JButton saveAs = new JButton(new ImageIcon(Paths.get("icons","save_as.png").toAbsolutePath().toString())); 
            JButton createSchedule = new JButton(new ImageIcon(Paths.get("icons","create_table.png").toAbsolutePath().toString())); 
            JButton addEmployee = new JButton(new ImageIcon(Paths.get("icons","add_employee.png").toAbsolutePath().toString()));
            JButton removeEmployee = new JButton(new ImageIcon(Paths.get("icons","remove_employee.png").toAbsolutePath().toString())); 
            JButton editEmployee = new JButton(new ImageIcon(Paths.get("icons","edit_employee.png").toAbsolutePath().toString()));   
    JMenu menuHelp = new JMenu("Help");
    JTabbedPane tabbedPane = new JTabbedPane();
    JTextField textField = new JXTextField();
    Schedule()
    {
        String str = Paths.get("icons","arrow_back.ico").toAbsolutePath().toString();
        System.out.println("ПУТЬ: "+str);
        ////////////////////////////////Настройка главного окна//////////////////////////////////////////
        super("Schedule");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setBounds(this.getLocation().x-width/2+70, this.getLocation().y-height/2+40, width, height);
        ////////////////////////////////////////////////Настройки меню Файл/////////////////////////////////////////////////////
        openEmployees.addActionListener(this);
        openSchedule.addActionListener(this);
        menuOpen.add(openSchedule);
        menuOpen.add(openEmployees);
        menuFile.add(menuOpen);
        
        
        ///////////////////////////////////////////Настройки элементов главного MENU/////////////////////////////////////////////////////
        menuBar.add(menuFile);
        menuBar.add(new JSeparator(JSeparator.VERTICAL));
        menuBar.add(menuHelp);
        /////////////////////////////////Меню инструментов///////////////////////////////////////////////////////
        back.addActionListener(this);
        forward.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        createSchedule.addActionListener(this);
        addEmployee.addActionListener(this);
        removeEmployee.addActionListener(this);
        editEmployee.addActionListener(this);
        
        back.addMouseListener(this);
        forward.addMouseListener(this);
        save.addMouseListener(this);
        saveAs.addMouseListener(this);
        createSchedule.addMouseListener(this);
        addEmployee.addMouseListener(this);
        removeEmployee.addMouseListener(this);
        editEmployee.addMouseListener(this);
 
        back.setBackground(new Color(212, 210, 210));
        forward.setBackground(new Color(212, 210, 210));
        save.setBackground(new Color(212, 210, 210));
        saveAs.setBackground(new Color(212, 210, 210));
        createSchedule.setBackground(new Color(212, 210, 210));
        addEmployee.setBackground(new Color(212, 210, 210));
        removeEmployee.setBackground(new Color(212, 210, 210));
        editEmployee.setBackground(new Color(212, 210, 210));
        
        menuTools.add(back);
        menuTools.add(forward);
        menuTools.add(save);
        menuTools.add(saveAs);
        menuTools.add(createSchedule);
        menuTools.add(addEmployee);
        menuTools.add(removeEmployee);
        menuTools.add(editEmployee);
        menuTools.setOrientation(JToolBar.VERTICAL);
        menuTools.setFloatable(false);
        ///////////////////////////////////Строка состояния////////////////////////////////////////////////
        textField.setBorder(BorderFactory.createLoweredBevelBorder());
        textField.setEditable(false);
        textField.setFont(new Font("Verdena", Font.ITALIC, 14));
        ////////////////////////////////////Добавляем все элементы в главное окно/////////////////////////////////
        this.setJMenuBar(menuBar);
        this.add(menuTools,BorderLayout.WEST);
        this.add(textField,BorderLayout.SOUTH);
        
      
        ///////////////////////////////////////////////////////////////////////////////////////////////
        this.setVisible(true);
    }
    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
        new Schedule();   
    }

    
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openEmployees )//Открываемт аблицу с сотрудниками
        {
            out.println("OPEN button CLICKED! Employees is OPENED");
            
            try
            {
                JScrollPane scrollTable = new JScrollPane(new TableEmployees(Employee.readFromFile()));
                tabbedPane.addTab("Сотрудники",null,scrollTable,"Все сотрудники учреждения");
                this.add(tabbedPane,BorderLayout.CENTER);
                tabbedPane.repaint();
            }
            catch(IOException ex)
            {
                out.println(ex);
            }
            catch(ClassNotFoundException ex)
            {
                out.println(ex);
            }
            this.setMinimumSize(new Dimension(width, height));
            this.pack();
        }
        if(e.getSource() == openSchedule )//открываем таблицу с основным графиком
        {
            out.println("OPEN button CLICKED! Schedule is OPENED");
            try
            {
                
                JFileChooser fDialog = new JFileChooser(new File(Paths.get("").toAbsolutePath().toString()));
                fDialog.setFileFilter(new TblFileFilter());
                
                int err = fDialog.showOpenDialog(this);
                if(err==JFileChooser.APPROVE_OPTION)
                {
                    if(fDialog.getSelectedFile().toString().endsWith("tbl"))
                    {
                        MainTable table = MainTable.readTableFromFile(fDialog.getSelectedFile().toString());
                        arrayTableModels.add(table);
                        JScrollPane scrollTable = new JScrollPane(new MainJTable(new ScheduleTableModel(table)));
                        tabbedPane.addTab(String.format("%s %d", DatePicker.MONTHS_OF_YEAR[table.getDate().get(Calendar.MONTH)].toUpperCase(),table.getDate().get(Calendar.YEAR)),null,scrollTable,"Все сотрудники учреждения");

                        this.add(tabbedPane,BorderLayout.CENTER);
                        this.setMinimumSize(new Dimension(width,height));
                        this.pack();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Файд должен иметь расширение .tbl!");
                    }
                }  
            }
            catch(IOException ex)
            {
                out.println(ex);
            }
            catch(ClassNotFoundException ex)
            {
                out.println(ex);
            }  
        }
        if(e.getSource()==back)
        {
            textField.setText("Откатить изменения!!");
        }
        if(e.getSource()==forward)
        {
            textField.setText("Накатить изменения!!");
        }
        if(e.getSource()==save)
        {
            textField.setText("Сохранить!!");
        }
        if(e.getSource()==saveAs)
        {
            textField.setText("Сохранить как!!");
        }
        if(e.getSource()==createSchedule)
        {
            textField.setText("Создать новый график!!");
            try {
                CreationNewTableDialog dialog = new CreationNewTableDialog(this, Employee.readFromFile());
            } catch (IOException ex) {
                textField.setText("Error, can not open file with employeess: "+ex);
            } catch (ClassNotFoundException ex) {
                textField.setText("Error, can not find file with employeess: "+ex);
            }
        }
        if(e.getSource()==addEmployee)
        {
            textField.setText("Добавить сотрудника!!");
        }
        if(e.getSource()==removeEmployee)
        {
            textField.setText("Удалить сотрудника!!");
        }
        if(e.getSource()==editEmployee)
        {
            textField.setText("Редактировать карту сотрудника!!");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
           ((JButton)e.getSource()).setBackground(new Color(252, 252, 217)); 
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() instanceof JButton) {
            ((JButton)e.getSource()).setBackground(new Color(212, 210, 210));
        }
    }

}
