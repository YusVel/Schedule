
package yusvel.schedule.employee;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Date.DatePicker;
import yusvel.schedule.employee.Employee;
import java.util.Calendar;

public class WindowEmployeeCreator  {
    Employee employee;
    JDialog mainFraim;
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
    JLabel cabinetNumberLabel;  
    JComboBox<Byte> cabinetNumberList;
    JButton buttonCreate;
    JPanel mainPanel;
    
    Font F = new Font(Font.MONOSPACED,Font.BOLD,16);
    int w =350;
    int h = 340;
    
    WindowEmployeeCreator()
    {
        System.out.println("Constructor WindowEmployeeCreator");
        mainFraim = new JDialog();
        mainPanel = new JPanel();
        buttonCreate = new JButton();
        cabinetNumberLabel = new JLabel("№ Кабинета: ",JLabel.RIGHT);
        cabinetNumberList = new JComboBox(new Byte[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        departmentList = new JComboBox(Employee.DEPARTMENTS);
        departmentLabel = new JLabel("Отделение: ",JLabel.RIGHT);
        workingRateList = new JComboBox(new Float[]{1.0f,0.75f,0.5f,0.25f});
        workingRateLabel = new JLabel("Ставка: ",JLabel.RIGHT);
        postList = new JComboBox(Employee.POSTS);
        postLabel = new JLabel("Должность: ",JLabel.RIGHT);
        bithPiker = new DatePicker();
        bithDayLabel = new JLabel("Дата рождения: ",JLabel.RIGHT);
        patronomicTextField = new JTextField();
        patronomicLabel = new JLabel("Отчество: ",JLabel.RIGHT);
        nameTextField = new JTextField();
        nameLabel = new JLabel("Имя: ",JLabel.RIGHT);
        surnameTextField = new JTextField();
        surnameLabel = new JLabel("Фамилия: ",JLabel.RIGHT);
        
        
        mainFraim.setModal(true);

                ///////////////////////////////////WInDOW mainFraim///////////////////////////////////
        
        mainFraim.setSize(w, h);
        mainFraim.setLocationRelativeTo(null);
        mainFraim.setResizable(false);
        mainFraim.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        mainFraim.setLayout(null);
        mainFraim.setBackground(Color.GRAY);
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
         ///////////////////////Номер Кабинета ///////////////////////////////////
        cabinetNumberLabel.setFont(F);
        cabinetNumberLabel.setBounds(15, 150, 150, 20);
        cabinetNumberLabel.setForeground(Color.RED);   
        cabinetNumberList.setLayout(null);
        cabinetNumberList.setBounds(160, 150,160, 20); 
         //////////////////Кнопка создать////////////////////
        buttonCreate.setBounds(110, 260, 120, 25);
        buttonCreate.addActionListener(e->{
                                        employee.setSurname(this.surnameTextField.getText());
                                        employee.setName(nameTextField.getText());
                                        employee.setPatronomic(patronomicTextField.getText());
                                        employee.setBithDay(bithPiker.getDate());
                                        employee.setPost((byte)postList.getSelectedIndex());
                                        employee.setWorkingRate((Float)workingRateList.getSelectedItem());
                                        employee.setCabinetNumber((Byte)cabinetNumberList.getSelectedItem());
                                        employee.setDepartment((byte)departmentList.getSelectedIndex());
                                        employee.setBeginEmployment(Calendar.getInstance());
                                       
                                        //System.out.println("Сотрудник создан в окне WindowEmployee!");
                                        //System.out.println(employee.toString());
                                        
                                        mainFraim.dispose();
                                        
                                         });
 
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
        mainPanel.add(cabinetNumberLabel);
        mainPanel.add(cabinetNumberList);
        
    };
    public void changedEmployee(Employee employeeNeedToChanged)
    {
        employee = employeeNeedToChanged;
        surnameTextField.setText(employeeNeedToChanged.surname);
        nameTextField.setText(employeeNeedToChanged.name);
        patronomicTextField.setText(employeeNeedToChanged.patronomic);
        bithPiker.setDate(employeeNeedToChanged.bithDay);
        postList.setSelectedIndex(employeeNeedToChanged.getPost());
        workingRateList.setSelectedItem(employeeNeedToChanged.getWorkingRate());
        cabinetNumberList.setSelectedIndex(employeeNeedToChanged.getCabineNumber()-1);
        departmentList.setSelectedIndex(employeeNeedToChanged.getDepartment());
        
        buttonCreate.setText("Изменить");
        mainFraim.setTitle("Изменените данные сотрудника");
        mainPanel.add(buttonCreate);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        mainFraim.add(mainPanel);
        mainFraim.setVisible(true);
    }
    
    public Employee createNewEmployee() 
    { 
        employee = new Employee();
  
    ///////////////////////Добавляем все элементы/////////////////////////    
        buttonCreate.setText("Создать");
        mainFraim.setTitle("Введите данные нового сотрудника");
        mainPanel.add(buttonCreate);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        mainFraim.add(mainPanel);
        mainFraim.setVisible(true);
        return employee;
    }

}
