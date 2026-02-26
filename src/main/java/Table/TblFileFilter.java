/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class TblFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if(f.isDirectory()){return true;}
        return f.toString().endsWith("tbl");
    }

    @Override
    public String getDescription() {
        return "Schedule-table (*.tbl)";
    }
    
}
