package com.sql.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvWriter;

public class InsertDataIntoTable
{
	static String fileName;
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	public static String insertData( String str )
	{
		//str = str.replaceAll(",", "");
		String[] token = str.split("[ >]");
		/*for (int i = 0; i < token.length; i++) {
			System.out.println(token[i]);
		}*/
		int count=0;
		if(TableCreation.useDatabase==null)
		{
			fileName = dir+"/Databases Folder/nehra/"+token[3]+".csv";
		}
		else
			fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[3]+".csv";
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			List<String> lines = new ArrayList<String>();
			String lineString = null;
			while((lineString = br.readLine())!=null )
			{
				lines.add(lineString);
			}
			br.close();
			String column = lines.get(0);
			//System.out.println(column+"\n");
			String[]token1 = column.split("[ ,]");
			for (int i = 0; i < token.length-1; i++) {
				if(i>5)
					++count;
			}//end of for loop
			
			if(count==token1.length/2)
			{
				if(count >token1.length/2)
				{
					return InsertTableError.tooManyValues();
				}
				int j = 6;
				for (int i = 0; i < token1.length; i++) {
					if(i%2!=0)
					{		
						//System.out.println("outer loop"+i);
						for ( ; j < token.length-1; j++) {
							if(j>5)
							{
								//System.out.println("inner loop query "+j);
								token[j] = token[j].replaceAll(",", "");
								if(token1[i].equals("String"))
								{
									
									if(token[j].startsWith("'")&&token[j].endsWith("'"))
									{
										j++;
										break;
									}
									else
										return InsertTableError.errorInStringValue(token[j]);
								}
								else if(token1[i].equals("char"))
								{
									if(token[j].startsWith("'")&&token[j].endsWith("'"))
									{
										j++;
										break;
									}
										
								else
									return InsertTableError.errorInCharValue(token[j]);
								}
								else if(token1[i].equals("int"))
								{
									if(token[j].matches("[0-9]+"))
									{
										j++;
										break;
									}
								else
									return InsertTableError.errorInIntValue(token[j]);
								}
								else if(token1[i].equals("float"))
								{
									if(token[j].matches("[0-9]*[.]*[0-9]*"))
									{
										j++;
										break;
									}
								else
									return InsertTableError.errorInFloatValue(token[j]);
								}
								else if(token1[i].equals("boolean"))
								{
									if(token[j].equalsIgnoreCase("true")||token[j].equalsIgnoreCase("false"))
									{
										j++;
										break;
									}
								else
									return InsertTableError.errorInBooleanValue(token[j]);
								}
								else if(token1[i].equals("Date"))
								{
									if(token[j].startsWith("'")&&token[j].endsWith("'"))
									{
										token[j] = token[j].replaceAll("'", "");
									if(token[j].matches("[0-3][0-9][-][0-1][0-9][-][0-9][0-9]"))
									{
										j++;
										break;
									}
									else
										return InsertTableError.errorInDateValue(token[j]);
									}
									else 
										return InsertTableError.errorInDateValue(token[j]);
								}
							}//end of if(i>5)
						}//end of inner for loop
						
						
					}//end of if(i%2!=0)
				}//end of outter for loop
				//---------------------------------insert data-----------------------//
				CsvWriter writer = new CsvWriter(new FileWriter(fileName,true),',');
				for (int j2 = 0; j2 < token.length-1; j2++) 
				{
					if(j2>5)
					{
						token[j2] = token[j2].replaceAll("'", "");	
						writer.write(token[j2]);
					}		
				}
				writer.endRecord();
				writer.close();
				return String.format("1 row inserted....");
			}
			else
				return InsertTableError.columnAndValuesNotEqueal();
		}//end of try block
		catch (IOException e)
		{
			e.printStackTrace();
		} 
		return null;
		
	}
}
