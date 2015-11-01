package com.sql.database;

import java.io.File;

public class InsertTableParser {
	
	static String dir = System.getProperty("user.dir").replace('\\', '/');

	public static String insertValuesIntoTable( String str )
	{
		String[] token = str.split("[  >]");
		//insert into registration ( "virender", 'mail', 4545, 788.12 );
		/*for (int i = 0; i < token.length; i++)
		{
			System.out.println(token[i]);
			System.out.println(i);
		}*/
		//System.out.println(token[2].length());
		if(token[2].equalsIgnoreCase("into"))
		{
			File file;
			boolean fileExists = false;
			
			if(TableCreation.useDatabase==null)
			{
				file = new File(dir+"/Databases Folder/nehra");
			}
			else
				file = new File(dir+"/Databases Folder/"+TableCreation.useDatabase);
			String[] files = file.list();
			 for (int i = 0; i < files.length; i++) {
				if(files[i].equalsIgnoreCase(token[3]+".csv"))
					fileExists = true;
			 }//end of for loop
			 
			 if(fileExists)
			 {
				 //System.out.println(token[3]+".csv");
				 if(token[4].endsWith("("))
					 return InsertTableError.errorInValuesKeySpace(token[4]);
				 if(token[4].equalsIgnoreCase("values"))
					{
					 if(token[5].length()>1)
							return InsertTableError.spaceafterOpenParentheses(token[5]);
					 
					 //System.out.println(token[token.length-1].length());
					 if(token[token.length-1].charAt(0)==')')
						{
							if(token[token.length-1].charAt(1)==';')
							{  
							
								return InsertDataIntoTable.insertData(str);
								
								
							}//end of if ';'
							else
								return InsertTableError.semicolonMissingInEnd();
						}//end of if ')'
					 else
						 return InsertTableError.spaceInClosingParentheses();
					}//end of values
				 else
					 return InsertTableError.errorInValuesKey(token[4]);
			 }//end of fileExists
			 else
				 return InsertTableError.errorTableNotExist(token[3]);
			 
		}//end of into
		else
			return InsertTableError.errorInInto(token[2]);
		//return null;
		
	}//end of  function insertValuesIntoTable
	
}//end of class
