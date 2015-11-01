package com.sql.database;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;

public class _addKeyListener extends KeyAdapter implements KeyListener
{
	public _Frame frame;
	public String str;
	public String[] showdb;
	public int count = 0;
	public CsvReader csvReader;
	public	List<String> list = new ArrayList<String>();
	public _addKeyListener(_Frame frame) {
		this.frame = frame;
	}

	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			String str1 = this.frame.jTextArea.getText();
			str = str1.substring(str1.lastIndexOf("NanSql>"));
			str = str.replaceAll("[ ]+", " ");
			str = str.replaceAll("NanSql>[ ]+", "NanSql>");
			String[] token = str.split("[ \\s > ]");
			/*for (int i = 0; i < token.length; i++) {
				System.out.println(token[i]);
				System.out.println(i);
			}*/
			
			//---------------------------only press enter--------------------------------//

			if(token.length==1)
			{
				this.frame.jTextArea.append("NanSql>");
				
			}
			
			//---------------------------------Clear Screen------------------------------------------//

			else if(token[1].equalsIgnoreCase("clear"))
			{
				this.frame.jTextArea.setText(null);
				this.frame.jTextArea.append("NanSql>");
			}
			else if(token[1].equalsIgnoreCase("clear;"))
			{
				this.frame.jTextArea.append(DatabaseError.errorInClear());
				this.frame.jTextArea.append("\nNanSql>");
			}
			//---------------------------exit-------------------------------------------//
			
			else if( token[1].equalsIgnoreCase("exit")||token[1].equalsIgnoreCase("exit;") )
			{
				System.exit(0);
			}

			//---------------------------show all databases-----------------------------///

			else if(token[1].equalsIgnoreCase("show"))
			{
				if(token.length==3)
				{
					showdb = DatabaseCSUParser.showDatabases(str);
					if(showdb.length==1)
					{
						this.frame.jTextArea.append(showdb[0]);
						this.frame.jTextArea.append("\nNanSql>");
					}
					else
					{
						this.frame.jTextArea.append("Databases..");
						for (int i = 0; i < showdb.length; ++i) {
							
							count = i;
							//System.out.println("\n"+showdb[i]);
							this.frame.jTextArea.append("\n"+showdb[i]);					
						}
						//System.out.println(showdb.length);
						this.frame.jTextArea.append("\n");
						this.frame.jTextArea.append("Total Database "+(count+1));
						this.frame.jTextArea.append("\nNanSql>");
					}
				}
				else
				{
					this.frame.jTextArea.append(DatabaseError.errorInQuery(str));
					this.frame.jTextArea.append("\nNanSql>");
				}
					
			}
			
			
			//--------------------create database----------------------------------------//
			
			
			else if(token[1].equalsIgnoreCase("create"))
			{
				if(token[2].equalsIgnoreCase("table"))
				{
					this.frame.jTextArea.append(CreateTableParser.createTable(str));
					this.frame.jTextArea.append("\nNanSql>");
				}
				else if(token[2].equalsIgnoreCase("database"))
				{
					String res = DatabaseCSUParser.createDatabase(str);
					if(res==null)
					this.frame.jTextArea.setText("NanSql>");
					else
					{
						this.frame.jTextArea.append(res);
						this.frame.jTextArea.append("\nNanSql>");
					}
				}
				else
				{
					this.frame.jTextArea.append(DatabaseError.errorInCreate(token[2]));
					this.frame.jTextArea.append("\nNanSql>");
				}
			}
			//-----------------------Insert Values-------------------------------------//
			else if(token[1].equalsIgnoreCase("insert"))
			{
				this.frame.jTextArea.append(InsertTableParser.insertValuesIntoTable(str));
				this.frame.jTextArea.append("\nNanSql>");
			}
//----------------------------Drop table-------------------------------------------------//
			
