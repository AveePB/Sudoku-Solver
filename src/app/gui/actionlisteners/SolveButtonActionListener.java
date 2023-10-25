package app.gui.actionlisteners;

//Application Custom Packages
import app.game.Sudoku;

//API Providing GUI
import javax.swing.JTextField;

//Abstract Window Toolkit
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class SolveButtonActionListener implements ActionListener {
    //SolveButtonActionListener const values:
    private static final int GRID_SIZE = 9;

    //GUI components:
    private JTextField[][] cellTextFields;

    //Game components:
    private Sudoku sudoku;

    /**
     * Constructs the solve button action listener object.
     * @param cellTextFields the sudoku fields.
     */
    public SolveButtonActionListener(JTextField[][] cellTextFields) {
        this.cellTextFields = cellTextFields;
        this.sudoku = new Sudoku();
    }

    /**
     * Constructs the grid from sudoku cells.
     * @return the sudoku grid (char[][]).
     */
    private char[][] getSudokuGrid() {
        int n = SolveButtonActionListener.GRID_SIZE;
        char[][] grid = new char[n][n];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (this.cellTextFields[row][col].getText().length() == 0)
                    grid[row][col] = '.';
                else
                    grid[row][col] = this.cellTextFields[row][col].getText().charAt(0);
            }
        }
        return grid;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int n = SolveButtonActionListener.GRID_SIZE;
        char[][] grid = getSudokuGrid();

        boolean isSolved = this.sudoku.solve(grid);
        this.sudoku.clearGrid();

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                this.cellTextFields[row][col].setEditable(false);
                this.cellTextFields[row][col].setText(String.valueOf(grid[row][col]));
                if (isSolved)
                    this.cellTextFields[row][col].setBackground(new Color(75, 207, 119));
                else
                    this.cellTextFields[row][col].setBackground(new Color(194, 89, 80));
            }
        }
    }
}
