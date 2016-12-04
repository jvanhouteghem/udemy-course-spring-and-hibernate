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

I. Spring configuration with java code
---

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

II. Spring bean with java code
---

In the previous example we defined <bean (...)> in xml. Now we will use @Bean.

Development process : 
- Defined method to expose bean
- Inject bean dependencies
- Read Spring java configuration class
- Retrieve bean from Spring container

### A. Defined method to expose bean

1) Create new class named SadFortuneService

```java
public class SadFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Today is a sad day";
	}

}
```

2) Create new class SwimCoach

```java
public class SwimCoach implements CoachAnnotations {

	private FortuneService fortuneService;
	
	public SwimCoach(FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout() {
		return "Swim 1000 meters as a warm up";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
```

3) Update SportConfig

```java
@Configuration
// Comment cause two beans are manually define
//@ComponentScan("com.jvanhouteghem.springdemoannotations")
public class SportConfig {
	
	// define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService(){
		return new SadFortuneService();
	}
	
	// define bean for our swim coach AND inject dependency
	@Bean
	public CoachAnnotations swimCoach(){
		return new SwimCoach(sadFortuneService());
	}
	
}
```

4) Create new main class

```java
public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the bean from spring container
		CoachAnnotations theCoach = context.getBean("swimCoach", CoachAnnotations.class);
		
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
>>> Swim 1000 meters as a warm up
>>> Today is a sad day
```

### B. Injecting value from properties file


