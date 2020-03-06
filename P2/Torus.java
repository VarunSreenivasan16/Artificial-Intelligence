
import java.util.*;

/**
 * Blueprint for the State instance
 * 
 * @author Varun
 *
 */
class State {
	
	/*board, parentPt, and depth are instance variables corresponding to the
	 *       State instance 
	 */
	int[] board;
	State parentPt;
	int depth;

	/**
	 * Constructor for State
	 * @param arr - representation of the puzzle in a 1-D array
	 */
	public State(int[] arr) {
		
		//initialize all variables
		this.board = Arrays.copyOf(arr, arr.length);
		this.parentPt = null;
		this.depth = 0;
	}
	
	
	
	/**
	 * Generates the successors for the corresponding State instance
	 * @return the successors for a State
	 */
	public State[] getSuccessors() {
		
		// TO DO: get all four successors and return them in sorted order
		
		//stores the successor states
		State[] successors = new State[4];
		
		//tracks position of zero
		int zeroLocation = 0;
		
		//determines position of zero
		for(int i = 0; i < this.board.length; i++)
		{
			if(this.board[i] == 0)
			{
				zeroLocation = i;
			}
		}
		
		 //ArrayLists that will be used to sort
		 ArrayList<String> myList = new ArrayList<String>();
		 ArrayList<String> tempSort = new ArrayList<String>();
		 
		 //Instances corresponding to the 4 successor states
		 State state;
		 State state1;
		 State state2;
		 State state3;
		 
		 //check if can't move up in the puzzle
		if(zeroLocation < 3)
		{
			//initialize state 
			//add string representation of board configuration
			state = new State(successorGenerator(zeroLocation, this.board , 6));
			myList.add(Arrays.toString(state.board));
			
		}
		else
		{
			//initialize state 
			//add string representation of board configuration
			state = new State(successorGenerator(zeroLocation, this.board, -3));
			myList.add(Arrays.toString(state.board));
		
		}
		
		//check if can't move down in puzzle
		if(zeroLocation > 5)
		{
			//initialize state1 
			//add string representation of board configuration
			state1 = new State(successorGenerator(zeroLocation, this.board, -6 ));
			myList.add(Arrays.toString(state1.board));
			
		}
		else
		{
			//initialize state1 
			//add string representation of board configuration
			state1 = new State(successorGenerator(zeroLocation, this.board, 3));
			myList.add(Arrays.toString(state1.board));
			
		}
		
		//check if can't move left in puzzle
		if((zeroLocation % 3) == 0)
		{
			//initialize state2
			//add string representation of board configuration
			state2 = new State(successorGenerator(zeroLocation, this.board, 2));
			myList.add(Arrays.toString(state2.board));
			
		}
		else
		{
			//initialize state2
			//add string representation of board configuration
			state2 = new State(successorGenerator(zeroLocation, this.board, -1));
			myList.add(Arrays.toString(state2.board));
			
		}
		
		//check if can't move right in puzzle
		if((zeroLocation % 3) == 2)
		{
			//initialize state3
			//add string representation of board configuration
			state3 = new State(successorGenerator(zeroLocation, this.board, -2));
			myList.add(Arrays.toString(state3.board));
			
		}
		else
		{
			//initialize state3
			//add string representation of board configuration
			state3 = new State(successorGenerator(zeroLocation, this.board, 1));
			myList.add(Arrays.toString(state3.board));
			
		}
		
		
		//add elements to the list for sorting
		for(int i = 0; i < myList.size(); i++)
		{
			tempSort.add(myList.get(i));
		}
		
		//sorts the list
		Collections.sort(tempSort);
		
		//compare unsorted list and sorted list to determine the State
		for(int i = 0; i < tempSort.size(); i++)
		{
			for(int j = 0; j < tempSort.size(); j++ )
			{
				String value1 = myList.get(i);
				String value2 = tempSort.get(j);
				
				//check if String values being compared are same
				if(value1.equals(value2))
				{
					//assign the State instances in the correct order to the array
					if(i == 0)
					{
						successors[j] = state;
					}
					else if(i == 1)
					{
						successors[j] = state1;
					}
					else if(i == 2)
					{
						successors[j] = state2;
					}
					else if(i == 3)
					{
						successors[j] = state3;
					}
				}
			}
		}
		
		return successors;
	}
	
	
	/**
	 * Helper method to help generate the successor states
	 * @param zeroPos - location of zero
	 * @param array - the array corresponding to board configuration
	 * @param move - the shift applied on the empty square
	 * @return
	 */
	private int [] successorGenerator(int zeroPos, int [] array, int move)
	{
		//create and initialize new array
		int [] temp = new int[9];
		for(int i = 0; i < temp.length; i++ )
		{
			temp[i] = array[i];
		}
		
		//shift the empty tile and square
		temp[zeroPos] = temp[zeroPos + move];
		temp[zeroPos + move] = 0;
		
		return temp;
		
	}
	
