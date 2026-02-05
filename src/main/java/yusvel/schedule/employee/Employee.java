
package yusvel.schedule.employee;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import yusvel.schedule.employee.WindowEmployeeCreator;


class Human
{
    String surname;
    String name;
    String patronomic;
    Calendar bithDay;
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
}





public class Employee extends Human implements Comparable<Employee>// Класс работник
{
    
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
  public static final String[] DEPARTMENTS = new String[]
{
    "ОПО",
    "Лечебное отделение №1",
    "Лечебное отделение №2",
    "Детский отделение",
    "Вспомог. персонал"
};

static public Employee create()
{
    return new WindowEmployeeCreator().createNewEmployee();   
}
    private Float workingRate;
    private Byte post;
    private Byte department;
    private Calendar beginEmployment;
    private Calendar endEmployment;
    private Byte cabinetNumber;
    
    private boolean isEmployed;
      
     Employee()
    {
       super("Surname","Name","Patronomic",Calendar.getInstance());
       workingRate = 1.0f;
       post = 0;
       department = 0;
       beginEmployment = Calendar.getInstance();
       cabinetNumber = 1;
       isEmployed = false;
    }
    @Override
    public int compareTo(Employee other) {
        if(this.post.equals(other.post))
        {
            return other.cabinetNumber-this.cabinetNumber;
        }
        return other.post-this.post;
    }

    //////////////Создание окна для заполнения полей///////////////////

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
    }
    public void setBeginEmployment(Calendar beginEmployment)
    {
        this.beginEmployment = beginEmployment;
        isEmployed=true;
    }
    public void setEndEmployment(Calendar endEmployment)
    {
        this.endEmployment = endEmployment;
        isEmployed=false;
    }
    public void setCabinetNumber(Byte cabinetNumber)
    {
        this.cabinetNumber = cabinetNumber;
    }
    ///
    /// @return 
    public String getFullName()
    {
        return String.format("%s %s %s ", surname,name,patronomic);
    }
    public String getPost(){return POSTS[post];}
    
    ArrayList<Employee> readFromFile(String path)
    {
        return null;
    }
    
    @Override public String toString()
    {
        String result;
        result = surname + ' '+name+' '+patronomic+'\n'+ "Bithday: "+bithDay.get(Calendar.DAY_OF_MONTH)+" "+ bithDay.get(Calendar.MONTH)+" "+ bithDay.get(Calendar.YEAR)+'\n'+POSTS[post]+" "+DEPARTMENTS[department]+'\n'+"Кабинет: "+cabinetNumber;
        return result;
    }
    @Override public boolean equals(Object obj)
    {
        if(obj==null){return false;}
        if(!obj.getClass().equals(this.getClass())){return false;}
        Employee another = (Employee)obj;
        return   ((Human)this).equals((Human)another)
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



