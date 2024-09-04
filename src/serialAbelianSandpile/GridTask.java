package serialAbelianSandpile;
import java.util.concurrent.RecursiveTask;

public class GridTask extends RecursiveTask<Boolean>{
    private int rowStart, rowEnd, colStart, colEnd;
    private int[][] grid, updateGrid;
    private boolean change = false;
    private int CUTOFF = 90;
    
    public GridTask(int rowStart, int rowEnd, int colStart, int colEnd, int[][] grid, int[][] updateGrid){
        
        this.rowStart = rowStart;
        this.rowEnd = rowEnd;
        this.colStart = colStart;
        this.colEnd = colEnd;
        this.grid = grid;
        this.updateGrid = updateGrid; 
    }
    
    protected Boolean compute(){
        change = false;
        //if less than CUTOFF process it directly
        if (  ((rowEnd - rowStart) < CUTOFF) && ((colEnd - colStart) < CUTOFF) ){
            for( int i = rowStart; i < rowEnd; i++ ) {
                for( int j = colStart; j < colEnd; j++ ) {
                    updateGrid[i][j] = (grid[i][j] % 4) + 
                            (grid[i-1][j] / 4) +
                            grid[i+1][j] / 4 +
                            grid[i][j-1] / 4 + 
                            grid[i][j+1] / 4;
                    if (grid[i][j]!=updateGrid[i][j]) {  
                        change=true;
                    }
            }} //end nested for
            return change;
        }
        else {
            //split rows
            if ((rowEnd - rowStart) > (colEnd - colStart)){
                int middle = (int) (rowEnd + rowStart)/2;
                GridTask left = new GridTask(rowStart, middle, colStart, colEnd, grid, updateGrid);
                GridTask right = new GridTask(middle, rowEnd, colStart, colEnd, grid, updateGrid);
                left.fork();
                boolean rightChange = right.compute();
                boolean leftChange = left.join();
                return (rightChange || leftChange);


            }  else {
                //split columns
                int middle = (int) (colEnd + colStart) / 2;
                GridTask left = new GridTask(rowStart, rowEnd, colStart, middle, grid, updateGrid);
                GridTask right = new GridTask(rowStart, rowEnd, middle, colEnd, grid, updateGrid);
                left.fork();
                boolean rightChange = right.compute();
                boolean leftChange = left.join();
                return (rightChange || leftChange);
            }
        }
    }
}
