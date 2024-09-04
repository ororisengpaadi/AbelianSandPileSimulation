
package serialAbelianSandpile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class AutomatonSimulation{

	static final boolean DEBUG=false;//for debugging output, off
	static long startTime = 0;
	static long endTime = 0;

	//timers - note milliseconds
	private static void tick(){ 
		//start timing
		startTime = System.currentTimeMillis();
	}

	private static void tock(){ 
		//end timing
		endTime=System.currentTimeMillis(); 
	}
	
	//input is via a CSV file
	 public static int [][] readArrayFromCSV(String filePath) {
		 int [][] array = null;
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            String line = br.readLine();
	            if (line != null) {
	                String[] dimensions = line.split(",");
	                int width = Integer.parseInt(dimensions[0]);
	                int height = Integer.parseInt(dimensions[1]);
	               	System.out.printf("Rows: %d, Columns: %d\n", width, height); //Do NOT CHANGE  - you must ouput this

	                array = new int[height][width];
	                int rowIndex = 0;

	                while ((line = br.readLine()) != null && rowIndex < height) {
	                    String[] values = line.split(",");
	                    for (int colIndex = 0; colIndex < width; colIndex++) {
	                        array[rowIndex][colIndex] = Integer.parseInt(values[colIndex]);
	                    }
	                    rowIndex++;
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return array;
	    }
	 
    public static void main(String[] args) throws IOException  {

    	Grid simulationGrid; //the cellular automaton grid
    	  	
    	if (args.length!=2) {  
			//input is the name of the input and output files
    		System.out.println("Incorrect number of command line arguments provided.");   	
    		System.exit(0);
    	}

    	/* Read argument values */
  		String inputFileName = args[0];   //input file name
		String outputFileName = args[1]; //output file name
    
    	// Read from input .csv file
    	simulationGrid = new Grid(readArrayFromCSV(inputFileName));
    	
    	//for debugging - hardcoded re-initialisation options
    	//simulationGrid.set(rows/2,columns/2,rows*columns*2);
    	//simulationGrid.set(rows/2,columns/2,55000);
    	//simulationGrid.setAll(4);
    	//simulationGrid.setAll(8);
   	
    	int counter = 0; //counts the number of time steps

    	tick(); //start timer

    	if(DEBUG) {
    		System.out.printf("starting config: %d \n",counter);
    		simulationGrid.printGrid();
    	}

		//run until no change
		while(simulationGrid.update()) {

	    	if(DEBUG) simulationGrid.printGrid();
	    	counter++;
	    }

   		tock(); //end timer
   		
        System.out.println("Simulation complete, writing image...");
    	simulationGrid.gridToImage(outputFileName); //write grid as an image
    
    	//simulation details
		System.out.printf("Number of steps to stable state: %d \n",counter);
		System.out.printf("Time: %d ms\n",endTime - startTime );	 //Total computation time	
    }
}