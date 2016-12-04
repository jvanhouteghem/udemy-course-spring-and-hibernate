# udemy-course-spring-and-hibernate

Step 4 : Spring Inversion of Control - Java Annotations
---

What are java annotations ?
- Special labels/markers added to java classes
- Provide meta-data about the class 
- Processed at compile time or run-time for special processing

3 steps :
- Enable component scanning in Spring config file
- Add the @Component Annotation to your Java classes
- Retrieve bean from Spring container

I. Explicit Component Names
---

A. Enable component scanning in Spring config file

Let's start by create new package com.jvanhouteghem.springdemoannotations for spring annotations
and a new applicationContext file named applicationContextAnnotations.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

	<!--  add entry to enable component scanning -->
  	<context:component-scan base-package="com.jvanhouteghem.springdemoannotations"/>
    
</beans>
```

B. Add the @Component Annotation to your Java classes

1) Create Coach2 interface

```java
public interface CoachAnnotations {
	
	public String getDailyWorkout();

}
```

2) Create new TennisCoach class and add the @Component 

```java
import org.springframework.stereotype.Component;

@Component("thatSillyCoach")
public class TennisCoach implements CoachAnnotations {

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

}
```

NB : Spring will automatically register this bean

C. Retrieve bean from Spring container

Create new class : 

```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnotations.xml");
		
		// get the bean from spring container
		CoachAnnotations theCoach = context.getBean("thatSillyCoach", CoachAnnotations.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// close the context
		context.close();
	}

}
```

Output : 

```
>>> INFOS: Loading XML bean definitions from class path resource [applicationContextAnnotations.xml]
>>> Practice your backhand volley
```

II. Default Component Names
---

Spring also supports default bean IDs which is the class name with first letter in lower-case.

TennisCoach (the class name) --> tennisCoach (default bean ID)

A. Update TennisCoach by removing @Component("thatSillyCoach")

```java
package com.jvanhouteghem.springdemoannotations;

import org.springframework.stereotype.Component;

// Now this use the default bean id "tennisCoach" (update : remove @Component("thatSillyCoach"))
@Component

public class TennisCoach implements CoachAnnotations {

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

}
```

B. Retrieve the bean - Update AnnotationDemoApp

```java
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnotations.xml");
		
		// get the bean from spring container (update)
		CoachAnnotations theCoach = context.getBean("tennisCoach", CoachAnnotations.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// close the context
		context.close();
	}

}
```

Output : 

```
>>> INFOS: Loading XML bean definitions from class path resource [applicationContextAnnotations.xml]
>>> Practice your backhand volley
>>> déc. 04, 2016 1:18:37 PM org.springframework.context.support.ClassPathXmlApplicationContext doClose
```

III. Spring Dependency Injection with Annotations and Autowiring
---

For dependency injection, Spring can use auto wiring. 
Spring will look for a class that matches the property (match by type: class or interface).
Spring will inject it automatically ... hence it is autowired.

3 Autoriwing injection types : 
- Constructor injection
- Setter injection
- Field injections

3 Steps :
- Defined the dependency interface and class
- Create a constructor in your class for injections
- Configure the dependency injection with @Autowired annotations

A. Constructor injection with annotations and autowiring

1) Defined the dependency interface and class

Create new Interface named FortuneService

```java
public interface FortuneService {
	
	public String getFortune();

}
```

2) Create a class to implement the interface

```java
import org.springframework.stereotype.Component;

@Component
public class HappyFortuneService implements FortuneService{

	@Override
	public String getFortune() {
		return "Today is your lucky day";
	}

}
```

3) Add a new method to the Coach interface

```java
public interface CoachAnnotations {
	
	public String getDailyWorkout();
	
	// (new)
	public String getDailyFortune();

}
```

B. Create a constructor in your class for injections

1) Update TennisCoach

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements CoachAnnotations {
	
	// (new)
	private FortuneService fortuneService;
	
	// (new)
	@Autowired
	public TennisCoach (FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	// (new)
	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
```

2) Update AnnotationDemoApp

```java
public class AnnotationDemoApp {

	public static void main(String[] args) {
		
		// read spring config file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContextAnnotations.xml");
		
		// get the bean from spring container
		CoachAnnotations theCoach = context.getBean("tennisCoach", CoachAnnotations.class);
		
		// call a method on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// call method to get the daily fortune (new)
		System.out.println(theCoach.getDailyFortune());
		
		// close the context
		context.close();
	}

}
```

Output : 

```
>>> Practice your backhand volley
>>> Today is your lucky day
```

C. Setter injection with annotations and autowiring

We will inject dependencies by calling setter method(s) on your class

How it works ?
- Injecting FortuneService into a Coach implementation
- Spring will scan @Components
- Any one implements FortuneService interface ???
- If so, let's inject them. For example : HappyFortuneService

2 steps : 
- Create setter method(s) in your class for injections
- Configure the dependency injection with @Autowired Annotation

1) Create setter method(s) in your class for injections

Update TennisCoach : 

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements CoachAnnotations {
	
	private FortuneService fortuneService;
	
	// define a default constructor (new)
	public TennisCoach(){
		System.out.println("TennisCoach : inside default constructor");
	}
	
	// define a setter method (new)
	@Autowired
	public void setFortuneService(FortuneService theFortuneService){
		System.out.println("TennisCoach : inside setFortuneService() method");
		fortuneService = theFortuneService;
	}
	
	/* (update)
	@Autowired
	public TennisCoach (FortuneService theFortuneService){
		fortuneService = theFortuneService;
	}*/

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

}
```

Output : 

```
>>> TennisCoach : inside default constructor
>>> TennisCoach : inside setFortuneService() method
>>> Practice your backhand volley
>>> Today is your lucky day
```






