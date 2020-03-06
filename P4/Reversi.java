import java.util.*;
 

/**
 * State class is the blueprint for the State object
 * @author Varun
 *
 */
class State {
	
	//board contains all the values in the board game
    char[] board;
    
   /**
    * constructor for State instance
    * @param arr the board containing all game values
    */
    public State(char[] arr) {
        this.board = Arrays.copyOf(arr, arr.length);
    }
    
    /**
     * provides game theoretic value of board
     * @return  game theoretic value
     */
    public int getScore() {

        // TO DO: return game theoretic value of the board

    	int score = 0;
    	
    	int darkCount = 0;
    	int lightCount = 0;
    	
    	//count dark disks and light disks
    	for(int i = 0; i < this.board.length; i++)
    	{
    		if(this.board[i] == '1')
    		{
    			darkCount++;
    		}
    		else if(this.board[i] == '2')
    		{
    			lightCount++;
    		}
    	}
    	
    	//return 1 if more dark disks
    	if(darkCount > lightCount)
    	{
    		score = 1;
    	}
    	//return -1 if more light disks
    	else if(darkCount < lightCount)
    	{
    		score = -1;
    	}
    	//return 0 if tie
    	else
    	{
    		score = 0;
    	}
    	
        return score;
    }
    
    /**
     * check if terminal State
     * @return true if terminal state, false otherwise
     */
    public boolean isTerminal() {
    	
        // TO DO: determine if the board is a terminal node or not and return boolean
    	
    	boolean is_terminal = false;
    	
    	//no.of successors for player 1
    	int player1Size = getSuccessors('1').length;
    	
    	//no.of successors for player 2
    	int player2Size = getSuccessors('2').length;
    	
    	//if no successors for player 1 and 2, set is_terminal to true
    	if(player1Size == 0 && player2Size == 0)
    	{
    		is_terminal = true;
    	}

        return is_terminal;
    }

    /**
     * generates successors
     * @param player
     * @return array of successors
     */
    public State[] getSuccessors(char player) {

        // TO DO: get all successors and return them in proper order
    	
    	//succ contains the successors
    	ArrayList<State> succ = new ArrayList<State>();
    	
    	// temp is a 2-D array used to represent the game in 2-D
    	char [][] temp = new char[4][4];
    	int counter = 0;
    	
    	//place all board values in the 2-D array (make operations easier)
    	for(int i = 0; i < temp.length; i++)
    	{
    		for(int j = 0; j < temp[i].length; j++)
    		{
    			temp[i][j] = this.board[counter];
    			counter++;
    		}
    		
    	}
    	
    	//1-D array containing board values (used to generate successor states)
    	char [] tempBoard = new char[16];
    	
    	//place all board values in tempBoard
    	for(int i = 0; i < tempBoard.length; i++)
    	{
    		tempBoard[i] = this.board[i];
    	}
    	
    	//instance of State
    	State tempState = null;
    	
    	//records opponent game value
    	char opponentVal = '1';
    	
    	//checks if player is 1 or 2 and appropriately sets opponent value
    	if(player == '1')
    	{
    		opponentVal = '2';
    	}
    	
    	//loop through the 2-D board
    	for(int i = 0; i < temp.length; i++)
    	{
    		for(int j = 0; j < temp[i].length; j++)
    		{
    			//check if empty disk
    			if(temp[i][j] == '0')
    			{
    				//boolean variables that will be used to generate successors
    				boolean change = false;
    				boolean temp1 = false;
    				boolean temp1D = false;
    				boolean temp2 = false;
    				boolean temp2D = false;
    				boolean temp3 = false;
    				boolean temp3D = false;
    				
    				
    				//check if i > 1 and j > 1, appropriately check for successors
    				if(i > 1 && j > 1)
    				{
    					//vertical case 1
    					if(temp[i-1][j] == opponentVal && temp[i-2][j] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4)+ j] = player;	
							change = true;
							temp1 = true;
						}
    					//vertical case 2
    					else if(i == 3 && temp[i-1][j] == opponentVal && 
    							temp[i-2][j] == opponentVal
    							&& temp[i-3][j] == player )
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4)+ j] = player;	
							tempBoard[((i-2)*4)+ j] = player;
							change = true;
							temp1D = true;
    					}
    					
