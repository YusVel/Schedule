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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author yusup
 */
public class MainJTable extends JTable implements MouseListener, ActionListener, KeyListener {

    int beginSelectedRow = -1;
    int endSelectedRow = -1;
    int beginSelectedColumn = -1;
    int endSelectedColumn = -1;

    int beginSelectedRowForCopy = -1;
    int endSelectedRowForCopy = -1;
    int beginSelectedColumnForCopy = -1;
    int endSelectedColumnForCopy = -1;
    ArrayList<ArrayList<Designations>> arrForCopy = new ArrayList<ArrayList<Designations>>();

    ///сохраняем позицию ячейки, где кликали ПКМ
    int clickedColumnByMousebutton3;
    int clickedRowByMousebutton3;

    static PopupMenu popup = new PopupMenu();
    static String settingEl;

    public MainJTable(ScheduleTableModel tableModel) {
        for (String el : Designations.ACCEPTABLE) //создаем контекстное меню для редактируемых ячеек
        {
            MenuItem menu = new MenuItem(el);
            menu.addActionListener(this);
            popup.add(menu);
        }
        popup.setFont(new Font("Montserrat", Font.BOLD, 14));
        popup.addSeparator();
        MenuItem menu = new MenuItem("Копировать");
        menu.addActionListener(this);
        popup.add(menu);
        menu = new MenuItem("Вставить");
        menu.addActionListener(this);
        menu.setEnabled(false);
        popup.add(menu);
        this.add(popup);

        this.setDefaultRenderer(Designations.class, new DesignationCellRenderer());
        this.setDefaultEditor(Designations.class, new DesignationCellEditor());
        this.getSelectionModel().setSelectionMode(DefaultListSelectionModel.SINGLE_INTERVAL_SELECTION);
        this.addMouseListener(this);
        this.addKeyListener(this);
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
                beginSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMinSelectionIndex();
                endSelectedRow = ((DefaultListSelectionModel) e.getSource()).getMaxSelectionIndex();
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
                beginSelectedColumn = ((DefaultListSelectionModel) e.getSource()).getMinSelectionIndex();
                endSelectedColumn = ((DefaultListSelectionModel) e.getSource()).getMaxSelectionIndex();
                System.out.println("Вы выделили СТОЛБЕЦ c №: " + beginSelectedColumn + " по № " + endSelectedColumn);

            
        
        ///TODO
                                                                                            }
                                                                                    });
    
    
        this.setRowSelectionInterval(2, 2);
        this.setColumnSelectionInterval(2, 2);
    }

    @Override
    public void mouseClicked(MouseEvent e) { //левая кнопка мыши
        clickedColumnByMousebutton3 = this.columnAtPoint(new Point(e.getX(), e.getY()));
        clickedRowByMousebutton3 = this.rowAtPoint(new Point(e.getX(), e.getY()));
        System.out.println("Point(" + clickedRowByMousebutton3 + ", " + clickedColumnByMousebutton3 + ")");
        if (this.getSelectedColumn() > 1 && e.getButton() == 3 && clickedColumnByMousebutton3 >= 2 && clickedRowByMousebutton3 >= 0) {

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
                    arrForCopy.clear();
                    int tempR = beginSelectedRowForCopy = beginSelectedRow;
                    endSelectedRowForCopy = endSelectedRow;
                    int tempC = beginSelectedColumnForCopy = beginSelectedColumn;
                    endSelectedColumnForCopy = endSelectedColumn;

                    for (int r = 0; r <= endSelectedRowForCopy - beginSelectedRowForCopy; r++) {
                        arrForCopy.add(new ArrayList<Designations>());
                        for (int c = 0; c <= endSelectedColumnForCopy - beginSelectedColumnForCopy; c++) {
                            arrForCopy.get(r).add(c, new Designations(this.getValueAt(tempR, tempC).toString()));
                            tempC++;
                        }
                        tempC = beginSelectedColumnForCopy;
                        tempR++;
                    }

                    for (ArrayList<Designations> r : arrForCopy) {
                        for (Designations c : r) {
                            System.out.print(c.toString());
                        }
                        System.out.println();
                    }

                    System.out.println("Скопированы ячейки (" + beginSelectedRowForCopy + ", " + beginSelectedColumnForCopy + "):(" + endSelectedRowForCopy + ", " + endSelectedColumnForCopy + ")");
                    popup.getItem(popup.getItemCount() - 1).setEnabled(true);
                    break;
                case "Вставить":
                    int rPaste = clickedRowByMousebutton3;
                    int cPaste = clickedColumnByMousebutton3;

                    System.out.println("Вставляем в ячейку " + "(" + rPaste + ", " + cPaste + ")");

                    for (int r = 0; r < arrForCopy.size(); r++) {
                        for (int c = 0; c < arrForCopy.get(0).size(); c++) {
                            if (cPaste < this.getColumnCount() && rPaste < this.getRowCount()) {
                                this.setValueAt(arrForCopy.get(r).get(c), rPaste, cPaste);

                                cPaste++;
                            }

                        }
                        cPaste = clickedColumnByMousebutton3;
                        rPaste++;
                    }
                    this.repaint();

                    break;
                default:
                    for (int c = beginSelectedColumn; c <= endSelectedColumn && c >= 2; c++) {
                        for (int r = beginSelectedRow; r <= endSelectedRow && r >= 0; r++) {
                            this.setValueAt(new Designations(((MenuItem) e.getSource()).getLabel()), r, c);
                        }
                    }
                    this.repaint();
                    break;
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (this.getSelectedColumn() >= 2 && !e.isControlDown())//если без CTL
        {
            switch (e.getKeyChar()) {
                case 'у':;
                case 'E':;
                case 'e':;
                case 'У':
                    this.setValueAt(new Designations("У"), this.getSelectedRow(), this.getSelectedColumn());
                    break;
                case 'B':;
                case 'b':;
                case 'В':;
                case 'в':
                    this.setValueAt(new Designations("В"), this.getSelectedRow(), this.getSelectedColumn());
                    ;
                    break;
                default:;
                    break;
            }
        }
        if (this.getSelectedColumn() >= 2 && e.isAltDown()) //с CTRL
        {
            switch (e.getKeyChar()) {
                case 'у':;
                case 'E':;
                case 'e':;
                case 'У':
                    this.setValueAt(new Designations("Уд"), this.getSelectedRow(), this.getSelectedColumn());
                    break;
                case 'В':;
                case 'B':;
                case 'b':;
                case 'в':
                    this.setValueAt(new Designations("Вд"), this.getSelectedRow(), this.getSelectedColumn());
                    break;
                default:;
                    break;
            }
        }
        if (this.getSelectedColumn() >= 2 && e.getKeyChar() == KeyEvent.VK_DELETE) {
            this.setValueAt(new Designations(" "), this.getSelectedRow(), this.getSelectedColumn());
        }

        this.repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
