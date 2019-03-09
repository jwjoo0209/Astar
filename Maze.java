import java.util.Random;

public class Maze {
	public Block [][] maze;
	public int maxXDimension=0;
	public int maxYDimension=0;
	
	public int startXDimension = 0;
	public int startYDimension = 0;
	public int endXDimension = 0;
	public int endYDimension = 0;
	
	public Maze(int x, int y){
		maxXDimension = x;
		maxYDimension = y;
		maze = new Block[maxXDimension][maxYDimension]; 
		
		for(int i=0; i<maxXDimension; i++){
			for(int j=0; j<maxYDimension; j++){
				Block b=new Block();
				
				//set dimensions of the block
				b.xDimension = i;
				b.yDimension = j;
				maze[i][j]=b;
			}
		}
	}
	
	public Maze(int x, int y, int probability, int seed){
		maxXDimension = x;
		maxYDimension = y;
		maze = new Block[maxXDimension][maxYDimension]; 
		
		//initialize the maze
		for(int i=0; i<maxXDimension; i++){
			for(int j=0; j<maxYDimension; j++){
				Block b=new Block();
				
				//set dimensions of the block
				b.xDimension = i;
				b.yDimension = j;
				
				//to make sure that each block has a percent of chance to be open or closed
				Random rand;
				if(seed == -1){
					rand = new Random();
				}
				else{
					rand = new Random(seed++);
				}
				int percentClosed = rand.nextInt((100 - 0) + 1) + 0;
				if(percentClosed<probability){
					b.blockStatus=1;
				}
				
				maze[i][j]=b;
			}
		}
		
		//choosing start and end blocks
		do{
			Random xStartRand;
			Random yStartRand;
			Random xEndRand;
			Random yEndRand;
			
			if(seed==-1){
				xStartRand = new Random();
				yStartRand = new Random();
				xEndRand = new Random();
				yEndRand = new Random();
			}
			else{
				xStartRand = new Random(seed++);
				yStartRand = new Random(seed++);
				xEndRand = new Random(seed++);
				yEndRand = new Random(seed++);
			}
			
			//maxXDimension-1 because the array starts from 0
			startXDimension = xStartRand.nextInt(((maxXDimension-1) - 0) + 1) + 0;
			startYDimension = yStartRand.nextInt(((maxYDimension-1) - 0) + 1) + 0;
			endXDimension = xEndRand.nextInt(((maxXDimension-1) - 0) + 1) + 0;
			endYDimension= yEndRand.nextInt(((maxYDimension-1) - 0) + 1) + 0;
		} while(((startXDimension==endXDimension) && (startYDimension==endYDimension)) 
				|| maze[startXDimension][startYDimension].toString() != "#" || maze[endXDimension][endYDimension].toString() != "#" );
		
		maze[startXDimension][startYDimension].blockStatus=2;
		maze[endXDimension][endYDimension].blockStatus=3;
		
		maze[startXDimension][startYDimension].gValue=0;
		maze[startXDimension][startYDimension].hValue = Math.abs(startXDimension-endXDimension) + Math.abs(startYDimension-endYDimension);
		
		maze[endXDimension][endYDimension].hValue = Math.abs(startXDimension-endXDimension) + Math.abs(startYDimension-endYDimension);

	}
	
	public Block returnBlock(int x, int y){
		try {
			return maze[x][y];
		}
		catch(ArrayIndexOutOfBoundsException exception) {
		    return null;
		}		
	}

	public void showMaze(){
		for(int i=0;i<maxXDimension;i++){
			for(int j=0;j<maxYDimension;j++){
				System.out.print(maze[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public void showMazeWithColRow(){
		for(int i=0;i<maxXDimension;i++){
			System.out.print("\t"+i);
		}
		System.out.println();
		for(int i=0;i<maxXDimension;i++){
			System.out.print(i + "\t");
			for(int j=0;j<maxYDimension;j++){
				System.out.print(maze[i][j]+"\t");
			}
			System.out.println();
		}
	}
	
	public void showMazeWithNumbers(){
		for(int i=0;i<maxXDimension;i++){
			System.out.print("\t"+i);
		}
		System.out.println();
		for(int i=0;i<maxXDimension;i++){
			System.out.print(i + "\t");
			for(int j=0;j<maxYDimension;j++){
				System.out.print(maze[i][j]+"["+i+", "+j+"]="+"g:"+maze[i][j].gValue
						+"h:"+maze[i][j].hValue+"f:"+maze[i][j].fValue+"\t");
			}
			System.out.println();
		}
	}

}