    					//horizontal case 1
    					if(temp[i][j-1] == opponentVal && temp[i][j-2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i)*4) + (j-1)] = player;
							change = true;
							temp2 = true;
							
						}
    					//horizontal case 2
    					else if(j == 3 && temp[i][j-1] == opponentVal && 
    							temp[i][j-2] == opponentVal && temp[i][j-3] == player)
    					{
    						tempBoard[(i*4) + j] = player;
    						tempBoard[i*4 + (j-1)] = player;
    						tempBoard[i*4 + (j-2)] = player;
    						change = true;
							temp2D = true;
    					}
    					
    					//diagonal case 1
    					if(temp[i-1][j-1] == opponentVal && temp[i-2][j-2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4) + (j-1)] = player;
							change = true;
							temp3 = true;
							
						}
    					
    					//diagonal case 2
    					else if(i == 3 && j == 3 && 
    							temp[i-1][j-1] == opponentVal && 
    							temp[i-2][j-2] == opponentVal
    							&& temp[i-3][j-3] == player)
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4) + (j-1)] = player;
							tempBoard[((i-2)*4) + (j-2)] = player;
							change = true;
							temp3D = true;
    					}
    					
    					//check if any change to tempBoard as been made
    					//if change has been made generate successors State
    					//and reset tempBoard to original values
    					if(change)
    					{
    						//generate the successor State if change occurred
	    					tempState = new State(tempBoard);
	    					succ.add(tempState);
	    					
	    					//check if change caused by vertical case 1
	    					if(temp1)
	    					{
	    						tempBoard[((i-1)*4) +j] = opponentVal;
	    					}
	    					//check if change caused by vertical case 2
	    					else if(temp1D)
	    					{
	    						tempBoard[((i-1)*4)+ j] = opponentVal;	
								tempBoard[((i-2)*4)+ j] = opponentVal;
	    					}
	    					
	    					//check if change caused by horizontal case 1
	    					if(temp2)
	    					{
	    						tempBoard[((i)*4) + (j-1)] = opponentVal;
	    					}
	    					
	    					//check if change caused by horizontal case 2
	    					else if(temp2D)
	    					{
	    						tempBoard[i*4 + (j-1)] = opponentVal;
	    						tempBoard[i*4 + (j-2)] = opponentVal;
	    					}
	    					
	    					//check if change caused by diagonal case 1
	    					if(temp3)
	    					{
	    						tempBoard[((i-1)*4) + (j-1)] = opponentVal;
	    					}
	    					
	    					//check if change caused by diagonal case 2
	    					else if(temp3D)
	    					{
	    						tempBoard[((i-1)*4) + (j-1)] = opponentVal;
								tempBoard[((i-2)*4) + (j-2)] = opponentVal;
	    					}
	    					
							tempBoard[((i)*4) + j] = '0';
    					}
						
    				}
    				
    				//check if i > 1 and j <= 1, appropriately check for successors
    				else if(i > 1 && j <= 1)
    				{
    					//vertical case 1
    					if(temp[i-1][j] == opponentVal && temp[i-2][j] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4) + j] = player;	
							change = true;
							temp1 = true;
						}
    					
    					//vertical case 2
    					else if(i == 3 && temp[i-1][j] == opponentVal && 
    							temp[i-2][j] == opponentVal
    							&& temp[i-3][j] == player)
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4)+ j] = player;	
							tempBoard[((i-2)*4)+ j] = player;
							change = true;
							temp1D = true;
    					}
    					
    					//horizontal case 1
    					if(temp[i][j+1] == opponentVal && temp[i][j+2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i)*4) + (j+1)] = player;
							change = true;
							temp2 = true;
							
						}
    					
    					//horizontal case 2
    					else if(j == 0 && temp[i][j+1] == opponentVal && 
    							temp[i][j+2] == opponentVal && temp[i][j+3] == player )
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i)*4) + (j+1)] = player;
							tempBoard[((i)*4) + (j+2)] = player;
							change = true;
							temp2D = true;
    					}
    					
    					//diagonal case 1
    					if(temp[i-1][j+1] == opponentVal && temp[i-2][j+2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4) + (j+1)] = player;
							change = true;
							temp3 = true;
	
						}
    					
    					//diagonal case 2
    					else if(i == 3 && j == 0 && temp[i-1][j+1] == opponentVal &&
    							temp[i-2][j+2] == opponentVal && temp[i-3][j+3] == player )
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i-1)*4) + (j+1)] = player;
							tempBoard[((i-2)*4) + (j+2)] = player;
							change = true;
							temp3D = true;
    					}
    					
    					//check if any change to tempBoard as been made
    					//if change has been made generate successors State
    					//and reset tempBoard to original values
    					if(change)
    					{
	    					tempState = new State(tempBoard);
	    					succ.add(tempState);
	    					
	    					//vertical case 1
	    					if(temp1)
	    					{
	    						tempBoard[((i-1)*4) + j] = opponentVal;
	    					}
	    					
	    					//vertical case 2
	    					else if(temp1D)
	    					{
	    						tempBoard[((i-1)*4) + j] = opponentVal;
	    						tempBoard[((i-2)*4) + j] = opponentVal;
	    					}
	    					
	    					//horizontal case 1
	    					if(temp2)
	    					{
	    						tempBoard[((i)*4) + (j+1)] = opponentVal;
	    					}
	    					
	    					//horizontal case 2
	    					else if(temp2D)
	    					{
	    						tempBoard[((i)*4) + (j+1)] = opponentVal;
	    						tempBoard[((i)*4) + (j+2)] = opponentVal;
	    					}
	    					
	    					//diagonal case 1
	    					if(temp3)
	    					{
	    						tempBoard[((i-1)*4) + (j+1)] = opponentVal;
	    					}
	    					
	    					//diagonal case 2
	    					else if(temp3D)
	    					{
	    						tempBoard[((i-1)*4) + (j+1)] = opponentVal;
	    						tempBoard[((i-2)*4) + (j+2)] = opponentVal;
	    					}
	    					
	    					tempBoard[(i*4) + j] = '0';
    					}
    					
    				}
    				
    				//check if i <= 1 and j > 1, appropriately check for successors
    				else if(i <= 1 && j > 1)
    				{
    					//vertical case 1
    					if(temp[i+1][j] == opponentVal && temp[i+2][j] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i+1)*4) + (j)] = player;
							change = true;
							temp1 = true;
							
						}
    					
    					//vertical case 2
    					else if(i == 0 && temp[i+1][j] == opponentVal && 
    							temp[i+2][j] == opponentVal && temp[i+3][j] == player)
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i+1)*4) + (j)] = player;
							tempBoard[((i+2)*4) + (j)] = player;
							change = true;
							temp1D = true;
    					}
    					
    					//horizontal case 1
    					if(temp[i][j-1] == opponentVal && temp[i][j-2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i)*4) + (j-1)] = player;
							change = true;
							temp2 = true;
						}
    					
    					//horizontal case 2
    					else if(j == 3 && temp[i][j-1] == opponentVal &&
    							temp[i][j-2] == opponentVal && temp[i][j-3] == player)
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i)*4) + (j-1)] = player;
							tempBoard[((i)*4) + (j-2)] = player;
							change = true;
							temp2D = true;
    					}
    					
    					//diagonal case 1
    					if(temp[i+1][j-1] == opponentVal && temp[i+2][j-2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i+1)*4) + (j-1)] = player;
							change = true;
							temp3 = true;
							
						}
    					
    					//diagonal case 2
    					else if(i == 0 && j == 3 && temp[i+1][j-1] == opponentVal && 
    							temp[i+2][j-2] == opponentVal && temp[i+3][j-3] == player)
    					{
    						tempBoard[(i*4) + j] = player;
							tempBoard[((i+1)*4) + (j-1)] = player;
							tempBoard[((i+2)*4) + (j-2)] = player;
							change = true;
							temp3D = true;
    					}
    					
    					
    					if(change)
    					{
	    					tempState = new State(tempBoard);
							succ.add(tempState);
							
							if(temp1)
							{
								tempBoard[((i+1)*4) + (j)] = opponentVal;
							}
							else if(temp1D)
							{
								tempBoard[((i+1)*4) + (j)] = opponentVal;
								tempBoard[((i+2)*4) + (j)] = opponentVal;
							}
							if(temp2)
							{
								tempBoard[((i)*4) + (j-1)] = opponentVal;
							}
							else if(temp2D)
							{
								tempBoard[((i)*4) + (j-1)] = opponentVal;
								tempBoard[((i)*4) + (j-2)] = opponentVal;
							}
							if(temp3)
							{
								tempBoard[((i+1)*4) + (j-1)] = opponentVal;
							}
							else if(temp3D)
							{
								tempBoard[((i+1)*4) + (j-1)] = opponentVal;
								tempBoard[((i+2)*4) + (j-2)] = opponentVal;
							}
							tempBoard[(i*4) + j] = '0';
    					}
    					
    		
    				}
    				
    				//check if i <= 1 and j <= 1, appropriately check for successors
    				else if(i <= 1 && j <= 1)
    				{
    					//vertical case 1
    					if(temp[i+1][j] == opponentVal && temp[i+2][j] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i+1)*4) + (j)] = player;
							change = true;
							temp1 = true;
							
						}
    					
    					//vertical case 2
    					else if(i == 0 && temp[i+1][j] == opponentVal && 
								temp[i+2][j] == opponentVal & temp[i+3][j] == player )
						{
							tempBoard[(i*4) + j] = player;
    						tempBoard[(i+1)*4 + j] = player;
    						tempBoard[(i+2)*4 + j] = player;
    						change = true;
							temp1D = true;
						}
    					
    					//horizontal case 1
    					if(temp[i][j+1] == opponentVal && temp[i][j+2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i)*4) + (j+1)] = player;
							change = true;
							temp2 = true;
							
						}
    					
    					//horizontal case 2
    					else if(j == 0 && temp[i][j+1] == opponentVal && temp[i][j+2] == opponentVal
    							&& temp[i][j+3] == player)
    					{
    						tempBoard[(i*4) + j] = player;
    						tempBoard[i*4 + (j+1)] = player;
    						tempBoard[i*4 + (j+2)] = player;
    						change = true;
							temp2D = true;
    					}
    					
    					//diagonal case 1
    					if(temp[i+1][j+1] == opponentVal && temp[i+2][j+2] == player)
						{
							tempBoard[(i*4) + j] = player;
							tempBoard[((i+1)*4) + (j+1)] = player;
							change = true;
							temp3 = true;
							
						}
    					
    					//diagonal case 2
    					else if(i == 0 && j == 0 && temp[i+1][j+1] == opponentVal && temp[i+2][j+2] == opponentVal &&
    							temp[i+3][j+3] == player)
    					{
    						tempBoard[(i*4) + j] = player;
    						tempBoard[(i+1)*4 + (j+1)] = player;
    						tempBoard[(i+2)*4 + (j+2)] = player;
    						change = true;
							temp3D = true;
    					}
    					
    					if(change)
    					{
	    					tempState = new State(tempBoard);
							succ.add(tempState);
							
							if(temp1)
							{
								tempBoard[((i+1)*4) + (j)] = opponentVal;
							}
							else if(temp1D)
							{
								tempBoard[((i+1)*4) + (j)] = opponentVal;
								tempBoard[((i+2)*4) + (j)] = opponentVal;
							}
							if(temp2)
							{
								tempBoard[((i)*4) + (j+1)] = opponentVal;
							}
							else if(temp2D)
							{
								tempBoard[((i)*4) + (j+1)] = opponentVal;
								tempBoard[((i)*4) + (j+2)] = opponentVal;
							}
							if(temp3)
							{
								tempBoard[((i+1)*4) + (j+1)] = opponentVal;
							}
							else if(temp3D)
							{
								tempBoard[((i+1)*4) + (j+1)] = opponentVal;
								tempBoard[((i+2)*4) + (j+2)] = opponentVal;
							}
							
							tempBoard[(i*4) + j] = '0';
							
    					}
    					
    				}
    				
    				
    					
    				
    			}
    		}
    		
    	}
    	
    	
    	//store all successors in an array
    	State [] successors = new State[succ.size()];
    	
    	for(int i = 0; i < succ.size(); i++)
    	{
    		successors[i] = succ.get(i);
    	}
    	

        return successors;
    }
    
    /**
     * print the states depending on the option
     * @param option - the option that is chosen
     * @param player - max player or min player
     */
    public void printState(int option, char player) {

        // TO DO: print a State based on option (flag)
    	
    	//check if option is 1
    	if(option == 1)
    	{
    		//check if not terminal state
    		if(!isTerminal())
    		{
    			//generate successors
    			State [] tempArray = getSuccessors(player);
    			
    			//if no successors print board
    			if(tempArray.length == 0)
    			{
    				System.out.println(this.getBoard());
    			}
    			else
    			{
	    			//print successors
	    			for(int i = 0; i < tempArray.length; i++)
	    			{
	    				System.out.println(tempArray[i].getBoard());
	    			}
    			}
    			
    		}
    	}
    	
    	//check if option is 2
    	else if(option == 2)
    	{
    		//if not terminal print out non-terminal
    		if(!isTerminal())
    		{
    			System.out.println("non-terminal");
    		}
    		else
    		{
    			//print game theoretic value
    			System.out.println(getScore());
    		}
    	}
    	
    	//check if option is 3
    	else if(option == 3)
    	{
    		//run minimax
    		System.out.println(Minimax.run(this, player));
    		System.out.println(Minimax.statesVisited);
    	}
    	//check if option 4 or 6
    	else if(option == 4 || option == 6)
    	{
    		
    		//generate successors for state
    		State [] successors = this.getSuccessors(player);
    		
    		//store the successor values in the arraylist
    		ArrayList<Integer> vals = new ArrayList<Integer>();
    		
    		//iterate through successors
    		for(int i = 0; i < successors.length; i++)
    		{
    			//check if option is 4
    			if(option == 4)
    			{
    				//check if player is 1
	    			if(player == '1')
	    			{
	    				//run minimax for min successor nodes
	    				vals.add(Minimax.run(successors[i], '2'));
	    			}
	    			else
	    			{
	    				//run minimax for max successor nodes
	    				vals.add(Minimax.run(successors[i], '1'));
	    			}
    			}
    			//check if option is 6
    			else if(option == 6)
    			{
    				//check if player is 1
    				if(player == '1')
	    			{
    					//run minimax with pruning for min successor nodes
	    				vals.add(Minimax.run_with_pruning(successors[i], '2'));
	    			}
	    			else
	    			{
	    				//run minimax with pruning for max successor nodes
	    				vals.add(Minimax.run_with_pruning(successors[i], '1'));
	    			}
    			}
    		}
    		
    		
    		String optimalMove = "";
    		
    		//if no successors and not terminal optimal move is current state
    		if(vals.size() == 0 )
    		{
    			if(!this.isTerminal())
    			{
    				optimalMove = this.getBoard();
    				System.out.println(optimalMove);
    			}
    			
    		}
    		else
    		{
    			int negSize = 0;
        		int posSize = 0;
        		int zeroSize = 0;
        		
	    		//check if player is 1
	    		if(player == '1')
	    		{
	    			//iterate through all min successor nodes
	    			for(int i = 0; i < vals.size(); i++)
	    			{
	    				//if value of successor is 1 and 1 hasn't been seen before
	    				if(vals.get(i) == 1 && posSize == 0)
	    				{
	    					//choose as optimal move
	    					optimalMove = successors[i].getBoard();
	    					break;
	    				}
	    				//if value is 0
	    				else if(vals.get(i) == 0 && zeroSize == 0)
	    				{
	    					//becomes optimal move if 1 hasn't been seen 
	    					optimalMove = successors[i].getBoard();
	    					zeroSize = 1;
	    					negSize = 1;
	    				}
	    				//if value is -1
	    				else if(vals.get(i) == -1 && negSize == 0)
	    				{
	    					//set as optimal move if 0 or 1 haven't been seen
	    					optimalMove = successors[i].getBoard();
	    					negSize = 1;
	    				}
	    			}
	    		}
	    		
	    		//check if player 2
	    		else if(player == '2')
	    		{
	    			//iterate through all max successor nodes
	    			for(int i = 0; i < vals.size(); i++)
	    			{
	    				//check if value is -1
	    				if(vals.get(i) == -1 && negSize == 0)
	    				{
	    					//set as optimal move
	    					optimalMove = successors[i].getBoard();
	    					break;
	    					
	    				}
	    				
	    				//check if value is 0
	    				else if(vals.get(i) == 0 && zeroSize == 0)
	    				{
	    					//set as optimal move if -1 hasn't been discovered yet
	    					optimalMove = successors[i].getBoard();
	    					posSize = 1;
	    					zeroSize = 1;
	    				}
	    				
	    				//check if value is 1
	    				else if(vals.get(i) == 1 && posSize == 0)
	    				{
	    					//set as optimal move if 0 or 1 haven't been discovered
	    					optimalMove = successors[i].getBoard();
	    					posSize = 1;
	    				}
	    			}
	    		}
	    		
	    		//print optimal move
	    		System.out.println(optimalMove);
	    		
    		
    		}
    		
    	}
    	
    	//check if option 5
    	else if(option == 5)
    	{
    		//run minimax with pruning
    		System.out.println(Minimax.run_with_pruning(this, player));
    		System.out.println(Minimax.statesVisited);
    	}
    	
    	

    }

    public String getBoard() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            builder.append(this.board[i]);
        }
        return builder.toString().trim();
    }

    public boolean equals(State src) {
        for (int i = 0; i < 16; i++) {
            if (this.board[i] != src.board[i])
                return false;
        }
        return true;
    }
}

