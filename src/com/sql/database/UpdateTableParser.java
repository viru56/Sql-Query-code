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

public class UpdateTableParser {

	static File file;
	static String fileName;
	static List<String> list;
	static List<String> list1;
	static List<String> list2;
	static String error;
	static int count;
	static int startCount;
	static int wherePos;
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	public static String updateTable( String str )
	{
		List<Integer> ColumnIndex = new ArrayList<Integer>();
		List<Integer> ListIndex = new ArrayList<Integer>();
		list = new ArrayList<String>();
		list1 = new ArrayList<String>();
		list2 = new ArrayList<String>();
		String[] token = str.split("[ \\s > ]");
		if(TableCreation.useDatabase==null)
			file = new File(dir+"/Databases Folder/nehra");
		else
			file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase);
		boolean fileExists = false;
		String[] files = file.list();
		 for (int i = 0; i < files.length; i++) {
			if(files[i].equalsIgnoreCase(token[2]+".csv"))
				fileExists = true;
		 }//end of for loop
		 if(fileExists)
		 {
			 if(TableCreation.useDatabase==null)
					fileName = dir+"/Databases Folder/nehra\\"+token[2]+".csv";
				else
					fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[2]+".csv";
			 try 
				{
				 	boolean checkSyntex = false;
					BufferedReader br = new BufferedReader(new FileReader(fileName));
					String lineString = null;
					while((lineString = br.readLine())!=null )
					{
						list.add(lineString);
					}
					br.close();
					
					if(token[3].equalsIgnoreCase("set"))
					{
						for (int i = 0; i < token.length; i++) {
							if(token[i].equalsIgnoreCase("where"))
							{
								count = i;
								checkSyntex = true;
								break;
							}//end if(where)
						}//end of for
						wherePos=count;
						if(checkSyntex)
						{
							String[] temp;
							startCount = 4;
							String columns = "";
							while(startCount < count)
							{
								columns = columns.concat(token[startCount]);
								startCount++;
							}//end of while
							//System.out.println(columns);
							temp = columns.split(",");
							for (int i = 0; i < temp.length; i++) {
								String[]temp1 = temp[i].split("=");
								if(temp1.length>2)
								{
									return UpdateTableError.errorCommaInClolumn(temp1[i]);
								}
								if(temp1.length<2)
								{
									return UpdateTableError.errorInEqualSign(temp[i]);
								}
								
							}//end of for loop
							
							if(columns.endsWith(","))
							{
								return UpdateTableError.errorCommaInLastClolumn(token[count-1]);
							}
							String[] temp2;
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
							{
								return UpdateTableError.errorInSemicolon();
							}
							
							String column = list.get(0);
							token = column.split("[ ,]");
							temp = columns.split("[,=]");
							
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
												ColumnIndex.add(j/2);
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
							/////////////////where condition parser/////////////////////
							column = list.get(0);
							token = column.split("[ ,]");
							temp = whereCondition.split("=");
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
							temp2 = columns.split("[,=]");
							token = whereCondition.split("=");							
							if(token[1].startsWith("'")&&token[1].endsWith("'"))
								token[1] = token[1].replaceAll("'", "");
							for (int j = 1; j < list.size(); j++) {
								temp = list.get(j).split(",");
								for (int j2 = 0; j2 < temp.length; j2++) {
									if(temp[j2].equals(token[1]))
									{
										ListIndex.add(j);
										list1.add(list.get(j));
										list.set(j, "");
									}
								}
							}
							
							for (int j = 0; j < list1.size(); j++) {
								
								token = list1.get(j).split(",");
								
								for (int k2 = 0, j2 = 0; j2 < token.length; j2++) {
									
									for ( int k = 0; k < ColumnIndex.size();k++) {
										
										if(j2==ColumnIndex.get(k))
										{	
											for (; k2 < temp2.length; k2++) {
												if(k2%2!=0)
												{
													if(temp2[k2].startsWith("'"))
														temp2[k2]=temp2[k2].replaceAll("'", "");
													token[j2]=temp2[k2];
													break;
												}
											}//end of k2
										break;}//end of if(j2==k)
									}//end of for(k)
									//System.out.println(j2+" "+token[j2]);
									k2++;
									list2.add(token[j2]+",");
								}//end of for(j2)
							}//end of for(j)
							
							int traverse = 0;
							startCount=0;
							String listString = "";
							for (int i = 0; i < list2.size(); i++) {
								
								listString = listString.concat(list2.get(i));
								traverse++;
								if(traverse==(list2.size()/ListIndex.size()))
								{	
									list.set(ListIndex.get(startCount), listString.substring(0, listString.length()-1));
									startCount++;
									listString = "";
									traverse=0;
								}//end of if
							}//end of for
							BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
							for (int i = 0; i < list.size(); i++) {
								writer.write("");
							}
							writer.close();
							CsvWriter csvWriter = new CsvWriter(new FileWriter(fileName,true),',');
							for (int i = 0; i < list.size(); i++) {
								token = list.get(i).split(",");
								for (int j = 0; j < token.length; j++) {
									csvWriter.write(token[j]);	
								}
								csvWriter.endRecord();
							}
							csvWriter.close();
							return ListIndex.size()+" row updated.";
						}//end of if(checkSyntex)
						else
							return UpdateTableError.errorMissingWhereKeyword();
					}//end of if(set)
					else
						return UpdateTableError.errorMissingSetKeyword(token[3]);
				}
			 catch (IOException e)
				{
					e.printStackTrace();
				}
		 }//end of if(fileExists)
		 else
			 return UpdateTableError.errorTableNotExist(token[2]);
		return null;
		
	}//end of function updateTable
	
}//end of class
