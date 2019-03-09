public class main {
	public static int countRouting = 0;
	public static int countComputation = 0;
	public static int countRouting2 = 0;
	public static int countComputation2 = 0;
	
	public static void main(String[] args) {

		int seed = 0;
		
		/*
		//Simple example of generating 10*10 maze before search and after search
		Maze maze = new Maze(10, 10, 30, 7); 
		System.out.println("Demo for generating Maze");
		System.out.println();
		maze.showMaze();
		
		System.out.println();
		System.out.println("Simple example for Repeated Forward A*");
		
		AStarRepeatedGGreater aStarRGG = new AStarRepeatedGGreater(maze);
		aStarRGG.findPath();
		System.out.println();
		maze.showMaze();
		
		System.out.println();
		System.out.println("Simple example for Repeated Backward A*");
		
		AStarRepeatedGGreaterBackward aStarRGGB = new AStarRepeatedGGreaterBackward(maze);
		aStarRGGB.findPath();
		System.out.println();
		maze.showMaze();
		
		System.out.println();
		System.out.println("<3> Simple example for Adaptive A*");
		
		AStarRepeatedGGreaterAdaptive aStarRGGA = new AStarRepeatedGGreaterAdaptive(maze);
		aStarRGGA.findPath();
		System.out.println();
		maze.showMaze();
		*/
		
		/*
		//part 0 generating maze (showing with example A* repeated backward)
		//seed = -1 means it's random
		System.out.println("Start Part0...");
		for (int i=0; i<50; i++) {
			seed += 1000;
			Maze m = new Maze(101, 101, 30, seed);
			System.out.println();
			System.out.println("Generating Maze " + i);
			System.out.println();
			m.showMaze();
		}
		System.out.println("End Part0...");
		*/

		/*
		//Part 2 experiment
		//int seed = 0;
		System.out.println("Start Part2...");
		for(int i=0; i<50; i++){
			seed += 1000;
			Maze m1=new Maze(101, 101, 30, seed); 
			Maze m2=new Maze(101, 101, 30, seed); 
			
			long start1 = System.currentTimeMillis();
			AStarRepeatedGLower aStarRGL = new AStarRepeatedGLower(m1);		
			aStarRGL.findPath();
			long end1 = System.currentTimeMillis();
			double time1 = (end1 - start1) / (double)1000;
			//m1.showMaze();

			//System.out.println();
			
			long start2 = System.currentTimeMillis();
			AStarRepeatedGGreater aStarRGG = new AStarRepeatedGGreater(m2);		
			aStarRGG.findPath();
			long end2 = System.currentTimeMillis();
			double time2 = (end2 - start2) / (double)1000;
			//m2.showMaze();

			//Printing Runtime
			//System.out.println(time1+" "+time2);
			
			//Printing Routing Number
			//System.out.println(countRouting+" "+countRouting2);
			
			//Printing number of Cell visited
			//System.out.println(countComputation+" "+countComputation2);
			
			System.out.println();
			countRouting = 0;
			countRouting2 = 0;
			countComputation = 0;
			countComputation2 = 0;
		}
		System.out.println("End Part2...");
		System.out.println();
		*/
		
		
		/*
		//Part 3 Experiment
		System.out.println("Start Part3...");
		for(int i=0; i<50; i++){
			seed += 1000;
			Maze m1=new Maze(101, 101, 30, seed); 
			Maze m2=new Maze(101, 101, 30, seed);
			
			long start1 = System.currentTimeMillis();
			AStarRepeatedGGreater aStarRGG = new AStarRepeatedGGreater(m1);		
			aStarRGG.findPath();
			long end1 = System.currentTimeMillis();
			double time1 = (end1 - start1) / (double)1000;
			
			long start2 = System.currentTimeMillis();
			AStarRepeatedGGreaterBackward aStarRGGB = new AStarRepeatedGGreaterBackward(m2);		
			aStarRGGB.findPath();
			long end2 = System.currentTimeMillis();
			double time2 = (end2 - start2) / (double)1000;			
			
			//Printing Runtime
			//System.out.println(time1+" "+time2);
			
			//Printing Routing Number
			//System.out.println(countRouting+" "+countRouting2);
			
			//Printing number of Cell visited
			System.out.println(countComputation+" "+countComputation2);
			countRouting = 0;
			countRouting2 = 0;
			countComputation = 0;
			countComputation2 = 0;
		}
		System.out.println("End Part3...");
		System.out.println();
		*/
		
		
		//Part 5 Experiment
		int sum1 = 0;
		int sum2 = 0;
		System.out.println("Start Part5...");
		for(int i=0; i<50; i++){
			seed += 1000;
			//seed=48000;
			Maze m1=new Maze(101, 101, 30, seed); 
			Maze m2=new Maze(101, 101, 30, seed); 
			
			long start1 = System.currentTimeMillis();
			AStarRepeatedGGreater aStarRGG = new AStarRepeatedGGreater(m1);		
			aStarRGG.findPath();
			long end1 = System.currentTimeMillis();
			double time1 = (end1 - start1) / (double)1000;
			
			//System.out.println();
			//m1.showMaze();
			//System.out.println();
			
			long start2 = System.currentTimeMillis();
			AStarRepeatedGGreaterAdaptive aStarRGGA = new AStarRepeatedGGreaterAdaptive(m2);		
			aStarRGGA.findPath();
			long end2 = System.currentTimeMillis();
			double time2 = (end2 - start2) / (double)1000;
			
			//m2.showMaze();
			
			//Printing Runtime
			//System.out.println(time1+" "+time2);
			
			sum1 +=countComputation;
			sum2 +=countComputation2;
			
			//System.out.println(seed+" "+countRouting+" "+countRouting2);
			System.out.println(countComputation+" "+countComputation2);
			
			
			countRouting = 0;
			countRouting2 = 0;
			countComputation = 0;
			countComputation2 = 0;
		}
		System.out.println();
		System.out.println(sum1/50+" "+ sum2/50);
		System.out.println("End Part5...");
		
		
	}

}
