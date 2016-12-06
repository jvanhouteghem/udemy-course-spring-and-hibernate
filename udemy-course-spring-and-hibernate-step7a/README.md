# udemy-course-spring-and-hibernate

Step 7 : Spring MVC : creating a spring home controller and view
---

I. Install
---

Download and import this this seed : https://github.com/jvanhouteghem/spring-seed-mvc

II. Our first home controller
---

NB : This steps is already done by default in the seed.

Development process : 
- create controller class
- define controller method
- add request mapping to controller method
- return view name
- develop view page

1) Create new package named com.jvanhouteghem.springdemo

2) Create controller class

```java
@Controller
public class HomeController {
	
	// You can use any method name
	@RequestMapping("/")
	public String showPage(){
		// Return view name in WEB-INF/view
		return "main-menu";
	}
	
}
```

3) Create view page named main-menu.jsp

```html
<!DOCTYPE html>
<html>

    <body>

        <h2>Spring MVC Home Page</h2>

        <p>Hello world</p>

    </body>

</html>
```

Run on server and connect to : http://localhost:8080/spring-mvc-demo/

III. Reading HTML form data
---

Remember : if you use annotation mapping, the method name don't matters.

Development process : 
- create controller class
- create controller method to show html form
- create view page for html form
- create controller method to process html form
- develop view page for confirmation

1) Create new class HelloWorldController

```java
@Controller
public class HelloWorldController {

	// need a controller method to show the initial HTML form
	@RequestMapping("/showForm")
	public String showForm(){
		return "helloworld-form";
	}
	
	// need a controller method to process the HTML form
	@RequestMapping("/processForm")
	public String processForm(){
		return "helloworld";
	}
}
```

2) Create new view file named helloworld-form.jsp

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Hello World - Input Form</title>
</head>
<body>

	<form action="processForm" method="GET">
		<input type="text" name="studentName" placeholder="What's your name" /> 
		<input type="submit" />
	</form>

</body>
</html>
```

3) Try it : http://localhost:8080/spring-mvc-demo/processForm?studentName=zzz

4) Create new view helloworld.jsp

```html
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	</head>
	<body>
		<p> Hello World of Spring ! </p>
		
		<p>Student name : ${param.studentName}
		
	</body>
</html>
```

5) Lets add a link to main-menu.jsp

```html
<!DOCTYPE html>
<html>

	<body>
	
		<h2>Spring MVC Home Page</h2>
	
		<a href="showForm">Hello world form</a>
	
	</body>

</html>
```

IV. Adding data to the spring model


