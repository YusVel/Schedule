
package yusvel.schedule.employee;
import javax.swing.*;
import java.awt.event.*;
import java.util.Calendar;
import yusvel.schedule.employee.WindowEmployee;


public class Employee // Класс работник
{
    
 public static final String[] POST_JOB = new String[]
{
    "Хирург-стоматолог",
    "Терапевт-стоматолог",
    "Ортодонт",
    "Детский стоматолог",
    "Мед.сестра",
    "Старшая мед.сестра",
    "Шлавная мед.сестра",
    "Заведующий отделением",
    "Начальник медицинской службы",
    "Главный врач"
};
  public static final String[] DEPARTMENT_JOB = new String[]
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
    private String post;
    private String department;
    private Calendar beginEmployment;
    private Calendar endEmployment;
    
    private boolean isEmployed;
      
   public Employee()
    {
       
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
    public void setPost(String post)
    {
        this.post = post ;
    }
    public void setDepartment(String department)
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
    
 
    ///ГЕТТЕР
    /// @return 
    public String getFullName()
    {
        return String.format("%s %s %s ", surname,name,patronomic);
    }
    public String getPost(){return post;}
    
    @Override public String toString()
    {
        String result;
        result = surname + ' '+name+' '+patronomic+'\n'+ "Bithday: "+bithDay.get(Calendar.DAY_OF_MONTH)+" "+ bithDay.get(Calendar.MONTH)+" "+ bithDay.get(Calendar.YEAR)+'\n'+post+" "+department;
        return result;
    }
    

}



