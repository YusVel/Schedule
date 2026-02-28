
package Table;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import yusvel.schedule.employee.Employee;

public class CreationNewTableDialog extends JDialog {
    Calendar date = Calendar.getInstance();
    Float workingHour;
    ArrayList<Employee> employees;
    JLabel workingHourlabel = new JLabel("Количество часов в текущем месяце: ");
    public CreationNewTableDialog(JFrame owner, ArrayList<Employee> employees) {
        super(owner,"Создание новой таблицы");
        this.setModal(true);
        this.setLocationRelativeTo(null);
        this.setLayout(new GridLayout());
        
        workingHourlabel.setFont(new Font("Verdena", Font.PLAIN, 16));
        
        this.add(workingHourlabel);
        
        this.pack();
        this.setResizable(false);
        this.setVisible(true);
    }
    
}
