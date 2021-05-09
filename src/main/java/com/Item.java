package com;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

	
	

	public class Item {

		public Connection connect()
		{
		 Connection con = null;

		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");
		 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/items ",
		 "root", "");
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
		 e.printStackTrace();
		 }

		 return con;
		}
		
		public String insertItem(String code, String name, String price, String desc) {
			 String output = ""; 
			 
			 try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database"; 
			 } 
			 // create a prepared statement
			 String query = " insert into item(itemID,itemCode,itemName,itemPrice,itemDesc)values (?, ?, ?, ?, ?)"; 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 // binding values
			 preparedStmt.setInt(1, 0); 
			 preparedStmt.setString(2, code); 
			 preparedStmt.setString(3, name); 
			 preparedStmt.setDouble(4, Double.parseDouble(price)); 
			 preparedStmt.setString(5, desc);

			//execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 output = "Inserted successfully"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while inserting"; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
			}

			public String readItems()
			{ 
				String output = ""; 
				try
				{ 
				 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for reading."; 
					 } 
					 
			 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>Item Code</th>" 
								 +"<th>Item Name</th><th>Item Price</th>"
								 + "<th>Item Description</th>" 
								 + "<th>Update</th><th>Remove</th></tr>"; 
					 
					 String query = "select * from item"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
			 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String itemID = Integer.toString(rs.getInt("itemID")); 
						 String itemCode = rs.getString("itemCode"); 
						 String itemName = rs.getString("itemName"); 
						 String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
						 String itemDesc = rs.getString("itemDesc"); 
						 
			 // Add a row into the html table
						 output += "<tr><td><input id ='hidItemIDUpdate' name ='hidItemIDUpdate'type='hidden' value='" + itemID + " '>" 
						 			+ itemCode + "</td>";
						
						 output += "<td>" + itemName + "</td>"; 
						 output += "<td>" + itemPrice + "</td>"; 
						 output += "<td>" + itemDesc + "</td>";
			 // buttons
						 output += "<td><input name='btnUpdate' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td><form method='post' action='item.jsp'><input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'> <input name='hidItemIDDelete' type='hidden' value='" + itemID + "'>" + "</form></td></tr>";  
					 } 
					 con.close(); 
					 
			 // Complete the html table
					 output += "</table>"; 
			 } 
				catch (Exception e) 
				{ 
				 output = "Error while reading the items."; 
				 System.err.println(e.getMessage()); 
				} 
				return output; 
			}


			public String[] readSingleItems(int itemID) {
				String output[] = new String[4];

				try {
					Connection con = connect();
					if (con == null) {
						System.err.println("Error while connecting to the database for reading.");
					}

					String query = "select * from item where itemID = ? ";
					PreparedStatement stmt = con.prepareStatement(query);
					stmt.setInt(1, itemID);

					ResultSet rs = stmt.executeQuery();
					// iterate through the rows in the result set
					while (rs.next()) {
						output[0] = (rs.getString("itemCode"));
						output[1] = (rs.getString("itemName"));
						output[2] = (rs.getString("itemPrice"));
						output[3] = (rs.getString("itemDesc"));
					}

				} catch (Exception e) {
					// output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}

				return output;
			}

			public String updateItem(int itemID,String itemCode,String itemName,String itemPrice,String itemDesc)
			{ 
				String output = ""; 
				try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for updating."; 
					 } 
				 // create a prepared statement
					 String query = "update item set itemName = ?, itemPrice = ?, itemDesc = ? where itemID = ?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setString(1, itemCode);
					 preparedStmt.setString(2, itemName);
					 preparedStmt.setDouble(3, Double.parseDouble(itemPrice));
					 preparedStmt.setString(4, itemDesc);
					 preparedStmt.setInt(5, itemID);


					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Updated successfully"; 
				 } 
				catch (Exception e) 
				 { 
					 output = "Error while updating the item."; 
					 System.err.println(e.getMessage()); 
				 } 
				return output; 
			}

			public String deleteItem(String itemID)
			{ 
				String output = ""; 
				try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for deleting."; 
					 } 
				 // create a prepared statement
					 String query = "delete from item where itemID=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(itemID)); 

					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 output = "Deleted successfully"; 
				 } 
				catch (Exception e) 
				 { 
					 output = "Error while deleting the item."; 
					 System.err.println(e.getMessage()); 
				 } 
				return output; 
			}


		
	}
	

