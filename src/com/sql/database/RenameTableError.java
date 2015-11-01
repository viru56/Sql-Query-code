package com.sql.database;

public class RenameTableError {
	public static String errorInToKeyord()
	{
		return "Error(0701): You have an error in your NanSql Syntax. Missing Keyword * to *. ";
	}
	public static String errorInSemicolon( String str )
	{
		return "Error(0702): You have an error in your NanSql Syntax.(Semicolon not fond.) Error near * "+str +"*";
	}
	public static String errorInTableName( String str )
	{
		return "Error(0703): You have an error in your NanSql Syntax.Table not Exists  * "+str +"*";
	}

}
