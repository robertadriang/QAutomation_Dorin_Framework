package stepDefinitions;


import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.Speciality;
import models.invalidModels.invalidSpeciality;
import net.thucydides.core.annotations.Steps;
import rest.Endpoints;
import stepImplementation.SpecialityStepImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static net.serenitybdd.rest.SerenityRest.rest;
import static org.junit.Assert.*;

public class SpecialityStepDefinitions {

  @Steps
  SpecialityStepImpl specialityStepImpl;

  static Response response;

  static String responseString;

  static int statusCode;

  static int lastId;

  @When("the user requests all specialities")
  public void theUserRequestsAllSpecialities() {
    response=RestAssured.
            given().
            when().get(Endpoints.BASE_URL+Endpoints.SPECIALTIES);

    statusCode=response.getStatusCode();

    responseString = response.then().assertThat().statusCode(200)
            .extract().body().asString();
  }

  @Then("the status code is {int}")
  public void theStatusCodeThen(int statusCode) {
    assertEquals("The status code is:",statusCode,SpecialityStepDefinitions.statusCode);
  }


  @Given("I try to make an invalid specialties {} post request")
  public void iTryToMakeAnInvalidSpecialtiesPostRequest(String nam) {
    invalidSpeciality invalidExample=new invalidSpeciality(1,nam);
    statusCode=RestAssured.given().contentType(ContentType.JSON)
            .body(invalidExample)
            .when()
            .post(Endpoints.BASE_URL+Endpoints.SPECIALTIES)
            .getStatusCode();
  }

  @Then("the new specialty {} is not returned by the API")
  public void theNewSpecialtyIsNotReturnedByTheAPI(String nam) {
    assertFalse(responseString.contains(nam));
  }


  @Given("I try to make an invalid specialties post request without body")
  public void iTryToMakeAnInvalidSpecialtiesPostRequestWithoutBody() {
    response=RestAssured.
            given().
            when().post(Endpoints.BASE_URL+Endpoints.SPECIALTIES);

    statusCode=response.getStatusCode();

    responseString = response.then().extract().body().asString();
  }

  @And("an exception is thrown")
  public void anExceptionIsThrown() {
    assertTrue(responseString.toLowerCase(Locale.ROOT).contains("exception"));
  }

  @Given("I try to make a valid specialties {} post request")
  public void iTryToMakeAValidSpecialtiesSpecialityPostRequest(String name) {
    Speciality speciality=new Speciality(null,name);
    statusCode=RestAssured.given().contentType(ContentType.JSON)
            .body(speciality)
            .when()
            .post(Endpoints.BASE_URL+Endpoints.SPECIALTIES)
            .getStatusCode();
    System.out.println(statusCode);
  }

  @Then("the new specialty {} is returned by the API")
  public void theNewSpecialtySpecialityIsReturnedByTheAPI(String name) {
    assertTrue(responseString.contains(name));
  }

  @Given("I have at least one specialty returned by the API")
  public void iHaveAtLeastOneSpecialtyReturnedByTheAPI() {
    theUserRequestsAllSpecialities();
    assertFalse(responseString.isEmpty());
  }

  @When("the user deletes the last specialty")
  public void theUserDeletesTheLastSpecialty() {
    Gson gson=new Gson();
   Speciality[] apiResponse = gson.fromJson(SpecialityStepDefinitions.responseString,Speciality[].class);
   lastId=apiResponse[apiResponse.length-1].getId();
    System.out.println(apiResponse);
    System.out.println(lastId);
    statusCode=RestAssured.given().delete(Endpoints.BASE_URL+Endpoints.SPECIALTIES+"/"+String.valueOf(lastId)).getStatusCode();
    System.out.println(statusCode);
  }

  @And("the deleted specialty is not returned by the API")
  public void theDeletedSpecialtyIsNotReturnedByTheAPI() {
    theUserRequestsAllSpecialities();
    assertFalse(responseString.contains(String.valueOf(lastId)));
  }
}
