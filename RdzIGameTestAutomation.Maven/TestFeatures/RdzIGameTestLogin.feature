Feature: IGame Login Smoke Test

  Background: #Currently test feature suport only Chrome and Firefox. Supported values are: chrome, firefox
		Given I open IGame page in "firefox"

	Scenario: Negative Test Case - Emtpy Password
  	Given I open Login form  
  	When I enter username as "test"
  	Then Log In button should remain disabled
	  	
	Scenario: Negative Test Case - Wrong Credentials
  	Given I open Login form 
  	When I enter username as "test"
  	And I enter password as "test"
  	And I press login button
  	Then Validation message should appear
  	And Mesage text should be "The username and/or password is incorrect"
  
  @DoLogOut	
  Scenario Outline:
  	Given I open Login form 
  	When I enter username as "<Email>"
  	And I enter password as "<Password>"
  	And I press login button
  	Then User should be loged in
  	
  	Examples:
  		|Email										|Password					|
  		|ratkodz@mailinator.com		|KjnOM816kE8rn6e	|
  		|ratko.dz@mailinator.com	|OncSDalAY118p7e	|