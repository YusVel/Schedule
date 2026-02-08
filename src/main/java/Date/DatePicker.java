
package Date;
import java.awt.Color;
import yusvel.schedule.employee.Employee;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.table.JTableHeader;

public class DatePicker extends JPanel implements ActionListener,MouseListener {
    private Calendar date;
    public JTextField yearField;
    public JTextField monthField;
    public JTextField dayOfMonthField;
    JButton button;
    JPanel  tip = new JPanel();
    public static final String[] MONTHS_OF_YEAR = new String[]{ 
             "Январь","Февраль", "Март","Апрель","Май","Июнь", "Июль","Август", "Сентябрь","Октябрь","Ноябрь","Декабрь"
            };
   public DatePicker()
    {
        super();
        date = Calendar.getInstance();
        
        yearField = new JTextField(Integer.toString(date.get(Calendar.YEAR)));
        yearField.setBounds(43, 1, 40, 20);
        yearField.setEditable(false);
        monthField = new JTextField(Integer.toString(date.get(Calendar.MONTH)+1));
        monthField.setBounds(22, 1, 20, 20);
        monthField.setEditable(false);
        dayOfMonthField = new JTextField(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));  
        dayOfMonthField.setBounds(2, 1, 20, 20);
        dayOfMonthField.setEditable(false);
        button = new JButton("дата");
        button.setBounds(84, 1, 78, 19);
        button.addMouseListener(this);
        
        
        setLayout(null);
        this.setSize(170, 20);
        add(yearField);
        add(monthField);
        add(dayOfMonthField);
        add(button);
        setVisible(true);
    }
    public DatePicker(Calendar anotheDate)
    {
        super();
        date = anotheDate;
        
        yearField = new JTextField(Integer.toString(date.get(Calendar.YEAR)));
        yearField.setBounds(43, 1, 40, 20);
        yearField.setEditable(false);
        monthField = new JTextField(Integer.toString(date.get(Calendar.MONTH)+1));
        monthField.setBounds(22, 1, 20, 20);
        monthField.setEditable(false);
        dayOfMonthField = new JTextField(Integer.toString(date.get(Calendar.DAY_OF_MONTH)));  
        dayOfMonthField.setBounds(2, 1, 20, 20);
        dayOfMonthField.setEditable(false);
        button = new JButton("дата");
        button.setBounds(84, 1, 78, 19);
        button.addMouseListener(this);
        
        
        setLayout(null);
        this.setSize(170, 20);
        add(yearField);
        add(monthField);
        add(dayOfMonthField);
        add(button);
        setVisible(true);
    }
    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar anotheDate)
    {
        yearField.setText(Integer.toString(anotheDate.get(Calendar.YEAR)));
        monthField.setText(Integer.toString(anotheDate.get(Calendar.MONTH)+1));
        dayOfMonthField.setText(Integer.toString(anotheDate.get(Calendar.DAY_OF_MONTH)));
        date = anotheDate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) { //клик по кнопке "дата" вызфывает создание окна выбора даты
       
            this.setSize(170, 188);
            tip.setLayout(null);
            tip.setBackground(Color.GRAY);
            tip.setBounds(dayOfMonthField.getBounds().x,dayOfMonthField.getBounds().y+20, 160, 168);

           
            ///////////////////////Выпадающий список месяцев//////////////////////

            JComboBox<String> listMonth = new JComboBox(MONTHS_OF_YEAR);
            listMonth.setBounds(5, 5, 80, 20);
            listMonth.setSelectedIndex(date.get(Calendar.MONTH));

            ///////////////////////Выпадающий список годов////////////////////////
            Integer[] YEARS = new Integer[201];
            for(int i = -100;i<YEARS.length-100;i++)
            {
                YEARS[i+100] = date.get(Calendar.YEAR)+i;
            }
            JComboBox<Integer> listYear = new JComboBox(YEARS);
            listYear.setBounds(81, 5, 75, 20);
            listYear.setSelectedIndex(100);

//////////////////////////////Панель с днями месяца///////////////////////////
             JPanel panelDays = new JPanel();
             panelDays.setLayout(null);
             panelDays.setBorder(BorderFactory.createBevelBorder(1));
             panelDays.setBounds(5, 27, 150, 115);



              //////////////////////Таблица с числами//////////////////////

             JTable table = new JTable();
             table.setLayout(null);
             table.setBounds(1, 20, 150, 100);
             table.setCellSelectionEnabled(true); 
             JTableHeader header = table.getTableHeader();
             header.setBounds(1, 0, 150, 20);
             
             panelDays.add(table);
             panelDays.add(header);
             
            

                      //////////////////////Кнопка "ок"//////////////////////  
            JButton buttonOk = new JButton("OK");
            buttonOk.setBounds(5, 143, 150, 20);
            buttonOk.setEnabled(false);


            tip.add(panelDays);
            tip.add(listYear);
            tip.add(listMonth);
            tip.add(buttonOk);
            this.add(tip);
            
            table.setModel(new CalendarTableModel(date)); ///добавид установку модели в конце, так как при первой загрузке таблицы разрер не соответствовал панели
            
            System.out.println("\nCLICK");

            ////////////////////////Обработка ивентов/////////////////////////////////
             buttonOk.addActionListener(event->{ ////////////////////////Клик на ОК//////////////////////////////
                                                //System.out.printf("Date %d-%s-%d Picked",date.get(Calendar.DAY_OF_MONTH),MONTHS_OF_YEAR[date.get(Calendar.MONTH)],date.get(Calendar.YEAR));
                                                this.setSize(180, 20);
                                               });
            table.addMouseListener(new MouseListener(){ ////////////////////Активация кнопки ок при клеке на число///////////////////
                                                    @Override
                                                    public void mouseClicked(MouseEvent e) {
                                                    Integer valueAt = (Integer)table.getModel().getValueAt(table.getSelectedRow(), table.getSelectedColumn());
                                                     if(valueAt==null)
                                                        {
                                                            if(date.get(Calendar.DAY_OF_YEAR)<10){dayOfMonthField.setText('0'+Integer.toString(date.get(Calendar.DAY_OF_YEAR)));}
                                                            else{dayOfMonthField.setText(Integer.toString(date.get(Calendar.DAY_OF_YEAR)));}
                                                            buttonOk.setEnabled(false);
                                                            //System.out.println("ButtonOK turnOFF value in table=NULL");
                                                        }
                                                     else
                                                        {
                                                            if(valueAt<10){dayOfMonthField.setText('0'+(valueAt).toString());}
                                                            else{dayOfMonthField.setText((valueAt).toString());}
                                                            date.set(Calendar.DAY_OF_MONTH, valueAt);
                                                           buttonOk.setEnabled(true);
                                                           //System.out.println("ButtonOK turnON");
                                                        }
                                                    }

                                                    @Override
                                                    public void mousePressed(MouseEvent e) {}

                                                    @Override
                                                    public void mouseReleased(MouseEvent e) { }

                                                    @Override
                                                    public void mouseEntered(MouseEvent e) {}

                                                    @Override
                                                    public void mouseExited(MouseEvent e) {}
                                                });   
           listYear.addActionListener(envent->{
                                                date.set(YEARS[listYear.getSelectedIndex()],
                                                                             listMonth.getSelectedIndex(),
                                                                             1);
                                                date.setFirstDayOfWeek(Calendar.MONDAY);
                                                table.setModel(new CalendarTableModel(date));
                                                yearField.setText(Integer.toString(YEARS[listYear.getSelectedIndex()]));
                                                buttonOk.setEnabled(false);
                                               });

          listMonth.addActionListener(envent->{
                                                date.set(YEARS[listYear.getSelectedIndex()],
                                                                             listMonth.getSelectedIndex(),
                                                                             1);
                                                date.setFirstDayOfWeek(Calendar.MONDAY);
                                                table.setModel(new CalendarTableModel(date));
                                                if(listMonth.getSelectedIndex()<9){monthField.setText('0'+Integer.toString(listMonth.getSelectedIndex()+1));}
                                                else{monthField.setText(Integer.toString(listMonth.getSelectedIndex()+1));}
                                                buttonOk.setEnabled(false);
                                               });  

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    
}
