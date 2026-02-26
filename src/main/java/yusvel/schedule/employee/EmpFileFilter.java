
package yusvel.schedule.employee;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class EmpFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){return true;}
        return f.toString().endsWith(".emp");
    }

    @Override
    public String getDescription() {
        return "Employees-table (*.emp)";
    }
    
}
