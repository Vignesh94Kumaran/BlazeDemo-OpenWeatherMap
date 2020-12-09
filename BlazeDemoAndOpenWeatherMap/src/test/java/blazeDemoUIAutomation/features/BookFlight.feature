Feature: Book Flight and Validate Confirmation Id


@Automated 
@UIAutomation 
Scenario: Validate successful navigation to BlazeDemo booking page 
	Given As Customer I navigate to 'BlazeDemo' application 
	When I click on 'Travel The World' tab in 'index' page 
	Then I validate 'Welcome to the Simple Travel Agency!' text in 'index' page 
	
	
@Automated 
@UIAutomation 
Scenario Outline: Validate Selected departure and destination city in Reserve Page 
	Given As Customer I navigate to 'BlazeDemo' application 
	When I click on 'Travel The World' tab in 'index' page 
	And I select '<departure>' as 'departure' city in 'index' page 
	And I select '<destination>' as 'destination' city in 'index' page 
	And I click on 'Find Flights' button in 'index' page 
	Then I validate 'Flights from <departure> to <destination>:' text in 'reserve' page 
	And I validate 'Departs' column has '<departure>' in 'reserve' page 
	And I validate 'Arrives' column has '<destination>' in 'reserve' page 
	
	Examples: 
		| departure | destination |
		| Portland  | New York    |


@Automated 
@UIAutomation 
Scenario Outline: Create booking and Validate navigation to Confirmation page and Confimration ID has been generated 
	Given As Customer I navigate to 'BlazeDemo' application 
	When I click on 'Travel The World' tab in 'index' page 
	And I select '<departure>' as 'departure' city in 'index' page 
	And I select '<destination>' as 'destination' city in 'index' page 
	And I click on 'Find Flights' button in 'index' page 
	And I click on 'Choose This Flight' button for '<Flight #>' flight number in 'reserve' page 
	And I fill the form in 'purchase' page 
		| Name  | Address 		 | City 	 | State 	 | Zip Code | Card Type 	   | Credit Card Number | Month | Year | Name on Card |
		| Chris | Newtown Street | Bangalore | Karnataka | 560075	| American Express | 123456789		    | 12    | 2002 | Chris Evans  | 
	And I click on 'Purchase Flight' button in 'purchase' page 
	Then I validate 'Thank you for your purchase today!' text in 'confirmation' page 
	And I validate 'Id' has been generated in 'confirmation' page 
	
	Examples: 
		| departure | destination | Flight # |
		| Portland  | New York    | 12       |


#Below test case is not automated because application is not working as expected. (Whatever flight is chosen no change to flight details in UI for purchase page)
@ManualUITestCase 
Scenario Outline: Validate navigation to purchase page with selected city details and chosen Flight details in purchase page 
	Given As Customer I navigate to 'BlazeDemo' application 
	When I click on 'Travel The World' tab in 'index' page 
	And I select '<departure>' as 'departure' city in 'index' page 
	And I select '<destination>' as 'destination' city in 'index' page 
	And I click on 'Find Flights' button in 'index' page 
	And I get the details of '<Flight #>' flight number in 'purchase' page 
	And I click on 'Choose This Flight' button for '<Flight #>' flight number in 'reserve' page 
	Then I validate 'Your flight from '<departure city code>' to '<destiantion city code>' has been reserved.' text in 'purchase' page 
	And I validate flight details and price present in 'purchase' page with the retrieved flight details and price in 'reserve' page 

	Examples: 
		| departure | destination | departure city code | destiantion city code | Flight # |
		| Portland  | New York    | PDX					| DUB					| 12       |
	
	
#Below test case is not automated because application is not working as expected. (UI is not implemented for the validation messages.)
@ManualUITestCase 
Scenario: Validate error messages for purchase flight without filling form 
	Given As Customer I navigate to 'BlazeDemo' application 
	When I click on 'Travel The World' tab in 'index' page 
	And I select '<departure>' as 'departure' city in 'index' page 
	And I select '<destination>' as 'destination' city in 'index' page 
	And I click on 'Find Flights' button in 'index' page 
	And I get the details of '<Flight #>' flight number in 'purchase' page 
	And I click on 'Choose This Flight' button for '<Flight #>' flight number in 'reserve' page 
	And I click on 'Purchase Flight' button in 'purchase' page 
	Then I validate error messages received for mandatory fields in 'purchase' page 
		| Field 			 | Error Message				   |
		| Name				 | Name is requried.			   |
		| Address 			 | Address is required.			   |
		| City				 | City is required.  			   |
		| State 			 | State is required. 			   |
		| Zip Code			 | Zip Code is required. 		   |
		| Credit Card Number | Credit Card Number is required. |
		| Name on Card 		 | Name on Card is required. 	   | 
	
	
#Below test case is not automated because application is not working as expected. (UI is not implemented for the validation messages.)
@ManualUITestCase 
Scenario: Validate error messages for Zip Code, Credit Card Number, Month and Year fields in form 
	Given As Customer I navigate to 'BlazeDemo' application 
	When I click on 'Travel The World' tab in 'index' page 
	And I select '<departure>' as 'departure' city in 'index' page 
	And I select '<destination>' as 'destination' city in 'index' page 
	And I click on 'Find Flights' button in 'index' page 
	And I get the details of '<Flight #>' flight number in 'purchase' page 
	And I click on 'Choose This Flight' button for '<Flight #>' flight number in 'reserve' page 
	And I fill the form in 'purchase' page 
		| Name  | Address 		 | City 	 | State 	 | Zip Code | Card Type 	   | Credit Card Number | Month | Year | Name on Card |
		| Chris | Newtown Street | Bangalore | Karnataka | xyz   	| American Express | asd   		 	 	| ab    | sdgv | Chris Evans  |
	And I click on 'Purchase Flight' button in 'purchase' page 
	Then I validate error messages received for mandatory fields in 'purchase' page 
		| Field 			 | Error Message				   										  |
		| Zip Code			 | Zip Code should not contain alphabets or special characters. 		  |
		| Credit Card Number | Credit Card Number should not contain alphabets or special characters. |
		| Month  			 | Month should not contain alphabets or special characters. 		      |
		| Year               | Year should not contain alphabets or special characters.               |
	When I fill the form in 'purchase' page 
		| Zip Code | Credit Card Number | Month | Year  |
		| 12345    | 123   		 	    | 123   | 30587 |
	And I click on 'Purchase Flight' button in 'purchase' page 
	Then I validate error messages received for mandatory fields in 'purchase' page 
		| Field 			 | Error Message				   								  |
		| Credit Card Number | Credit Card Number should be with maximum length of 16 digits. |
		| Month 			 | Month should be less than 12. 								  |
		| Year 				 | Year is invalid. Please check.								  |
