package com.sql.database;
public class DatabaseError
{
	public static String semicolonError( )
	{
		return String.format("Error(0001): You have error in your NanSql Syntax. semicolon missing at the end.");
	}
	
	public static String errorInQuery( String str)
	{
		return String.format("Error(0002): You have an error in your NanSql Syntax . Errror in the query '"+str+"'");
	}
	
	public static String errorInCreate( String str)
	{
		return String.format("Error(0003): You have an error in your NanSql Syntax. Errror in the query near  *"+str+"* ...");
	}
	
	public static String errorInDatabase( String str)
	{
		return String.format("Error(0004): You have an error in your NanSql Syntax. Errror in the query near  *"+str+"* ...");
	}
	public static String errorInCreation( )
	{
		return String.format("Error: (0000) Unknown Error..");
	}
	public static String errorInShow( String str)
	{
		return String.format("Error(00011): You have an error in your NanSql Syntax. Errror in the query near  *"+str+"* ...");
	}
	public static String errorInShowDatabases( String str)
	{
		return String.format("Error(0001): You have an error in your NanSql Syntax. Errror in the query near  *"+str+"* ...");
	}
	
	public static String errorInClear()
	{
		return String.format("Error(1111): Unexpected Symbol... ;* ");
	}
}
