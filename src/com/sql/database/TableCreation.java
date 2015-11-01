package com.sql.database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.csvreader.CsvWriter;

public class TableCreation
{
	static String temp;
	static String useDatabase;
	static File file = null;
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	public static String  fileCreate(String str)
	{
		str = str.replaceAll(", ", " ");
		String[] token = str.split("[ > , ]");
		/*for (int i = 0; i < token.length; i++) {
			System.out.println(i);
			System.out.println(token[i]);
		}*/
	try 
	{
		if(useDatabase==null)
			file = new File(dir+"/Databases Folder/nehra");
		else
			file = new File(dir+"/Databases Folder/"+useDatabase);
		String filename = file.getPath()+"/"+token[3]+".csv";
		if(new File(filename).exists())
			return TableError.tableNameAlreadyExist(token[3]);
		else
		{
			CsvWriter writer = new CsvWriter(new FileWriter(filename,true),',');
			for (int i = 0; i < token.length-1; i++) 
			{
				if(i>4)
				{
					if(i%2!=0)
					{
						temp = token[i];
						//System.out.println(temp);
					}
					else
					{
						writer.write(temp+" "+token[i]);
						//System.out.println(temp+" "+"("+token[i]+")");
					}
				}
			}	
			writer.endRecord();
			writer.close();
			return "1 table created.";
		}
		
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	}
}
