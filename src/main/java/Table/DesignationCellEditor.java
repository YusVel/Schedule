package Table;

import java.awt.Color;
import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import javax.swing.*;
import java.awt.MenuItem;
import javax.swing.event.ChangeEvent;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import org.jdesktop.swingx.JXTextField;

public class DesignationCellEditor extends JXTextField implements TableCellEditor {

    List designationCellEditorLiseners = new ArrayList(); //так как данный редакор генерирует события отмена редактирование и конец редактирования то мы должы регестрировать слушателей, которые будут реагировать на синалы от редактора.
    private ChangeEvent event = new ChangeEvent(this);
    static PopupMenu popup = new PopupMenu();
    DesignationCellEditor() {
        for(String el:Designations.ACCEPTABLE)
        {
            MenuItem menu = new MenuItem(el);
            menu.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                             ((DesignationCellEditor)popup.getParent()).setText(((MenuItem)e.getSource()).getLabel());
                                                             ((DesignationCellEditor)popup.getParent()).stopCellEditing();
                                                        }
                                                    });
            popup.add(menu);
        }
        this.add(popup);
        this.setToolTipText(Designations.getAcceptables());
        this.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            stopCellEditing();
                                                        }
                                                    });
        this.addMouseListener(new MouseListener(){
                                                    @Override
                                                    public void mouseClicked(MouseEvent e) {
                                                          
                                                        if(e.getButton()==MouseEvent.BUTTON3)
                                                        {
                                                             popup.show((DesignationCellEditor)e.getSource(), 0, 0);
                                                         
                                                        }
                                                    }
                                                    @Override
                                                    public void mousePressed(MouseEvent e) {}
                                                    @Override
                                                    public void mouseReleased(MouseEvent e) {}
                                                    @Override
                                                    public void mouseEntered(MouseEvent e) {}
                                                    @Override
                                                    public void mouseExited(MouseEvent e) {}
                                                });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) { //возвращаем значение Text в редактор JTextField
        this.setBackground(new Color(222, 255, 223));
        this.setText(((Designations) ((MainJTable) table).getModel().getValueAt(row, column)).toString());
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return new Designations(this.getText());
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        //ячейка редактируется если кликов больше 1
        return anEvent.getClass().equals(MouseEvent.class) && ((MouseEvent) anEvent).getClickCount() > 1;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        Designations s;
        try {
            s = new Designations(this.getText());
            for (int i = 0; i < designationCellEditorLiseners.size(); i++) {
                ((CellEditorListener) designationCellEditorLiseners.get(i)).editingStopped(event);
            }
        } catch (IllegalArgumentException e) {
            this.requestFocus();
            JOptionPane.showMessageDialog(null, "Допустимые значения в ячейке: "+Designations.getAcceptables());
            return false;
        }
        return true;
    }

    @Override
    public void cancelCellEditing() {
        for (int i = 0; i < designationCellEditorLiseners.size(); i++) {
            ((CellEditorListener) designationCellEditorLiseners.get(i)).editingCanceled(event);
        }
    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {
        designationCellEditorLiseners.add(l);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {
        designationCellEditorLiseners.remove(l);
    }

}
