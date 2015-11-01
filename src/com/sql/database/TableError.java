package com.sql.database;

public class TableError
{
	public static String errorInCreate( String str)
	{
		return  "Error(0100): You have an error in your NanSql Syntax. Errror in the query near *"+str+" *..." ;
	}
	public static String errorInTable( String str)
	{
		return  "Error(0101): You have an error in your NanSql Syntax. Errror in the query near *"+str+" *...";
	}
	public static String errorInTableName( )
	{
		return  "Error(0111): You have an error in your NanSql Syntax.In table name special character are not allowed.";
	}
	public static String errorInTableNameLength( )
	{
		return  "Error(0112): You have an error in your NanSql Syntax. Table name should less than 30 characters.";
	}
	public static String errorInTableNameNumber( )
	{
		return  "Error(0113): You have an error in your NanSql Syntax.In table name One Alphabet character is Compulsory.";
	}
	public static String errorInTableNameUnderscor( )
	{
		return  "Error(0114): You have an error in your NanSql Syntax.In table name One Alphabet character is Compulsory.";
	}
	public static String errorInTableNameStartingChar( )
	{
		return  "Error(0115): You have an error in your NanSql Syntax. table name can not start with digits .";
	}
	public static String errorInTableNameDataTypes( )
	{
		return  "Error(0116): You have an error in your NanSql Syntax. table name should not DataTypes.";
	}
	public static String missingOpenParentheses( )
	{
		return  "Error(0117): You have an error in your NanSql Syntax.Open parentheses is not found.";
	}
	public static String spaceBeforeOpenParentheses( String str )
	{
		return  "Error(0118): You have an error in your NanSql Syntax near * "+str+" * .Space is rquired between table name and Open parentheses.";
	}
	public static String commaError( String str )
	{
		return  "Error(0119): You have an error in your NanSql Syntax near *"+str+" *.comma not found.";
	}
	public static String commaErrorLastDatatype( String str )
	{
		return  "Error(0120): You have an error in your NanSql Syntax near *"+str+" *.comma not required.";
	}
	public static String datatypeMissing( String str )
	{
		return  "Error(0121): You have an error in your NanSql Syntax near *"+str+" *.DataType not found.";
	}
	public static String semicolonMissingInEnd( )
	{
		return  "Error(0122): You have an error in your NanSql Syntax. Query not properly ended(';'write in the end ).";
	}
	public static String tableNameAlreadyExist( String str )
	{
		return  "Error(0123): You have an error in your NanSql Syntax near *"+str+" * .Table name Already Exist.";
	}
	public static String errorInColomnNameDatatype( String str )
	{
		return  "Error(0150): You have an error in your NanSql Syntax near *"+str+" * .Colomn name should not DataTypes.";
	}
	public static String errorInColumnNameNumber( String str )
	{
		return  "Error(0151): You have an error in your NanSql Syntax  near *"+str+" * .In Column name One Alphabet character is Compulsory.";
	}
	public static String errorInColumnNameUnderscor( String str )
	{
		return  "Error(0152): You have an error in your NanSql Syntax near *"+str+" * .In Column name One Alphabet character is Compulsory.";
	}
	public static String errorInColumnNameStartingChar( String str )
	{
		return  "Error(0153): You have an error in your NanSql Syntax near *"+str+" * . Column name can not start with digits .";
	}
	public static String spaceAfterOpenParentheses( String str )
	{
		return  "Error(0154): You have an error in your NanSql Syntax near *"+str+" * .Space is rquired between Column name and Open parentheses.";
	}
	public static String spaceBeforeCloseingParentheses( String str )
	{
		return  "Error(0155): You have an error in your NanSql Syntax near *" +str+ " * .Space is rquired between Column name and Closing parentheses.";
	}
	
}
