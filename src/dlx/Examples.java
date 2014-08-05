package dlx;

public class Examples{

    static void runCoverExample(){
        int[][] example = { { 0, 0, 1, 0, 1, 1, 0 }, 
                            { 1, 0, 0, 1, 0, 0, 1 },
                            { 0, 1, 1, 0, 0, 1, 0 }, 
                            { 1, 0, 0, 1, 0, 0, 0 },
                            { 0, 1, 0, 0, 0, 0, 1 }, 
                            { 0, 0, 0, 1, 1, 0, 1 } 
                          };

        DancingLinks DLX = new DancingLinks(example);
        DLX.runSolver();
    }

    static void runSudokuExample(){
        String[] exampleSudoku = { "..9748...", "7........", ".2.1.9...",
                "..7...24.", ".64.1.59.", ".98...3..", "...8.3.2.",
                "........6", "...2759.." };
        
        int[][] hardest = {
                {8,0,0,0,0,0,0,0,0},
                {0,0,3,6,0,0,0,0,0},
                {0,7,0,0,9,0,2,0,0},
                {0,5,0,0,0,7,0,0,0},
                {0,0,0,0,4,5,7,0,0},
                {0,0,0,1,0,0,0,3,0},
                {0,0,1,0,0,0,0,6,8},
                {0,0,8,5,0,0,0,1,0},
                {0,9,0,0,0,0,4,0,0}
        }; // apparently the hardest sudoku
        
        
        SudokuDLX sudoku = new SudokuDLX();
        sudoku.solve(hardest);
        
        NaiveSudokuSolver naive = new NaiveSudokuSolver();
        naive.solve(hardest);
    }

    public static void main(String[] args){
        runCoverExample();
        runSudokuExample();
        
//        SudokuDLX sudoku = new SudokuDLX();
//        sudoku.generateAllSolutions();
        
    }

}
