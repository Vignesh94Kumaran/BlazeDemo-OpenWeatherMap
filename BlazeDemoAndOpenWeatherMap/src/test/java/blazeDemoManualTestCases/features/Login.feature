Feature: Login to BlazeDemo application 

#Note: Since this assignment is of writing Manual Test Cases the input test data like url are passed in the feature file itself.

@ManualUITestCase
Scenario: Navigate to blazedemo login page and Validate email address field, password field and login button are present 
	Given As customer I navigate to 'https://blazedemo.com/login' application 
	Then I validate 'Email Address' text field, 'Password' text field and 'Login' button are present 
	

@ManualUITestCase	
Scenario: Enter valid email address and password then Validate successful login 
	Given As customer I navigate to 'https://blazedemo.com/login' application 
	When I enter 'E-Mail Address' as 'test@gmail.com' in 'Login' page 
	And I enter 'Password' as 'Password@123' in 'Login' page 
	And I click on 'Login' button in 'Login' page 
	Then I validate successful login and navigation to 'Home' page 
	

@ManualUITestCase	
Scenario Outline: Enter invalid Email Address and Validate Error Messages 
	Given As customer I navigate to 'https://blazedemo.com/login' application 
	When I enter 'E-Mail Address' as '<Email Address>' in 'Login' page 
	And I enter 'Password' as 'Test123' in 'Login' page 
	And I click on 'Login' button in 'Login' page 
	Then I validate error message for 'E-Mail Address' as '<Error Message>' in 'Login' page 
	
	#Added error message from blaze demo application and some of the error messages I have added since application was not throwing those error messages.
	Examples: 
		| Email Address   										 | Error Message 				 	    							|
		| 				  										 | Please fill out this field.	 	    							|
		| test			  										 | Please enter an email address. 	    							|
		| test.com		  										 | Please enter an email address. 	    							|
		| test@.com		  										 | Please enter an email address. 	    							|
		| test@gmail	  										 | Please enter a valid email address. 								|
		| test@gmail.com. 										 | Please enter an email address. 	    							|
		| te@st@gmail.com 										 | Please enter an email address. 	    							|
		| testtesttesttesttesttesttesttesttesttesttest@gmail.com | Please enter email address with maximum length of 50 characters. | 
		| ,test@gmail.com										 | Please enter an email address.									|
		| test,@gmail.com										 | Please enter an email address.									|
		

@ManualUITestCase		
Scenario Outline: Enter invalid password and Validate Error Messages 
	Given As customer I navigate to 'https://blazedemo.com/login' application 
	When I enter 'E-Mail Address' as 'test@gmail.com' in 'Login' page 
	And I enter 'Password' as '<Password>' in 'Login' page 
	And I click on 'Login' button in 'Login' page 
	Then I validate error message for 'Password' as '<Error Message>' in 'Login' page 
	
	# Consider 'test' is an invalid Password for 'test@gmail.com' email address.
	Examples: 
		| Password | Error Message 				 						 |
		| 		   | Please fill out this field. 						 |
		| test	   | Password is incorrect. Please enter valid Password. |
		

@ManualUITestCase		
Scenario: Enter email address and password as null and Validate Error Messages 
	Given As customer I navigate to 'https://blazedemo.com/login' application 
	When I enter 'E-Mail Address' as '' in 'Login' page 
	And I enter 'Password' as '' in 'Login' page 
	And I click on 'Login' button in 'Login' page 
	Then I validate error message for 'Email Address' as 'Please fill out this field.' in 'Login' page 
