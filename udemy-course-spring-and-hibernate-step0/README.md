# udemy-course-spring-and-hibernate


Step 0 : Installs
---

I. Pre-requisites
---
```
- Install jdk
- IDE (eclipse)
```
II. Install tomcat 8.0
---
```
- Go to http://tomcat.apache.org/download-80.cgi
- Select Version 8.0.39 - Download 32/64 bit windows service installer 
- Run install (choose full install ; user : admin ; pass : admin)
- Verify installation : http://localhost:8080
```

IV. Connection Eclipse and Tomcat
---
Server > Add server > Select tomcat 8.0 > Select tomcat installation directory (mine was on C:\Program Files\Apache Software Foundation\Tomcat 8.0)

V. Install Spring (Maven option)
--- 
Here is the pom.xml (src : http://crunchify.com/how-to-import-all-spring-mvc-dependencies-to-your-maven-project/) : 
```
<dependencies>
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>3.8.1</version>
        <scope>test</scope>
    </dependency>

    <!-- spring-context which provides core functionality -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>4.1.6.RELEASE</version>
    </dependency>

    <!-- The spring-aop module provides an AOP Alliance-compliant aspect-oriented 
        programming implementation allowing you to define -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>4.1.6.RELEASE</version>
    </dependency>

    <!-- The spring-webmvc module (also known as the Web-Servlet module) contains 
        Spring’s model-view-controller (MVC) and REST Web Services implementation 
        for web applications -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>4.1.6.RELEASE</version>
    </dependency>

    <!-- The spring-web module provides basic web-oriented integration features 
        such as multipart file upload functionality and the initialization of the 
        IoC container using Servlet listeners and a web-oriented application context -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>4.1.6.RELEASE</version>
    </dependency>
</dependencies>
```
