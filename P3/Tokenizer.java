import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Tokenizer {

	private static final String DATA_PATH = "./WARC201709";
	private static final String FILE_EXTENSION = ".txt";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		File dir = new File(DATA_PATH);
		File [] file = dir.listFiles();
		ArrayList<File> temp = new ArrayList<File>();
		
		for(int i = 0; i < file.length; i++)
		{
			if(file[i].getName().endsWith(FILE_EXTENSION))
			{
				temp.add(file[i]);
				
				
			}
		}
		
		int numWords = 0;
		int filesScanned = 0;
		ArrayList<String> word = new ArrayList<String>();
		ArrayList<Integer> frequency = new ArrayList<Integer>();
		
		for(int i = 0; i < temp.size(); i++)
		{
			
			try
			{
				Scanner scanner = new Scanner(temp.get(i));
				
				int tempVal =  0;
				String words = "";
				
				while(scanner.hasNext())
				{
					words = scanner.next();
					
					if(tempVal == 0)
					{
						filesScanned++;
						tempVal++;
					}
					
					if(!word.contains(words))
					{
						word.add(words);
						frequency.add(1);
						numWords++;
					}
					else
					{
						frequency.add(index(word, words), frequency.remove(index(word, words)) + 1);
						numWords++;
					}
					
					
				}
				
			}
			catch(IOException e)
			{
				System.out.println("Error");
			}
			
			
		}
		
		System.out.println("Files Scanned " + filesScanned);
		System.out.println("Number of Words " + numWords);
		
		
		
		ArrayList<Integer> Dup = new ArrayList<Integer>();
		ArrayList<String> containsL = new ArrayList<String>();
		
		for(int i = 0; i < word.size(); i++)
		{
			Dup.add(frequency.get(i));
		}
		
		Collections.sort(Dup);
		int listSize = 0;
		

		System.out.println("Distinct Words " + Dup.size());
		
		System.out.println("");
		System.out.println("Most used Words");
		
		for(int i = Dup.size() -1; i > Dup.size() - 25; i--)
		{
			for(int j = 0; j < Dup.size(); j++)
			{
				if(Dup.get(i).equals(frequency.get(j)))
				{
					if(!containsL.contains(word.get(j)))
					{
						System.out.println(word.get(j) + "\t" + frequency.get(j));
						containsL.add(word.get(j));
						
						break;
					}
				}
			}
		}
		
		System.out.println("");
		System.out.println("Least Used Words");
		
		for(int i = 0; i < 20; i++)
		{
			for(int j = 0; j < Dup.size(); j++)
			{
				if(Dup.get(i).equals(frequency.get(j)))
				{
					if(!containsL.contains(word.get(j)))
					{
						System.out.println(word.get(j) + "\t" + frequency.get(j));
						containsL.add(word.get(j));
						
						break;
					}
				}
			}
		}
		
		
	/*	try
		{
			
			File filetemp = new File("output.txt");
			PrintWriter output = new PrintWriter(filetemp);
			
			for(int i = Dup.size() - 1; i >= 0; i--)
			{
				output.println(Dup.get(i));
			}
			
		}
		catch(IOException e)
		{
			
		}
		*/
		
		
		
		
	
		
	}
	
	/*private static void writeFiles(String fileName) 
	{
		
		try
		{
			
			//file is an instance of File class
			File file = new File(fileName);
			
			//output is an instance of PrintWriter
			PrintWriter output = new PrintWriter(file);
			
			//accesses the list of places in memory
			for(int i = 0; i < place.size(); i++)
			{
				
				output.println(place.get(i).getName() + "; " + place.get
						(i).getAddress());
				
			}
			
			output.close();
			
		}
		
		//handles the exception
		catch (FileNotFoundException e)
		{
			
			System.out.println("Unable to write to file: " + fileName);
			
		}*/
		
	//}
	
	
	
	private static int index(ArrayList<String> temp, String word)
	{
		for(int i = 0; i < temp.size(); i++)
		{
			if(temp.get(i).equals(word))
			{
				return i;
			}
		}
		
		return 0;
	}

}
