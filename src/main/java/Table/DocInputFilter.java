package Table;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DocInputFilter extends DocumentFilter {//фильтр проверяет посимвольный ввод

    String regEx;
    int signs = 0;

    public DocInputFilter(String regEx) {
        super();
        this.regEx = regEx;
    }

    @Override
    public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) {

        if (isNumber(string)) {
            try {

                if (string.equals(",") || string.equals(".")) {
                    signs--;
                }
                super.insertString(fb, offset, string, attr);
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        }
    }

    @Override
    public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) {
        if (isNumber(text) && signs < 2) {
           // System.out.println("Количество запятых: " + signs);
            try {
                super.replace(fb, offset, length, text, attrs);
            } catch (BadLocationException ex) {
                System.out.println(ex);
            }
        } else {
            //System.out.println("Удаляем ввод: " + text);
            if (text.equals(",") || text.equals(".")) {
                signs--;
            }
        }
    }

    public boolean isNumber(String text) {
        if (text.equals(",") || text.equals(".")) {
            signs++;
        }
       // System.out.println("Введенное значение: " + text);
        return text.matches(regEx) && signs < 2;
    }

}
