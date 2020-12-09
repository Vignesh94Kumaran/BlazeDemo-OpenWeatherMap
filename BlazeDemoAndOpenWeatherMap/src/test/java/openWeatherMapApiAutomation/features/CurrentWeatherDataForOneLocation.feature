Feature: Call current weather data for one location


@Automated 
@APIAutomation 
Scenario Outline: Call current weather data with City name and Validate 200 OK status and city name 
	Given As user I load the 'weather' api with city name as '<City Name>' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response with status code 200 and city name as '<City Name>' 
	
	Examples: 
		| City Name |
		| London    |
		

@Automated 
@APIAutomation 
Scenario: Call current weather data with City name and Validate the response headers 
	Given As user I load the 'weather' api with city name as 'America' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response with status code 200 
	And I validate 'weather' api response headers 
		| Server    | Content-Type     |
		| openresty | application/json |
	

@Automated 
@APIAutomation  
Scenario: Call current weather data without passing city name and validate 400 bad request status and message 
	Given As user I load the 'weather' api' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response with status code 400 and message as 'Nothing to geocode' 


@Automated 
@APIAutomation 
Scenario Outline: Call current weather data with City name as null and improper then validate 400 bad request status and message 
	Given As user I load the 'weather' api with city name as '<City>' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response with status code <Status Code> and message as '<Message>' 
	
	Examples:
		| City     | Status Code | Message            |
		| 		   | 400         | Nothing to geocode |
		| American | 404         | city not found     |
	

@Automated 
@APIAutomation 
Scenario: Call current weather data without api key and Validate 401 unauthorized status and message 
	Given As user I load the 'weather' api without api key and with city name as 'America' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response with status code 401 and message as 'Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.' 
	
	
@Automated 
@APIAutomation 
Scenario Outline: Call current weather data with api key as null and improper then Validate 401 unauthorized status and message 
	Given As user I load the 'weather' api with api key as '<Api Key>' and with city name as 'America' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response with status code <Status Code> and message as '<Message>' 
	
	Examples:
		| Api Key | Status Code | Message 																		    |
		| 		  | 401         | Invalid API key. Please see http://openweathermap.org/faq#error401 for more info. |
		| 12345	  | 401         | Invalid API key. Please see http://openweathermap.org/faq#error401 for more info. |

	
@Automated 
@APIAutomation 
Scenario Outline: Call current weather data with City name and Validate the response details 
	Given As user I load the 'weather' api with city name as 'New York' 
	When I call 'weather' api with 'GET' Http request 
	Then I validate 'weather' api response contains '<collection>' collection details 
	
	Examples: 
		| collection |
		| coord      |
		| weather    |
		| base       |
		| main       |
		| visibility |
		| wind       |
		| clouds     |
		| dt         |
		| sys        |
		| timezone   |
		| id         |
		| name       |
		| cod        |