class Minimax {
	
	//global variable to count number of states visited
	public static int statesVisited = 0;
	
	/**
	 * return max value from max player
	 * @param curr_state - current state
	 * @return max value
	 */
	private static int max_value(State curr_state) {
		
        // TO DO: implement Max-Value of the Minimax algorithm
		
		//increment states visted
		statesVisited++;
		
		//check if terminal state
		if(curr_state.isTerminal())
		{
			return curr_state.getScore();
		}
		//if not terminal and no successors
		else if(curr_state.getSuccessors('1').length == 0)
		{
			
			return min_value(curr_state);
			
		}
		else
		{
			int alpha = Integer.MIN_VALUE;
			
			//recursively iterate through successors
			for(int i = 0; i < curr_state.getSuccessors('1').length; i++)
			{
				alpha = Math.max(alpha, min_value(curr_state.getSuccessors('1')[i])) ;
				
			}
			
			return alpha;
		}
		
		

	}
	
	/**
	 * 
	 * return min value for min player
	 * @param curr_state - current state
	 * @return min value
	 */
	private static int min_value(State curr_state) {
		
        // TO DO: implement Min-Value of the Minimax algorithm
		
		//increment states visited
		statesVisited++;
		
		//check if terminal state
		if(curr_state.isTerminal())
		{
			return curr_state.getScore();
		}
		//check if no successors and not terminal
		else if(curr_state.getSuccessors('2').length == 0)
		{
			return max_value(curr_state);
		}
		else
		{
			int beta = Integer.MAX_VALUE;
			
			//recursively iterate through successors
			for(int i = 0; i < curr_state.getSuccessors('2').length; i++)
			{
				beta = Math.min(beta, max_value(curr_state.getSuccessors('2')[i]));
			}
			
			return beta;
		}
	}
	
