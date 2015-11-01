package com.sql.database;

import java.io.BufferedReader;
import java.io.File;


public class DropTable 
{
	 static String[] token;
	 static String[] tokenTemp;
	 static String[] token3;
	 static File file;
	 static BufferedReader br;
	 static String dir = System.getProperty("user.dir").replace('\\', '/');
	
	public static String dropTable( String str)
	{
		token = str.split("[ \\s > ]");
			if(token[2].equalsIgnoreCase("table"))
			{				
				if(token[3].endsWith(";"))
				{
					token[3] = token[3].replaceAll(";", "");
					boolean fileExist = false;
					if(TableCreation.useDatabase==null)
						file = new File(dir+"/Databases Folder/nehra");
					else
						file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase);
					
					String[] files = file.list();
						for (int i = 0; i < files.length; i++) 
						{
							if(files[i].equalsIgnoreCase(token[3]+".csv"))
								fileExist = true;
						}
						if(fileExist)
						{
							if(TableCreation.useDatabase==null)
								file = new File(dir+"/Databases Folder/nehra/"+token[3]+".csv");
							else
								file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[3]+".csv");
							file.delete();
							return String.format("1 table dropped...");
						}//end of if(file exist )
						else
							return DropTableErrors.tableNameDoesNotExist(token[3]);
					}//end of if(;)
					else
						return DropTableErrors.semicolonMissingInEnd();
							
				}//end of if(table)
			else
				return DropTableErrors.syntaxErrorInTable(token[2]);
	}
}