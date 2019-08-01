# angular-spring-auth-sample

The sample code is created to demonstrate how one can secure a Javascript-based application utilzing the Authorization Code OAuth2 grant type against a Pivotal SSO service instance. This can be achieved by using the Implict grant type, however, such type is not recommended to use due to its less secured nature as it skips the extra authorization code exchange step.

Instead, this solution uses a Spring Boot application to handle the AuthCode-based authentication.

In order to run the app, we first need to build the angular app.

```
$ cd angular-app
$ ng buld
```

Then, copy the transpiled application code to a publicly accessible static folder in the `backend` Spring Boot application.

```
$ rm -rf ../backend/src/main/resources/public/* && cp dist/angular-app/* ../backend/src/main/resources/public/
```

Now, we can package up the Spring Boot application and push it to PCF.

```
$ mvn package -DskipTests
$ cf push
```