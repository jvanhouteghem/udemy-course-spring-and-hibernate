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

Development process : 
- Create properties file
- Load properties files in Spring config (by using @PropertySource)
- Reference values from Properties file (with @Value)

1) Create new file named sport.properties

```
foo.email=jvanhouteghem@emailfrompropertiesjavacode.com
foo.team= Mighty Java Coders
```

2) Load properties files in Spring config 

Update SportConfig :

```java

@Configuration
//@ComponentScan("com.jvanhouteghem.springdemoannotations")
@PropertySource("classpath:sport.properties") // (new)
public class SportConfig {
	
	// add support to resolve ${...} properties (new)
	@Bean
	public static PropertySourcesPlaceholderConfigurer
		propertySourcesPlaceholderConfigurer(){
			return new PropertySourcesPlaceholderConfigurer();
	}
	
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

3) Reference values from Properties file

Update SwimCoach : 

```java
public class SwimCoach implements CoachAnnotations {

	private FortuneService fortuneService;
	
	@Value("${foo.email}") // new
	private String email;
	
	@Value("${foo.team}") // new
	private String team;
	
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
	
	public String getEmail() { // new
		return email;
	}

	public String getTeam() { // new
		return team;
	}

}
```

4) Update SwimJavaConfigDemoApp

```java
public class SwimJavaConfigDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(SportConfig.class);
		
		// get the bean from spring container
		SwimCoach theCoach = context.getBean("swimCoach", SwimCoach.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// call method to get the daily fortune
		System.out.println(theCoach.getDailyFortune());
		
		// call our new swim coach methods ... has the props value injected
		System.out.println("email : " + theCoach.getEmail());
		System.out.println("team : " + theCoach.getTeam() );
		
		// close the context
		context.close();
	}

}
```

Output : 

```
>>> déc. 05, 2016 12:11:09 AM org.springframework.context.annotation.AnnotationConfigApplicationContext prepareRefresh
>>> INFOS: Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@300ffa5d: startup date [Mon Dec 05 00:11:09 CET 2016]; root of context hierarchy
>>> Swim 1000 meters as a warm up
>>> Today is a sad day
>>> email : jvanhouteghem@emailfrompropertiesjavacode.com
>>> team : Mighty Java Coders
>>> déc. 05, 2016 12:11:09 AM org.springframework.context.annotation.AnnotationConfigApplicationContext doClose
>>> INFOS: Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@300ffa5d: startup date [Mon Dec 05 00:11:09 CET 2016]; root of context hierarchy

```