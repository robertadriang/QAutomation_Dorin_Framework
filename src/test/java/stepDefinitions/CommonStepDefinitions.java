package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import static org.junit.Assert.assertTrue;

public class CommonStepDefinitions {
    @Given("The PetClinic application is up and running")
    public void verifyTheApplication() {
        RestAssured.
                given()
                .when()
                .get("http://localhost:9966/petclinic/swagger-ui.html#/owner-rest-controller")
                .then()
                .assertThat().statusCode(200);
    }

//    @Then("the status code is <statusCode>")
//    public void theStatusCodeIsStatusCode() {
//        RestAssured.
//                given().
//                when().get("http://localhost:9966/petclinic/api/owners").
//                then().assertThat().statusCode(200);
//    }  /// This is from the specialty status code. We can pass the status code and the slug of the url to have a higher end method?
}
