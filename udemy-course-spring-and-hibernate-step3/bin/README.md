# udemy-course-spring-and-hibernate

Step 3 : Spring Bean Scopes and Lifecycle
---

Scope refers to the lifecycle of a bean.

By default a scope is a singleton.
Spring container creates only one instance of the bean, by default. It is cached in memory.
All request for the bean will return a SHARED reference to the SAME bean.

There is several scopes : 
- singleton (creates single shared instance of the bean)
- prototype (creates a new bean instance for each container request)
- request (scoped to an HTTP web request. Only used for web apps)
- session (scoped to an HTTP web session. Only used for web apps)
- global-session (scoped to a global HTTP web session. Only used for web apps)