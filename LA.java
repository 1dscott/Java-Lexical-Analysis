/*
a lexical analyzer using java
 */

import java.io.*;
import java.util.Scanner;

public class LA
{
	public static void main(String[] args) 
	{
		
		Scanner scanner = new Scanner(System.in);
		int lineNumber=0;		  
	  
		//ask user for file name and scan file
		System.out.print("Please enter the file name or type quit to exit: ");
		//scan the file name
		String fileName = scanner.nextLine().trim(); 
		    
		// check if user wants to quit the program
		if (fileName.equalsIgnoreCase("quit")) 
		{  
			System.out.println("Program terminated."); 
		}
		//setup the file
		File f = new File(fileName);              
		if (f.exists()) 
		{ 		    	
			//is it a file
		    if (f.isFile() && f.canRead()) 
		    {          
		    		Scanner input = null;
		        try 
		        {
		          input = new Scanner(f);               
		          while (input.hasNextLine()) 
		          {
		        	  	 lineNumber++;
		        	  	 //store line of input
		        	  	 String words = input.nextLine();
		        	  	 //split on whitespace = ; , + - * / ( ) & 
		        	  	 String[]tokens = words.split("(?=\\s+)|(?<=\\s+)|(?=\\=)|(?<=\\=)|(?=\\;)|(?<=\\;)|(?=\\,)|(?<=\\,)|(?=\\+)|(?<=\\+)|(?=\\-)|(?<=\\-)|(?=\\*)|(?<=\\*)|(?=\\/)|(?<=\\/)|(?=\\()|(?<=\\()|(?=\\))|(?<=\\))|(?=\\&)|(?<=\\&)");
		            		            
		             for(int i = 0; i < tokens.length; i++)
		             {
		            	 
		        			   //identifier or key word
		        			   if(tokens[i].matches("^[a-zA-Z_][a-zA-Z0-9_-]*$"))
		        			   {
		        				    
			        				if(tokens[i].equals("int") || tokens[i].equals("double") || tokens[i].equals("String"))
			        				{
			        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1) + " keyword: " + tokens[i]);
			        				}
			        				else
			        				{
			        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1) +" identifier: " + tokens[i]);
			        				}
		        					
		        				}
		        				//operators      
		        				else if (tokens[i].matches("^(\\+|-|\\*|\\/|=|;|,|\\(|\\))$"))
		        				{
		        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1) +" operator: " + tokens[i]);
		        				}
		        				//int constant
		        				else if (tokens[i].matches("-?[0-9]+"))
		        				{
		        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1) +" int constant: " + tokens[i]);
		        				}
		        				//double constant
		        				else if (tokens[i].matches("^[-+]?[0-9]*\\.?[0-9]+$"))
		        				{
		        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1) +" double constant: " + tokens[i]);
		        				}
		        				//String constant \"([^\\\"]|\\.)*\"
		        				else if (tokens[i].matches("\"((\\\\\")|[^\"(\\\\\")])+\""))
		        				{
		        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1) +" string constant: " + tokens[i]);
		        				}
		        				//matches whitespace?
		        				else if(tokens[i].matches("\\s"))
		        				{
		        					//System.out.println("Line"+lineNumber+": "+indexNumber+" whitespace: " + tokens[i]);
		        				}
		        				else
		        				{
		        					System.out.println("Line"+lineNumber+": "+(words.indexOf(tokens[i])+1)+" error: " + tokens[i] + " not recognized");	
		        				}
		        			
		        			}
		            }
		          }
		         
		        catch (FileNotFoundException e) 
		        {
		          e.printStackTrace();
		        } 
		        finally 
		        {
		          if (input != null) 
		          {
		            input.close();                     
		          }
		        }
		      } 
		      else 
		      {             
		    	  	System.out.println("Cannot read the file.");
		      }
		
		    }
		    else
		    {
		    	System.out.println("The file does not exist."); 
		    }
	
		
	}//close main method
}//end

