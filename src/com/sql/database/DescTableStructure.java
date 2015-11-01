package com.sql.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class DescTableStructure {

	static File file;
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	public static String descTable( String str )
	{
		String[] token = str.split("[ \\s >]");
		if(token[2].endsWith(";"))
		{
			token[2] = token[2].replaceAll(";", "");
			if(TableCreation.useDatabase==null)
				file = new File(dir+"/Databases Folder/nehra/"+token[2]+".csv");
			else
				file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[2]+".csv");
			if(file.exists())
			{
				try {
					FileInputStream fs = new FileInputStream(file);
					BufferedReader br = new BufferedReader(new InputStreamReader(fs));
					String description = br.readLine();
					br.close();
					return description;
					}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
				return DescTableError.tableNotExist(token[2]);
			
		}//end of if (;)
		else
			return DescTableError.errorInSemicolon(token[2]);
		return null;	
	}
}
