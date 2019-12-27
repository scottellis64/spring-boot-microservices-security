# spring-boot-microservices-security

This project has been created in order to demonstrate how microservices in a Spring Boot architecture can be 
secured using OAuth2 authentication at UI and Rest Resource endpoints.

In its initial stage, the project is not secured in any way, representing how it all looks before any security is
applied at all.

This is a maven project in a hierarchical structure.  The root pom determines all specific dependency versions, and 
has as its parent the spring boot starter artifact.  The top level parent has three child projects, which are as follows:

- control-center
   * This is the parent project of the applications that handle configuration and defined the service registry
- services
   * Parent of all REST APIs (of which there is currently only one)
- ui 
   * Parent of all user interface projects
   
The entire project can be built from the root folder by typing:

```bash
./mvnw clean install
```

The projects must be started in this order:

- control-center/config-server
```bash
./mvnw spring-boot:run -pl control-center/config-server
```
- control-center/service-registry
```bash
./mvnw spring-boot:run -pl control-center/service-registry
```
- services/protected-rest-resource
```bash
./mvnw spring-boot:run -pl services/protected-rest-resource
```
- ui/ui-resources
```bash
./mvnw spring-boot:run -pl ui/ui-resources
```
- ui/authentication-test-ui
```bash
./mvnw spring-boot:run -pl ui/authentication-test-ui
```

Here is generally how the application is configured:

- The configuration server contains configurations for all other spring boot modules located on the classpath as sourced
from src/main/resources/config-repo
- There is a single rest resource with two endpoints that are not protected, but in the future will not be accessible
without some form of authentication.  These are:
   * GET http://localhost:8003/api/secured/user, returns { status: "OK" }
   * GET http://localhost:8003/api/secured/admin, returns { status: "OK" }
- The user interface can be viewed at http://localhost:8084/authtest
   * At page initialization, two ajax calls are made to the two rest calls detailed above, and the status text is 
   displayed on the page.  
   * There should be no issue with this page as there is no security getting in the way of the two ajax calls
- Zuul has been configured for the UI such that all ajax calls to the rest api and for any static web resources are 
obtained through a proxy.
   * The rest api calls as detailed above are directly requested from the rest endpoint, but the UI has been configured
   to resolve the api calls via proxy.  The calls above from the UI then are formatted as such:
      - GET http://localhost:8084/authtest/proxy/protected-rest-resource/api/secured/user
      - GET http://localhost:8084/authtest/proxy/protected-rest-resource/api/secured/admin
   * The ui-resources module contains web resources shared to other ui modules.  This avoids the need to configure 
   CORS for cross-domain requests.  
   
     
      
         


