package com.sql.database;

public class UpdateTableError {
	public static String errorTableNotExist( String str )
	{
		return "Error(0801): You have an error in your NanSql Syntax.Table does not Exist  * "+str +"*";
	}
	public static String errorMissingSetKeyword( String str )
	{
		return "Error(0802): You have an error in your NanSql Syntax.missing SET keyword near  * "+str +"*";
	}
	public static String errorMissingWhereKeyword( )
	{
		return "Error(0803): You have an error in your NanSql Syntax.missing *WHERE* keyword.";
	}
	public static String errorCommaInLastClolumn( String str )
	{
		return "Error(0804): You have an error in your NanSql Syntax. Missplaced comma near * "+str +"*";
	}
	public static String errorCommaInClolumn( String str )
	{
		return "Error(0805): You have an error in your NanSql Syntax.(seprate two column by ',' ) missing expression near * "+str +"*";
	}
	public static String errorInEqualSign(String str )
	{
		return "Error(0806): You have an error in your NanSql Syntax.missing equal sign near * "+str +"*";
	}
	public static String errorInWhereCondition( )
	{
		return "Error(0807): You have an error in your NanSql Syntax.Error in where condition.";
	}
	public static String errorInSemicolon( )
	{
		return "Error(0808): You have an error in your NanSql Syntax.End Query with semicolon ;";
	}
	public static String errorInClolumnName( String str )
	{
		return "Error(0809): You have an error in your NanSql Syntax. column name mismatch near * "+str +"*";
	}
	public static String errorInStringValue(String str)
	{
		return "Error(0810): You have an error in your NanSql Syntax.(Datatype is *String* put value in Single qoutes ' ' ). Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInCharValue(String str)
	{
		return "Error(0811): You have an error in your NanSql Syntax.(Datatype is *char* put value in ' ')Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInIntValue(String str)
	{
		return "Error(0812): You have an error in your NanSql Syntax.(Datatype is *int*)Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInFloatValue(String str)
	{
		return "Error(0813): You have an error in your NanSql Syntax.(Datatype is *float*)Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInBooleanValue(String str)
	{
		return "Error(0814): You have an error in your NanSql Syntax.(Datatype is *boolean* only 'true' or 'false' value allowed)Datatype missmatch near  * "+str+" *.";
	}
	public static String errorInDateValue(String str)
	{
		return "Error(0815): You have an error in your NanSql Syntax.(Datatype is *Date* format'19-01-15')Datatype missmatch near  * "+str+" *.";
	}
}
