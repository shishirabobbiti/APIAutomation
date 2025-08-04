Feature: Add Place API Testing

@AddPlace 
  Scenario: Add a place successfully
    Given Add Place payload
    When user calls "AddPlaceAPI" with "POST" http request
    Then user got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"


@AddPlace_Examples @Regression
  Scenario Outline: Add a place successfully with Dynamic values
    Given Add Place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then user got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
     And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:
    |name          |language |address   |
    |GreenResidency|English  |HNo:87-776|

@DeletePlace @Regression
    Scenario: Delete a place Successfully
    Given Delete Place payload
    When user calls "deletePlaceAPI" with "Delete" http request
    Then user got success with status code 200
    And "status" in response body is "OK"



   # Scenario Outline: Add a place successfully with Dynamic values
    #Given Add Place payload with "<name>" "<language>" "<address>"
    #When user calls "AddPlaceAPI" with "POST" http request
    #Then user got success with status code 200
    #And "status" in response body is "OK"
    #And "scope" in response body is "APP"
    #And verify place_Id created maps
		#When user calls "getPlaceAPI" with "GET" http request
		#And Verify "<name>"
    #Examples:
    #|name          |language |address   |
    #|White Residency|Spanish |Sea center|



