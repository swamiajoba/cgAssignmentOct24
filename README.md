# cgAssignmentOct24
Assignment given
1.Feign Client  -- done
 a) used application.properties instead of application.yml
2. Input Validation - done
3. Call endpoints programmatically in spring  -- done
4. GlobalExceptionHandler use ResponseEntity  --done
5. Pass Proper paramenter type in map method – done

Details about Projects
 

Above workspace has following projects
1.	Eureka_service_registry  -> Eureka server
2.	ConfigServerProject   -> fetches eureka client configuration from github and provide to other services
3.	CloudAPIGetway  -> Common API Gateway to access USER-SERVICE and ACCOUNT-SERVICE by port 9191
4.	UserServiceProject -> USER-SERVICE project
5.	AccountServiceProject -> ACCOUNT-SERVICE project
6.	RestClientWithRestTemplate -> Rest Client project accesses      USER-SERVICE and ACCOUNT-SERVICE through API Gateway using RestTemplate

Run Projects in above sequence . In “RestClientWithRestTemplate” project uncomment one by one code to access rest endpoints
In above projects “AccountServiceProject” is calling USER-SERVICE using Feign Client to access user by email.

There are two more projects of assignment
1.	LambdaMappingProject -> This project has Mapping.java which has a code for Stream map method
2.	SpringBoot3WithJWT -> In this project , spring security with jwt is implemented.





