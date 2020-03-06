
import java.util.*;
import java.io.*;

public class Chatbot{
    private static String filename = "./WARC201709_wid.txt";
    private static ArrayList<Integer> readCorpus(){
        ArrayList<Integer> corpus = new ArrayList<Integer>();
        try{
            File f = new File(filename);
            Scanner sc = new Scanner(f);
            while(sc.hasNext()){
                if(sc.hasNextInt()){
                    int i = sc.nextInt();
                    corpus.add(i);
                }
                else{
                    sc.next();
                }
            }
        }
        catch(FileNotFoundException ex){
            System.out.println("File Not Found.");
        }
        return corpus;
    }
    static public void main(String[] args){
        ArrayList<Integer> corpus = readCorpus();
		int flag = Integer.valueOf(args[0]);
        
		//flag = 100
        if(flag == 100){
			int w = Integer.valueOf(args[1]);
            int count = 0;
            //TODO count occurence of w
            
            //obtain count
            for(int i = 0; i < corpus.size(); i++)
            {
            	if(corpus.get(i) == w)
            	{
            		count++;
            	}
            }
            
            System.out.println(count);
            System.out.println(String.format("%.7f",count/(double)corpus.size()));
        }
        
        //flag = 200
        else if(flag == 200){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            //TODO generate
            
            double r = (double)n1/n2;
            int index = 0;
            double lowerBound = 0.0;
            double upperBound = 0.0;
            double[] values = new double[4700];
           
            initializeArrayUnigram(values, corpus);
            
            ArrayList<Interval> list = new ArrayList<Interval>();
            list = returnList(values);
            int i = returnIndex(list,r);
            index = list.get(i).index;
            lowerBound = list.get(i).lBound;
            upperBound = list.get(i).upBound;

            
            System.out.println(index);
            System.out.println(String.format("%.7f",lowerBound));
            System.out.println(String.format("%.7f",upperBound));
            

        }
        
        //flag = 300
        else if(flag == 300){
            int h = Integer.valueOf(args[1]);
            int w = Integer.valueOf(args[2]);
            int count = 0;
            ArrayList<Integer> words_after_h = new ArrayList<Integer>();
            //TODO
            
            for(int i = 0; i < corpus.size() - 1; i++)
            {
            	if(corpus.get(i) == h)
            	{
            		words_after_h.add(corpus.get(i+1));
            		
            		if(corpus.get(i + 1) == w)
            		{
            			count++;
            		}
            	}
            	
            }
            
            //output 
            System.out.println(count);
            System.out.println(words_after_h.size());
            System.out.println(String.format("%.7f",count/(double)words_after_h.size()));
        }
        else if(flag == 400){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h = Integer.valueOf(args[3]);
            //TODO
            
            double r = (double)n1/n2;
            
            int index = 0;
            double lowerBound = 0.0;
            double upperBound = 0.0;
            double[] values = new double[4700];
            
            int count = initializeArrayBigram(values, corpus, h);
            
            if(count != 0)
            {
            
	            for(int i = 0; i < values.length; i++)
	            {
	            	values[i] = values[i] / count;
	            }
	            
	            ArrayList<Interval> list = new ArrayList<Interval>();
	            list = returnList(values);
	           
	            int i = returnIndex(list, r);
	            index = list.get(i).index;
	            lowerBound = list.get(i).lBound;
	            upperBound = list.get(i).upBound;
	           
	            System.out.println(index);
	            System.out.println(String.format("%.7f",lowerBound));
	            System.out.println(String.format("%.7f",upperBound));
	            
            
            }
            else
            {
            	System.out.println("undefined");
            }
            
            
            
            
        }
        else if(flag == 500){
            int h1 = Integer.valueOf(args[1]);
            int h2 = Integer.valueOf(args[2]);
            int w = Integer.valueOf(args[3]);
            int count = 0;
            ArrayList<Integer> words_after_h1h2 = new ArrayList<Integer>();
            //TODO
            
            for(int i = 0; i < corpus.size() - 2; i++)
            {
            	if(corpus.get(i) == h1 && corpus.get(i + 1) == h2)
            	{
            		words_after_h1h2.add(corpus.get(i+2));
            		
            		if(corpus.get(i+2) == w)
            		{
            			count++;
            		}
            	}
            }
            //output 
            System.out.println(count);
            System.out.println(words_after_h1h2.size());
            if(words_after_h1h2.size() == 0)
                System.out.println("undefined");
            else
                System.out.println(String.format("%.7f",count/(double)words_after_h1h2.size()));
        }
        else if(flag == 600){
            int n1 = Integer.valueOf(args[1]);
            int n2 = Integer.valueOf(args[2]);
            int h1 = Integer.valueOf(args[3]);
            int h2 = Integer.valueOf(args[4]);
            //TODO
            
            double r = (double)n1/n2;
            int index = 0;
            double lowerBound = 0.0;
            double upperBound = 0.0;
            double[] values = new double[4700];
            
            int count = initializeArrayTrigram(values, corpus, h1, h2);

            if(count == 0)
            {
            	System.out.println("undefined");
            }
            else
            {
            	for(int i = 0; i < values.length; i++)
            	{
            		values[i] = values[i]/count;
            	}
            	
            	ArrayList<Interval> list = new ArrayList<Interval>();
            	list = returnList(values);
            	
            	int i = returnIndex(list, r);
            	index = list.get(i).index;
            	lowerBound = list.get(i).lBound;
            	upperBound = list.get(i).upBound;
	           
            	
	            System.out.println(index);
	            System.out.println(String.format("%.7f",lowerBound));
	            System.out.println(String.format("%.7f",upperBound));
            }
            
        }
        
        else if(flag == 700){
            int seed = Integer.valueOf(args[1]);
            int t = Integer.valueOf(args[2]);
            int h1=0,h2=0;

            Random rng = new Random();
            if (seed != -1) rng.setSeed(seed);

            if(t == 0){
                // TODO Generate first word using r
                double r = rng.nextDouble();
                
               //unigram model
                double[] values = new double[4700];
                initializeArrayUnigram(values, corpus);
                ArrayList<Interval> list = new ArrayList<Interval>();
                list = returnList(values);

                h1 = list.get(returnIndex(list, r)).index;
                System.out.println(h1);
                
                
                if(h1 == 9 || h1 == 10 || h1 == 12){
                    return;
                }
                
                // TODO Generate second word using r
                r = rng.nextDouble();
                
                
                //bigram
                
                values = new double[4700];
                int count = initializeArrayBigram(values, corpus, h1);
                if(count != 0)
                {
                
    	            for(int i = 0; i < values.length; i++)
    	            {
    	            	values[i] = values[i] / count;
    	            }
    	            
    	            list = new ArrayList<Interval>();
    	            list = returnList(values);
    	            h2 = list.get(returnIndex(list, r)).index;
    	            
    	            
                }
                else
                {
                	values = new double[4700];
                    initializeArrayUnigram(values, corpus);
                    list = new ArrayList<Interval>();
    	            list = returnList(values);
    	            h2 = list.get(returnIndex(list, r)).index;
                }
              
    	            
                System.out.println(h2);
            }
            
           
           //t = 1 
            else if(t == 1){
                h1 = Integer.valueOf(args[3]);
                // TODO Generate second word using r
                double r = rng.nextDouble();
                
                double[] values = new double[4700];
                
                int count = initializeArrayBigram(values, corpus, h1);
                  
                if(count != 0)
                {
                
    	            for(int i = 0; i < values.length; i++)
    	            {
    	            	values[i] = values[i] / count;
    	            }
    	            
    	            ArrayList<Interval> list = new ArrayList<Interval>();
    	            list = returnList(values);
    	            h2 = list.get(returnIndex(list, r)).index;
                
                }
                else
                {
                	values = new double[4700];
                    initializeArrayUnigram(values, corpus);
                    ArrayList<Interval> list = new ArrayList<Interval>();
    	            list = returnList(values);
    	            h2 = list.get(returnIndex(list, r)).index;
                }
                
                System.out.println(h2);
            }
            else if(t == 2){
                h1 = Integer.valueOf(args[3]);
                h2 = Integer.valueOf(args[4]);
            
            }

            while(h2 != 9 && h2 != 10 && h2 != 12){
                double r = rng.nextDouble();
                int w  = 0;
                // TODO Generate new word using h1,h2
                
                double[] values = new double[4700];
                
                int count = initializeArrayTrigram(values, corpus, h1, h2);
                
                if(count != 0)
                {
	             	for(int i = 0; i < values.length; i++)
	            	{
	            		values[i] = values[i]/count;
	            	}
	            	
	            	ArrayList<Interval> list = new ArrayList<Interval>();
		            list = returnList(values);
		            w = list.get(returnIndex(list, r)).index;
		            
                }
                else
                {
                	
                	values = new double[4700];
                	int tempCount = initializeArrayBigram(values, corpus, h2);
                	
                	if(tempCount != 0)
                	{
                		//bigram model
	                	for(int i = 0; i < values.length; i++)
	         	        {
	         	            values[i] = values[i] / tempCount;
	         	        }
	         	            
	         	       ArrayList<Interval> IntervalList = new ArrayList<Interval>();
	       	           IntervalList = returnList(values);
	         	       w = IntervalList.get(returnIndex(IntervalList, r)).index ;
         	            
         	            
                	}
                	
                	//unigram
                	else
                	{
                		values = new double[4700];
                		initializeArrayUnigram(values, corpus);
                        ArrayList<Interval> tempIntlist = new ArrayList<Interval>();
                        tempIntlist = returnList(values);
                        w = tempIntlist.get(returnIndex(tempIntlist, r)).index;
                		
                	}
                	
                }
                
                System.out.println(w);
                h1 = h2;
                h2 = w;
            }
        }

        return;
    }
    
