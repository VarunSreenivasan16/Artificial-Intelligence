
import java.util.*;

/**
 * successor class - generates successors from initial state
 * @author Varun
 *
 */
public class successor {
	
	/**
	 * JugState class is a blueprint of our JugState object
	 * @author Varun
	 *
	 */
    public static class JugState {
    	
    	//Capacity stores the maximum amount each jug can store
        int[] Capacity = new int[]{0,0,0};
        //Content stores the amount of liquid in each jug in the initial state
        int[] Content = new int[]{0,0,0};
        
        /**
         * Constructor
         * 
         * @param copyFrom - JugState instance
         */
        public JugState(JugState copyFrom)
        {
            this.Capacity[0] = copyFrom.Capacity[0];
            this.Capacity[1] = copyFrom.Capacity[1];
            this.Capacity[2] = copyFrom.Capacity[2];
            this.Content[0] = copyFrom.Content[0];
            this.Content[1] = copyFrom.Content[1];
            this.Content[2] = copyFrom.Content[2];
        }
        
        //constructor
        public JugState()
        {
        }
        
        /**
         * Constructor 
         *
         * @param A - Jug A capacity
         * @param B - Jug B capacity
         * @param C - Jug C capacity
         */
        public JugState(int A,int B, int C)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
        }
        
        /**
         * Constructor
         * 
         * @param A - Jug A capacity
         * @param B - Jug B capacity
         * @param C - Jug C capacity
         * @param a - Jug A content
         * @param b - Jug B content
         * @param c - Jug C content
         */
        public JugState(int A,int B, int C, int a, int b, int c)
        {
            this.Capacity[0] = A;
            this.Capacity[1] = B;
            this.Capacity[2] = C;
            this.Content[0] = a;
            this.Content[1] = b;
            this.Content[2] = c;
        }
 
        //used to print out contents
        public void printContent()
        {
            System.out.println(this.Content[0] + " " + this.Content[1] + " " + this.Content[2]);
        }
 
       /**
        * generates successor list
        * 
        * @return successor list
        */
        public ArrayList<JugState> getNextStates(){
            ArrayList<JugState> successors = new ArrayList<>();

            // TODO add all successors to the list
            
           
            //Empty Jug
            
            //iterate through each Jug
            for(int i = 0; i < this.Content.length; i++)
            {
            	//instance of JugState 
            	JugState state = new JugState(this);
            	
            	//check if not empty
            	if(state.Content[i] != 0)
            	{
            		//update Jugstate
            		state.Content[i] = 0;
            		
            		//add successor to list
            		successors.add(state);
                	
            	}
            	
            }
            
            
            //Fill Jug
            
            //iterate through each Jug
            for(int i = 0; i < this.Content.length; i++)
            {
            	//instance of JugState 
            	JugState state2 = new JugState(this);
            	
            	//check if not full
            	if(state2.Content[i] < state2.Capacity[i])
            	{
            		//update state
            		state2.Content[i] = state2.Capacity[i];
            		
            		//add successor to list
            		successors.add(state2);
            	}
            }
            
            /*Pour water from one jug to another until either the former
            	   is empty or the latter is full*/
            
            //iterate through each Jug
            for(int i = 0; i < this.Content.length; i++)
            {
            	//iterate through jugs again
            	for(int j = 0; j < this.Content.length; j++)
            	{
            		//instance of JugState
            		JugState state3 = new JugState(this);
            		
            		//change is true if state3 gets updated
            		boolean change = false;
            		
            		//check if i is not j and that j isn't empty
            		if(i !=j && state3.Content[j] != 0)
            		{
            			//check if Jug i (liquid is poured into it) isn't full
            			if(state3.Content[i] < state3.Capacity[i])
            			{
            				//record the extra liquid need to make Jug i full
            				int temp = state3.Capacity[i] - state3.Content[i];
            				
            				/*check if Jar j contains enough liquid to make 
            				 *      Jug i full
            				 */
            				if(state3.Content[j] >= temp)
            				{
            					//set Jug i to full
            					state3.Content[i] = state3.Capacity[i];
            					
            					//update Jug j value
            					state3.Content[j] = state3.Content[j] - temp;
            				}
            				
            				//if not enough liquid is present in j
            				else
            				{
            					//update Jug i 
            					state3.Content[i] = state3.Content[i] + 
            							state3.Content[j];
            					
            					//set content of Jug j to 0
            					state3.Content[j] = 0;
            				}
            				
            				//set change to true
            				change = true;
            			}
            			
            		}
            		
            		//check if state3 was updated
            		if(change)
            		{
                		successors.add(state3);
                	}
            	}
            }
            
            //return list
            return successors;
            
        }
        
    }

    public static void main(String[] args) {
        if( args.length != 6 )
        {
            System.out.println("Usage: java successor [A] [B] [C] [a] [b] [c]");
            return;
        }
        
        

        // parse command line arguments
        JugState a = new JugState();
        a.Capacity[0] = Integer.parseInt(args[0]);
        a.Capacity[1] = Integer.parseInt(args[1]);
        a.Capacity[2] = Integer.parseInt(args[2]);
        a.Content[0] = Integer.parseInt(args[3]);
        a.Content[1] = Integer.parseInt(args[4]);
        a.Content[2] = Integer.parseInt(args[5]);

       
        
        // Implement this function
        ArrayList<JugState> asist = a.getNextStates();

        // Print out generated successors
        for(int i=0;i< asist.size(); i++)
        {
            asist.get(i).printContent();
        }

        return;
    }
}

