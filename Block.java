
public class Block {
	public Block parentBlock = null;
	public Block childBlock = null;
	public int xDimension = 0;
	public int yDimension = 0;
	public int gValue = 0;
	public int hValue = 0;
	public int fValue = 0;
	public int blockStatus = 0;
	
	public void printStatus(){
		System.out.print("g:"+gValue+" h:"+hValue+" f:"+fValue);
	}
	
	@Override public String toString() {
		//"#" means block is open
		if(this.blockStatus == 0){
			return "#";
		}
		//"*" means block is closed
		else if(this.blockStatus == 1){
			return "*";
		}
		//"s" is the starting point
		else if(this.blockStatus == 2){
			return "s";
		}
		//"e" means the ending point
		else if(this.blockStatus == 3){
			return "e";
		}
		//"." means it is visited
		else if(this.blockStatus == 4){
			return ".";
		}
		//"-" means it is visited before another search (adaptive A*)
		else if(this.blockStatus == 5){
			return "-";
		}
		else{
			return "error";
		}
	}
}
