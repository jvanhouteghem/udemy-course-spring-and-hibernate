# udemy-course-spring-and-hibernate

Step 6 : Spring configuration with java code
---

Why : no XML ! 
Instead of configuring Spring container using XML we can configure the Spring container with Java code.

Refresher : 3 ways of configuring Spring container
- Full XML configuration
- XML component scan
- Java configuration class (no XML)

Development process
- Create a java class and annotate as @configuration
- Add component scanning support: @ComponentScan (optional)
- Read Spring Java configuration class
- Retrieve bean from Spring container

Lets create a java config class :

```java
@Configuration
@ComponentScan("com.jvanhouteghem.springdemoannotations")
public class SportConfig {

}
```

Now we want to retrieve the config class : 
```java
public class JavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the bean from spring container
		CoachAnnotations theCoach = context.getBean("tennisCoach", CoachAnnotations.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// call method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		// close the context
		context.close();
	}

}
```

Output : 

```
>>> TennisCoach : inside default constructor
>>> Practice your backhand volley
>>> The journey is the reward
```