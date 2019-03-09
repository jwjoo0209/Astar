import java.util.ArrayList;
import java.util.Collections;

public class AStarRepeatedGGreaterBackward {

	ArrayList<Block> openBlocks = new ArrayList<Block>();
	Maze realMaze;
	Maze tempMaze;
	Block currentState;// = maze[maze.startXDimension][maze.startYDimension];
	
	public AStarRepeatedGGreaterBackward(Maze m){
		realMaze=m;
		tempMaze= new Maze(m.maxXDimension,m.maxYDimension);
	}
	
	public void findPath(){
		
		while((realMaze.endXDimension != realMaze.startXDimension) || (realMaze.endYDimension!= realMaze.startYDimension)){
			main.countRouting2++;
			openBlocks.clear();
			for(int i=0; i<tempMaze.maxXDimension; i++){
				for(int j=0; j<tempMaze.maxYDimension; j++){
					if(tempMaze.returnBlock(i, j).blockStatus == 4){
						tempMaze.returnBlock(i, j).blockStatus=0;
					}
				}
			}
			
			tempMaze.endXDimension=realMaze.endXDimension;
			tempMaze.endYDimension=realMaze.endYDimension;
			tempMaze.startXDimension=realMaze.startXDimension;
			tempMaze.startYDimension=realMaze.startYDimension;
			currentState = tempMaze.returnBlock(tempMaze.endXDimension, tempMaze.endYDimension); 
			currentState.gValue=0;
			
			while((currentState.xDimension != tempMaze.startXDimension) || (currentState.yDimension!= tempMaze.startYDimension)){
				main.countComputation2++;
				addToOpenBlocks();	
				if(openBlocks.size()>0){
					currentState = chooseNextState();					
					if(currentState.blockStatus==0)
						currentState.blockStatus = 4;
				}
				else{
					System.out.println("Error! There is no way to reach destination!!");
					return;
				}
			}//tempMaze.showMaze();	System.out.println("Tracing A*");
			
			while((currentState.xDimension != tempMaze.endXDimension) || (currentState.yDimension!= tempMaze.endYDimension)){
				currentState.parentBlock.childBlock = currentState;
				currentState = currentState.parentBlock;
				//countShortestPath++;
			}
			while((currentState.xDimension != tempMaze.startXDimension) || (currentState.yDimension!= tempMaze.startYDimension)){
				
				if(realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus==0)
					realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus=4;
				if(realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus==1){
					tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus=1;
					currentState=currentState.parentBlock;
					realMaze.endXDimension = currentState.xDimension;
					realMaze.endYDimension = currentState.yDimension;
					realMaze.returnBlock(realMaze.endXDimension, realMaze.endYDimension).blockStatus=3;
					break;
				}
				
				currentState= currentState.childBlock;
				//countShortestPath++;
			}
			if(realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus==2){
				realMaze.endXDimension = currentState.xDimension;
				realMaze.endYDimension = currentState.yDimension;
				break;
			}
			//realMaze.showMaze();System.out.println("Tracing Repeated A*");
		}	//System.out.println(currentState.gValue);
		//
	}
	
	public Block chooseNextState() {
		//to get the correct destination we have to find the f value and then if f values are equal, find the nearest h value
		int index=0;
		int counter = 0;
		Collections.sort(openBlocks,new fValueComparator());
		Block minFMinGValue = new Block();
		minFMinGValue=openBlocks.get(0);		
		
		while((counter + 1 < openBlocks.size()) && (openBlocks.get(counter).fValue == openBlocks.get(counter+1).fValue)){
			if(openBlocks.get(counter+1) == null)
				break;
			//if blockStatus is start point
			if(openBlocks.get(counter+1).blockStatus == 2){
				minFMinGValue = openBlocks.get(counter+1);
				index=counter+1;
				break;
			}
			if(minFMinGValue.gValue < openBlocks.get(counter+1).gValue){
				minFMinGValue = openBlocks.get(counter+1);
				index=counter+1;
				
			}
			counter++;
		}
		
		openBlocks.remove(index);
		return minFMinGValue;		
		
	}
	
	private void addToOpenBlocks() {
		//xDimension-1, yDimension
		if(tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension) != null
				&& (tempMaze.returnBlock(currentState.xDimension-1, currentState.yDimension).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension-1, currentState.yDimension).blockStatus==2)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension)) == false){
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).hValue=Math.abs((currentState.xDimension - 1)-tempMaze.startXDimension) + Math.abs(currentState.yDimension-tempMaze.startYDimension);				
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).fValue=tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).gValue + tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).hValue;
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension));
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).parentBlock=tempMaze.returnBlock(currentState.xDimension , currentState.yDimension);	
			}
		}
		//xDimension+1, yDimension
		if(tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension) != null
				&& (tempMaze.returnBlock(currentState.xDimension+1, currentState.yDimension).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension+1, currentState.yDimension).blockStatus==2)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension)) == false){
				tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).hValue=Math.abs((currentState.xDimension + 1)-tempMaze.startXDimension) + Math.abs(currentState.yDimension-tempMaze.startYDimension);				
				tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).fValue=tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).gValue + tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).hValue;				
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension));
				tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).parentBlock=tempMaze.returnBlock(currentState.xDimension ,  currentState.yDimension);	
			}
		}
		//xDimension, yDimension-1
		if(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1) != null
				&& (tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1).blockStatus==2)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1)) == false){
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension -1).hValue=Math.abs(currentState.xDimension -tempMaze.startXDimension) + Math.abs((currentState.yDimension-1)-tempMaze.startYDimension);				
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).fValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).gValue + tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).hValue;
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1));
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1).parentBlock=tempMaze.returnBlock(currentState.xDimension , currentState.yDimension);
			}
		}
		//xDimension, yDimension+1
		if(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1) != null
				&& (tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1).blockStatus==2)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1)) == false){
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension +1).hValue=Math.abs(currentState.xDimension -tempMaze.startXDimension) + Math.abs((currentState.yDimension+1)-tempMaze.startYDimension);								
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).fValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).gValue + tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).hValue;				
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1));
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1).parentBlock=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension);	
			}
		}
		
	}
}