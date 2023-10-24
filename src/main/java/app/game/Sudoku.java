package app.game;


public class Sudoku {
    //Sudoku structures:
    private boolean[][] isCln = new boolean[9][9], isRow = new boolean[9][9];
    private boolean[][][] isMagicSquare = new boolean[3][3][9];

    /**
     * Solves the sudoku.
     * @param mat the sudoku grid.
     * @param row the current row.
     * @param cln the current column.
     * @return true if sudoku is solved otherwise false.
     */
    private boolean backtracking(char[][] mat, int row, int cln) {
        if(row == 9) return true;

        int next_row = row + (cln+1) / 9, next_cln = (cln+1) % 9;
        if(mat[row][cln] != '.') return backtracking(mat, next_row, next_cln);

        for(int i=0; i<9; i++) {
            if(this.isCln[cln][i] || this.isRow[row][i]) continue;
            if(this.isMagicSquare[row/3][cln/3][i]) continue;

            mat[row][cln] = (char) ('1' + i);
            this.isCln[cln][i] = true;
            this.isRow[row][i] = true;
            this.isMagicSquare[row/3][cln/3][i] = true;

            if(backtracking(mat, next_row, next_cln)) return true;

            mat[row][cln] = '.';
            this.isCln[cln][i] = false;
            this.isRow[row][i] = false;
            this.isMagicSquare[row/3][cln/3][i] = false;
        }
        return false;
    }

    /**
     * Clears the sudoku grid.
     */
    public void clearGrid() {
        this.isRow = new boolean[9][9];
        this.isCln = new boolean[9][9];
        this.isMagicSquare = new boolean[3][3][9];
    }

    /**
     * Solves the sudoku gird.
     * @param board the sudoku grid.
     * @return true if sudoku is solved otherwise false.
     */
    public boolean solve(char[][] board) {
        for(int row=0; row<9; row++) {
            for(int cln=0; cln<9; cln++) {
                if(board[row][cln] == '.') continue;
                int val = board[row][cln] - '1';

                if(this.isCln[cln][val] || this.isRow[row][val]) return false;
                if(this.isMagicSquare[row/3][cln/3][val]) return false;

                this.isCln[cln][val] = true;
                this.isRow[row][val] = true;
                this.isMagicSquare[row/3][cln/3][val] = true;
            }
        }
        return backtracking(board, 0, 0);
    }
}