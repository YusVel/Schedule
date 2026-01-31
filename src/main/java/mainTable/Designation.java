/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package mainTable;


public class Designation { // объекты обозначения в главной таблице с графиком работы
    private final static String[] acceptable = {"У","В","У/В","Б", "Ух", "У7",};// 
    private final String value; //обозначение в графике У,В,У/В,Б и тд
    private Boolean condition = true; //Действительно ли выходил на работу
    private int indexOf(String val) //возвращает индекс элемента в массиве приемлемых значений иначе возвращает -1
    {
        int counter = 0;
        for(String v:acceptable)
        {
            if(v.equals(val)){
                return counter;
            }
            counter++;
        }
        return -1;
    }
    public Designation(){value = null;condition = false;}
    public Designation(String val){ //если введен неприемлемый аргумент выбрасываем исключеение (не соответствует ни одному из элментов массива acctptable)
        if(indexOf(val)==-1)
        {
            throw new IllegalArgumentException("IllegalArgumentException: Аргумент, передаваемый конструктору Designation() не найден в массиве String[] acceptable = {\"У\",\"В\",\"У/В\",\"Б\", \"Ух\", \"У7\",};");
        }
        value = val;
        condition = true;
    }
    public void setCondition(Boolean cond){condition = cond;} // cjcnjzybz
}
