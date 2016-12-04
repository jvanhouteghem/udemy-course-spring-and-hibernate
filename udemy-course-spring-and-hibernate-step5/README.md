# udemy-course-spring-and-hibernate

Step 5 : Bean scopes with annotation 
---

Refresher : Bean scopes
- Scope refers to the lifecycle of a Bean
- How long does the bean live ?
- How many instances are created ?
- How is the bean shared ?

Refresher : The default scope is a singleton. What is a singleton ?
- Spring container creates only one instance of the bean, by default
- It is in cached memory
- All request for the bean will return a SHARED reference to the SAME bean.

The annotation for scope is @Scope

