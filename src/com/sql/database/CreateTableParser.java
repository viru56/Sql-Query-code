package com.sql.database;

import java.io.File;

public class CreateTableParser {
	 static String[] token;
	 static String[] tokenTemp;
	 static String[] token3;
	 static File file;
	 
	public static String createTable( String str)
	{
		token = str.split("[  >]");
		/*for (int i = 0; i < token.length; i++)
		{
			System.out.println(token[i]);
			System.out.println(i);
		}*/
		if(token[1].equalsIgnoreCase("create"))
		{
			if(token[2].equalsIgnoreCase("table"))
			{
				//-----------------------table name validation----------------//
				if(token[3].endsWith("("))
				{
					return TableError.spaceBeforeOpenParentheses(token[3]);
				}
				if(token[3].matches("[a-zA-Z_0-9]{1,30}+"))
				{
					if(token[3].equalsIgnoreCase("String")||token[3].equalsIgnoreCase("int")||token[3].equalsIgnoreCase("float")||token[3].equalsIgnoreCase("char")||token[3].equalsIgnoreCase("boolean")||token[3].equalsIgnoreCase("Date"))
					{
						return TableError.errorInTableNameDataTypes();
					}
					if( token[3].matches("[0-9]+"))
					{
						return TableError.errorInTableNameNumber();
					}
					if(token[3].matches("[_]+"))
					{
						return TableError.errorInTableNameUnderscor();
					}
					if(token[3].substring(0,1).matches("[0-9]"))
					{
						return TableError.errorInTableNameStartingChar();
					}
					else
					{
						if(token[4].length()>1)
						{
							return TableError.spaceBeforeOpenParentheses(token[4]);
						}
						if(token[4].equals("("))
						{
							if(token[token.length-1].charAt(0)==')')
							{
								if(token[token.length-1].charAt(1)==';')
								{
									//System.out.println(token[token.length-1].length());
									for (int i = 0; i < token.length-1; i++) 
									{
										if(i>4)
										{
											if(i%2==0)
											{
												if(token[i].endsWith(",")||i==(token.length-2))
												{
											
													if(i==(token.length-2))
													{
														if(token[i].endsWith(","))
																return TableError.commaErrorLastDatatype(token[i]);
													}
													if(i!=(token.length-2))
															{
																//token[token.length-2] = token[token.length-2].replaceAll(token[token.length-2], token[token.length-2]+",");
																//System.out.println(token[token.length-2]);
										
																token[i] = token[i].substring(0, token[i].lastIndexOf(','));
															}
													//System.out.println(i+" "+"Datatypes  "+token[i]);
													if(token[i].equals("String")||token[i].equals("int")||token[i].equals("float")||token[i].equals("char")||token[i].equals("boolean")||token[i].equals("Date"))
													{
														//System.out.println(i+" "+"Datatypes  "+token[i]);
													}
													else
														return TableError.datatypeMissing(token[i]);
												}
												else
													return TableError.commaError(token[i]);
											}			
											if(i%2!=0)
											{
												if(token[i].equals("String")||token[i].equals("int")||token[i].equals("float")||token[i].equals("char")||token[i].equals("boolean")||token[i].equals("Date"))
												{
													return TableError.errorInColomnNameDatatype(token[i]);
												}
												if(token[3].matches("[a-zA-Z_0-9]{1,30}+"))
												{
													if( token[3].matches("[0-9]+"))
													{
														return TableError.errorInColumnNameNumber( token[i]);
													}
													if(token[3].matches("[_]+"))
													{
														return TableError.errorInColumnNameUnderscor( token[i] );
													}
													if(token[3].substring(0,1).matches("[0-9]"))
													{
														return TableError.errorInColumnNameStartingChar( token[i] );
													}
												//System.out.println(i+" "+"column Name  "+token[i]);
												}
											}
										}
											
									}//end of for loop
									//String dir = System.getProperty("user.dir");
									//System.out.println(dir);
									//----------------------------------------creating table--------------------------------//
									
									 return TableCreation.fileCreate(str);
									
									/////////////////////////////////////////////////////////////////////////////////////////
								}
								else
									return TableError.semicolonMissingInEnd();
							}
							else
							{
								return TableError.spaceBeforeCloseingParentheses(token[token.length-1]);
							}
							
						}
						else
						{
							return TableError.missingOpenParentheses();
						}				
					}
				}
				else
				{
					if(token[3].length()>30)
					{
						return TableError.errorInTableNameLength();
					}
					return TableError.errorInTableName();
				}
			}
			else
			{
				return TableError.errorInTable(token[2]);
			}
		}
		else
		{
			return TableError.errorInCreate(token[1]);
		}
		//return null;
		
	}
}
