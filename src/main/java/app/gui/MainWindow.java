package app.gui;

//Application Custom Packages
import app.gui.actionlisteners.ClearButtonActionListener;
import app.gui.actionlisteners.SolveButtonActionListener;
import app.gui.doc.SudokuCellDoc;

//API Providing GUI
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.CompoundBorder;

//Abstract Window Toolkit
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;


public class MainWindow {
    //Window const values:
    private static final String ICON_PATH = "./src/main/resources/img/main-window-icon.png";
    private static final String WINDOW_TITLE = "Sudoku Solver";
    private static final int WINDOW_SIZE = 520;
    private static final int SUDOKU_CELL_FONT_SIZE = 32;
    private static final int BUTTON_FONT_SIZE = 20;
    private static final int GRID_SIZE = 3;

    private static final int GAP_BETWEEN_GAME_AND_BUTTON_PANELS = 10;
    private static final int GAP_BETWEEN_BUTTONS = 6;
    private static final int GAP_BETWEEN_MAGIC_SQUARES = 4;
    private static final int GAP_BETWEEN_CELLS = 1;

    //GUI components:
    private JButton clearButton, solveButton;
    private JFrame mainFrame;
    private JPanel buttonPanel, gamePanel;
    private JPanel[][] magicSquarePanels;
    private JTextField[][] cellTextFields;

    /**
     * Constructs a main window object.
     */
    public MainWindow() {
        BorderLayout mainFrameLayout = new BorderLayout();
        mainFrameLayout.setVgap(MainWindow.GAP_BETWEEN_GAME_AND_BUTTON_PANELS);

        this.mainFrame = new JFrame(MainWindow.WINDOW_TITLE);
        this.mainFrame.setLayout(mainFrameLayout);

        this.mainFrame.setSize(MainWindow.WINDOW_SIZE, MainWindow.WINDOW_SIZE);
        this.mainFrame.setIconImage(new ImageIcon(MainWindow.ICON_PATH).getImage());

        initGamePanel();
        initButtonPanel();
    }

    /**
     * Initializes the game panel components in the main frame.
     */
    private void initGamePanel() {
        //Initializes the sudoku game panel.
        GridLayout gamePanelLayout = new GridLayout();
        gamePanelLayout.setHgap(MainWindow.GAP_BETWEEN_MAGIC_SQUARES);
        gamePanelLayout.setVgap(MainWindow.GAP_BETWEEN_MAGIC_SQUARES);
        gamePanelLayout.setRows(MainWindow.GRID_SIZE);
        gamePanelLayout.setColumns(MainWindow.GRID_SIZE);

        this.gamePanel = new JPanel();
        this.gamePanel.setLayout(gamePanelLayout);
        this.gamePanel.setBackground(Color.black);
        this.gamePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, MainWindow.GAP_BETWEEN_MAGIC_SQUARES));


        //Initializes the sudoku magic squares.
        GridLayout magicSquareLayout = new GridLayout();
        magicSquareLayout.setHgap(MainWindow.GAP_BETWEEN_CELLS);
        magicSquareLayout.setVgap(MainWindow.GAP_BETWEEN_CELLS);
        magicSquareLayout.setRows(MainWindow.GRID_SIZE);
        magicSquareLayout.setColumns(MainWindow.GRID_SIZE);

        this.magicSquarePanels = new JPanel[MainWindow.GRID_SIZE][MainWindow.GRID_SIZE];
        for (int row=0; row<MainWindow.GRID_SIZE; row++) {
            for (int col=0; col<MainWindow.GRID_SIZE; col++) {
                this.magicSquarePanels[row][col] = new JPanel();
                this.magicSquarePanels[row][col].setLayout(magicSquareLayout);
                this.magicSquarePanels[row][col].setBackground(Color.black);
                this.gamePanel.add(this.magicSquarePanels[row][col]);
            }
        }

        //Initializes the sudoku cells.
        int size = MainWindow.GRID_SIZE * MainWindow.GRID_SIZE;
        this.cellTextFields = new JTextField[size][size];
        for (int row=0; row<size; row++) {
            for (int col=0; col<size; col++) {
                this.cellTextFields[row][col] = new JTextField();
                this.cellTextFields[row][col].setDocument(new SudokuCellDoc());
                this.cellTextFields[row][col].setFont(new Font("", Font.BOLD, MainWindow.SUDOKU_CELL_FONT_SIZE));
                this.cellTextFields[row][col].setHorizontalAlignment(SwingConstants.CENTER);
                this.cellTextFields[row][col].setBorder(new CompoundBorder().getInsideBorder());

                this.magicSquarePanels[row/3][col/3].add(this.cellTextFields[row][col]);
            }
        }

        this.mainFrame.add(this.gamePanel, BorderLayout.CENTER);
    }

    /**
     * Initializes the button panel components in the main frame.
     */
    private void initButtonPanel() {
        //Initializes the button panel.
        FlowLayout buttonPanelLayout = new FlowLayout();
        buttonPanelLayout.setHgap(MainWindow.GAP_BETWEEN_BUTTONS);

        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(buttonPanelLayout);

        //Initializes the clear button.
        this.clearButton = new JButton("  Clear  ");
        this.clearButton.setFont(new Font("", Font.PLAIN, MainWindow.BUTTON_FONT_SIZE));
        this.clearButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        this.clearButton.addActionListener(new ClearButtonActionListener(this.cellTextFields));
        this.buttonPanel.add(this.clearButton);

        //Initializes the solve button.
        this.solveButton = new JButton("  Solve  ");
        this.solveButton.setFont(new Font("", Font.PLAIN, MainWindow.BUTTON_FONT_SIZE));
        this.solveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        this.solveButton.addActionListener(new SolveButtonActionListener(this.cellTextFields));
        this.buttonPanel.add(this.solveButton);

        this.mainFrame.add(this.buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Opens the main window.
     */
    public void open() {
        this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.mainFrame.setVisible(true);
    }
}
