
package yusvel.schedule.employee;
import Table.Designations;
import javax.swing.*;
import java.awt.event.*;
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
import java.util.Objects;
import yusvel.schedule.employee.WindowEmployeeCreator;


class Human implements Serializable
{
    protected String surname;
    protected String name;
    protected String patronomic;
    protected Calendar bithDay;
    Human(String surname,String name,String patronomic,Calendar bithDay)
    {
        this.surname = surname;
        this.name = name;
        this.patronomic = patronomic;
        this.bithDay = bithDay;
    }
    @Override public String toString()
    {
        return surname + ' '+name+' '+patronomic+ " ("+ bithDay.get(Calendar.DAY_OF_MONTH)+"."+ bithDay.get(Calendar.MONTH)+"."+ bithDay.get(Calendar.YEAR)+")\n";
    }
    @Override public boolean equals(Object obj)
    {
        if(obj==null){return false;}
        if(!obj.getClass().equals(this.getClass())){return false;}
        Employee another = (Employee)obj;
        return this.surname.equals(another.surname)
                &&this.name.equals(another.name)
                &&this.patronomic.equals(another.patronomic)
                &&this.bithDay.equals(another.bithDay);
         
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.surname);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.patronomic);
        hash = 67 * hash + Objects.hashCode(this.bithDay);
        return hash;
    }
    public String getName(){return name;}
    public String getSurname(){return surname;}
    public String getPatronomic(){return patronomic;}
}





public class Employee extends Human implements Comparable<Employee>, Serializable// Класс работник
{
    private Float workingRate;
    private Byte post;
    private Byte department;
    private Calendar beginEmployment;
    private Calendar endEmployment;
    private Byte cabinetNumber;
    private ArrayList<Designations> workSchedule; //рабочий график каждого сотрудника
    private boolean workingShift; // кто рабочая смена 1 - нечетные числа - утро(8:00-14:00), 0 нечетные числа - вечер(15:00-21:00)
      
     Employee()
    {
       super("Surname","Name","Patronomic",Calendar.getInstance());
       workingRate = 1.0f;
       post = 0;
       department = 0;
       beginEmployment = Calendar.getInstance();
       cabinetNumber = 1;
       workingShift = false;
    }
    Employee(Employee other)
    {
       super(other.surname,other.name,other.patronomic,other.bithDay);
       workingRate = other.workingRate;
       post = other.post;
       department = other.department;
       beginEmployment = other.beginEmployment;
       endEmployment = other.endEmployment;
       cabinetNumber = other.cabinetNumber;
       workSchedule = other.workSchedule;
       workingShift = other.workingShift;
    }
    @Override
    public int compareTo(Employee other) {
        if(this.post.equals(other.post))//если должности одинаковые
        {
            if(this.department.equals(other.department))//если отделения одинаковые
            {
                if(this.cabinetNumber.equals(other.cabinetNumber))
                {
                    return this.surname.compareTo(other.surname);
                }
                return this.cabinetNumber - other.cabinetNumber;
            }
            return this.department-other.department;
        }
        else
        {
            return this.post-other.post;
        }
    }
    static private String fileName = "treeEmployees.emp";// короткое имя файла с сотрудниками 
    public static final String[] POSTS = new String[]
        {
            "Хирург-стоматолог",
            "Пародонтолог",
            "Терапевт-стоматолог",
            "Ортодонт",
            "Детский стоматолог",
            "Мед.сестра",
            "Старшая мед.сестра",
            "Шлавная мед.сестра",
            "Заведующий отделением",
            "Рентген-лаборант",
            "Начальник медицинской службы",
            "Главный врач"
        };
     public static final String[] DEPARTMENTS = new String[] // *****!!!!!не стоит менять порядок строк в массиве))*****!!!!!
        {
            "ОПО",
            "Лечебное отделение №1",
            "Лечебное отделение №2",
            "Детский отделение",
            "Вспомог. персонал"
        };
    ////////////////////////Создание  и изменение сотрудника через модальное окно JDialog/////////
    static public Employee create()
    {
        return new WindowEmployeeCreator().createNewEmployee();   
    }

    public void chandgePassport()
    {
      new WindowEmployeeCreator().changedEmployee(this);
    }

