

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
import javax.swing.table.AbstractTableModel;
import java.awt.Toolkit;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import javax.swing.event.TableModelListener;
import javax.swing.event.CellEditorListener;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.event.ChangeListener;
import java.io.File;


import static java.lang.System.out;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.TableModelEvent;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTextField;
import org.jdesktop.swingx.treetable.AbstractTreeTableModel;
import yusvel.schedule.employee.EmployeeTableModel;
import yusvel.schedule.employee.EmpFileFilter;
import yusvel.schedule.employee.TableEmployees;
import yusvel.schedule.employee.WindowEmployeeCreator;


public class Schedule extends JFrame implements ActionListener, MouseListener, ComponentListener, CellEditorListener{
    private int tableCount = 0;
    int width = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    ArrayList<AbstractTableModel> arrayTableModels = new ArrayList<AbstractTableModel>(); //массив всех таблиц в главном окне
    ArrayList<ArrayList<AbstractTableModel>> arrayContextsTables = new ArrayList<ArrayList<AbstractTableModel>>();//здесь будем сохранять состояние таблиц
    ArrayList<Integer> arrayContextsIPositionsTables = new ArrayList<Integer>();
    String fileNameToSave;
    
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("Файл");
        JMenu menuOpen = new JMenu("Открыть");
            JMenuItem openScheduleMenu = new JMenuItem("График");
            JMenuItem openEmployeesMenu = new JMenuItem("Список сотрудников");
    JToolBar menuTools = new JToolBar ();
            JButton backButton = new JButton(new ImageIcon(Paths.get("icons","arrow_back.png").toAbsolutePath().toString()));
            JButton forwardButton = new JButton(new ImageIcon(Paths.get("icons","arrow_forward.png").toAbsolutePath().toString()));
            JButton saveButton = new JButton(new ImageIcon(Paths.get("icons","save.png").toAbsolutePath().toString()));
            JButton saveAsButton = new JButton(new ImageIcon(Paths.get("icons","save_as.png").toAbsolutePath().toString())); 
            JButton createTableButton = new JButton(new ImageIcon(Paths.get("icons","create_table.png").toAbsolutePath().toString()));
                JDialog creationDialog = new JDialog(this);
                    JPanel creationPanel = new JPanel(new GridBagLayout());
                        JButton createScheduleTable = new JButton("График");
                        JButton createEmployeesTable = new JButton("Сотрудники");
            JButton addEmployeeButton = new JButton(new ImageIcon(Paths.get("icons","add_employee.png").toAbsolutePath().toString()));
            JButton removeEmployeeButton = new JButton(new ImageIcon(Paths.get("icons","remove_employee.png").toAbsolutePath().toString())); 
            JButton editEmployeeButton = new JButton(new ImageIcon(Paths.get("icons","edit_employee.png").toAbsolutePath().toString()));   
    JMenu menuHelp = new JMenu("Help");
    JTabbedPane tabbedPane = new JTabbedPane();
    JTextField outputConsolLine = new JXTextField();
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
        openEmployeesMenu.addActionListener(this);
        openScheduleMenu.addActionListener(this);
        menuOpen.add(openScheduleMenu);
        menuOpen.add(openEmployeesMenu);
        menuFile.add(menuOpen);
        
        ///////////////////////////////////////////Настройки элементов главного MENU/////////////////////////////////////////////////////
        menuBar.add(menuFile);
        menuBar.add(new JSeparator(JSeparator.VERTICAL));
        menuBar.add(menuHelp);
        /////////////////////////////////Меню инструментов///////////////////////////////////////////////////////
        backButton.addActionListener(this);
        forwardButton.addActionListener(this);
        saveButton.addActionListener(this);
        saveAsButton.addActionListener(this);
        createTableButton.addActionListener(this);
        addEmployeeButton.addActionListener(this);
        removeEmployeeButton.addActionListener(this);
        editEmployeeButton.addActionListener(this);
        createScheduleTable.addActionListener(this);
        createEmployeesTable.addActionListener(this);
        
