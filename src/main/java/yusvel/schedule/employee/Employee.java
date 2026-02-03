
package yusvel.schedule.employee;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import yusvel.schedule.employee.WindowEmployeeCreator;


public class Employee implements Comparable<Employee>// Класс работник
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

    private String surname;
    private String name;
    private String patronomic;
    private Calendar bithDay;
    private Float workingRate;
    private Byte post;
    private Byte department;
    private Calendar beginEmployment;
    private Calendar endEmployment;
    private Byte cabinetNumber;
    
    private boolean isEmployed;
      
   public Employee()
    {
       surname = "Surname";
       name = "Name";
       patronomic = "Patronomic";
       bithDay = Calendar.getInstance();
       workingRate = 1.0f;
       post = 0;
       department = 0;
       beginEmployment = Calendar.getInstance();
       cabinetNumber = 1;
    }
    @Override
    public int compareTo(Employee other) {
        if(this.post.equals(other.post))
        {
            return other.cabinetNumber-this.cabinetNumber;
        }
        return other.post-this.post;
    }
    
    
    public Employee(Employee one)
    {
        System.out.println("Constructor COPY");
        
        this.surname = one.surname;
        this.name = one.name;
        this.patronomic = one.patronomic;
        this.bithDay = one.bithDay;
        this.workingRate = one.workingRate;
        this.post = one.post;
        this.department=one.department;
        this.beginEmployment = one.beginEmployment;
        this.endEmployment = endEmployment;  
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
 
    ///ГЕТТЕР
    /// @return 
    public String getFullName()
    {
        return String.format("%s %s %s ", surname,name,patronomic);
    }
    public String getPost(){return POSTS[post];}
    
    @Override public String toString()
    {
        String result;
        result = surname + ' '+name+' '+patronomic+'\n'+ "Bithday: "+bithDay.get(Calendar.DAY_OF_MONTH)+" "+ bithDay.get(Calendar.MONTH)+" "+ bithDay.get(Calendar.YEAR)+'\n'+POSTS[post]+" "+DEPARTMENTS[department]+'\n'+"Кабинет: "+cabinetNumber;
        return result;
    }
    
}



