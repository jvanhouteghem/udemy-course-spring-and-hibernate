# udemy-course-spring-and-hibernate

Step 1 : Spring Inversion of Control - XML Configuration
---
---

Step 1.1 : Without Spring Inversion of Control (the hardcoded mode :-( )
---

I. A simple start example without Spring
---

A. Create new package (mine is com.jvanhouteghem.springdemo)

B. Add BaseballCoach class

```java
package com.jvanhouteghem.springdemo;

public class BaseballCoach {
	
	public String getDailyWorkout(){
		return "Spend 30 minutes on batting practice";
	}

}
```

C. Add MyApp (main) class

```java
package com.jvanhouteghem.springdemo;

public class MyApp {

	public static void main(String[] args) {
		// create the object
		BaseballCoach theCoach = new BaseballCoach();
		
		// use the object
		System.out.println(theCoach.getDailyWorkout());

	}

}
```

II. More fun, lets add interface ! 
---

A. Create interface named Coach

```java
package com.jvanhouteghem.springdemo;

public interface Coach {
	
	public String getDailyWorkout();

}
```

B. Update BaseballCoach 

```java
package com.jvanhouteghem.springdemo;

public class BaseballCoach implements Coach{
	
	@Override
	public String getDailyWorkout(){
		return "Spend 30 minutes on batting practice";
	}

}
```

Add Override makes an error ?
Eclipse: right button on project -> Properties... > Java compiler, then choose 1.6 or higher.

C. Update MyApp

```java
package com.jvanhouteghem.springdemo;

public class MyApp {

	public static void main(String[] args) {
		// create the object
		Coach theCoach = new BaseballCoach();
		
		// use the object
		System.out.println(theCoach.getDailyWorkout());

	}

}
```

III. Now we want to switch from BaseballCoach() to TrackCoach()
---

A. Update MyApp

```java
package com.jvanhouteghem.springdemo;

public class MyApp {

	public static void main(String[] args) {
		// create the object
		Coach theCoach = new TrackCoach();
		
		// use the object
		System.out.println(theCoach.getDailyWorkout());

	}

}
```

B. Create TrackCoach class

```java
package com.jvanhouteghem.springdemo;

public class TrackCoach implements Coach {

	@Override
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}

}

```

Step 1.2 : Lets use Spring Inversion of Control - XML Configuration
---

Now we want to change the used class from a config file ... How will we do that ? With Spring of course !

A. Create applicationContext.xml in src/main/resource

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- Define your beans here -->
    <bean id="myCoach" 
    	class="com.jvanhouteghem.springdemo.TrackCoach">
    </bean>
    
</beans>
```

B. Create new (main) class named HelloSpringApp

```java
package com.jvanhouteghem.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloSpringApp {

	public static void main(String[] args) {
		
		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		Coach theCoach = context.getBean("myCoach", Coach.class); // the bean id
		
		// call methods on the bean
		System.out.println(theCoach.getDailyWorkout());
		
		// close the context
		context.close();
	}

}
```

Exception : 
```
Exception in thread "main" java.lang.IllegalStateException: BeanFactory not initialized or already closed - call 'refresh' before accessing beans via the ApplicationContext
```
Mean your ApplicationContext is not conform with your Spring dependencies.

Also make sure you properly add "applicationContext.xml" in ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

C. Lets change the configuration file

Now if we want to change the configuration file we can just switch the name of the bean in applicationContext.

```xml
    <!-- Define your beans here -->
    <bean id="myCoach" 
    	class="com.jvanhouteghem.springdemo.BaseballCoach">
    </bean>
```