package app.gui.actionlisteners;

//API Providing GUI
import javax.swing.JTextField;

//Abstract Window Toolkit
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ClearButtonActionListener implements ActionListener {
    //ClearButtonActionListener const values:
    private static final int GRID_SIZE = 9;

    //GUI components:
    private JTextField[][] cellTextFields;

    /**
     * Constructs the solve button action listener object.
     * @param cellTextFields the sudoku fields.
     */
    public ClearButtonActionListener(JTextField[][] cellTextFields) {
        this.cellTextFields = cellTextFields;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n = ClearButtonActionListener.GRID_SIZE;

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.cellTextFields[row][col].setBackground(Color.WHITE);
                this.cellTextFields[row][col].setEditable(true);
                this.cellTextFields[row][col].setText("");
            }
        }
    }
}