	/**
	 * perform max-value with alpha-beta pruning
	 * @param curr_state - current state
	 * @param alpha - best value for max player
	 * @param beta - best value for min player
	 * @return max value
	 */
	private static int max_value_with_pruning(State curr_state, int alpha, int beta) {
	    
        // TO DO: implement Max-Value of the alpha-beta pruning algorithm

		//increment states visited
		statesVisited++;
		
		//check if terminal state
		if(curr_state.isTerminal())
		{
			return curr_state.getScore();
		}
		//check if no successors and not terminal
		else if(curr_state.getSuccessors('1').length == 0)
		{
			return min_value_with_pruning(curr_state, alpha, beta);
		}
		else
		{
			//recursively iterate through successors
			for(int i = 0; i < curr_state.getSuccessors('1').length; i++)
			{
				alpha = Math.max(alpha, 
						min_value_with_pruning
						(curr_state.getSuccessors('1')[i], alpha, beta));
				if(alpha >= beta)
				{
					return beta;
				}
					
			}
			
			return alpha;
		}
		
	}
	
	/**
	 * perform min-value with alpha beta pruning
	 * @param curr_state - current state
	 * @param alpha - best value for max player
	 * @param beta - best value for min player
	 * @return min-value
	 */
	private static int min_value_with_pruning(State curr_state, int alpha, int beta) {
	    
        // TO DO: implement Min-Value of the alpha-beta pruning algorithm
		
		//increment states visited
		statesVisited++;
		
		//check if terminal state
		if(curr_state.isTerminal())
		{
			return curr_state.getScore();
		}
		//check if not terminal and no successors
		else if(curr_state.getSuccessors('2').length == 0)
		{
			return max_value_with_pruning(curr_state, alpha, beta);
		}
		else
		{
			//recursively iterate through successors
			for(int i = 0; i < curr_state.getSuccessors('2').length; i++)
			{
				beta = Math.min(beta, 
						max_value_with_pruning
						(curr_state.getSuccessors('2')[i], alpha, beta));
				if(alpha >= beta)
				{
					return alpha;
				}
			}
			return beta;
		}

	}
	
