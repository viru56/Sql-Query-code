package com.sql.database;

public class DropTableErrors 
{
	public static String semicolonMissingInEnd( )
	{
		return "Error(0601): You have an error in your NanSql Syntax. Query not properly ended(';'write in the end )";
	}
	public static String tableNameDoesNotExist( String str )
	{
		return "Error(0602): You have an error in your NanSql Syntax near *"+str+" * .Table name not Exist.";
	}
	
	public static String syntaxErrorInTable( String str )
	{
		return "Error(0603): You have an error in your NanSql Syntax near *"+str+" * .Missing Keyword 'table'.";
	}
}
