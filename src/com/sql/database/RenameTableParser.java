package com.sql.database;

import java.io.File;

public class RenameTableParser {
	static File file;
	static String fileName;
	static String newfileName;
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	public static String renameTable( String str )
	{
		String[] token = str.split("[ \\s > ]");
		if(token[3].equalsIgnoreCase("to"))
		{
			if(token[4].endsWith(";"))
			{
				token[4] = token[4].replaceAll(";", "");
				if(token[4].matches("[a-zA-Z_0-9]{1,30}+"))
				{
					if(token[4].equalsIgnoreCase("String")||token[4].equalsIgnoreCase("int")||token[4].equalsIgnoreCase("float")||token[4].equalsIgnoreCase("char")||token[4].equalsIgnoreCase("boolean")||token[4].equalsIgnoreCase("Date"))
					{
						return TableError.errorInTableNameDataTypes();
					}
					if( token[4].matches("[0-9]+"))
					{
						return TableError.errorInTableNameNumber( );
					}
					if(token[4].matches("[_]+"))
					{
						return TableError.errorInTableNameUnderscor( );
					}
					if(token[4].substring(0,1).matches("[0-9]"))
					{
						return TableError.errorInTableNameStartingChar( );
					}
				}
				else
				{
					if(token[4].length()>30)
					{
						return TableError.errorInTableNameLength( );
					}
					return TableError.errorInTableName( );
				}
				
				boolean fileExists = false;
				if(TableCreation.useDatabase==null)
					file = new File(dir+"/Databases Folder/nehra");
				else
					file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase);
			
				String[] files = file.list();
				 for (int i = 0; i < files.length; i++) {
					if(files[i].equalsIgnoreCase(token[2]+".csv"))
						fileExists = true;
				 }//end of for loop
				 if(fileExists)
				 {
					 if(TableCreation.useDatabase==null)
							fileName = dir+"/Databases Folder/nehra/"+token[2]+".csv";
						else
							fileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[2]+".csv";
					 
					 if(TableCreation.useDatabase==null)
							 newfileName = dir+"/Databases Folder/nehra/"+token[4]+".csv";
						else
							newfileName = dir+"/Databases Folder/"+TableCreation.useDatabase+"/"+token[4]+".csv";
					
					 File oldName = new File(fileName);
					 File newNmae = new File(newfileName);
				
					 if(oldName.renameTo(newNmae))
					 {
						 return "Table renamed.";
					 }
					 else
						 return DatabaseError.errorInCreation();
				 }//end of (fileExists)
				 else
					return RenameTableError.errorInTableName(token[2]); 
			}//end of token[4].endsWith(";")
			else
				return RenameTableError.errorInSemicolon(token[4]);
		}//end of if(as)
		else
			return RenameTableError.errorInToKeyord();
	}
	
}
