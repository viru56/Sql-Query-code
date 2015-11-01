package com.sql.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class FetchDataFromTable
{
	static String[] err = new String[2];
	static File file;
	static CsvReader csvReader;
	static String error;
	static String fileName;
	static List<String> list;
	static List<String> listError;
	static int count;
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	
 	public static List<String> selectAllData( String str )
	{
		list = new ArrayList<String>();
		String[] token = str.split("[ \\s > ]");
		if(token[3].equalsIgnoreCase("from"))
		{
			if(token[4].endsWith(";")||token[5].equals(";"))
			{
				token[4] = token[4].replaceAll(";", "");
				boolean fileExists = false;
				
				if(TableCreation.useDatabase==null)
					file = new File(dir+"/Databases Folder/nehra");
				else
					file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase);
			
				String[] files = file.list();
				 for (int i = 0; i < files.length; i++) {
					if(files[i].equalsIgnoreCase(token[4]+".csv"))
						fileExists = true;
				 }//end of for loop
				 if(fileExists)
				 {
					 if(TableCreation.useDatabase==null)
							fileName = dir+"/Databases Folder/nehra/"+token[4]+".csv";
						else
							fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[4]+".csv";
					 try 
						{
							BufferedReader br = new BufferedReader(new FileReader(fileName));
							String lineString = null;
							while((lineString = br.readLine())!=null )
							{
								list.add(lineString);
							}
							br.close();
							return list;
						}catch (IOException e)
						{
							e.printStackTrace();
						} 
				 }//end of if(fileExists)
				 else
				 {
					 error = SelectTableError.errorInTableName(token[4]);
					 list.add(error);
						return list;
				 }
			}//end of if(;)
			else
			{////////////////////////////where condition////////////////////////////////////
				if(token[5].equalsIgnoreCase("where"))
				{
					if(TableCreation.useDatabase==null)
						fileName = dir+"/Databases Folder/nehra/";
					else
						fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/";
					file = new File(fileName);
					String[] files = file.list();
					boolean fileExists = false;
					for (int i = 0; i < files.length; i++) 
					 {				 
						if(files[i].equalsIgnoreCase(token[4]+".csv"))
							fileExists = true;
					 }//end of for loop
					if(fileExists)
					{
						//int rowCount=0;
							try 
							{
								List<String> list1 = new ArrayList<String>();
								if(TableCreation.useDatabase==null)
									fileName = dir+"/Databases Folder/nehra/"+token[4]+".csv";
								else
									fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[4]+".csv";
								BufferedReader fileRead=new BufferedReader(new FileReader(fileName));
								List<String> list = new ArrayList<String>();
								String lineString = null;
								while((lineString = fileRead.readLine())!=null )
									list.add(lineString);
								fileRead.close();	
								if(token[5].equalsIgnoreCase("where"))
								{
									String[] temp2;
									int count = 5;
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
											{
												error = UpdateTableError.errorInEqualSign(temp2[i]);
												list1.add(error);
												return list1;
											}
											if(temp2.length > 2)
											{
												error = UpdateTableError.errorMissingWhereKeyword();
												list1.add(error);
												return list1;
											}
										}
									}//end of if(;)
									else
									{
										error = UpdateTableError.errorInSemicolon();
										list1.add(error);
										return list1;
									}
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
												error =  UpdateTableError.errorInClolumnName(temp[i]);
												list1.add(error);
												return list1;
											}
										}//end of if(i%2==0)
										
										if(i%2!=0)
										{
											if(token[count+1].equals("String"))
											{
												if(temp[i].startsWith("'")&&temp[i].endsWith("'"))
												{continue;}
												else
												{
													error = UpdateTableError.errorInStringValue(temp[i]);
													list1.add(error);
													return list1;
												}
											}
											else if(token[count+1].equals("char"))
											{
												if(temp[i].startsWith("'")&&temp[i].endsWith("'"))
												{continue;}
												else
												{
													error = UpdateTableError.errorInCharValue(temp[i]);
													list1.add(error);
													return list1;
												}
											}
											else if(token[count+1].equals("int"))
											{
												if(temp[i].matches("[0-9]+"))
												{continue;}
												else
												{
													error = UpdateTableError.errorInIntValue(temp[i]);
													list1.add(error);
													return list1;
												}
											}
											else if(token[count+1].equals("float"))
											{
												if(temp[i].matches("[0-9]*[.]*[0-9]*"))
												{continue;}
												else
												{
													error = UpdateTableError.errorInFloatValue(temp[i]);
													list1.add(error);
													return list1;
												}
											}
											else if(token[count+1].equals("boolean"))
											{
												if(temp[i].equalsIgnoreCase("true")||temp[i].equalsIgnoreCase("false"))
												{continue;}
												else
												{
													error = UpdateTableError.errorInBooleanValue(temp[i]);
													list1.add(error);
													return list1;
												}
											}
											else if(token[count+1].equals("Date"))
											{
												if(temp[i].startsWith("'")&&temp[i].endsWith("'"))
												{
													temp[i] = temp[i].replaceAll("'", "");
													if(temp[i].matches("[0-3][0-9][-][0-1][0-9][-][0-9][0-9]"))
													{continue;}
													else
													{
														error = UpdateTableError.errorInDateValue(temp[i]);
														list1.add(error);
														return list1;
													}
												}
												else 
												{
													error =  UpdateTableError.errorInDateValue(temp[i]);
													list1.add(error);
													return list1;
												}
											}
										}//end of if(i%2!0)
										
									}//end of for(i)
									int col = count;
									count=0;
									
									token = whereCondition.split("=");							
									if(token[1].startsWith("'")&&token[1].endsWith("'"))
										token[1] = token[1].replaceAll("'", "");
									list1.add(list.get(0));
									for (int j = 1; j < list.size(); j++) {
										temp = list.get(j).split(",");
										for (int j2 = 0; j2 < temp.length; j2++) {
											if(temp[j2].equals(token[1]))
											{
													count++;
													list1.add(list.get(j));
											}//end of if
											
										}//end of for(j2)
									}//end of for(j)
								}//end of if where
								return list1;
							}//end of try
							catch (IOException e)
							{
								e.printStackTrace();
							}
					 }//end of if(fileExists)
					 else
					 {
						 error = UpdateTableError.errorTableNotExist(token[2]);
						 list.add(error);
						 return list;
					 }
									
				}//end of if(where)
				else
				{
					error = UpdateTableError.errorMissingWhereKeyword();
					list.add(error);
					return list;
				}
							
			}//end of else
		}//end of if from
		else
		{	
			error = SelectTableError.errorInFrom(token[3]);
			list.add(error);
			return list;
		}
		return null;
	}//end of function selectAllData
	
	public static List<String> fetchDatabyColumn( String str )
	{
		String[] token = str.split("[ \\s > ]");
		boolean checkSyntex = false;
		list = new ArrayList<String>();
		listError = new ArrayList<String>();
		//List<Integer> ColumnIndex = new ArrayList<Integer>();
		for (int i = 0; i < token.length; i++) {
			if(token[i].equalsIgnoreCase("from"))
			{
				count = i;
				checkSyntex = true;
				break;
			}
		}//end of for loop
		if(checkSyntex)
		{
			if(token[count+1].endsWith(";"))
			{
				token[count+1] = token[count+1].replaceAll(";", "");
				boolean fileExists = false;
				if(TableCreation.useDatabase==null)
					file = new File(dir+"/Databases Folder/nehra");
				else
					file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase);
			
				String[] files = file.list();
				 for (int i = 0; i < files.length; i++) {
					if(files[i].equalsIgnoreCase(token[count+1]+".csv"))
						fileExists = true;
				 }//end of for loop
				 if(fileExists)
				 {
					 if(TableCreation.useDatabase==null)
							fileName = dir+"/Databases Folder/nehra/"+token[count+1]+".csv";
						else
							fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[count+1]+".csv";
					 try 
						{
							BufferedReader br = new BufferedReader(new FileReader(fileName));
							int startCount = 2;
							String row = null;
							while((row = br.readLine())!=null )
							{
								list.add(row);
							}
							br.close();
							if(token[count-1].endsWith(","))
								{
									error = SelectTableError.errorCommaInLastClolumn(token[count-1]);
									listError.add(error);
									return listError;
								}
							else 
								{
								 
								while(startCount< count-1)
								{
									if((token[startCount].endsWith(","))==false)
									{
										error = SelectTableError.errorCommaInClolumn(token[startCount]);
										listError.add(error);
										return listError;									
									}
									startCount++;
								}
								}//end of else
								String[] temp;
								startCount = 2;
								String columns = "";
								while(startCount < count)
								{
									columns = columns.concat(token[startCount]);
									startCount++;
								}//end of while
								temp = columns.split(",");
								String column = list.get(0);
								token = column.split("[ ,]");
								for (int i = 0; i < temp.length; i++) {
										boolean isColumn = false;
									for (int j = 0; j < token.length; j++) {
										if(j%2==0)
										{
											if(temp[i].equalsIgnoreCase(token[j]))
											{
												//ColumnIndex.add(j/2);
												//System.out.print(token[j]+"\t");
												isColumn = true;
												break;
											}
										}//end of if(j%2==0)	
									}//end of for(j)
									if(isColumn==false)
									{
										error = SelectTableError.errorInClolumnName(temp[i]);
										listError.add(error);
										return listError;
									}
								}//end of for(i)
								/*System.out.print("\n");
								for (int k = 1; k < list.size(); k++) {
									temp =  list.get(k).split(",");
									for (int i = 0; i < ColumnIndex.size(); i++) {
										System.out.print(temp[ColumnIndex.get(i)]+"\t");
									}
									System.out.print("\n");
								}*/
								
							return list;
						}catch (IOException e)
						{
							e.printStackTrace();
						} 
				 }//end of if(fileExists)
				 else
				 {
					 error = SelectTableError.errorInTableName(token[count+1]);
					 listError.add(error);
						return listError;
				 }
			}//end of if(;)
			else
			{
				error = SelectTableError.errorInSemicolon(token[count+1]);
				listError.add(error);
				return listError;
			}
			
		}//end of if(checkSyntex)
		else
		{
			error = SelectTableError.errorInFromMissing();
			listError.add(error);
			return listError;
		}
		return null;
		
	}//end of function fetchDatabyColumn()
}//end of class
