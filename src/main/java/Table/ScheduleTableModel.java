
package Table;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.table.AbstractTableModel;
import yusvel.schedule.employee.Employee;

/**
 *
 * @author yusup
 */
public class ScheduleTableModel extends AbstractTableModel implements Serializable{
    MainTable mainTable;
    ScheduleTableModel(MainTable table)
    {
        mainTable = table;
    }
    
    @Override
    public int getRowCount() {
        return mainTable.getEmployees().size();
    }

    @Override
    public int getColumnCount() {
        return mainTable.getDate().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return mainTable.getEmployees().get(rowIndex)
                        .getWorkSchedule().get(columnIndex);// из злавной таблицы получаем массив сотрудников по индексу строки определяем
                                                           //нужного сотрудника и получаем у него массив обозначений(рабочий график) а оттуда
                                                           //получаем по индексу колонки нужное обохзначение(У,В,у/В, ОТ и тд...
    }
    
    
}
