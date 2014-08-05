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
        SudokuDLX sudoku = new SudokuDLX();
        sudoku.runSolver(exampleSudoku);
    }

    public static void main(String[] args){
        runCoverExample();
        runSudokuExample();
    }

}