        backButton.addMouseListener(this);
        forwardButton.addMouseListener(this);
        saveButton.addMouseListener(this);
        saveAsButton.addMouseListener(this);
        createTableButton.addMouseListener(this);
        addEmployeeButton.addMouseListener(this);
        removeEmployeeButton.addMouseListener(this);
        editEmployeeButton.addMouseListener(this);
        createScheduleTable.addMouseListener(this);
        createEmployeesTable.addMouseListener(this);
        tabbedPane.addMouseListener(this);
 
        backButton.setBackground(new Color(212, 210, 210));
        forwardButton.setBackground(new Color(212, 210, 210));
        saveButton.setBackground(new Color(212, 210, 210));
        saveAsButton.setBackground(new Color(212, 210, 210));
        createTableButton.setBackground(new Color(212, 210, 210));
        addEmployeeButton.setBackground(new Color(212, 210, 210));
        removeEmployeeButton.setBackground(new Color(212, 210, 210));
        editEmployeeButton.setBackground(new Color(212, 210, 210));
        createEmployeesTable.setBackground(new Color(212, 210, 210)); 
        createScheduleTable.setBackground(new Color(212, 210, 210)); 
        
        creationDialog.setLayout(new GridBagLayout());
        creationDialog.setLocation(this.getX()+57,this.getY()+225);
        creationDialog.setBackground(new Color(112, 112, 112));
        creationDialog.setVisible(false);
        creationPanel.add(createScheduleTable,new GridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(1,1,1,1),0,0));
        creationPanel.add(createEmployeesTable,new GridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.BOTH,new Insets(1,1,1,1),0,0));
        creationDialog.add(creationPanel);
        creationDialog.setUndecorated(true);
        creationDialog.pack(); 
        creationDialog.setModal(false);
        this.addComponentListener(this);
        
        menuTools.add(backButton);
        menuTools.add(forwardButton);
        menuTools.add(saveButton);
        menuTools.add(saveAsButton);
        menuTools.add(createTableButton);
        menuTools.add(addEmployeeButton);
        menuTools.add(removeEmployeeButton);
        menuTools.add(editEmployeeButton);
        menuTools.setOrientation(JToolBar.VERTICAL);
        menuTools.setFloatable(false);
        ////////////////////////////////Настройка TabedPane///////////////////////////////////////////////
        tabbedPane.setBorder(BorderFactory.createLoweredBevelBorder());
        ///////////////////////////////////Строка состояния////////////////////////////////////////////////
        outputConsolLine.setBorder(BorderFactory.createLoweredBevelBorder());
        outputConsolLine.setEditable(false);
        outputConsolLine.setFont(new Font("Verdena", Font.ITALIC, 14));
        ////////////////////////////////////Добавляем все элементы в главное окно/////////////////////////////////
        this.setJMenuBar(menuBar);
        this.add(menuTools,BorderLayout.WEST);
        this.add(outputConsolLine,BorderLayout.SOUTH);
        this.add(tabbedPane,BorderLayout.CENTER);
      
        ///////////////////////////////////////////////////////////////////////////////////////////////
        this.setVisible(true);
    }
    public static void main(String[] args) throws  FileNotFoundException, IOException, ClassNotFoundException  {
        new Schedule();   
    }

    public void addScrollTable(JScrollPane scrollTable)
    {
        JTable table = ((JTable) scrollTable.getViewport().getView());
        AbstractTableModel tableModel = (AbstractTableModel) table.getModel();
        addListenersForChanging(table); //добавляем слушателея для отслеживания изменений в ячейках
        if (tableModel.getClass().equals(ScheduleTableModel.class)) {
            ((ScheduleTableModel)tableModel).setID(tableCount);
            tableCount++;
            MainTable mainTable = ((ScheduleTableModel)tableModel).getMainTable();
            tabbedPane.addTab(String.format("%s %d", DatePicker.MONTHS_OF_YEAR[mainTable.getDate().get(Calendar.MONTH)].toUpperCase(), mainTable.getDate().get(Calendar.YEAR)), null, scrollTable, "");
            arrayTableModels.add(tableModel);
            arrayContextsTables.add(new ArrayList<AbstractTableModel>());
            arrayContextsTables.get(tableCount-1).add(tableModel);// добавляем первый кадр в массив моделей
            arrayContextsIPositionsTables.add(0);// У первого кадра поциция 0
        }
        else if (tableModel.getClass().equals(EmployeeTableModel.class)) {
            ((EmployeeTableModel) tableModel).setID(tableCount);
            tableCount++;
            tabbedPane.addTab("Employees" + DatePicker.MONTHS_OF_YEAR[Calendar.getInstance().get(Calendar.MONTH)] + Calendar.getInstance().get(Calendar.YEAR) + ".emp", null, scrollTable, "");
            arrayTableModels.add(tableModel);
            arrayContextsTables.add(new ArrayList<AbstractTableModel>());
            arrayContextsTables.get(tableCount - 1).add(tableModel);// добавляем первый кадр в массив моделей
        }
        this.repaint();
    }
    private Integer isIdMatchedInContextsTablesArray(Integer ID)
    {
        for(int i = 0;i<arrayContextsTables.size();i++)
        {
            AbstractTableModel tableModel = arrayContextsTables.get(i).get(0);
            if(tableModel.getClass().equals(ScheduleTableModel.class))
            {
                if (((ScheduleTableModel) tableModel).getID().equals(ID)) {
                    return i;
                }
            }
            if(tableModel.getClass().equals(EmployeeTableModel.class))
            {
                if (((EmployeeTableModel) tableModel).getID().equals(ID)) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openEmployeesMenu)//Открываемт аблицу с сотрудниками
        {
            creationDialog.setVisible(false);
            JFileChooser fDialog = new JFileChooser(new File(Paths.get("").toAbsolutePath().toString()));
            fDialog.setDialogType(JFileChooser.SAVE_DIALOG);
            fDialog.setAcceptAllFileFilterUsed(false);
            fDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fDialog.setFileFilter(new EmpFileFilter());
            int err = fDialog.showOpenDialog(this);
            switch (err) {
                case JFileChooser.APPROVE_OPTION:
                    try {
                        TableEmployees table = new TableEmployees(Employee.readFromFile(fDialog.getSelectedFile().toString()), EmployeeTableModel.EDITABLE);
                        JScrollPane scrollTable = new JScrollPane(table);
                        addScrollTable(scrollTable);
                        tabbedPane.repaint();
                        this.setMinimumSize(new Dimension(width, height));
                        this.pack();
                        outputConsolLine.setText("Файл " + fDialog.getSelectedFile().toString()+ " ОТКРЫТ! ID = " +((EmployeeTableModel)table.getModel()).getID());
                    } catch (ClassNotFoundException ex) {
                        out.println(ex);
                    } catch (FileNotFoundException ex) {
                        outputConsolLine.setText(" Исключение при чтении FileNotFoundException");
                    } catch (IOException ex) {
                        outputConsolLine.setText(" Исключение при чтении IOException: указанный файл отсутствует или поврежден ");
                    }
                    break;


                case JFileChooser.CANCEL_OPTION:
                    outputConsolLine.setText("Открытие  файла " + fDialog.getSelectedFile() + " отменено!");
                    break;
                case JFileChooser.ERROR_OPTION:
                    JOptionPane.showMessageDialog(this, "Невозможно сохранить файл: " + fDialog.getSelectedFile());
                    break;
            }

        }
        if (e.getSource() == openScheduleMenu)//открываем таблицу с основным графиком
        {
            creationDialog.setVisible(false);
            JFileChooser fDialog = new JFileChooser(new File(Paths.get("").toAbsolutePath().toString()));
            fDialog.setDialogType(JFileChooser.SAVE_DIALOG);
            fDialog.setAcceptAllFileFilterUsed(false);
            fDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fDialog.setFileFilter(new TblFileFilter());
            int err = fDialog.showOpenDialog(this);
            switch (err) {
                case JFileChooser.APPROVE_OPTION:
                    ScheduleTableModel tableModel;
                    try {
                        tableModel = new ScheduleTableModel(MainTable.readTableFromFile(fDialog.getSelectedFile().toString()));
                        MainJTable table = new MainJTable(tableModel);
                        addScrollTable(new JScrollPane(table));
                        tableModel.fireTableDataChanged();
                        outputConsolLine.setText("Файл " + fDialog.getSelectedFile().toString()+" ОТКРЫТ! ID = " +((ScheduleTableModel)table.getModel()).getID());
                    } catch (ClassNotFoundException ex) {
                        out.println(ex);
                    } catch (FileNotFoundException ex) {
                        outputConsolLine.setText(" Исключение при чтении FileNotFoundException");
                    } catch (IOException ex) {
                        outputConsolLine.setText(" Исключение при чтении IOException: указанный файл отсутствует или поврежден ");
                    }
                    break;
                case JFileChooser.CANCEL_OPTION:
                    outputConsolLine.setText("Открытие  файла " + fDialog.getSelectedFile() + " отменено!");
                    break;
                case JFileChooser.ERROR_OPTION:
                    JOptionPane.showMessageDialog(this, "Невозможно сохранить файл: " + fDialog.getSelectedFile());
                    break;
            }
        }
        if(e.getSource()==backButton)
        {
            creationDialog.setVisible(false);
            if(tabbedPane.getComponentCount()>0)
            {
                JTable table = ((JTable)((JScrollPane)tabbedPane.getSelectedComponent()).getViewport().getView());
                removeListenersForChanging(table);
                int realPosition = arrayContextsIPositionsTables.get(tabbedPane.getSelectedIndex());
                if(realPosition>0)
                {
                    arrayContextsIPositionsTables.set(tabbedPane.getSelectedIndex(), realPosition - 1);
                   table.setModel(arrayContextsTables.get(tabbedPane.getSelectedIndex()).get(realPosition-1));
                   out.println("Назад");
                   this.repaint();
                }
                addListenersForChanging(table);
            }
            else
            {
                out.println("Нечего откатывать!!");
            }
            
        }
        if(e.getSource()==forwardButton)
        {
            outputConsolLine.setText("Накатить изменения!!");
            creationDialog.setVisible(false);
        }
        if(e.getSource()==saveButton)///////////////СОХРАНИТЬ///////////////
        {
            creationDialog.setVisible(false);
            if (tabbedPane.getComponentCount() == 0) {
                outputConsolLine.setText("What? Нет ни обной таблицы для сохранения");
            } else {
                if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(ScheduleTableModel.class)) //если вбранная таблица - график,
                {
                    fileNameToSave = ((ScheduleTableModel) arrayTableModels.get(tabbedPane.getSelectedIndex())).getMainTable().getShortFileNameToSave();//то вытаскиваем имя изнутри модели
                    try {
                        ((ScheduleTableModel) arrayTableModels.get(tabbedPane.getSelectedIndex())).getMainTable().writeTableToFile(Paths.get(fileNameToSave).toAbsolutePath().toString());
                    } catch (FileNotFoundException ex) {
                        outputConsolLine.setText("Ошибка сохранения файла: ");
                    }
                } else if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(EmployeeTableModel.class))//если вбранная таблица - таблица сотрудников,
                {
                    fileNameToSave = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());//то вытаскиваем имя изнутри компонента
                    EmployeeTableModel tableModel = (EmployeeTableModel)(((TableEmployees)((JScrollPane)(tabbedPane.getSelectedComponent())).getViewport().getView()).getModel());
                    try {
                        Employee.writeToFile(tableModel.getEmployees(), Paths.get(fileNameToSave).toAbsolutePath().toString());
                    } catch (FileNotFoundException ex) {
                          outputConsolLine.setText("Ошибка сохранения файла: "+fileNameToSave);
                    }
                }
                outputConsolLine.setText("Сохраняем в " + Paths.get(fileNameToSave).toAbsolutePath().toString());

            }

        }
        if (e.getSource() == saveAsButton) {
            /////////////СОХРАНИТЬ КАК////////////
            creationDialog.setVisible(false);
            if (tabbedPane.getComponentCount() == 0) {
                outputConsolLine.setText("What? Нет ни обной таблицы для сохранения");
            } else if (((JScrollPane) (tabbedPane.getSelectedComponent())).getViewport().getView().getClass().equals(MainJTable.class)) {
                outputConsolLine.setText("Сохранить как!! " + MainJTable.class);
                JFileChooser fDialog = new JFileChooser(new File(Paths.get("").toAbsolutePath().toString()));
                fDialog.setDialogType(JFileChooser.SAVE_DIALOG);
                fDialog.setAcceptAllFileFilterUsed(false);
                fDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fDialog.setFileFilter(new TblFileFilter());
                fileNameToSave = ((ScheduleTableModel) arrayTableModels.get(tabbedPane.getSelectedIndex())).getMainTable().getShortFileNameToSave();//то вытаскиваем имя изнутри модели
                fDialog.setSelectedFile(new File(fileNameToSave));
                int err = fDialog.showSaveDialog(this);
                switch (err) {
                    case JFileChooser.APPROVE_OPTION -> {
                        fDialog.setDialogTitle("Сохранить как");

                        try {
                            outputConsolLine.setText("Сохраняем в " + fDialog.getSelectedFile().toString());
                            ((ScheduleTableModel) arrayTableModels.get(tabbedPane.getSelectedIndex())).getMainTable().writeTableToFile(fDialog.getSelectedFile().toString());
                        } catch (FileNotFoundException ex) {
                            outputConsolLine.setText("Невозможно сохранить файл: " + fDialog.getSelectedFile().toString());
                        }

                    }
                    case JFileChooser.ERROR_OPTION ->
                        JOptionPane.showMessageDialog(this, "Невозможно сохранить", "ERROR!", JOptionPane.WARNING_MESSAGE);
                    case JFileChooser.CANCEL_OPTION ->
                        outputConsolLine.setText("Сохранение отменено!");
                    default -> {
                    }
                }
            }
            else if(((JScrollPane)(tabbedPane.getSelectedComponent())).getViewport().getView().getClass().equals(TableEmployees.class)) {
                outputConsolLine.setText("Сохранить как!! "+TableEmployees.class);
                JFileChooser fDialog = new JFileChooser(new File(Paths.get("").toAbsolutePath().toString()));
                fDialog.setDialogType(JFileChooser.SAVE_DIALOG);
                fDialog.setAcceptAllFileFilterUsed(false);
                fDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                fDialog.setFileFilter(new EmpFileFilter());
                fileNameToSave = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
                fDialog.setSelectedFile(new File(fileNameToSave));
                int err = fDialog.showSaveDialog(this);
                switch(err)
                {
                    case JFileChooser.APPROVE_OPTION: 
                        EmployeeTableModel tableModel = (EmployeeTableModel)(((TableEmployees)((JScrollPane)(tabbedPane.getSelectedComponent())).getViewport().getView()).getModel());
                    {
                        try {
                            Employee.writeToFile(tableModel.getEmployees(),fDialog.getSelectedFile().toString());
                        } catch (IOException ex) {
                            outputConsolLine.setText("Невозможно сохранить файл: " + fDialog.getSelectedFile().toString());
                        }
                    }
                        break;

                    case JFileChooser.CANCEL_OPTION:outputConsolLine.setText("Сохранение TableEmployees отменено!!");break;
                    case JFileChooser.ERROR_OPTION:JOptionPane.showMessageDialog(this, "Невозможно сохранить", "ERROR!", JOptionPane.WARNING_MESSAGE);break;
                }
            }

        }
        if (e.getSource() == createScheduleTable) {//Создать рабочий график 
            JFileChooser fDialog = new JFileChooser(new File(Paths.get("").toAbsolutePath().toString()));
            fDialog.setDialogTitle("Выберите файл с сотрудниками для генерации графика");
            fDialog.setDialogType(JFileChooser.OPEN_DIALOG);
            fDialog.setAcceptAllFileFilterUsed(false);
            fDialog.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            fDialog.setFileFilter(new EmpFileFilter());
            int err = fDialog.showOpenDialog(this);
            switch (err) {
                case JFileChooser.APPROVE_OPTION:
                {
                    try {
                        new CreationNewTableDialog(this, Employee.readFromFile(fDialog.getSelectedFile().toString()));
                    } catch (IOException ex) {
                        outputConsolLine.setText("Файл "+fDialog.getSelectedFile().toString()+ " поврежден! Создайте новый файл со списком мотрудников! (Ошибка ввода/вывода*)");
                    } catch (ClassNotFoundException ex) {
                        outputConsolLine.setText("Файл "+fDialog.getSelectedFile().toString()+ " поврежден! Создайте новый файл со списком мотрудников!");
                    }
                }
                    
                    break;

                case JFileChooser.ERROR_OPTION:
                    outputConsolLine.setText("Файл "+fDialog.getSelectedFile().toString()+ " поврежден или отсутствует! Неизвестная ошибка!");
                    break;
                case JFileChooser.CANCEL_OPTION:
                    outputConsolLine.setText("Открытие файла отменено!");
                    break;

            }
            creationDialog.setVisible(false);
        }
        if (e.getSource() == createEmployeesTable) { //Создать таблицу с  сотрудниками

            JScrollPane scroolPane = new JScrollPane(new TableEmployees(new ArrayList<Employee>(), EmployeeTableModel.EDITABLE));
            addScrollTable(scroolPane);
            creationDialog.setVisible(false);
            this.repaint();
        }
        if(e.getSource()==createTableButton)///////////////////СОздать новый График//////////////////////////////
        {
            outputConsolLine.setText("Создать новый график!!");
            creationDialog.setVisible(true);
            this.repaint();
        }
        if (e.getSource() == addEmployeeButton) { ////////////////////////ДОБАВИТЬ СОТРУДНИКА/////////////////
            creationDialog.setVisible(false);
            if (tabbedPane.getComponentCount() == 0) {
                outputConsolLine.setText("Для того, чтобы  добавить сотрудника, создайте сначала таблицу");
            } else {
                outputConsolLine.setText("Добавить сотрудника!!");
                if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(EmployeeTableModel.class)) {////////////к списку сотрудников//////////////
                    outputConsolLine.setText("Добавить сотрудника в таблицу с сотрудниkами");
                    EmployeeTableModel tableModel = (EmployeeTableModel) ((TableEmployees) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView()).getModel();
                    tableModel.addRowWithEmployee(new WindowEmployeeCreator().createNewEmployee());
                    tableModel.fireTableDataChanged();
                } else if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(ScheduleTableModel.class)) { //////////////в рабочий график/////////////////
                    outputConsolLine.setText("Добавить сотрудника в таблицу с рабочим графиком");
                    ScheduleTableModel tableModel = (ScheduleTableModel)arrayTableModels.get(tabbedPane.getSelectedIndex());
                    tableModel.addEmployee(new WindowEmployeeCreator().createNewEmployee());
                    tableModel.fireTableDataChanged();
                }
            }
        }
        if (e.getSource() == removeEmployeeButton) {////////////////////////Удалить СОТРУДНИКА/////////////////
            creationDialog.setVisible(false);
            outputConsolLine.setText("Удалить сотрудника!!");
            if (tabbedPane.getComponentCount() == 0) {
                outputConsolLine.setText("Некого удалять, создайте сначала таблицу");
            } else {
                outputConsolLine.setText("Удалить сотрудника!!");
                if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(EmployeeTableModel.class)) {
                    ////////////из таблицы сотрудников//////////////
                    outputConsolLine.setText("Добавить сотрудника в таблицу с сотрудниkами");
                    TableEmployees table = ((TableEmployees) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView());
                    EmployeeTableModel tableModel = (EmployeeTableModel) table.getModel();
                    try {
                        ///удаляем выбранную строку
                        tableModel.removeEmployee(table.getSelectedRow());
                    } catch (IndexOutOfBoundsException ex) {
                        if (table.getRowCount() == 0) {
                            outputConsolLine.setText("Tаблица пустая! Что вы собрались удалить?");
                        } else if (table.getSelectedRow() == -1) {
                            outputConsolLine.setText("Вы не выбрали строку для удаления!");
                        }
                    }
                    tableModel.fireTableDataChanged();
                } else if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(ScheduleTableModel.class)) {
                    //////////////из рабочего графика/////////////////
                    outputConsolLine.setText("Удалить сотрудника в таблицу с рабочим графиком");
                    MainJTable table = ((MainJTable) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView());
                    ScheduleTableModel tableModel = (ScheduleTableModel) table.getModel();
                    try {
                        ///удаляем выбранную строку
                        tableModel.removeEmployee(table.getSelectedRow());
                    } catch (IndexOutOfBoundsException ex) {
                        if (table.getRowCount() == 0) {
                            outputConsolLine.setText("Tаблица пустая! Что вы собрались удалить?");
                        } else if (table.getSelectedRow() == -1) {
                            outputConsolLine.setText("Вы не выбрали строку для удаления!");
                        }
                    }
                    tableModel.fireTableDataChanged();
                }
            }
        }
        if(e.getSource()==editEmployeeButton)
        {
            outputConsolLine.setText("Редактировать карту сотрудника!!");
            creationDialog.setVisible(false);
            if (arrayTableModels.get(tabbedPane.getSelectedIndex()).getClass().equals(EmployeeTableModel.class)) {
                    ////////////из таблицы сотрудников//////////////
                    outputConsolLine.setText("Добавить сотрудника в таблицу с сотрудниkами");
                    TableEmployees table = ((TableEmployees) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView());
                    EmployeeTableModel tableModel = (EmployeeTableModel) table.getModel();
                     try {
                        new WindowEmployeeCreator().changedEmployee(tableModel.getEmployees().get(table.getSelectedRow()));
                    } catch (IndexOutOfBoundsException ex) {
                        if (table.getRowCount() == 0) {
                            outputConsolLine.setText("Tаблица пустая! Что вы собрались редактировать?");
                        } else if (table.getSelectedRow() == -1) {
                            outputConsolLine.setText("Вы не выбрали строку для редактирования!");
                        }
                    }
                    tableModel.fireTableDataChanged();
            }
            else
            {
                outputConsolLine.setText("Откройте/выберите таблицу с сотрудниками!");
            }
        }
        if(e.getSource()==this)
        {
            System.out.println("clik!!!!!");
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==tabbedPane)
        {
            creationDialog.setVisible(false);
            outputConsolLine.setText("№ "+tabbedPane.getSelectedIndex());
        }
        
    }

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

    @Override
    public void componentResized(ComponentEvent e) {
    creationDialog.setLocation(this.getX()+57,this.getY()+225);
    creationDialog.setVisible(false);}

    @Override
    public void componentMoved(ComponentEvent e) {
    creationDialog.setLocation(this.getX()+57,this.getY()+225);
    creationDialog.setVisible(false);}

    @Override
    public void componentShown(ComponentEvent e) {
    creationDialog.setLocation(this.getX()+57,this.getY()+225);
    creationDialog.setVisible(false);}

    @Override
    public void componentHidden(ComponentEvent e) {
    creationDialog.setLocation(this.getX()+57,this.getY()+225);
    creationDialog.setVisible(false);}


    @Override
    public void editingStopped(ChangeEvent e) {
       /* try {
            arrayContextsIPositionsTables.set(tabbedPane.getSelectedIndex(), arrayContextsIPositionsTables.get(tabbedPane.getSelectedIndex())+1);
            arrayContextsTables.get(tabbedPane.getSelectedIndex()).add((AbstractTableModel)((ScheduleTableModel)arrayTableModels.get(tabbedPane.getSelectedIndex())).clone());
        } catch (CloneNotSupportedException ex) {
            out.println("Произошла ошибка CloneNotSupportedException "+ ex);
        }*/
        out.println("Изменения в таблице № "+tabbedPane.getSelectedIndex()+ " сохранены! Количество кадров - "+arrayContextsTables.get(tabbedPane.getSelectedIndex()).size()+ ". Позиция - "+arrayContextsIPositionsTables.get(tabbedPane.getSelectedIndex()));
    }

    @Override
    public void editingCanceled(ChangeEvent e) {
        out.println("Произошла ОТМЕНА изменения в таблице № " + tabbedPane.getSelectedIndex() + " Resource: " + e.getSource().getClass());
    }

    private void addListenersForChanging(JTable table) {
        table.getDefaultEditor(JTextField.class).addCellEditorListener(this);
        table.getDefaultEditor(JComboBox.class).addCellEditorListener(this);
        table.getDefaultEditor(JCheckBox.class).addCellEditorListener(this);
        table.getDefaultEditor(Designations.class).addCellEditorListener(this);
    }

    private void removeListenersForChanging(JTable table) {
        table.getDefaultEditor(JTextField.class).removeCellEditorListener(this);
        table.getDefaultEditor(JComboBox.class).removeCellEditorListener(this);
        table.getDefaultEditor(JCheckBox.class).removeCellEditorListener(this);
        table.getDefaultEditor(Designations.class).removeCellEditorListener(this);
    }
}
