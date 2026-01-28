
package yusvel.schedule.employee;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Date.DatePicker;
import yusvel.schedule.employee.Employee;
import java.util.Calendar;

public class WindowEmployee extends JFrame implements ActionListener {
    Employee employee;
    JLabel surnameLabel;  
    JTextField surnameTextField;
    JLabel nameLabel;
    JTextField nameTextField;
    JLabel patronomicLabel; 
    JTextField patronomicTextField;
    JLabel bithDayLabel;  
    DatePicker bithPiker;
    JLabel postLabel;    
    JComboBox<String> postList;
    JLabel workingRateLabel;
    JComboBox<Float> workingRateList;
    JLabel departmentLabel;  
    JComboBox<String> departmentList;
    JButton button;
    JPanel mainPanel;
    public WindowEmployee() 
    {
        super("Добавить сотрудника");
        this.mainPanel = new JPanel();
        this.button = new JButton("OK");
        this.departmentList = new JComboBox(Employee.DEPARTMENT_JOB);
        this.departmentLabel = new JLabel("Отделение: ",JLabel.RIGHT);
        this.workingRateList = new JComboBox(new Float[]{1.0f,0.75f,0.5f,0.25f});
        this.workingRateLabel = new JLabel("Ставка: ",JLabel.RIGHT);
        this.postList = new JComboBox(Employee.POST_JOB);
        this.postLabel = new JLabel("Должность: ",JLabel.RIGHT);
        this.bithPiker = new DatePicker();
        this.bithDayLabel = new JLabel("Дата рожденье: ",JLabel.RIGHT);
        this.patronomicTextField = new JTextField();
        this.patronomicLabel = new JLabel("Отчество: ",JLabel.RIGHT);
        this.nameTextField = new JTextField();
        this.nameLabel = new JLabel("Имя: ",JLabel.RIGHT);
        this.surnameTextField = new JTextField();
        this.surnameLabel = new JLabel("Фамилия: ",JLabel.RIGHT);
        
        
        this.employee = new Employee();
        System.out.println("Constructor WINDOW");
        Font F = new Font(Font.MONOSPACED,Font.BOLD,16);
        int w =350;
        int h = 340;
   
        ///////////////////////////////////WInDOW///////////////////////////////////
        
        this.setSize(w, h);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBackground(Color.GRAY);
        
   ///////////////////////////////////Фамилия //////////////////////////////////
        surnameLabel.setFont(F);
        surnameLabel.setBounds(15, 10, 150, 20);
        surnameLabel.setForeground(Color.RED);
        surnameTextField.setBounds(160, 10, 160, 20);
    
   
  ///////////////////////////////////ИМЯ //////////////////////////////////
        nameLabel.setFont(F);
        nameLabel.setBounds(15, 30, 150, 20);
        nameLabel.setForeground(Color.RED);
        nameTextField.setBounds(160, 30, 160, 20);
    
        
    ///////////////////////////////////Отчество //////////////////////////////////   
        patronomicLabel.setFont(F);
        patronomicLabel.setBounds(15, 50, 150, 20);
        patronomicLabel.setForeground(Color.RED);
        patronomicTextField.setBounds(160, 50, 160, 20);
        
        ////////////////////////////////ДеньРожденье ////////////////////////////////// 
        bithDayLabel.setFont(F);
        bithDayLabel.setBounds(15, 70, 150, 20);
        bithDayLabel.setForeground(Color.RED);
        bithPiker.setLocation(158, 69);
       
    //////////////////////////////////Должность //////////////////////////////////    
        postLabel.setFont(F);
        postLabel.setBounds(15, 90, 150, 20);
        postLabel.setForeground(Color.RED);
        postList.setLayout(null);
        postList.setBounds(160, 90,160, 20);

    //////////////////////////////////Ставка/////////////////////////////////        
        workingRateLabel.setFont(F);
        workingRateLabel.setBounds(15, 110, 150, 20);
        workingRateLabel.setForeground(Color.RED);   
        workingRateList.setBounds(160, 110,160, 20);
        
        
    /////////////////////////////////Отделение/////////////////////////////////      
        departmentLabel.setFont(F);
        departmentLabel.setBounds(15, 130, 150, 20);
        departmentLabel.setForeground(Color.RED);   
        departmentList.setLayout(null);
        departmentList.setBounds(160, 130,160, 20);   
        
        
        //////////////////Кнопка////////////////////
     
        button.addActionListener(this);
        button.setBounds(110, 260, 120, 25);
     
        
    ///////////////////////Добавляем все элементы/////////////////////////    
        mainPanel.setLayout(null);
        mainPanel.setBounds(2, 2, 331, 298);
        mainPanel.add(surnameLabel);
        mainPanel.add(surnameTextField);
        mainPanel.add(nameLabel);
        mainPanel.add(nameTextField);
        mainPanel.add(patronomicLabel);
        mainPanel.add(patronomicTextField);        
        mainPanel.add(bithDayLabel);
        mainPanel.add(bithPiker);
        mainPanel.add(postLabel);
        mainPanel.add(postList);
        mainPanel.add(workingRateLabel);
        mainPanel.add(workingRateList);
        mainPanel.add(departmentLabel);
        mainPanel.add(departmentList);
        mainPanel.add(button);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        this.add(mainPanel);
        this.setVisible(true);
    }
    public Employee getEmployee()
    {
        return employee;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
                                        employee.setSurname(this.surnameTextField.getText());
                                        employee.setName(nameTextField.getText());
                                        employee.setPatronomic(patronomicTextField.getText());
                                        employee.setBithDay(bithPiker.getDate());
                                        employee.setPost((String)postList.getSelectedItem());
                                        employee.setWorkingRate((Float)workingRateList.getSelectedItem());
                                        employee.setDepartment((String)departmentList.getSelectedItem());
                                        employee.setEndEmployment(Calendar.getInstance());
                                        System.out.println("Сотрудник создан в окне WindowEmployee!");
                                        System.out.println(employee.toString());
                                        //this.dispose();
    }

}
