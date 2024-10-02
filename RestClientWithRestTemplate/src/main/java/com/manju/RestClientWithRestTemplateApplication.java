package com.manju;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.web.client.RestTemplate;

import com.manju.model.Account;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootApplication
public class RestClientWithRestTemplateApplication {

	private static final Logger log = LoggerFactory.getLogger(RestClientWithRestTemplateApplication.class);
	public static void main(String[] args) throws Exception {
		ConfigurableApplicationContext context=SpringApplication.run(RestClientWithRestTemplateApplication.class, args);
		RestTemplate restTemplate=(RestTemplate) context.getBean(RestTemplate.class);
		
		// Checking password length
		// wrongPasswordLength(restTemplate);
		
		// checking for email
		//	wrongEmail(restTemplate);
		
		// checking for fullName - it can not be null
		//	noFullName(restTemplate);
		
		// Invalid createdAt date format
		//	wrongDateFormat(restTemplate);
		
		// inserting valid users
			//insertValidUser1(restTemplate);
			//insertValidUser2(restTemplate);
		
		// Error creating duplicate user
		//	insertValidUser2(restTemplate);
		
		// Access ACCOUNT-SERVICE to generate Accounts
		// check min balance
		//	checkMinBalance(restTemplate);
		
		// Insert account for manju
			//insertManjuAccount1(restTemplate);
		
		//	insertManjuAccount2(restTemplate);
		
		// update Account aid=2 with balance 70000
		//	updateAccount(restTemplate);
		
		// delete Account aid=2
		//	deleteAccount(restTemplate);
		
		// Insert accounts for seetha
			//insertSeethaAccount1(restTemplate);
			//insertSeethaAccount2(restTemplate);
		
		// search accounts by seetha's email
		//	searchAccountsByEmail(restTemplate);
			
		// search accounts by invalid email
		//	searchAccountsByInvalidEmail(restTemplate);
						
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	
	public static void wrongPasswordLength(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/users/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // password length is min 8 chars in size
	     
	     String jsonBody="{\n"
	     		+ "    \"username\":\"manju\",\n"
	     		+ "    \"password\":\"manju\",\n"
	     		+ "    \"email\":\"manju@abc.com\",\n"
	     		+ "    \"fullName\":\"Manjiri Tatke\",\n"
	     		+ "    \"createdAt\":\"2024-09-20\",\n"
	     		+ "    \"roles\":[{\n"
	     		+ "        \"name\":\"BANKEMP\"\n"
	     		+ "    },\n"
	     		+ "    {\n"
	     		+ "        \"name\":\"CUSTOMER\"\n"
	     		+ "    }]\n"
	     		+ "}";

	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				String userResult = restTemplate.postForObject(url, entity,  String.class);
				log.info(userResult);
				System.out.println(userResult);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}


	public static void wrongEmail(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/users/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"username\":\"manju\",\n"
	     		+ "    \"password\":\"manju1234\",\n"
	     		+ "    \"email\":\"manju\",\n"
	     		+ "    \"fullName\":\"Manjiri Tatke\",\n"
	     		+ "    \"createdAt\":\"2024-09-20\",\n"
	     		+ "    \"roles\":[{\n"
	     		+ "        \"name\":\"BANKEMP\"\n"
	     		+ "    },\n"
	     		+ "    {\n"
	     		+ "        \"name\":\"CUSTOMER\"\n"
	     		+ "    }]\n"
	     		+ "}";

	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				String userResult = restTemplate.postForObject(url, entity,  String.class);
				log.info(userResult);
				System.out.println(userResult);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
		
	}
	
	public static void noFullName(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/users/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // fullName can not be null
	     
	     String jsonBody="{\n"
	     		+ "    \"username\":\"manju\",\n"
	     		+ "    \"password\":\"manju1234\",\n"
	     		+ "    \"email\":\"manju@abc.com\",\n"
	     		+ "    \"fullName\":null,\n"
	     		+ "    \"createdAt\":\"2024-09-20\",\n"
	     		+ "    \"roles\":[{\n"
	     		+ "        \"name\":\"BANKEMP\"\n"
	     		+ "    },\n"
	     		+ "    {\n"
	     		+ "        \"name\":\"CUSTOMER\"\n"
	     		+ "    }]\n"
	     		+ "}";

	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				String userResult = restTemplate.postForObject(url, entity,  String.class);
				log.info(userResult);
				System.out.println(userResult);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
	
	}

	public static void wrongDateFormat(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/users/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"username\":\"manju\",\n"
	     		+ "    \"password\":\"manju1234\",\n"
	     		+ "    \"email\":\"manju@abc.com\",\n"
	     		+ "    \"fullName\":\"Manjiri Tatke\",\n"
	     		+ "    \"createdAt\":\"20-09-2024\",\n"
	     		+ "    \"roles\":[{\n"
	     		+ "        \"name\":\"BANKEMP\"\n"
	     		+ "    },\n"
	     		+ "    {\n"
	     		+ "        \"name\":\"CUSTOMER\"\n"
	     		+ "    }]\n"
	     		+ "}";

	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				String userResult = restTemplate.postForObject(url, entity,  String.class);
				log.info(userResult);
				System.out.println(userResult);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}
	
	public static void insertValidUser1(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/users/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"username\":\"manju\",\n"
	     		+ "    \"password\":\"manju1234\",\n"
	     		+ "    \"email\":\"manju@abc.com\",\n"
	     		+ "    \"fullName\":\"Manjiri Tatke\",\n"
	     		+ "    \"createdAt\":\"2024-09-20\",\n"
	     		+ "    \"roles\":[{\n"
	     		+ "        \"name\":\"BANKEMP\"\n"
	     		+ "    },\n"
	     		+ "    {\n"
	     		+ "        \"name\":\"CUSTOMER\"\n"
	     		+ "    }]\n"
	     		+ "}";

	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				String userResult = restTemplate.postForObject(url, entity,  String.class);
				log.info(userResult);
				System.out.println(userResult);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}



	public static void insertValidUser2(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/users/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"username\":\"seetha\",\n"
	     		+ "    \"password\":\"seetha1234\",\n"
	     		+ "    \"email\":\"seetha@abc.com\",\n"
	     		+ "    \"fullName\":\"Seetha Das\",\n"
	     		+ "    \"createdAt\":\"2024-09-20\",\n"
	     		+ "    \"roles\":[{\n"
	     		+ "		\"id\":2,\n"
	     		+ "        \"name\":\"CUSTOMER\"\n"
	     		+ "    } ]\n"
	     		+ "}";

	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				String userResult = restTemplate.postForObject(url, entity,  String.class);
				log.info(userResult);
				System.out.println(userResult);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}

	}

