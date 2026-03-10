package Table;

import Date.DatePicker;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;
import yusvel.schedule.Schedule;
import yusvel.schedule.employee.Employee;
import yusvel.schedule.employee.EmployeeTableModel;
import yusvel.schedule.employee.TableEmployees;

public class CreationNewTableDialog extends JDialog implements ActionListener, KeyListener {

    static final Font FONT = new Font("Verdena", Font.PLAIN, 14);
    
    Calendar date = Calendar.getInstance();
    Double workingHour;
    JLabel workingHourlabel = new JLabel("Количество часов \nв текущем месяце: ");
    JTextField workingHourTextField = new JTextField();
    JLabel monthLabel = new JLabel("Месяц: ");
    JComboBox monthComboBox = new JComboBox(DatePicker.MONTHS_OF_YEAR);
    JLabel yearLabel = new JLabel("Год: ");
    JTextField yearTextField = new JTextField(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
    JLabel employeesLabel = new JLabel("Список сотрудников");
    JLabel filters = new JLabel("Фильтры");
    JCheckBox allFilters = new JCheckBox("Все",false);
    JLabel postsLabel = new JLabel("по должностям:");
    JLabel departmentsLabel = new JLabel("по отделениям:");
    JPanel filtersPanel = new JPanel();
    JPanel filtersPanelPosts = new JPanel(new GridLayout(6, 2));
    JPanel filtersPanelDepartments = new JPanel(new GridLayout(5, 1));

    ArrayList<JCheckBoxListener> arrayCheckBoxesOfPosts = new ArrayList<JCheckBoxListener>();
    ArrayList<JCheckBoxListener> arrayCheckBoxesOfDepartments = new ArrayList<JCheckBoxListener>();

    JScrollPane scrollTable;
    JButton createButton = new JButton("Создать");

    Insets insets = new Insets(3, 3, 3, 3);

    public CreationNewTableDialog(JFrame owner, ArrayList<Employee> employees) {
        super(owner, "Создание новой таблицы");
        
        this.setModal(true);
        this.setLocation(owner.getX() + 250, owner.getY() + 50);
        this.setLayout(new GridBagLayout());

        scrollTable = new JScrollPane(new TableEmployees(employees, EmployeeTableModel.SELECTABLE));
        scrollTable.setPreferredSize(new Dimension(500, 300));
        createButton.addActionListener(this);

        workingHourlabel.setFont(FONT);
        DocInputFilter filter = new DocInputFilter("[[0-9]+[.]?[0-9]*]");
        ((PlainDocument)workingHourTextField.getDocument()).setDocumentFilter(filter);
        
        monthLabel.setFont(FONT);
        monthComboBox.setPreferredSize(new Dimension(80, 20));
        monthComboBox.addActionListener(this);

        yearLabel.setFont(FONT);
        yearTextField.setPreferredSize(new Dimension(80, 20));
        ((PlainDocument) yearTextField.getDocument()).setDocumentFilter(new DocInputFilter("\\d*"));
       
        
        employeesLabel.setFont(FONT);

        filters.setFont(new Font("Verdena", Font.BOLD, 14));
        allFilters.addActionListener(this);
        filtersPanel.setLayout(new GridBagLayout());

        for (int i = 0; i < Employee.POSTS.length; i++) {
            JCheckBoxListener checbox = new JCheckBoxListener(Employee.POSTS[i],false);
            checbox.addActionListener(this);
            filtersPanelPosts.add(checbox, BorderLayout.CENTER);
            arrayCheckBoxesOfPosts.add(checbox);
        }
        for (int i = 0; i < Employee.DEPARTMENTS.length; i++) {
            JCheckBoxListener checbox = new JCheckBoxListener(Employee.DEPARTMENTS[i],false);
            checbox.addActionListener(this);
            filtersPanelDepartments.add(checbox, BorderLayout.CENTER);
            arrayCheckBoxesOfDepartments.add(checbox);
        }

        filtersPanel.add(filtersPanelPosts,new GridBagConstraints(0, 0, 2, 5, 0, 0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets, 0, 0));
        filtersPanel.add(new JSeparator(JSeparator.VERTICAL), new GridBagConstraints(2, 0, 1, 5, 0, 0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, new Insets(3, 3, 3, 10), 0, 0));
        filtersPanel.add(filtersPanelDepartments,new GridBagConstraints(3, 0, 1, 5, 0, 0, GridBagConstraints.WEST, GridBagConstraints.VERTICAL, insets, 0, 0));

        this.add(monthLabel,new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(yearLabel, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(workingHourlabel,new GridBagConstraints(2, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(monthComboBox,new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(yearTextField,new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(workingHourTextField,new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(new JSeparator(JSeparator.HORIZONTAL), new GridBagConstraints(0, 3, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(filters,new GridBagConstraints(0, 4, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(allFilters,new GridBagConstraints(1,4,1,1,1.0,1.0,GridBagConstraints.WEST,GridBagConstraints.NONE,insets,0,0));
        this.add(postsLabel, new GridBagConstraints(0, 5, 2, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
        this.add(departmentsLabel,new GridBagConstraints(2, 5, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
        this.add(filtersPanel,new GridBagConstraints(0, 6, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        this.add(scrollTable, new GridBagConstraints(0, 7, 3, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, insets, 0, 0));
        this.add(createButton, new GridBagConstraints(0, 8, 3, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, insets, 0, 0));
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == allFilters) {
            for (JCheckBoxListener el : arrayCheckBoxesOfPosts) {
                el.setSelected(allFilters.isSelected());
            }
            for (JCheckBoxListener el : arrayCheckBoxesOfDepartments) {
                el.setSelected(allFilters.isSelected());
            }
            TableEmployees table = (TableEmployees) scrollTable.getViewport().getView();
            table.chekBoxfilter(((JCheckBox)e.getSource()).getText(), ((JCheckBox)e.getSource()).isSelected());
        }
        if (e.getSource().getClass().equals(JCheckBoxListener.class)) {
            System.out.println("Фильтр: "+((JCheckBoxListener)e.getSource()).getText()+" - "+((JCheckBoxListener)e.getSource()).isSelected());
            TableEmployees table = (TableEmployees) scrollTable.getViewport().getView();
            table.chekBoxfilter(((JCheckBoxListener)e.getSource()).getText(), ((JCheckBoxListener)e.getSource()).isSelected());
        }
        if(e.getSource()==createButton)
        {
            System.out.println("Cоздать новую таблицу");
            ArrayList<Employee> arrayEmployees = new ArrayList<Employee>();
            TableEmployees table = (TableEmployees)scrollTable.getViewport().getView();
            EmployeeTableModel tableModel = (EmployeeTableModel)table.getModel();
            for(int i = 0;i<table.getRowCount();i++)
            {
                if(tableModel.getArrayBooleans().get(i))
                {
                    arrayEmployees.add(tableModel.getEmployees().get(i));
                }
            }

            if (yearTextField.getText().isEmpty()) {
                yearTextField.setBorder(BorderFactory.createLineBorder(new Color(245, 117, 69),2));
            } else if (workingHourTextField.getText().isEmpty()) {
                workingHourTextField.setBorder(BorderFactory.createLineBorder(new Color(245, 117, 69),2));
            } else {
                date.set(Integer.parseInt(yearTextField.getText()), monthComboBox.getSelectedIndex(), 1); //настраиваем дату
                workingHour = Double.valueOf(workingHourTextField.getText());//получаем количество часов
                MainTable newTable = new MainTable(arrayEmployees, date, workingHour);// создаем на основе выще указанных данных объект класса MainTable
                JScrollPane newScrollTable = new JScrollPane(new MainJTable(new ScheduleTableModel(newTable))); //запаковываем MaibTable в модель, модель таблицы в MainJTable, а потом в JScrollPane! ГОТОВО!
                ((Schedule)this.getParent()).addScrollTable(newScrollTable);
                this.setVisible(false);
            }

        }

        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