    //СЕТТЕРЫ
   public void setSurname(String surname){
        this.surname = surname;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPatronomic(String patronomic){
        this.patronomic = patronomic;
    }
    public void setBithDay(Calendar bithDay)
    {
        this.bithDay = bithDay;
    }
    public void setWorkingRate(Float workingRate)
    {
        this.workingRate = workingRate;
    }
    public void setPost(Byte post)
    {
        this.post = post ;
    }
    public void setDepartment(Byte department)
    {
        this.department = department;
        if(department==1){workingShift=true;} //если отделение 1 то это всегда утренняя смена
        if(department==2){workingShift=false;}//если отделение 2 то это всегда  вечерняя смена
    }
    public void setBeginEmployment(Calendar beginEmployment)
    {
        this.beginEmployment = beginEmployment;
    }
    public void setEndEmployment(Calendar endEmployment)
    {
        this.endEmployment = endEmployment;
    }
    public void setCabinetNumber(Byte cabinetNumber)
    {
        this.cabinetNumber = cabinetNumber;
    }
    public static void setShortFileNameToIO(String name)
    {
        fileName = name;
    }
    public void setWorkSchedule(ArrayList<Designations> workSchedule)
    {
        this.workSchedule = workSchedule; 
    }
    public void setWorkingShift(Boolean workingShift)
    {
        this.workingShift = workingShift;
    }
    ///
    /// @return 
    public String getFullName()
    {
        return String.format("%s %s %s ", surname,name,patronomic);
    }
    public Byte getPost(){return post;}
    public Float getWorkingRate(){return workingRate;}
    public Byte getDepartment(){return department;}
    public Byte getCabineNumber(){return cabinetNumber;}
    public Calendar getBeginEmployment(){return beginEmployment;}
    public ArrayList<Designations> getWorkSchedule(){return workSchedule;}
    public Boolean getWorkingShift(){return workingShift;}
    /////////////////////////Запись и чтение/////////////////////////////////////
  
    /// @throws java.io.FileNotFoundException
    /// @throws java.lang.ClassNotFoundException
    public static ArrayList<Employee> readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        ArrayList<Employee> result = new ArrayList<>();
        try
        {
            File file = new File(Paths.get("").toAbsolutePath().toString(),fileName);
            if(!file.exists()){throw new FileNotFoundException("Файл для чтения отсутствует");}
            try(FileInputStream fin = new FileInputStream(file); ObjectInputStream oifin = new ObjectInputStream(fin))
                {
                    result = (ArrayList<Employee>)oifin.readObject();  
                }
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage()+"исключение при чтении FileNotFoundException");
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage()+ "исключение при чтении IOException");
        }
        
        return result;
    }
    
    
    public static void writeToFile(ArrayList<Employee> arr) throws FileNotFoundException, IOException
    {

         File file = new File(Paths.get("").toAbsolutePath().toString(),fileName);
         file.setReadable(true);
         file.setWritable(true);
         if(!file.exists()) //создаем файл для записи данных, если он не существует
         {
             file.createNewFile();
             System.out.println("CREATED new file: "+file.getPath());
         }
        try( ObjectOutputStream oOut = new ObjectOutputStream(new FileOutputStream(file)))
        {
            oOut.writeObject(arr);
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.getMessage()+"исключение при записи ArrayList<Employee> в "+file.getPath());
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage()+ "исключение при записи ArrayList<Employee> в "+file.getPath());
        }
    }
    
    @Override public String toString()
    {
        String result;
        result = surname + ' '+name+' '+patronomic
                +" ("+ "Bithday: "+bithDay.get(Calendar.DAY_OF_MONTH)+" "+ bithDay.get(Calendar.MONTH)+1+" "+ bithDay.get(Calendar.YEAR)+") "
                +POSTS[post]+" "+DEPARTMENTS[department]+", "+"Кабинет: "+cabinetNumber
                + ", Нечетные числа: "+(workingShift?" УТРО(8:00-14:00)":" ВЕЧЕР(15:00-21:00)");
        return result;
    }
    @Override public boolean equals(Object obj)
    {
        if(obj==null){return false;}
        if(!obj.getClass().equals(this.getClass())){return false;}
        Employee another = (Employee)obj;
        return   this.surname.equals(another.surname)
                &&this.name.equals(another.name)
                &&this.patronomic.equals(another.patronomic)
                &&this.bithDay.equals(another.bithDay)
                &&this.workingRate.equals(another.workingRate)
                &&this.post.equals(another.post)
                &&this.department.equals(another.department);
         
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 97 * hash + Objects.hashCode(this.workingRate);
        hash = 97 * hash + Objects.hashCode(this.post);
        hash = 97 * hash + Objects.hashCode(this.department);
        hash = 97 * hash + Objects.hashCode(this.beginEmployment);
        hash = 97 * hash + Objects.hashCode(this.endEmployment);
        return hash;
    }
}



