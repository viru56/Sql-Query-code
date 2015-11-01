package com.sql.database;

public class DescTableError {
	public static String errorInSemicolon( String str )
	{
		return "Error(0501): You have an error in your NanSql Syntax.(Semicolon not fond.) Error near * "+str +"*...";
	}
	public static String tableNotExist( String str )
	{
		return "Error(0500): You have an error in your NanSql Syntax.Table not Exists * "+str +" *";
	}
}
