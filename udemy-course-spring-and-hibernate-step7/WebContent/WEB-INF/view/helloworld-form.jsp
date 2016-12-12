<%@ page import="com.jvanhouteghem.springmvc.Student"  %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Hello World - Input Form</title>
</head>
<body>

	
	<%! String chaine = "Hello world";  
		Student s = new Student();
	%>
	
	<%= s.studentTest %>
	
	<%= chaine %>

	<form action="processForm" method="GET">
		<input type="text" name="studentName" placeholder="What's your name" /> 
		<input type="submit" />
	</form>
	
		<form action="processFormVersionTwo" method="GET">
		<input type="text" name="studentName" placeholder="What's your name LOUDER" /> 
		<input type="submit" />
	</form>

</body>
</html>