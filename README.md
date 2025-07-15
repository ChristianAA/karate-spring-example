# REST API testing project example: Karate + Spring

This repository contains the base setup of a REST API testing project, implemented with Karate + Spring Framework.

It includes some basic GET tests that are executed against the **[FREE TO GAME](https://www.freetogame.com/api-doc)** API.

# Requirements

* JDK 17
* Maven >= 3.8.7

# Test Execution

1. Download or clone the repository
2. Open a terminal
3. From the project root directory run:  

 `mvn clean test`

# Karate Environment

**karate.env** propertie can be used on **karate-config.js** file to set global variables. For example, it is possible
to change API's base url depending on the environment:

```
    // BASE_URL
    if(env == 'test') {
        config.BASE_URL = 'https://www.freetogame.com/api/';
    }
    else if(env == 'jenkins') {
        config.BASE_URL = 'https://www.freetogame.com/api/';
    }
```

The default "karate.env" value in this project will be "test".
 
 ```
     // Test environment by default
     var env = karate.env;
     if(!env) {
         env = 'test';
     }
  ```

To change the default value from the command line, run:

`mvn test -DargLine=-Dkarate.env=jenkins`

Check [Karate Framework - Switching the Environment](https://github.com/intuit/karate#switching-the-environment) for more info.
   
# Profiles
 
Non-Karate properties (maven execution parameters, database connections...) can be defined in **application.properties** file.

Depending on the execution environment it may be interesting to use **Profiles** to overwrite this properties.

For example, specific variables of an integration environment can be defined by creating the **application-jenkins.properties** file.

To run the tests with this profile, execute:

`mvn clean test -Dspring.profiles.active=jenkins`

All the properties defined in **application-jenkins.properties** file will overwrite the ones in **application.properties** file.
 
# Results

To view the test report, open the **'/karate-spring-example/target/karate-reports/karate-summary.html'** file once
the execution has finished.

There is another html report also in: **'/karate-spring-example/target/cucumber-html-reports/0'** folder.

# Links
    
   [Karate Framework](https://github.com/intuit/karate)
   
   [Free to game API documentation](https://www.freetogame.com/api/)