	/**
	 * run minimax
	 * @param curr_state - current state
	 * @param player - player
	 * @return
	 */
	public static int run(State curr_state, char player) {

        // TO DO: run the Minimax algorithm and return the game theoretic value
		int gameVal = 0;
		
		//if player 1 run max value
		if(player == '1')
		{
			gameVal = max_value(curr_state);
		}
		
		//if player 2 run min value
		else
		{
			gameVal = min_value(curr_state);
		}
		
		return gameVal;
	}
	
	/**
	 * run minimax with pruning
	 * @param curr_state - current state
	 * @param player - player
	 * @return
	 */
	public static int run_with_pruning(State curr_state, char player) {
	    
        // TO DO: run the alpha-beta pruning algorithm and return the game theoretic value
	
		//initialize alpha and beta
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		int gameVal = 0;
		
		//if player 1 run max value with pruning
		if(player == '1')
		{
			gameVal = max_value_with_pruning(curr_state, alpha, beta);
		}
		
		//if player 2 run min value with pruning
		else
		{
			gameVal = min_value_with_pruning(curr_state, alpha, beta);
		}
		
		return gameVal;
	}
}

public class Reversi {
    public static void main(String args[]) {
        if (args.length != 3) {
            System.out.println("Invalid Number of Input Arguments");
            return;
        }
        int flag = Integer.valueOf(args[0]);
        char[] board = new char[16];
        for (int i = 0; i < 16; i++) {
            board[i] = args[2].charAt(i);
        }
        int option = flag / 100;
        char player = args[1].charAt(0);
        if ((player != '1' && player != '2') || args[1].length() != 1) {
            System.out.println("Invalid Player Input");
            return;
        }
        State init = new State(board);
        init.printState(option, player);
    }
}