    private static void initializeArrayUnigram(double [] values, ArrayList<Integer> list)
    {
    	for(int i = 0; i < list.size(); i++)
        {
			values[list.get(i)]++;
        }
		for(int i = 0; i < values.length; i++)
        {
        	values[i] = values[i] / list.size();
        }
    }
    private static int initializeArrayBigram(double [] values, ArrayList<Integer> list, int h1)
    {
    	int count = 0;
    	 for(int i = 0; i < list.size() - 1; i++)
         {
         	if(list.get(i) == h1)
         	{
         		count++;
         		values[list.get(i+1)]++;
         	}
         }
    	 
    	 return count;
    }
    private static int initializeArrayTrigram(double [] values, ArrayList<Integer> list, 
    		int h1, int h2)
    {
    	int count = 0;
        
        for(int i = 0; i < list.size() - 2; i++)
        {
        	if(list.get(i) == h1 && list.get(i+1) == h2)
        	{
        		count++;
        		values[list.get(i+2)]++;
        	}
        }
        
        return count;
    }
    
    private static ArrayList<Interval> returnList(double[] values)
    {
    	  ArrayList<Interval> list = new ArrayList<Interval>();
          double sumL = 0;
          double sumR = 0;
          Interval temp = null;
          
          for(int i = 0; i < values.length; i++)
          {
          	if(values[i] != 0)
          	{
          		sumL = sumR;
              	sumR = sumR + values[i];
              	temp = new Interval(i, sumL, sumR);
              	list.add(temp);
          	}
          }
          
          return list;
    }
    
    private static int returnIndex(ArrayList<Interval> list, double r)
    {
    	
    	for(int i = 0; i < list.size(); i++)
    	{
    		if(i == 0)
        	{
        		if(r >= list.get(i).lBound 
        				&& r <= list.get(i).upBound)
        		{
        			return i;
        		}
        		
        	}
        	else 
        	{
        		if(r > list.get(i).lBound 
        				&& r <= list.get(i).upBound)
        		{
        			return i;
        		}
        	}
    	}
    	
    	return 0;
    }
}

class Interval
{
	public int index;
	public double lBound;
	public double upBound;
	
	public Interval(int ind, double l, double r)
	{
		this.index = ind;
		this.lBound = l;
		this.upBound = r;
	}
	
}
