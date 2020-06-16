# REST API testing project example: Karate + Spring

This repository contains the base setup of a REST API testing project, implemented with Karate + Spring Framework.

It includes some basic GET tests that are executed against the **Age Of Empires II** API.

# Requirements

* JDK 8
* Maven >= 3.6

# Test Execution

1. Download or clone the repository
2. Open a terminal
3. From the project root directory run:  

 `mvn test`

# Karate Environment

**karate.env** propertie can be used on **karate-config.js** file to set global variables. For example, it is possible
to change API's base url depending on the environment:

```
    // BASE_URL
    if(env == 'test') {
        config.BASE_URL = 'https://age-of-empires-2-api.herokuapp.com/api/v1/';
    }
    else if(env == 'jenkins') {
        config.BASE_URL = 'https://age-of-empires-2-api.herokuapp.com/api/v1/';
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

`mvn test -Dspring.profiles.active=jenkins`

All the properties defined in **application-jenkins.properties** file will overwrite the ones in **application.properties** file.
 
# Results

To view the test report, open the **'/karate-spring-example/results/cucumber-html-reports/overview-features.html'** file once
the execution has finished.

# Links
    
   [Karate Framework](https://github.com/intuit/karate)
   
   [Age of Empires II resources API](https://age-of-empires-2-api.herokuapp.com/docs/)