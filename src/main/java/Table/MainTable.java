/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import Date.DatePicker;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import yusvel.schedule.employee.Employee;


public class MainTable implements Serializable{
    private ArrayList<Employee> arrEmployees;
    private Calendar date;
    private Double workingHoursPerMonth;
    private String fileName;
    public MainTable(){}
    public MainTable(ArrayList<Employee> arrEmployees,Calendar date, Double workingHoursPerMonth)
    {
        if(arrEmployees==null||date==null||workingHoursPerMonth==null||workingHoursPerMonth<0.0)
        {
            if(arrEmployees==null){
                throw new IllegalArgumentException("В конструкторе недопустимый аргумент: MainTable(ArrayList<Employee>==NULL ...)");
            }
            if(date==null){
                throw new IllegalArgumentException("В конструкторе недопустимый аргумент: MainTable(...,Calendar date == NULL, ...)");
            } 
            if(workingHoursPerMonth==null||workingHoursPerMonth<0.0){
                throw new IllegalArgumentException("В конструкторе недопустимый аргумент: MainTable(..., Double workingHoursPerMonth==NULL or < 0.0)");
            }
        }
        this.arrEmployees = arrEmployees;
        this.date = date;
        this.workingHoursPerMonth = workingHoursPerMonth;
        if(arrEmployees.isEmpty())
        {
            System.out.println("Load empty arrayList<Emplyee>");
            /////ToDo/////
        }
        
        fileName = "Schedue_of_"+DatePicker.MONTHS_OF_YEAR[date.get(Calendar.MONTH)]+"_"+date.get(Calendar.YEAR)+".tbl";
        
        System.out.println("График на "+DatePicker.MONTHS_OF_YEAR[date.get(Calendar.MONTH)]+" "+date.get(Calendar.YEAR)+"год "+ workingHoursPerMonth +" ч. Будет охранен в "+fileName);
        for(Employee e: arrEmployees) //инициализируем поле ArrayList<Designations> workSchedule 
        {
            if(e.getWorkSchedule()==null||e.getWorkSchedule().size()!=date.getActualMaximum(Calendar.DAY_OF_MONTH))//если данное поле не инициализировано или оно не соотверствует по размеру текущей дате (излишние проверки?)
            {
                e.setWorkSchedule(new ArrayList<Designations>(date.getActualMaximum(Calendar.DAY_OF_MONTH)));
            }
        }
        genereteDesignationsForAllEmployees();
    }
    ////////////////метод авогенерации массива с обозначениями ArraList<Designations> у всех сотрудников///////////////////////////////
     
    private void genereteDesignationsForAllEmployees()
    {
        Calendar firstDayMonthofWeek = Calendar.getInstance();
        firstDayMonthofWeek.set(date.get(Calendar.YEAR),date.get(Calendar.MONTH),1); //первый день текущего месяца
        
        for(Employee e:arrEmployees)//проходим по массиву сотрудников
        {
            int weekDay = firstDayMonthofWeek.get(Calendar.DAY_OF_WEEK);//определяем день недели первого дня месяца
            for(int i =0;i<date.getActualMaximum(Calendar.DAY_OF_MONTH);i++)//у каждого сотрудника инициализируем массив с графиком работы(обозначений)
            {
                int val = 0;
                Boolean cond = false;
                weekDay%=7;
                if(weekDay==0||weekDay==1){val=0;}//если  выходной день, то оставляем пустую ячейку Designation
                else if(e.getWorkingShift()==true&&i%2==1){val=1;cond=true;} //если смена у доктора нечетная и число нечетное то "У"
                else if(e.getWorkingShift()==true&&i%2==0){val=2;cond=true;}//если смена у доктора нечетная и число четное то "В"
                else if(e.getWorkingShift()==false&&i%2==1){val=2;cond=true;} //если смена у доктора нечетная и число нечетное то "В"
                else if(e.getWorkingShift()==false&&i%2==0){val=1;cond=true;}//если смена у доктора нечетная и число четное то "У"
                e.getWorkSchedule().add(new Designations(val,cond));
                weekDay++; 
            }
        }
    }
    
    //Инициализация массива с обозначениями ArraList<Designations> для одного сотрудника//
    
    /////////////////////////Геттеры////////////////////////////////////
    public ArrayList<Employee> getEmployees(){return arrEmployees;}
    public Calendar getDate(){return date;};
    public Double getWorkingHours(){return workingHoursPerMonth;}
    //////////////////////////Сеттеры////////////////////////////////
    public void setArrEmployees(ArrayList<Employee> arrEmployees)
    {
        if(arrEmployees==null){
                throw new IllegalArgumentException("В методе недопустимый аргумент: setArrEmployees(ArrayList<Employee> arrEmployees==NULL )");}
        this.arrEmployees = arrEmployees;
    }
    public void setWorkingHoursPerMonth(Double workingHoursPerMonth)
    {
        if(workingHoursPerMonth==null||workingHoursPerMonth<0.0){
                throw new IllegalArgumentException("В методе недопустимый аргумент: setWorkingHoursPerMonth(Double workingHoursPerMonth==NULL or < 0.0)"); }
        this.workingHoursPerMonth = workingHoursPerMonth;
    }
    
    
    
    public void writeTableToFile() throws FileNotFoundException, IOException
    {
         File file = new File(Paths.get("").toAbsolutePath().toString(),fileName);
         file.setReadable(true);
         file.setWritable(true);
        try(var ois = new ObjectOutputStream(new FileOutputStream(file));)
        {
         if(!file.exists()) //создаем файл для записи данных, если он не существует
         {
             file.createNewFile();
             System.out.println("CREATED new file: "+file.getPath());
         }
            ois.writeObject(this);
            System.out.println("Записываем: "+file.getPath());
        }
        catch(IOException e)
        {
            System.out.println("Не удалось записать таблицу в "+file.getPath()+"ErroR: "+e);
        }
    }
    public static MainTable readTableFromFile(String fullFileName)throws FileNotFoundException, IOException, ClassNotFoundException
    {
         File file = new File(fullFileName);
         file.setReadable(true);
         file.setWritable(true);
         try(var ois = new ObjectInputStream(new FileInputStream(file));)
            {
                if(!file.exists()) // он не существует?
                {
                    throw new FileNotFoundException(fullFileName + " - данного файла нет.");  
                }
                System.out.println("Загружаем таблицу из "+file.getPath());
                MainTable tmp = (MainTable)ois.readObject(); 
                return tmp;
            }
         catch(IOException e)
            {
                System.out.println("Не удалось прочитать таблицу из "+file.getPath()+"ErroR:"+e);
            }
    return null;
    }
   /* @Override
    public String toString()
    {
        return String.format("[%s-%d] %d сотр. ", DatePicker.MONTHS_OF_YEAR[date.get(Calendar.MONTH)],date.get(Calendar.YEAR),arrEmployees.size());
    }*/
}
