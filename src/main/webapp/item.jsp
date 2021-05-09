<%@ page import="com.Item"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	//insert items
	if (request.getParameter("itemCode") != null) {
		
		Item itemObj = new Item();
		
		String stsMsg = itemObj.insertItem(request.getParameter("itemCode"),
						 request.getParameter("itemName"),
				         request.getParameter("itemPrice"),
				         request.getParameter("itemDesc"));
		
		session.setAttribute("statusMsg", stsMsg);
		
	}
	//update items
	
	if (request.getParameter("itemID") != null) {
		Item itemObj = new Item();
		session.setAttribute("is_in_update_mode", request.getParameter("itemID"));
	}
	
	//setting data
	
	String itemCode;
	String itemName;
	String itemPrice;
	String itemDesc;
	
	if (String.valueOf(session.getAttribute("is_in_update_mode")) == ""|| session.getAttribute("is_in_update_mode") == null) {
		
		itemCode = "";
		itemName = "";
		itemPrice = "";
		itemDesc = "";
		
	} else {
		Item itemObj = new Item();
		
		System.out.println(session.getAttribute("is_in_update_mode"));
		
		String temp[] = itemObj.readSingleItems(Integer.parseInt(String.valueOf(session.getAttribute("is_in_update_mode"))));
		itemCode = temp[0];
		itemName = temp[1];
		itemPrice = temp[2];
		itemDesc = temp[3];
	}
	// insert when click save button
	//update
	
	if (request.getParameter("itemCode") != null && (session.getAttribute("is_in_update_mode") != null)) {
		
			Item itemObj = new Item();
			
			String stsMsg = itemObj.updateItem(Integer.parseInt(String.valueOf(session.getAttribute("is_in_update_mode"))),
					
			request.getParameter("itemCode"), request.getParameter("itemName"), request.getParameter("itemPrice"),request.getParameter("itemDesc"));
			itemCode = "";
			itemName = "";
			itemPrice = "";
			itemDesc = "";
			
			
		session.setAttribute("is_in_update_mode", "");
		session.setAttribute("statusMsg", stsMsg);
		
	} else if (request.getParameter("itemCode") != null) {
		//insert new
		Item itemObj = new Item();
		String stsMsg = itemObj.insertItem(request.getParameter("itemCode"), request.getParameter("itemName"),request.getParameter("itemPrice"), request.getParameter("itemDesc"));
		session.setAttribute("statusMsg", stsMsg);
	
	}
	
	//delete items
	
	if (request.getParameter("hidItemIDDelete") != null) 
 	{ 
	 	Item itemObj = new Item(); 
	 	String stsMsg = itemObj.deleteItem(request.getParameter("hidItemIDDelete")); 
	 	session.setAttribute("statusMsg", stsMsg); 
	} 
	
	
%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/item.js"></script>
</head>
<body>
<div class = "container">
<div class = "row">
<div class = "col">
	<h1>Items Management</h1>
	
	<form id="formItem" name="formItem" method="post" action="item.jsp">
		 Item code:
		 <input id="itemCode" name="itemCode" type="text"
 						class="form-control form-control-sm">
 						
		<br> Item name:
		<input id="itemName" name="itemName" type="text"
 						class="form-control form-control-sm">
 						
		<br> Item price:
		<input id="itemPrice" name="itemPrice" type="text"
 						class="form-control form-control-sm">
 						
 						
		<br> Item description:
		<input id="itemDesc" name="itemDesc" type="text"
						 class="form-control form-control-sm">
						 
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidItemIDSave" name="hidItemIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success">
 		<% out.print(session.getAttribute("statusMsg")); %>
 		
 	</div>
 	<div id = "alertError" class="alert-danger"></div>
	
	<br>
	<%
	 Item itemObj = new Item(); 
	 out.print(itemObj.readItems()); 
	%>

</body>
</html> 