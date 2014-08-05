package dlx;

import java.util.*;
import dlx.DancingLinks.*;

public class SudokuDLX{
    int S = 9;
    int side = 3;

    private int[][] fromCharArr(String[] s){
        int[][] out = new int[S][S];
        for(int i = 0; i < S; i++){
            for(int j = 0; j < S; j++){
                int num = s[i].charAt(j) - '1';
                if (num >= 1 && num <= 9)
                    out[i][j] = num;
            }
        }
        return out;
    }

    // sudoku has numbers 1-9. A 0 indicates an empty cell that we will need to
    // fill in.
    private int[][] makeExactCoverGrid(int[][] sudoku){

        int[][] R = sudokuExactCover();

        for(int i = 1; i <= S; i++){
            for(int j = 1; j <= S; j++){
                int n = sudoku[i - 1][j - 1];
                if (n != 0){ // zero out in the constraint board
                    for(int num = 1; num <= S; num++){
                        if (num != n){
                            Arrays.fill(R[getIdx(i, j, num)], 0);
                        }
                    }
                }
            }
        }
        return R;
    }

    // Returns the base exact cover grid for a SUDOKU puzzle
    private int[][] sudokuExactCover(){
        int[][] R = new int[9 * 9 * 9][9 * 9 * 4];

        int hBase = 0;

        // row-column constraints
        for(int r = 1; r <= S; r++){
            for(int c = 1; c <= S; c++, hBase++){
                for(int n = 1; n <= S; n++){
                    R[getIdx(r, c, n)][hBase] = 1;
                }
            }
        }

        // row-number constraints
        for(int r = 1; r <= S; r++){
            for(int n = 1; n <= S; n++, hBase++){
                for(int c1 = 1; c1 <= S; c1++){
                    R[getIdx(r, c1, n)][hBase] = 1;
                }
            }
        }

        // column-number constraints

        for(int c = 1; c <= S; c++){
            for(int n = 1; n <= S; n++, hBase++){
                for(int r1 = 1; r1 <= S; r1++){
                    R[getIdx(r1, c, n)][hBase] = 1;
                }
            }
        }

        // box-number constraints

        for(int br = 1; br <= S; br += side){
            for(int bc = 1; bc <= S; bc += side){
                for(int n = 1; n <= S; n++, hBase++){
                    for(int rDelta = 0; rDelta < side; rDelta++){
                        for(int cDelta = 0; cDelta < side; cDelta++){
                            R[getIdx(br + rDelta, bc + cDelta, n)][hBase] = 1;
                        }
                    }
                }
            }
        }

        return R;
    }

    // row [1,S], col [1,S], num [1,S]
    private int getIdx(int row, int col, int num){
        return (row - 1) * S * S + (col - 1) * S + (num - 1);
    }

    private static boolean validateSudoku(int[][] grid){
        if (grid.length != 9 && grid.length != 15)
            return false; // only 9 or 15 for now
        for(int i = 0; i < grid.length; i++){
            if (grid[i].length != grid.length)
                return false;
            for(int j = 0; j < grid[i].length; j++){
                if (!(i >= 0 && i <= grid.length))
                    return false; // 0 means not filled in
            }
        }

        int N = grid.length;

        boolean[] b = new boolean[N+1];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if (grid[i][j] == 0)
                    continue;
                if (b[grid[i][j]])
                    return false;
                b[grid[i][j]] = true;
            }
            Arrays.fill(b, false);
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if (grid[j][i] == 0)
                    continue;
                if (b[grid[j][i]])
                    return false;
                b[grid[j][i]] = true;
            }
            Arrays.fill(b, false);
        }

        for(int i = 0; i < N; i += N / 3){
            for(int j = 0; j < N; j += N / 3){
                for(int d1 = 0; d1 < N / 3; d1++){
                    for(int d2 = 0; d2 < N / 3; d2++){
                        if (grid[i + d1][j + d2] == 0)
                            continue;
                        if (b[grid[i + d1][j + d2]])
                            return false;
                        b[grid[i + d1][j + d2]] = true;
                    }
                }
                Arrays.fill(b, false);
            }
        }
        return true;
    }

    public boolean runSolver(int[][] sudoku){
        S = sudoku.length;
        side = S / 3;
        return solve(sudoku);
    }

    public boolean runSolver(String[] s){
        S = s.length;
        side = S / 3;
        int[][] sudoku = fromCharArr(s);
        return solve(sudoku);
    }
    
    public void generateAllSolutions(){ // starts printing ALL valid sudokus. Obviously you'll have to abort
        int[][] cover = sudokuExactCover();
        DancingLinks dlx = new DancingLinks(cover, new SudokuHandler(S));
        dlx.runSolver();
    }

    private boolean solve(int[][] sudoku){
        if (!validateSudoku(sudoku)){
            System.out.println("Invalid sudoku board. Aborting.");
            return false;
        }
        // solve it here

        int[][] cover = makeExactCoverGrid(sudoku);

        DancingLinks dlx = new DancingLinks(cover, new SudokuHandler(S));
        dlx.runSolver();

        return true;
    }

}
