Feature: Test cases for speciality endpoint

  Scenario Outline: Verify get all specialties
    Given the specialities <specialities> are deleted from DB
    And the specialities <specialities> are added to DB
    When the user requests all specialities
    Then the status code is <statusCode>
    And verify the get all specialities response with DB
    Examples:
      | specialities         | statusCode |
      | automation,developer | 200        |

  Scenario Outline: Verify specialty is not added with invalid request
    Given I try to make an invalid specialties <speciality> post request
    When the user requests all specialities
    Then the new specialty <speciality> is not returned by the API
    And the specialty <speciality> is not added to the database
    Examples:
      | speciality |
      | ValidNameInvalidKey |

  Scenario Outline: Verify status code is 400 when the request is invalid
    Given I try to make an invalid specialties <speciality> post request
    Then the status code is <statusCode>
    Examples:
      | speciality          | statusCode |
      | ValidNameInvalidKey | 400        |

  Scenario Outline: Verify specialty post request with body missing
    Given I try to make an invalid specialties post request without body
    Then the status code is <statusCode>
    And an exception is thrown

    Examples:
      | statusCode |
      | 400        |

  Scenario Outline: Verify valid post specialty request
    Given I try to make a valid specialties <speciality> post request
    And the status code is <statusCode>
    When the user requests all specialities
    Then the new specialty <speciality> is returned by the API
    And the specialty <speciality> is added to the database
    Examples:
      | speciality  | statusCode |
      | TODAYS_DAYE | 201        |


  Scenario Outline: Verify delete specialty request
  Given I have at least one specialty returned by the API
  When the user deletes the last specialty
  Then the status code is <statusCode>
  And the deleted specialty is not returned by the API
  And the deleted specialty is not returned by the DB
    Examples:
      | statusCode |
      | 204        |


# Why an error is thrown if I don't add the empty example region?

#  facem request post cu body invalid - done
#  verficam status code (400) - done
#  verficam response (in cazul in care nu avem body)

##Tema:
##  - finalizat scenariul pozitiv    - DONE
  #(folositi structura step defs -> step impl   - DONE
  # folositi World.getResponse World.setResponse pentru a prelua raspunsul intre stepi
  # folositi SoftAssert pentru a compara in ultimul step din testul pozitiv datele din obiectul response cu cele
  # din baza de date : e.g
  # SoftAssertions softassert = new SoftAssertions();
  #    softassert.assertThat(10)
  #        .as("number is different")
  #        .isEqualTo(11);
  #    softassert.assertAll();)
##  - finalizat scenariul negativ pentru post



  #What to test

#  GET ALL SPECIALITIES:
#  - status code 200 - Done
#  - ce ne returneaza sa fie in db - done
#
#  stergem in caz ca exista 2 specialitati - done
#  adaugam 2 specialitati done
#  facem request get all - done
#  verificam status code - done
#  verificam datale din request cu cele din db - done
#
#
#  Create Speciality
#  - testam inserarea in db
#  - status code
#  - respose -> get id
#  -- testam request body invalid(incomplet/inexistend)
#  -- body cu content type gresit
#
#  facem requst post
#  verficam status code
#  verficam response
#  verificam db cu id din response
#
#  facem request post cu body invalid
#  verficam status code (400)
#  verficam response









#  Scenario Outline: Verify that user can create a new owner 2
#    When The user creates owner with firstName <firstName> and lastName <lastName>
#    Then The <firstName> <lastName> owner is in the response
#    And The <lastName> owner is created
#    Examples:
#      | firstName | lastName |
#      | Cristina  | Pichiu   |
