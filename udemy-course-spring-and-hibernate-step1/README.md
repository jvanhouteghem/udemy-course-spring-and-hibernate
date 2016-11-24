# udemy-course-spring-and-hibernate

Step 1 : Spring Inversion of Control - XML Configuration
---
---

Step 1.1 : Without Spring Inversion of Control (the hardcoded mode :-( ))
---

I. A simple start example without Spring
---

A. Create new package (mine is com.jvanhouteghem.step0)

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