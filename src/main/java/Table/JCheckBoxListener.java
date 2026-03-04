/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 *
 * @author yusup
 */
class JCheckBoxListener extends JCheckBox implements ActionListener {

    public JCheckBoxListener(String name,boolean value) {
        super(name,value);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().equals(JCheckBox.class)) {
            this.setSelected(((JCheckBox) e.getSource()).isSelected());
        }
    }

}
