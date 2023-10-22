package app.gui.doc;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class SudokuCellDoc extends PlainDocument {
    private static final int LIMIT = 1;

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if ((str == null) || ((getLength() + str.length()) > SudokuCellDoc.LIMIT)) return;

        for (int i=1; i<10; i++) {
            if (str.charAt(0) == (char) ('0'+i))
                super.insertString(offset, str, attr);
        }
    }
}
