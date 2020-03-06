import java.util.Random;

public class Ice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [] values = {118,151,121,96,110,117,132,104,125,118,125,123,110,127,131,99,
				126, 144,136,126,91,130,62,112,99,161,78,124,119,124,128,131,113,88,75,111,
				97,112,101,101,91,110,100,130,111,107,105,89,126,108,97,94,83,106,98,101,108,
				99,88,115,102,116,115,82,110,81,96,125,104,105,124,103,106,96,107,98,65,
				115,91,94,101,121,105,97,105,96,82,116,114,92,98,101,104,96,109,122,114,81,85,
				92,114,111,95,126,105,108,117,112,113,120,65,98,91,108,113,110,105,97,105,107,88,
				115,123,118,99,93,96,54,111,85,107,89,87,97,93,88,99,108,94,74,119,102,47,82,53,
				115,21,89,80,101,95,66,106,97,87,109,57,87,117,91,62 ,65 };

		
		
		int flag = Integer.valueOf(args[0]);
		
		if(flag == 100)
		{
			for(int i = 0; i < values.length; i++)
			{
				System.out.println(i + 1855 + " " + values[i]);
			}
		}
		else if(flag == 200)
		{
			double sum = 0;
			for(int i = 0; i < values.length; i++)
			{
				sum = sum + values[i];
			}
			
			double mean = sum/values.length;
			
			double variance = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				variance = variance + Math.pow((values[i] - mean), 2);
			}
			
			double standardDev = Math.sqrt(variance/(values.length - 1));
			
			System.out.println(values.length);
			System.out.println(String.format("%.2f",mean));
			System.out.println(String.format("%.2f",standardDev));
		}
		else if(flag == 300)
		{
			double betaZero = Double.valueOf(args[1]);
			double betaOne = Double.valueOf(args[2]);
			double meanError = 0.0;
			
			for(int i = 0; i < values.length; i++)
			{
				meanError = meanError + Math.pow((betaZero + (betaOne * (i + 1855)) - values[i]), 2);
			}
			
			double meanSquaredError = meanError/values.length;
			
			System.out.println(String.format("%.2f", meanSquaredError));
		}
		else if(flag == 400)
		{
			double betaZero = Double.valueOf(args[1]);
			double betaOne = Double.valueOf(args[2]);
			double derivative1 = 0;
			double derivative2 = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				derivative1 = derivative1 + (betaZero + ((i + 1855) * betaOne) - values[i]);
				derivative2 = derivative2 + ((betaZero + ((i + 1855) * betaOne) - values[i])
						* (i+1855));
			}
			
			derivative1 = 2*(derivative1/values.length);
			derivative2 = 2*(derivative2/values.length);
			
			System.out.println(String.format("%.2f", derivative1));
			System.out.println(String.format("%.2f", derivative2));
		}
		else if(flag == 500)
		{
			double n = Double.valueOf(args[1]);
			int T = Integer.valueOf(args[2]);
			double betaZero = 0;
			double betaOne = 0;
			double tempDer1 = 0;
			double tempDer2 = 0;
			
			for(int i = 0; i < T; i++)
			{
				tempDer1 = 0;
				tempDer2 = 0;
				
				for(int j = 0; j < values.length; j++)
				{
					tempDer1 = tempDer1 + (betaZero + ((j + 1855) * betaOne) - values[j]);
					tempDer2 = tempDer2 + ((betaZero + ((j + 1855) * betaOne) - values[j])
							* (j+1855));
				}
				
				betaZero = betaZero - ((n*2)/values.length)*tempDer1;
				betaOne = betaOne - ((n*2)/values.length)*tempDer2;
				
				
				double meanError = 0.0;
				
				for(int m = 0; m < values.length; m++)
				{
					meanError = meanError + Math.pow((betaZero + (betaOne * (m + 1855)) - values[m]), 2);
				}
				
				double meanSquaredError = meanError/values.length;
				
				
				System.out.println((i+1) + " " + String.format("%.2f", betaZero) 
				+ " " + String.format("%.2f", betaOne) + " " + 
						String.format("%.2f", meanSquaredError));
				
				
			}
			
			
		}
		else if(flag == 600)
		{
			double xMean = 0;
			double yMean = 0;
			double xSum = 0;
			double ySum = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				xSum = xSum + (i + 1855);
				ySum = ySum + values[i];
			}
			
			xMean = xSum/values.length;
			yMean = ySum/values.length;
			
			
			double betaZero = 0;
			double betaOne = 0;
			double numeratorSum = 0;
			double denominatorSum = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				numeratorSum = numeratorSum + ((i+1855) - xMean )*(values[i] - yMean);
				denominatorSum = denominatorSum + Math.pow((i+1855) - xMean, 2);
			}
			
			betaOne = numeratorSum/denominatorSum;
			betaZero = yMean - betaOne*xMean;
			
			double meanError = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				meanError = meanError + Math.pow((betaZero + (betaOne * (i + 1855)) - values[i]), 2);
			}
			
			double meanSquaredError = meanError/values.length;
			
			System.out.println(String.format("%.2f", betaZero) + " " + String.format("%.2f", betaOne) 
			+ " " + String.format("%.2f", meanSquaredError));
		}
		else if(flag == 700)
		{

			int year = Integer.valueOf(args[1]);
			double xMean = 0;
			double yMean = 0;
			double xSum = 0;
			double ySum = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				xSum = xSum + (i + 1855);
				ySum = ySum + values[i];
			}
			
			xMean = xSum/values.length;
			yMean = ySum/values.length;
			
			
			double betaZero = 0;
			double betaOne = 0;
			double numeratorSum = 0;
			double denominatorSum = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				numeratorSum = numeratorSum + ((i+1855) - xMean )*(values[i] - yMean);
				denominatorSum = denominatorSum + Math.pow((i+1855) - xMean, 2);
			}
			
			betaOne = numeratorSum/denominatorSum;
			betaZero = yMean - betaOne*xMean;
			
			double days = betaZero + betaOne*year;
			System.out.println(String.format("%.2f", days));
		}
		else if(flag == 800)
		{
			double xMean = 0;
			double xSum = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				xSum = xSum + (i+1855);
			}
			
			xMean = xSum/values.length;
			
			double xSD = 0;
			double xVar = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				xVar = xVar + Math.pow((i+1855) - xMean, 2);
			}
			
			
			xSD = Math.sqrt(xVar/(values.length-1));
			
			double [] xValues = new double[values.length];
			
			for(int i = 0; i < xValues.length; i++)
			{
				xValues[i] = i + 1855;
			}
			
			for(int i = 0; i < xValues.length; i++)
			{
				xValues[i] = (xValues[i] - xMean)/xSD;
			}
			
			double n = Double.valueOf(args[1]);
			int T = Integer.valueOf(args[2]);
			double betaZero = 0;
			double betaOne = 0;
			double tempDer1 = 0;
			double tempDer2 = 0;
			
			
			for(int i = 0; i < T; i++)
			{
				tempDer1 = 0;
				tempDer2 = 0;
				
				for(int j = 0; j < values.length; j++)
				{
					tempDer1 = tempDer1 + (betaZero + ( xValues[j] * betaOne) - values[j]);
					tempDer2 = tempDer2 + ((betaZero + ( xValues[j]* betaOne) - values[j])
							* xValues[j]);
				}
				
				betaZero = betaZero - ((n*2)/values.length)*tempDer1;
				betaOne = betaOne - ((n*2)/values.length)*tempDer2;
				

				double meanError = 0.0;
				
				for(int m = 0; m < values.length; m++)
				{
					meanError = meanError + Math.pow((betaZero + 
							(betaOne * (xValues[m])) - values[m]), 2);
				}
				
				double meanSquaredError = meanError/values.length;
				
				
				System.out.println((i+1) + " " + String.format("%.2f", betaZero) 
				+ " " + String.format("%.2f", betaOne) + " " + 
						String.format("%.2f", meanSquaredError));
				
				
			
			}
					
		}
		else if(flag == 900)
		{
			double xMean = 0;
			double xSum = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				xSum = xSum + (i+1855);
			}
			
			xMean = xSum/values.length;
			
			double xSD = 0;
			double xVar = 0;
			
			for(int i = 0; i < values.length; i++)
			{
				xVar = xVar + Math.pow((i+1855) - xMean, 2);
			}
			
			
			xSD = Math.sqrt(xVar/(values.length-1));
			
			double [] xValues = new double[values.length];
			
			for(int i = 0; i < xValues.length; i++)
			{
				xValues[i] = i + 1855;
			}
			
			for(int i = 0; i < xValues.length; i++)
			{
				xValues[i] = (xValues[i] - xMean)/xSD;
			}
			
			double n = Double.valueOf(args[1]);
			int T = Integer.valueOf(args[2]);
			double betaZero = 0;
			double betaOne = 0;
			double tempDer1 = 0;
			double tempDer2 = 0;
			
			
			Random rng = new Random();
			for(int i = 0; i < T; i++)
			{
				tempDer1 = 0;
				tempDer2 = 0;
				
				int seed = rng.nextInt(162);
				
				
				tempDer1 = (betaZero + ( xValues[seed] * betaOne) - values[seed]);
				tempDer2 = ((betaZero + ( xValues[seed]* betaOne) - values[seed])
							* xValues[seed]);
				
				
				betaZero = betaZero - (2*n)*tempDer1;
				betaOne = betaOne - (2*n)*tempDer2;
				

				double meanError = 0.0;
				
				for(int m = 0; m < values.length; m++)
				{
					meanError = meanError + Math.pow((betaZero + 
							(betaOne * (xValues[m])) - values[m]), 2);
				}
				
				double meanSquaredError = meanError/values.length;
				
				
				System.out.println((i+1) + " " + String.format("%.2f", betaZero) 
				+ " " + String.format("%.2f", betaOne) + " " + 
						String.format("%.2f", meanSquaredError));
				
				
			}		
		}
		
		
	}
	
	
	
	

}
