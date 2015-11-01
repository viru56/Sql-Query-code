package com.sql.database;

public class InsertTableError {

	public static String errorInInto( String str)
	{
		return "Error(0301): You have an error in your NanSql Syntax. Missing Keyword 'into' in the query near *"+str+" *..." ;
	}
	public static String errorTableNotExist( String str)
	{
		return "Error(0302): Table *"+str+" * does not exist..." ;
	}
	public static String errorInValuesKey( String str)
	{
		return "Error(0303): You have an error in your NanSql Syntax. Missing Keyword 'values' in the query near *"+str+" *..." ;
	}
	public static String errorInValuesKeySpace( String str )
	{
		return "Error(0304): You have an error in your NanSql Syntax near * "+str+" *. Space is required between 'values'keyword and open Parentheses '(' " ;
	}
	public static String spaceafterOpenParentheses( String str )
	{
		return "Error(0305): You have an error in your NanSql Syntax near * "+str+" *. Space is required after open Parentheses '(' ";
	}
	public static String semicolonMissingInEnd( )
	{
		return "Error(0306): You have an error in your NanSql Syntax. Query not properly ended(';'write in the end ).";
	}
	public static String spaceInClosingParentheses( )
	{
		return "Error(0307): You have an error in your NanSql Syntax.Space is Required before Closing Parentheses.";
	}
	public static String columnAndValuesNotEqueal()
	{
		return "Error(0308): You have an error in your NanSql Syntax.Number of values and column mismatch (give Space after comma in values).";
	}
	public static String errorInStringValue(String str)
	{
		return "Error(0309): You have an error in your NanSql Syntax.(Datatype is *String* put value in Single qoutes ' ' ). Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInCharValue(String str)
	{
		return "Error(0310): You have an error in your NanSql Syntax.(Datatype is *char* put value in ' ')Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInIntValue(String str)
	{
		return "Error(0311): You have an error in your NanSql Syntax.(Datatype is *int*)Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInFloatValue(String str)
	{
		return "Error(0312): You have an error in your NanSql Syntax.(Datatype is *float*)Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInBooleanValue(String str)
	{
		return "Error(0313): You have an error in your NanSql Syntax.(Datatype is *boolean* only 'true' or 'false' value allowed)Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInDateValue(String str)
	{
		return "Error(0314): You have an error in your NanSql Syntax.(Datatype is *Date* format'19-01-15')Datatype missmatch near  * "+str+" *.";
	}
	public static String tooManyValues()
	{
		return "Error(0315): You have an error in your NanSql Syntax. too many values";
	}
}
