/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Date;
import javax.swing.table.AbstractTableModel;
import java.util.Calendar;

/**
 *
 * @author yusup
 */
public class CalendarTableModel extends  AbstractTableModel{
    final String[] DAYS_OF_WEEK = { 
            "Пн","Вт", "Ср","Чт","Пт","Сб", "Вс"
        };  
    Integer[][] tableDays;
    CalendarTableModel(Calendar calendar)
    {
         super();
         System.out.printf("%d-%d-%d", calendar.get(Calendar.DAY_OF_MONTH),calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));
         calendar.set(Calendar.DAY_OF_MONTH, 1);
        
         ////////////////////////расчитываем смещение дней месяца относительно дней недели
         int d = 1;     //итератор  
         int shift = (calendar.get(Calendar.DAY_OF_WEEK)-2)%7;//смещение в таблице по дате текущий год
         if(shift<0){shift+=7;}
         System.out.println("\nСмещение = "+shift);
         
         tableDays = new Integer[6][7];         
         for(int week = 0;week<6;week++)
         {
             for(int day = 0;day<7;day++ )
             {
                 if(shift>0){shift--; tableDays[week][day]=null;continue;}
                 if(d>calendar.getActualMaximum(Calendar.DAY_OF_MONTH)){tableDays[week][day]=null;continue;}
                 tableDays[week][day] = d++;
             }
         }
    }
    @Override
    public int getRowCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex)
    {  
        return DAYS_OF_WEEK[columnIndex];
    }
    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
       // if(tableDays[rowIndex][columnIndex]==null){System.out.println("RETUENING "+tableDays[rowIndex][columnIndex].toString());}
        //else{System.out.println("RETUENING NULL");}
        return tableDays[rowIndex][columnIndex];
    }
}