			//NanSql>drop table user;
			else if(token[1].equalsIgnoreCase("drop"))
			{
				this.frame.jTextArea.append(DropTable.dropTable(str));
				this.frame.jTextArea.append("\nNanSql>");
			}
			//-----------------------select all tables----------------------------------//
			else if(token[1].equalsIgnoreCase("select"))
			{
				if(token.length<5)
				{
					this.frame.jTextArea.append(SelectTableError.syntexEttror());
					this.frame.jTextArea.append("\nNanSql>");
				}
				//---------------select data from table------------------//
				
				if(token[2].equals("*"))
				{
					list = FetchDataFromTable.selectAllData(str);
					if(list.get(0).startsWith("Error"))
						this.frame.jTextArea.append(list.get(0));
					else if((!list.get(0).startsWith("Error"))&&list.size()==1)
						this.frame.jTextArea.append("Table is empty.");
					else
					{
						String column = list.get(0);
						token = column.split("[ ,]");
						for (int i = 0; i < token.length; i++) {
							if(i%2==0)
							{
								this.frame.jTextArea.append(token[i]+"\t");
							}//end of if(i%2==0)
						}//end of for loop
						String minus = "_";
						for (int i = 0; i <= (list.get(0).length()); i++) {
							String temp = "_";
							minus = minus.concat(temp);
						}
						this.frame.jTextArea.append("\n"+minus+"\n");
						for (int j = 1; j < list.size(); j++) {
							column = list.get(j);
							token = column.split(",");
							for (int i = 0; i < token.length; i++) {
									
									this.frame.jTextArea.append(token[i]+"\t");
							}
							this.frame.jTextArea.append("\n");
						}
						this.frame.jTextArea.append(list.size()-1+" row selected.");
					}
					this.frame.jTextArea.append("\nNanSql>");
				}//end of if (*)
				//---------------------------------------------------------------------//
				else if(token[2].equalsIgnoreCase("tables")||token[2].equalsIgnoreCase("table") )
				{
					showdb = SelectTable.selectAllTable(str);
					if(showdb.length==0)
						
					{
						this.frame.jTextArea.append("no table selected...");
						this.frame.jTextArea.append("\nNanSql>");
					}
					else if(showdb.length==1)
					{
						if(showdb[0].endsWith(".csv"))
						{
							this.frame.jTextArea.append("TName");
							this.frame.jTextArea.append("\n-----------");
							this.frame.jTextArea.append("\n"+showdb[0].replaceAll(".csv", ""));
							this.frame.jTextArea.append("\n1 table selected.");
						}
						else
							this.frame.jTextArea.append(showdb[0]);
						this.frame.jTextArea.append("\nNanSql>");
					}//end of if (length==1)
					else
					{
						this.frame.jTextArea.append("TName");
						this.frame.jTextArea.append("\n-----------");
						for (int i = 0; i < showdb.length; ++i) {
							if(showdb[i].endsWith(".csv"))
							{
								count = i;
								this.frame.jTextArea.append("\n"+showdb[i].replaceAll(".csv", ""));
							}
						}
						this.frame.jTextArea.append("\n"+(count+1)+" tables selected. ");
						this.frame.jTextArea.append("\nNanSql>");
					}
				}//end of else if(tables)
				//------------------------select column from table----------///
				
				else if(token.length >= 5)
				{
					list = FetchDataFromTable.fetchDatabyColumn(str);
					if(list.get(0).startsWith("Error"))
						this.frame.jTextArea.append(list.get(0));
					else if((!list.get(0).startsWith("Error"))&&list.size()==1)
						this.frame.jTextArea.append("Table is empty.");
					else
					{
						List<Integer> ColumnIndex = new ArrayList<Integer>();
						for (int i = 0; i < token.length; i++) {
							if(token[i].equalsIgnoreCase("from"))
							{
								count = i;
								break;
							}
						}//end of for(find from)
						
						String[] temp;
						int startCount = 2;
						String columns = "";
						while(startCount < count)
						{
							columns = columns.concat(token[startCount]);
							startCount++;
						}//end of while
						temp = columns.split(",");
						String column = list.get(0);
						token = column.split("[ ,]");
						for (int i = 0; i < temp.length; i++) {
							for (int j = 0; j < token.length; j++) {
								if(j%2==0)
								{
									if(temp[i].equalsIgnoreCase(token[j]))
									{
										ColumnIndex.add(j/2);
										this.frame.jTextArea.append(token[j]+"\t");
										break;
									}
								}	
							}//end of for(j)
							
						}//end of for(i)
						String minus = "_";
						for (int i = 0; i <= (ColumnIndex.size()*12); i++) {
							String add = "_";
							minus = minus.concat(add);
						}
						this.frame.jTextArea.append("\n"+minus+"\n");
						//System.out.print("\n");
						for (int k = 1; k < list.size(); k++) {
							temp =  list.get(k).split(",");
							for (int i = 0; i < ColumnIndex.size(); i++) {
								this.frame.jTextArea.append(temp[ColumnIndex.get(i)]+"\t");
							}
							this.frame.jTextArea.append("\n");
						}
						this.frame.jTextArea.append(list.size()-1+" row selected.");
					}//end of else
					this.frame.jTextArea.append("\nNanSql>");
				}//end of if else (token.length >= 5)
			}//end of if(select)		
			
