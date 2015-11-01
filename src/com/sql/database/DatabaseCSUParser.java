package com.sql.database;

import java.io.File;

public class DatabaseCSUParser
{
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	static File file;
	static String[] token;
	static String[] err = new String[1];
	static String db;
	//----------------------------------------show database-----------------------------------------//

	public static String[] showDatabases( String str)
	{
		String[] token = str.split("[\\s > ]");
		if(token.length==3)
		{
			if(token[2].endsWith(";"))
			{
				String[] temp = token[2].split("[ ; ]");
				token[2]=temp[0];
				if(token[2].equalsIgnoreCase("databases"))
					{
						file = new File(dir+"/Databases Folder");
						//file = new File("C:/Users/Virender Balwan/Desktop/cdacprject/Databases Folder");
						String[]dirs = file.list();
						return dirs;
					}
					else
					{
						err[0]  = DatabaseError.errorInDatabase(token[2]);
						return err;
					}
			}
			else
			{
				err[0]  = DatabaseError.semicolonError( );
				return err;
			}
		}
		return null;
			
	}

	//------------------------------------create database--------------------------------------------//

	public static String createDatabase(String str)
	{
		String[] token = str.split("[\\s > ]");
		
		if(token.length==4)
			{
				if(token[3].endsWith(";"))
				{
					String[] path =  token[3].split("[ ; ]");
					if(token[1].equalsIgnoreCase("create"))
					{
							if(token[2].equalsIgnoreCase("database"))
							{
								/////////////////
								boolean result = false;
								//file = new File("C:\\Users\\Virender Balwan\\Desktop\\cdacprject\\Databases Folder");
								file = new File(dir+"/Databases Folder");
								String[]dirs = file.list();
								for (int i = 0; i < dirs.length; i++) {
									if(path[0].equals(dirs[i]))
										result=true;
								}
								if(result==true)
								return String.format("Database is already exist...");
								else
									{
										//file = new File("C:\\Users\\Virender Balwan\\Desktop\\cdacprject\\Databases Folder\\"+path[0]);
										file = new File(dir+"/Databases Folder/"+path[0]); 
										boolean res = file.mkdir();
										 if(res==true)
										 {
											 db = path[0];
											 return "database created.. ";
										 }
										 else
											 return DatabaseError.errorInCreation();
									}
							}
							else
								return DatabaseError.errorInDatabase(token[2]);
					}
					else
						return DatabaseError.errorInShow(token[1]);
				}
				else
					return DatabaseError.semicolonError();
			}
		else
			return DatabaseError.errorInQuery(str);
		}
	//---------------------------------use database--------------------------------//
	public static String useDatabase(String str)
	{
		String[] token = str.split("[\\s > ]");
		if(token.length==3)
		{
			if(token[2].endsWith(";"))
			{
				boolean result = false;
				String[] path =  token[2].split("[ ; ]");
				TableCreation.useDatabase = path[0];
				//file = new File("C:\\Users\\Virender Balwan\\Desktop\\cdacprject\\Databases Folder");
				file = new File(dir+"/Databases Folder");
				String[]dirs = file.list();
				for (int i = 0; i < dirs.length; i++)
				{
					if(path[0].equals(dirs[i]))
						result=true;
				}
				if(result==true)
				return "Database changed...";
				else
					return "Error: Unknown Database "+path[0];
			}
			else
				return DatabaseError.semicolonError();
		}
		else
			return DatabaseError.errorInQuery(str);
	}
}
