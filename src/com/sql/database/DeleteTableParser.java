package com.sql.database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvWriter;

public class DeleteTableParser 
{
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	static File file;
	static BufferedReader br;
	static String fileName;
	public static String deleteTable( String str)
	{
		String[] token = str.split("[ \\s > ]");
		if(TableCreation.useDatabase==null)
			
			fileName = dir+"/Databases Folder/nehra/";
		else
			fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/";
		file = new File(fileName);
		String[] files = file.list();
		
		boolean fileExists = false;
		
		if(token.length==3||token.length==4)
		{
			if(token[2].endsWith(";")||token[3].equals(";"))
			{
				if(token[2].endsWith(";"))
					token[2]= token[2].replaceAll(";", "");
			}
			else
				return UpdateTableError.errorInSemicolon();
			 for (int i = 0; i < files.length; i++) 
			 {				 
				if(files[i].equalsIgnoreCase(token[2]+".csv"))
					fileExists = true;
			 }//end of for loop
			if(fileExists)
			{
				int rowCount=0;
					try 
					{
						if(TableCreation.useDatabase==null)
							//dir+"/Databases Folder/
							fileName = dir+"/Databases Folder/nehra/"+token[2]+".csv";
						else
							fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[2]+".csv";
						BufferedReader fileRead=new BufferedReader(new FileReader(fileName));
						List<String> list = new ArrayList<String>();
						String lineString = null;
						while((lineString = fileRead.readLine())!=null )
							list.add(lineString);
						fileRead.close();
						String tableStructure = list.get(0);	
						BufferedWriter fileWrite=new BufferedWriter(new FileWriter(fileName+token[2]+".csv"));
						for (int i = 1; i < list.size(); i++) 
						{
						 fileWrite.write("");
						 rowCount++;
						}
						fileWrite.write(tableStructure);
						fileWrite.close();
						return  rowCount+" row deleted.";
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				
			}//end of if file exist;
			else
				return UpdateTableError.errorTableNotExist(token[2]);
		}//end of if(token.length==3||token.length==4)
		else
			return DeleteTableParser.deleteTableWereCondition(str);
		return null;
	}//end of function delete table
	
	
	
	
	public static String deleteTableWereCondition( String str )
	{
		String[] token = str.split("[ \\s > ]");
		if(TableCreation.useDatabase==null)
			fileName = dir+"/Databases Folder/nehra/";
		else
			fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/";
		file = new File(fileName);
		String[] files = file.list();
		boolean fileExists = false;
		for (int i = 0; i < files.length; i++) 
		 {				 
			if(files[i].equalsIgnoreCase(token[2]+".csv"))
				fileExists = true;
		 }//end of for loop
		if(fileExists)
		{
			//int rowCount=0;
				try 
				{
					if(TableCreation.useDatabase==null)
						fileName = dir+"/Databases Folder/nehra/"+token[2]+".csv";
					else
						fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[2]+".csv";
					BufferedReader fileRead=new BufferedReader(new FileReader(fileName));
					List<String> list = new ArrayList<String>();
					String lineString = null;
					while((lineString = fileRead.readLine())!=null )
						list.add(lineString);
					fileRead.close();
					//String tableStructure = list.get(0);	
					if(token[3].equalsIgnoreCase("where"))
					{
						String[] temp2;
						int count = 3;
						String whereCondition = "";
						while(count<token.length-1)
						{
							whereCondition = whereCondition.concat(token[count+1]);
							count++;
						}
						if(whereCondition.endsWith(";"))
						{
							whereCondition = whereCondition.replaceAll(";", "");
							
							temp2 = whereCondition.split("=");
							for (int i = 0; i < temp2.length; i++) {
								if(temp2.length < 2)
									return UpdateTableError.errorInEqualSign(temp2[i]);
								if(temp2.length > 2)
									return UpdateTableError.errorMissingWhereKeyword();
							}
						}//end of if(;)
						else
							return UpdateTableError.errorInSemicolon();
						///////////////where condition////////////////////
						String column = list.get(0);
						token = column.split("[ ,]");
						String[] temp = whereCondition.split("=");
						for (int i = 0; i < temp.length; i++) 
						{
							if(i%2==0)
							{
								boolean isColumn = false;
								for (int j = 0; j < token.length; j++)
								{
									
									if(j%2==0)
									{
										if(temp[i].equalsIgnoreCase(token[j]))
										{
											count = j;
											isColumn = true;
											break;
										}
									}//end of if(j%2==0)
								}//end of for(j)
								if(isColumn==false)
								{
									return UpdateTableError.errorInClolumnName(temp[i]);
								}
							}//end of if(i%2==0)
							
							if(i%2!=0)
							{
								if(token[count+1].equals("String"))
								{
									if(temp[i].startsWith("'")&&temp[i].endsWith("'"))
									{continue;}
									else
										return UpdateTableError.errorInStringValue(temp[i]);
								}
								else if(token[count+1].equals("char"))
								{
									if(temp[i].startsWith("'")&&temp[i].endsWith("'"))
									{continue;}
								else
									return UpdateTableError.errorInCharValue(temp[i]);
								}
								else if(token[count+1].equals("int"))
								{
									if(temp[i].matches("[0-9]+"))
									{continue;}
								else
									return UpdateTableError.errorInIntValue(temp[i]);
								}
								else if(token[count+1].equals("float"))
								{
									if(temp[i].matches("[0-9]*[.]*[0-9]*"))
									{continue;}
								else
									return UpdateTableError.errorInFloatValue(temp[i]);
								}
								else if(token[count+1].equals("boolean"))
								{
									if(temp[i].equalsIgnoreCase("true")||temp[i].equalsIgnoreCase("false"))
									{continue;}
								else
									return UpdateTableError.errorInBooleanValue(temp[i]);
								}
								else if(token[count+1].equals("Date"))
								{
									if(temp[i].startsWith("'")&&temp[i].endsWith("'"))
									{
										temp[i] = temp[i].replaceAll("'", "");
										if(temp[i].matches("[0-3][0-9][-][0-1][0-9][-][0-9][0-9]"))
										{continue;}
										else
											return UpdateTableError.errorInDateValue(temp[i]);
									}
									else 
										return UpdateTableError.errorInDateValue(temp[i]);
								}
							}//end of if(i%2!0)
							
						}//end of for(i)
						count=0;
						List<String> list1 = new ArrayList<String>();
						token = whereCondition.split("=");							
						if(token[1].startsWith("'")&&token[1].endsWith("'"))
							token[1] = token[1].replaceAll("'", "");
						for (int j = 1; j < list.size(); j++) {
							temp = list.get(j).split(",");
							for (int j2 = 0; j2 < temp.length; j2++) {
								if(temp[j2].equals(token[1]))
								{
									count++;
									list.set(j, "");
								}//end of if
								
							}//end of for(j2)
						}//end of for(j)
						
						for (int j = 0; j < list.size(); j++) {
							if(list.get(j).equals("")==false)
								list1.add(list.get(j));
						}
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
							for (int i = 0; i < list.size(); i++) {	
									writer.write("");	
						}
						writer.close();
						CsvWriter csvWriter = new CsvWriter(new FileWriter(fileName,true),',');
						for (int i = 0; i < list1.size(); i++) {
							token = list1.get(i).split(",");
							for (int j = 0; j < token.length; j++) {
								csvWriter.write(token[j]);	
							}
							csvWriter.endRecord();
						}
						csvWriter.close();
						
						
						return count+" row deleted";
						
					}//end of if(where)
					else
						UpdateTableError.errorMissingWhereKeyword();
						
				}//end of try 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
			
		}//end of if file exist;
		else
			return UpdateTableError.errorTableNotExist(token[2]);
		
		return null;
		
	}//end of function deleteTablehereCondition
	
	
	
}//end of class
