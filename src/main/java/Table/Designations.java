/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Table;

import java.io.Serializable;
import java.util.Iterator;
import javax.swing.table.AbstractTableModel;


public class Designations implements Serializable { // объекты обозначения в главной таблице с графиком работы (можно было и воспользоваться ENUM)
    final static String[] ACCEPTABLE = {" ","У","В","У/В","Б", "Ух","Уд","Вд", "У7","ОТ"};// //все возможные состояния обозначения
    public static String getAcceptables(){return String.join(",", ACCEPTABLE);}
    private int value; //обозначение в графике У,В,У/В,Б и тд
    private Boolean condition = true; //Действительно ли выходил на работу
    private int indexOf(String val) //возвращает индекс элемента в массиве приемлемых значений иначе возвращает -1
    {
        int counter = 0;
        for(String v:ACCEPTABLE)
        {
            if(v.equals(val)){
                return counter;
            }
            counter++;
        }
        return -1;
    }
    public Designations(){value = 0;condition = false;}
    public Designations(int val,Boolean cond){value = val;condition = cond;}
    public Designations(String val){ //если введен неприемлемый аргумент выбрасываем исключеение (не соответствует ни одному из элментов массива acctptable)
        int index = indexOf(val);
        if(index==-1)
        {
            throw new IllegalArgumentException("IllegalArgumentException: Аргумент, передаваемый конструктору Designation("+val+") не найден в массиве String[] ACCEPTABLE = {\"\",\"У\",\"В\",\"У/В\",\"Б\", \"Ух\", \"У7\",};");
        }
        value = index;
        condition = true;
    }
    public void setCondition(Boolean cond){condition = cond;} // 
    public void setValue(int val,Boolean cond)
    {
        if(val>=0&&val<ACCEPTABLE.length){value = val;condition = cond;}
        else{throw new IllegalArgumentException("IllegalArgumentException: Аргумент, передаваемый конструктору Designation("+val+") не найден в массиве String[] ACCEPTABLE = {\"\",\"У\",\"В\",\"У/В\",\"Б\", \"Ух\", \"У7\",};");}
    }
    public int getValueInt(){return value;}
    @Override
    public String toString(){return ACCEPTABLE[value];}
}