			//----------------------------desc Table Structure--------------------//
			
			else if(token[1].equalsIgnoreCase("desc"))
			{
				String temp = DescTableStructure.descTable(str);
				if(temp.startsWith("Error"))
					this.frame.jTextArea.append(temp+"\n");
				else
				{
					this.frame.jTextArea.append("\tColumn Name\t\tType\n\t----------------------------------------------\n");
					showdb = temp.split("[ ,]");
					for (int i = 0; i < showdb.length; i++) {
						
						if(i%2==0)
						{
							temp = showdb[i];
						}
						if(i%2!=0)
						{
							this.frame.jTextArea.append("\t"+temp+"\t\t"+showdb[i]+"\n");
						}
						
					}//end of for loop
				}
				this.frame.jTextArea.append("NanSql>");
			}
			//-------------------------update table--------------------------------------//
			else if(token[1].equalsIgnoreCase("update"))
			{
				String update = UpdateTableParser.updateTable(str);
				if(update!=null)
					this.frame.jTextArea.append(update);
				else
					this.frame.jTextArea.append("0 row updated.");
				this.frame.jTextArea.append("\nNanSql>");
			}//end of else if(update)
			//-------------------------delete table--------------------------------------//
			
			else if(token[1].equalsIgnoreCase("delete"))
			{
				String delete = DeleteTableParser.deleteTable(str);
				if(delete!=null)
					this.frame.jTextArea.append(delete);
				else
					this.frame.jTextArea.append("0 row deleted.");
				this.frame.jTextArea.append("\nNanSql>");
			}//end of else if(delete)
			//-------------------------rename table--------------------------------------//
			else if(token[1].equalsIgnoreCase("rename"))
			{
				this.frame.jTextArea.append(RenameTableParser.renameTable(str));
				this.frame.jTextArea.append("\nNanSql>");
			}
			//----------------------changing database---(use)--------------------------//
			else if(token[1].equalsIgnoreCase("use"))
			{
				if(token.length==3)
				{
					this.frame.jTextArea.append(DatabaseCSUParser.useDatabase(str));
					this.frame.jTextArea.append("\nNanSql>");
				}
				else
				{
					this.frame.jTextArea.append(DatabaseError.errorInCreate(str));
					this.frame.jTextArea.append("\nNanSql>");
				}
			}
			else
			{
				this.frame.jTextArea.append(DatabaseError.errorInCreate(token[1]));
				this.frame.jTextArea.append("\nNanSql>");
			}
			//////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	/*public void keyTyped(KeyEvent e) {
	
	}*/
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
		{
				String str1 = this.frame.jTextArea.getText();
				str = str1.substring(str1.lastIndexOf("NanSql>"));
				if(str.matches("NanSql>"))
					this.frame.jTextArea.append(" ");
		}
		/*if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			String str1 = this.frame.jTextArea.getText();
			//System.out.println(str1);
			//str = str1.substring(0, str1.indexOf(';'));
			//System.out.println(str);
				this.frame.jTextArea.setText(str);
		}*/
		/*if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
				String str1 = this.frame.jTextArea.getText();
				str = str1.substring(str1.lastIndexOf("NanSql>"));
				if(str.matches("NanSql>"))
					System.out.println("left key");
					this.frame.jTextArea.append(" ");
		}*/
		/*if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			String str1 = this.frame.jTextArea.getText();
			str = str1.substring(str1.lastIndexOf("NanSql>"));
			if(str.matches("NanSql>"))
				this.frame.jTextArea.append(" ");
			//System.out.println("Down");
		}*/
	
	}
}
