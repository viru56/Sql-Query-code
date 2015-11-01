package com.sql.database;

public class SelectTableError
{

	public static String syntexEttror( )
	{
		return "Error(0400): You have an error in your NanSql Syntax.";
	}
	public static String errorInTables(String str )
	{
		return "Error(0401): You have an error in your NanSql Syntax. Missing Keyword ' tables ' in the Query near ' "+str +" '";
	}
	public static String errorInFrom( String str )
	{
		return "Error(0402): You have an error in your NanSql Syntax.(Missing Keyword 'from') Error near * "+str +"*...";
	}
	public static String errorInSemicolon( String str )
	{
		return "Error(0403): You have an error in your NanSql Syntax.(Semicolon not fond.) Error near * "+str +"*...";
	}
	public static String errorInDatabaseName( String str )
	{
		return "Error(0404): You have an error in your NanSql Syntax.Database not Exists  * "+str +"*";
	}
	public static String errorInTableName( String str )
	{
		return "Error(0405): You have an error in your NanSql Syntax.Table does not Exist  * "+str +"*";
	}
	public static String errorInFromMissing( )
	{
		return "Error(0406): You have an error in your NanSql Syntax.( Keyword 'from' is not found.)";
	}
	public static String errorCommaInLastClolumn( String str )
	{
		return "Error(0407): You have an error in your NanSql Syntax. Missplaced comma near * "+str +"*";
	}
	public static String errorCommaInClolumn( String str )
	{
		return "Error(0408): You have an error in your NanSql Syntax. missing expression near * "+str +"*";
	}
	public static String errorInClolumnName( String str )
	{
		return "Error(0409): You have an error in your NanSql Syntax. column name mismatch near * "+str +"*";
	}
}