	/**
	 * prints the states and other features if required
	 * 
	 * @param option - the option being worked on
	 */
	public void printState(int option) {
		
		// TO DO: print a torus State based on option (flag)
		
		//check if option isn't 3
		if(option != 3)
		{
			System.out.println(getBoard());
		}
		
		//perform specific print operations if option is 3
		else if(option == 3)
		{
			System.out.print(getBoard() + " parent ");
			
			if(parentPt == null)
			{
				for(int i = 0; i < 9; i++)
				{
					System.out.print("0 ");
				}
				
				System.out.println("");
			}
			else
			{
				System.out.println(parentPt.getBoard());
			}
		}
		

	}

	/**
	 * 
	 * @return the board configuration
	 */
	public String getBoard() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 9; i++) {
			builder.append(this.board[i]).append(" ");
		}
		return builder.toString().trim();
	}

	public boolean isGoalState() {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != (i + 1) % 9)
				return false;
		}
		return true;
	}

	public boolean equals(State src) {
		for (int i = 0; i < 9; i++) {
			if (this.board[i] != src.board[i])
				return false;
		}
		return true;
	}
	
	
	
}
	

	
/**
 * The main class used to run the program
 * @author Varun
 *
 */
public class Torus {
	
	/**
	 * Executes the program
	 * 
	 * @param args - initial board configuration, option, and cutoff
	 */
	public static void main(String args[]) {
		
		//Parse all the arguments
		if (args.length < 10) {
			System.out.println("Invalid Input");
			return;
		}
		int flag = Integer.valueOf(args[0]);
		int[] board = new int[9];
		for (int i = 0; i < 9; i++) {
			board[i] = Integer.valueOf(args[i + 1]);
		}
		int option = flag / 100;
		int cutoff = flag % 100;
		if (option == 1) {
			State init = new State(board);
			State[] successors = init.getSuccessors();
			for (State successor : successors) {
				successor.printState(option);
			}
		} else {
			State init = new State(board);
			Stack<State> stack = new Stack<>();
			List<State> prefix = new ArrayList<>();
			int goalChecked = 0;
			boolean goal = false;
			int maxStackSize = Integer.MIN_VALUE;

			// needed for Part E
			while (true) {				
				stack.push(init);
				
				while (!stack.isEmpty()) {
					//TO DO: perform iterative deepening; implement prefix list
					
					//determine max stack size
					if(stack.size() > maxStackSize)
					{
						maxStackSize = stack.size();
					}
					
					//pop from stack
					State temp = stack.pop();
					
					//tracks parent
					int parentPos = 0;
					
					//determines parent position
					for(int i = 0; i < prefix.size(); i++)
					{
						if( prefix.get(i).equals(temp.parentPt))
						{
							parentPos = i;
							break;
						}
					}
					
					
					//removes states after parent p
					while(prefix.size() > parentPos + 1)
					{
						prefix.remove(parentPos + 1);
						
					}
					
					//adds the popped state instance
					prefix.add(temp);
					
					//print state if not option 4 or 5
					if(option != 4 && option !=5)
					{
					
						temp.printState(option);
					}
					
					//increment goalChecked
					goalChecked++;
					
					//prints prefix list for option 4
					//terminates if goal found before cutoff
					if(option == 4)
					{
						if(goalChecked == cutoff + 1)
						{
							for(int m = 0; m < prefix.size(); m++)
							{
								prefix.get(m).printState(option);;
							}
						}
					}
					
					//check if goal found
					if(temp.isGoalState())
					{
						goal = true;
						
						//print out path to goal state
						if(option == 5)
						{
							for(int i = 0; i < prefix.size(); i++)
							{	
								prefix.get(i).printState(option);
							}
						}
						
						break;
					}
					
					
					//generate successors
					State [] successors = temp.getSuccessors();
					
					
					for(int i = 0; i < successors.length; i++)
					{
						boolean contains = false;
						
						//check if successors are already in prefix list
						for(int j = 0; j < prefix.size(); j++)
						{
							if(prefix.get(j).equals(successors[i]))
							{
								contains = true;
								break;
							}
						}
						
						
						if(!contains)
						{
							//set parent and depth
							successors[i].parentPt = temp;
							successors[i].depth = temp.depth + 1;
						
							//check if depth less than cutoff
							if(successors[i].depth <= cutoff )
							{
								stack.push(successors[i]);
							}
						
						}
						
					}
					
					
				}
				
				//break if option is 5
				if (option != 5)
					break;
				
				//print out details if goal state is reached
				if(goal)
				{
					System.out.println("Goal-check " + goalChecked);
					System.out.println("Max-stack-size " + maxStackSize);
					break;
				}
				
				//increment cutoff depth
				cutoff++;
				
				//re-initialize stack and prefix list
				stack =  new Stack<>();
				prefix = new ArrayList<>();
				
				
				
				//TO DO: perform the necessary steps to start a new iteration
			        //       for Part E

			}
		}
	}
}
