package com.sql.database;

import java.io.File;

public class SelectTable 
{
	static String dir = System.getProperty("user.dir").replace('\\', '/');
	static String[] err = new String[1];
	public static String[] selectAllTable( String str )
	{
		String[] token = str.split("[ \\s > ]");
		/*for (int i = 0; i < token.length; i++) {
			System.out.println(token[i]);
			System.out.println(i);
		}*/
		
		if(token[2].equalsIgnoreCase("tables"))
		{
			//System.out.println(token.length);
			if(token[3].equalsIgnoreCase("from"))
			{
				
				if(token[4].endsWith(";"))
				{
					token[4] = token[4].replaceAll(";", "");
					File file = new File(dir+"/Databases Folder/"+token[4]);
					if(file.exists())
					{
							String[]files = file.list();
							return files;
						/*for (int i = 0; i < files.length; i++) {
								System.out.println(files[i].replaceAll(".csv", ""));
						}*/
					}
					else
					{
						err[0] = SelectTableError.errorInDatabaseName(token[4 ]);
						return err;
					}
				}//end of if (;)
				else
				{
					err[0] = SelectTableError.errorInFrom(token[3]);
					return err;
				}
			}//end of if (from)
			else
			{
				err[0] = SelectTableError.errorInFrom(token[3]);
				return err;
			}
		}//end of if(tables)
		else
		{
			err[0] = SelectTableError.errorInTables(token[2]);
			return err;
		}
		//return null;
	}//end of function selectAllTable
}//end of class