	public static void checkMinBalance(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"balance\":1500.00,\n"
	     		+ "    \"username\": \"manju\"\n"
	     		+ "\n"
	     		+ "}";
	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				Account acc = restTemplate.postForObject(url, entity, Account.class);
				log.info(acc.toString());
				System.out.println(acc);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}
	
	
	public static void insertManjuAccount1(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"balance\":150000.00,\n"
	     		+ "    \"username\": \"manju\"\n"
	     		+ "\n"
	     		+ "}";
	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				Account acc = restTemplate.postForObject(url, entity, Account.class);
				log.info(acc.toString());
				System.out.println(acc);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}


	public static void insertManjuAccount2(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"balance\":50000.00,\n"
	     		+ "    \"username\": \"manju\"\n"
	     		+ "\n"
	     		+ "}";
	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				Account acc = restTemplate.postForObject(url, entity, Account.class);
				log.info(acc.toString());
				System.out.println(acc);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}
	
	public static void updateAccount(RestTemplate restTemplate) {
		//aid=2, balance=50000.0, username=manju
		String url="http://localhost:9191/accounts/update/2";
	    HttpHeaders headers = new HttpHeaders();        
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
          
         String jsonBody = "{\n"
         		+ "\"aid\":2,"
         		+ "\"balance\":70000,"
         		+ "\"username\":\"manju\""
         		+ "}";
        HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
         
        //POST Method to Add New Product
        ResponseEntity<Account> response = restTemplate.exchange(url,HttpMethod.PUT, entity, Account.class);
		Account responseBody=response.getBody();
		log.info(responseBody.toString());
		System.out.println(responseBody);

	}

	public static void deleteAccount(RestTemplate restTemplate) {
		String url="http://localhost:9191/accounts/delete/2";
		HttpHeaders headers = new HttpHeaders();        
        HttpEntity<String> entity = new HttpEntity<String>( headers);
        ResponseEntity<String> response= restTemplate.exchange(url, HttpMethod.DELETE, entity, String.class);
              
        String responseBody = response.getBody();
        log.info(responseBody);
        System.out.println(responseBody);
        log.info("Deleted "+response.getStatusCode());
        System.out.println("Deleted "+response.getStatusCode());
       
         
	}

	public static void insertSeethaAccount1(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     
	     // email is not valid
	     
	     String jsonBody="{\n"
	     		+ "    \"balance\":200000.00,\n"
	     		+ "    \"username\": \"seetha\"\n"
	     		+ "\n"
	     		+ "}";
	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				Account acc = restTemplate.postForObject(url, entity, Account.class);
				log.info(acc.toString());
				System.out.println(acc);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
			
		
	}

	public static void insertSeethaAccount2(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/create";
		 HttpHeaders headers = new HttpHeaders();        
	     headers.add("Accept", "application/json");
	     headers.add("Content-Type", "application/json");
	     

	     
	     String jsonBody="{\n"
	     		+ "    \"balance\":55500.00,\n"
	     		+ "    \"username\": \"seetha\"\n"
	     		+ "\n"
	     		+ "}";
	     HttpEntity<String> entity = new HttpEntity<String>(jsonBody, headers);
		
			try {
				Account acc = restTemplate.postForObject(url, entity, Account.class);
				log.info(acc.toString());
				System.out.println(acc);
			}
			catch(Exception e) {
				log.error(e.getMessage());
				System.out.println(e.getMessage());
			}
		
	}
	
	public static void searchAccountsByEmail(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/byUserEmail?email=seetha@abc.com";
		try { 
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		    String responseBody=response.getBody();
		    System.out.println(responseBody);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	
	public static void searchAccountsByInvalidEmail(RestTemplate restTemplate) throws Exception {
		String url="http://localhost:9191/accounts/byUserEmail?email=seetha@gmail.com";
		try { 
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
		    String responseBody=response.getBody();
		    System.out.println(responseBody);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
	
	
}
