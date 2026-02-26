/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import static Table.DesignationCellEditor.popup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import org.jdesktop.swingx.event.TableColumnModelExtListener;

/**
 *
 * @author yusup
 */
public class MainJTable extends JTable implements MouseListener, ActionListener {

    int beginSelectedRow = -1;
    int endSelectedRow = -1;
    int beginSelectedColumn = -1;
    int endSelectedColumn = -1;

    int beginSelectedRowForCopy = -1;
    int endSelectedRowForCopy = -1;
    int beginSelectedColumnForCopy = -1;
    int endSelectedColumnForCopy = -1;

    int col;
    ///сохраняем позицию ячейки, где кликали ПКМ
    int row;
    static PopupMenu popup = new PopupMenu();
    static String settingEl;

    public MainJTable(ScheduleTableModel tableModel) {
        for (String el : Designations.ACCEPTABLE) //создаем контекстное меню для редактируемых ячеек
        {
            MenuItem menu = new MenuItem(el);
            menu.addActionListener(this);
            popup.add(menu);
        }
        popup.setFont(new Font("Verdena", Font.ITALIC, 12));
        popup.addSeparator();
        MenuItem menu = new MenuItem("Копировать");
        menu.addActionListener(this);
        popup.add(menu);
        menu = new MenuItem("Вставить");
        menu.addActionListener(this);
        popup.add(menu);
        this.add(popup);

        this.setDefaultRenderer(Designations.class, new DesignationCellRenderer());
        this.setDefaultEditor(Designations.class, new DesignationCellEditor());

        this.addMouseListener(this);

        CellEditor editor = this.getDefaultEditor(Designations.class);
        editor.addCellEditorListener(new CellEditorListener() {
            @Override
            public void editingStopped(ChangeEvent e) {
                System.out.println(((CellEditor) e.getSource()).getCellEditorValue().toString());
            }

            @Override
            public void editingCanceled(ChangeEvent e) {
                System.out.println("Редактирование отменено");
            }
        });

        this.setModel(tableModel);
        if (this.getColumnCount() > 5) {
            this.getColumnModel().getColumn(0).setPreferredWidth(30);
            this.getColumnModel().getColumn(1).setPreferredWidth(200);
            for (int i = 2; i < this.getColumnCount(); i++) {
                this.getColumnModel().getColumn(i).setPreferredWidth(20);
            }
        }
        this.setFont(new Font("Verdena", Font.PLAIN, 20));
        this.setRowHeight(25);

        this.setFillsViewportHeight(true);
        //this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.setCellSelectionEnabled(true);

        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            ////////модель выбора строк/////////
                                                                                        @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                beginSelectedRowForCopy = beginSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMinSelectionIndex();
                endSelectedRowForCopy = endSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMaxSelectionIndex();
                System.out.println("Вы выделили СТРОКУ c №: " + beginSelectedRow + " по № " + endSelectedRow);

            
        
        ///TODO
                                                                                            }
                                                                                    });
        this.getColumnModel().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    return;
                }
                beginSelectedColumnForCopy = beginSelectedColumn = ((DefaultListSelectionModel) e.getSource()).getMinSelectionIndex();
                endSelectedColumnForCopy = endSelectedColumn = ((DefaultListSelectionModel) e.getSource()).getMaxSelectionIndex();
                System.out.println("Вы выделили СТОЛБЕЦ c №: " + beginSelectedColumn + " по № " + endSelectedColumn);

            
        
        ///TODO
                                                                                            }
                                                                                    });
    
    
        this.setRowSelectionInterval(2, 2);
        this.setColumnSelectionInterval(2, 2);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        col = this.columnAtPoint(new Point(e.getX(), e.getY()));
        row = this.rowAtPoint(new Point(e.getX(), e.getY()));
        System.out.println("Point(" + col + ", " + row + ")");
        if (this.getSelectedColumn() > 1 && e.getButton() == 3 && col >= 0 && row >= 0) {
            popup.show(this, e.getX() + 1, e.getY() + 1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Class<?> classElement = e.getSource().getClass();
        if (classElement.equals(MenuItem.class)) {
            switch (((MenuItem) e.getSource()).getLabel()) {
                case "Копировать":
                    System.out.println(beginSelectedRowForCopy);
                    System.out.println(endSelectedRowForCopy);
                    System.out.println(beginSelectedColumnForCopy);
                    System.out.println(endSelectedColumnForCopy);
                    break;
                case "Вставить":

                    break;
                default:
                    for (int c = beginSelectedColumn; c <= endSelectedColumn; c++) {
                        for (int r = beginSelectedRow; r <= endSelectedRow; r++) {
                            this.setValueAt(new Designations(((MenuItem) e.getSource()).getLabel()), r, c);
                        }
                    }
                    this.repaint();
                    break;
            }
        }

    }

}
