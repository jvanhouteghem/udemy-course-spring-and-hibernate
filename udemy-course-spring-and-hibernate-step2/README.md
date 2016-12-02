# udemy-course-spring-and-hibernate

Step 2 :Spring Dependency Injection - XML Configuration
---

There is 2 Spring Injection Types : 
- Constructor Injection
- Setter Injection

I. Constructor Injection

Summary : 
- Define the dependency interface and class
- Create a constructor in your class for injections
- Configure the dependency injection in Spring config file 

A. Define the dependency interface and class

1. Create new interface FortuneService

```java
public interface FortuneService {
	
	public String getFortune();

}
```

2. Create a class which implements the interface

```java
public class HappyFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "Today is your lucky day!";
	}

}
```

3. Update Coach interface

```java
public interface Coach {
	
	public String getDailyWorkout();
	
	public String getDailyFortune();

}
```

B. Create a constructor in your class for injections

1. Update BaseballCoach 

```java
public class BaseballCoach implements Coach{
	
	// define a private field for the dependency (new)
	private FortuneService fortuneService; 
	
	// defined a constructor for dependency injection (new)
	public BaseballCoach(FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout(){
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() {
		// TODO Auto-generated method stub
		return null;
	}

}
```

2. Update getDailyFortune() in BaseballCoach

```java
public class BaseballCoach implements Coach{
	
	// define a private field for the dependency
	private FortuneService fortuneService;
	
	// defined a constructor for dependency injection
	public BaseballCoach(FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}
	
	@Override
	public String getDailyWorkout(){
		return "Spend 30 minutes on batting practice";
	}

	@Override
	public String getDailyFortune() { // (update)
		// use my fortuneService to get a fortune
		return fortuneService.getFortune();
	}

}
```

3. Configure the dependency injection in Spring config file

Update applicationContext

```xml
	<!--  define the dependency -->
	<bean id="myFortune"
		class="com.jvanhouteghem.springdemo.HappyFortuneService">
	</bean>

    <!-- Define your beans here -->
    <bean id="myCoach" 
    	class="com.jvanhouteghem.springdemo.BaseballCoach">
    	<!--  set up constructor injection -->
    	<constructor-arg ref="myFortune"/>
    </bean>
```

4. Update HelloSpringApp

```java
public class HelloSpringApp {

	public static void main(String[] args) {
		
		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class); // the bean id
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// let's call our new method for fortunes (new)
		System.out.println(theCoach.getDailyFortune());
		// close the context
		context.close();
	}

}
```

NB : To remove argument error in MyApp just add no-arg constructor to TrackCoach class.

Output : 
```
>>> Spend 30 minutes on batting practice
>>> Today is your lucky day!
```

5. Now let's update TrackCoach

```java 
public class TrackCoach implements Coach {

	private FortuneService fortuneService;
	
    // (new)
	public TrackCoach(FortuneService fortuneService) {
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

    // (update)
	@Override
	public String getDailyFortune() {
		return "Just Do It: " + fortuneService.getFortune();
	}

}
```

6. Then swap BaseballCoach with TrackCoach in applicationContext

```xml
	<!--  define the dependency -->
	<bean id="myFortune"
		class="com.jvanhouteghem.springdemo.HappyFortuneService">
	</bean>

    <!-- Define your beans here -->
    <bean id="myCoach" 
    	class="com.jvanhouteghem.springdemo.TrackCoach">
    	<!--  set up constructor injection -->
    	<constructor-arg ref="myFortune"/>
    </bean>
```

Output : 
```
>>> Run a hard 5k
>>> Just Do It: Today is your lucky day!
```

II. Setter Injection

2 steps : 
- Create setter method(s) in your class for injection
- Configure the dependency injection in Spring config file

NB : A <property name="bestAthlete" (...) > will call the setter public void setBestAthlete(...
)

1) Create new class named CricketCoach which implements Coach

```java
public class CricketCoach implements Coach {

	private FortuneService fortuneService;

	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
```

2) Update CricketCoach by adding no-arg constructor and setter method

```java
public class CricketCoach implements Coach {

	private FortuneService fortuneService;

	// create a no-arg constructor
	public CricketCoach(){
		System.out.println("CricketCoach : inside no-arg constructor");
	}
	
	// our setter method used by Spring 
	public void setFortuneService(FortuneService fortuneService) {
		System.out.println("CricketCoach : inside setter method - setFortuneService");
		this.fortuneService = fortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice fast bowling for 15 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
```

3) Configure the dependency injection in Spring config file

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

	<!--  define the dependency -->
	<bean id="myFortune"
		class="com.jvanhouteghem.springdemo.HappyFortuneService">
	</bean>

    <!-- Define your beans here -->
    <bean id="myCoach" 
    	class="com.jvanhouteghem.springdemo.TrackCoach">
    	<!--  set up constructor injection -->
    	<constructor-arg ref="myFortune"/>
    </bean>
    
    <bean id="myCricketCoach"
    	class="com.jvanhouteghem.springdemo.CricketCoach">
 		
 		<!--  set up setter injection -->
 		<!--  NB : ref value must be equals to our id in line 11 -->
 		<!--  NB : name="fortuneService" will call setFortuneService(...) -->
 		<property name="fortuneService" ref="myFortuneService"></property>
 			
    </bean>

</beans>
```

4) Create a new app class for this demo

```java
public class SetterDemoApp {

	public static void main(String[] args) {

		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		CricketCoach theCoach = context.getBean("myCricketCoach", CricketCoach.class);
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		System.out.println(theCoach.getDailyFortune());
		// close the context
		context.close();
		
	}

}
```

Output : 

```
>>> CricketCoach : inside no-arg constructor
>>> CricketCoach : inside setter method - setFortuneService
>>> Practice fast bowling for 15 minutes
>>> Today is your lucky day!
```

