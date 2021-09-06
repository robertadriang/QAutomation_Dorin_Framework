package stepDefinitions;


import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import models.Speciality;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;
import util.World;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class DataValidationStepDefinitions {
  @Steps
  DatabaseStepImpl databaseStepImpl;

  @Given("Thedfsfds user get the list with all owners")
  public void getOwners () {

    World.getResponse();
  }

  @And("verify the get all specialities response with DB")
  public void verifyTheGetAllSpecialitiesResponseWithDB() {
    Gson gson=new Gson();
    List<Speciality> apiResponse = Arrays.asList(
            gson.fromJson(SpecialityStepDefinitions.responseString,Speciality[].class)
    );
    List<Speciality> dbResponse = databaseStepImpl.getAllSpecialities();
    assertEquals(apiResponse, dbResponse);

  }
}
