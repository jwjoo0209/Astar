import java.util.ArrayList;
import java.util.Collections;

public class AStarRepeatedGGreaterAdaptive {

	ArrayList<Block> openBlocks = new ArrayList<Block>();
	Maze realMaze;
	Maze tempMaze;
	Block currentState;// = maze[maze.startXDimension][maze.startYDimension];
	int countShortestPath=0;
	
	public AStarRepeatedGGreaterAdaptive(Maze m){
		realMaze=m;
		tempMaze= new Maze(m.maxXDimension,m.maxYDimension);
	}
	
	public void findPath(){
		
		while((realMaze.startXDimension != realMaze.endXDimension) || (realMaze.startYDimension!= realMaze.endYDimension)){
			main.countRouting2++;
			openBlocks.clear();
			for(int i=0;i<tempMaze.maxXDimension;i++){
				for(int j=0;j<tempMaze.maxYDimension;j++){
					if(tempMaze.returnBlock(i, j).blockStatus == 0){
						tempMaze.returnBlock(i, j).hValue=0;
					}
					
					if(tempMaze.returnBlock(i, j).blockStatus == 4){
						tempMaze.returnBlock(i, j).hValue=countShortestPath-tempMaze.returnBlock(i, j).gValue;
						tempMaze.returnBlock(i, j).blockStatus=0;
					}
				}
			}
			
			tempMaze.endXDimension=realMaze.endXDimension;
			tempMaze.endYDimension=realMaze.endYDimension;
			tempMaze.startXDimension=realMaze.startXDimension;
			tempMaze.startYDimension=realMaze.startYDimension;
			currentState = tempMaze.returnBlock(tempMaze.startXDimension, tempMaze.startYDimension); 
			currentState.gValue=0;
			
			while((currentState.xDimension != tempMaze.endXDimension) || (currentState.yDimension!= tempMaze.endYDimension)){
				main.countComputation2++;
				addToOpenBlocks();	
				if(openBlocks.size()>0){
					currentState = chooseNextState();					
					if(currentState.blockStatus==0)
						currentState.blockStatus = 4;
				}
				else{
					System.out.println("Error! There is no way we can reach destination!!");
					return;
				}
			}//tempMaze.showMaze();	System.out.println("Tracing A*");	
			
			countShortestPath=0;
			
			while((currentState.xDimension != tempMaze.startXDimension) || (currentState.yDimension!= tempMaze.startYDimension)){
				currentState.parentBlock.childBlock=currentState;
				currentState= currentState.parentBlock;
				countShortestPath++;
			}
			
			while((currentState.xDimension != tempMaze.endXDimension) || (currentState.yDimension!= tempMaze.endYDimension)){
				
				if(realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus==0)
					realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus=4;
				if(realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus==1){
					tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus=1;
					currentState=currentState.parentBlock;
					realMaze.startXDimension = currentState.xDimension;
					realMaze.startYDimension = currentState.yDimension;
					realMaze.returnBlock(realMaze.startXDimension, realMaze.startYDimension).blockStatus=2;
					break;
				}
				currentState= currentState.childBlock;
				//countShortestPath++;
			}		
			if(realMaze.returnBlock(currentState.xDimension, currentState.yDimension).blockStatus==3){
				realMaze.startXDimension = currentState.xDimension;
				realMaze.startYDimension = currentState.yDimension;
				break;
			}//System.out.println();
			//realMaze.showMaze();System.out.println("Tracing Repeated A*");
		}	//System.out.println(currentState.gValue);
		//
	}
	
	public Block chooseNextState() {
		//to get the correct destination we have to find the f value and then if f values are equal, find nearest h value
		int index=0;
		int counter = 0;
		Collections.sort(openBlocks,new fValueComparator());
		Block minFMinGValue = new Block();
		minFMinGValue=openBlocks.get(0);		
		
		while((counter + 1 < openBlocks.size()) && (openBlocks.get(counter).fValue == openBlocks.get(counter+1).fValue)){
			if(openBlocks.get(counter+1) == null)
				break;
			if(openBlocks.get(counter+1).blockStatus == 3){
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
		if(tempMaze.returnBlock(currentState.xDimension - 1 ,  currentState.yDimension) != null
				&& (tempMaze.returnBlock(currentState.xDimension-1,  currentState.yDimension).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension-1,  currentState.yDimension).blockStatus==3)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension)) == false){
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				if(tempMaze.returnBlock(currentState.xDimension-1, currentState.yDimension).hValue==0)
					tempMaze.returnBlock(currentState.xDimension-1, currentState.yDimension).hValue=Math.abs((currentState.xDimension-1) -tempMaze.endXDimension) + Math.abs(currentState.yDimension-tempMaze.endYDimension);				
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).fValue=tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).gValue + tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).hValue;
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension));
				tempMaze.returnBlock(currentState.xDimension - 1, currentState.yDimension).parentBlock=tempMaze.returnBlock(currentState.xDimension , currentState.yDimension);	
			}
		}
		//xDimension+1, yDimension
		if(tempMaze.returnBlock(currentState.xDimension + 1 , currentState.yDimension) != null
				&& (tempMaze.returnBlock(currentState.xDimension+1, currentState.yDimension).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension+1, currentState.yDimension).blockStatus==3)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension)) == false){
				tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				if(tempMaze.returnBlock(currentState.xDimension+1, currentState.yDimension).hValue==0)
					tempMaze.returnBlock(currentState.xDimension+1, currentState.yDimension).hValue=Math.abs((currentState.xDimension+1) -tempMaze.endXDimension) + Math.abs(currentState.yDimension-tempMaze.endYDimension);				
				tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).fValue=tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).gValue + tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension).hValue;				
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension + 1, currentState.yDimension));
				tempMaze.returnBlock(currentState.xDimension + 1,  currentState.yDimension).parentBlock=tempMaze.returnBlock(currentState.xDimension ,  currentState.yDimension);	
			}
		}
		//xDimension, yDimension-1
		if(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1) != null
				&& (tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1).blockStatus==3)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1)) == false){
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				if(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).hValue==0)	
					tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).hValue=Math.abs(currentState.xDimension -tempMaze.endXDimension) + Math.abs((currentState.yDimension-1)-tempMaze.endYDimension);				
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).fValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).gValue + tempMaze.returnBlock(currentState.xDimension, currentState.yDimension-1).hValue;
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension - 1));
				tempMaze.returnBlock(currentState.xDimension , currentState.yDimension - 1).parentBlock=tempMaze.returnBlock(currentState.xDimension , currentState.yDimension);
			}
		}
		//xDimension, yDimension+1
		if(tempMaze.returnBlock(currentState.xDimension,  currentState.yDimension + 1) != null
				&& (tempMaze.returnBlock(currentState.xDimension,  currentState.yDimension + 1).blockStatus==0
				|| tempMaze.returnBlock(currentState.xDimension,  currentState.yDimension + 1).blockStatus==3)){
			if(openBlocks.contains(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1)) == false){
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).gValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension).gValue+1;
				if(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).hValue==0)
					tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).hValue=Math.abs(currentState.xDimension -tempMaze.endXDimension) + Math.abs((currentState.yDimension+1)-tempMaze.endYDimension);								
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).fValue=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).gValue + tempMaze.returnBlock(currentState.xDimension, currentState.yDimension+1).hValue;				
				openBlocks.add(tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1));
				tempMaze.returnBlock(currentState.xDimension, currentState.yDimension + 1).parentBlock=tempMaze.returnBlock(currentState.xDimension, currentState.yDimension);	
			}
		}
		
	}
}