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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import org.jdesktop.swingx.JXTextField;

public class DesignationCellEditor extends JXTextField implements TableCellEditor,ActionListener,MouseListener, KeyListener {

    List designationCellEditorLiseners = new ArrayList(); //так как данный редакор генерирует события отмена редактирование и конец редактирования то мы должы регестрировать слушателей, которые будут реагировать на синалы от редактора.
    private ChangeEvent event = new ChangeEvent(this);
    static PopupMenu popup = new PopupMenu();
    Designations previousStateCell;
    int col;
    int row;
    DesignationCellEditor() {
        for(String el:Designations.ACCEPTABLE)
        {
            MenuItem menu = new MenuItem(el);
            menu.addActionListener(this);
            popup.add(menu);
        }
        popup.addActionListener(this);
        this.add(popup);
        this.setToolTipText(Designations.getAcceptables());
        this.addActionListener(this);
        this.addMouseListener(this);
        this.addKeyListener(this);
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
        if (anEvent.getSource() instanceof JTable table && table instanceof MainJTable mainJTable) {
            row = mainJTable.getSelectedRow();
            col = mainJTable.getSelectedColumn();
            if (row >= 0 && col > 1) {
                previousStateCell = (Designations) mainJTable.getValueAt(row, col);
               // System.out.println("Прежнее значение ячейки (" + row + "," + col + ") = " + previousStateCell);
            }

        }
       // previousStateCell = 
        return anEvent.getClass().equals(MouseEvent.class)&&((MouseEvent)anEvent).getClickCount()>1;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        Designations s;
        try {
            // System.out.println("Проверяем: "+this.getText());
            s = new Designations(this.getText());
            if (!previousStateCell.equals(s)) {
                for (int i = 0; i < designationCellEditorLiseners.size(); i++) {
                    ((CellEditorListener) designationCellEditorLiseners.get(i)).editingStopped(event);
                }
            }
            else
            {
                System.out.println("Измененное значение ячейки такое же!");
            }
        } catch (IllegalArgumentException e) {
            this.requestFocus();
            JOptionPane.showMessageDialog(null, "Допустимые значения в ячейке: " + Designations.getAcceptables());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().getClass().equals(MenuItem.class)) {
            //System.out.println("actionPerformed MenuItem: "+this.getText());
            this.setText(((MenuItem) e.getSource()).getLabel());
        }
            stopCellEditing();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource()==this) {
            popup.show(this, 0, 0);
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

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {
        if (!e.isControlDown()&&e.getSource()==this)//если без CTL
        {
            switch (e.getKeyChar()) {
                case 'у', 'E', 'e', 'У' -> {
                    this.setText("У"); stopCellEditing();
                }
                case 'B', 'd', 'D', 'в' -> {
                    this.setText("В"); stopCellEditing();
                  
                }
                default -> {
                    
                }
            }
        }
        if (e.isAltDown()&&e.getSource()==this) //с CTRL
        {
            switch (e.getKeyChar()) {
                case 'у', 'E', 'e', 'У' -> {
                    this.setText("Уд"); stopCellEditing();
                }
                case 'В', 'D', 'd', 'в' -> {
                    this.setText("Вд"); stopCellEditing();
                }
                default -> {
                    
                }
            }
        }
        if (e.getKeyChar() == KeyEvent.VK_DELETE&&e.getSource()==this) {
            this.setText(" "); stopCellEditing();
        }
    }

}
