package stepDefinitions;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import models.Speciality;
import net.thucydides.core.annotations.Steps;
import stepImplementation.DatabaseStepImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class DatabaseStepDefinitions {


  @Steps
  DatabaseStepImpl databaseStepImpl;


  @Given("^the specialities (.*) are deleted from DB$")
  public void deleteSpecialities (String specialities) {

    databaseStepImpl.deleteSpecialities(
            Arrays.asList(specialities.split(",")
            ));

  }

  @And("^the specialities (.*) are added to DB$")
  public void theSpecialitiesSpecialitiesAreAddedToDB(String specialities) {
    databaseStepImpl.addSpecialities(Arrays.asList(specialities.split(",")));
  }


  @And("the specialty {} is not added to the database")
  public void theSpecialtyIsNotAddedToTheDatabase(String nam) {
    List<Speciality> response=databaseStepImpl.getSpeciality(nam);

    boolean found=false;
    for(Speciality element:response){
      if (element.getName().equals(nam)) {
        found = true;
        break;
      }
    }
    assertFalse(found);
  }

  @And("the specialty {} is added to the database")
  public void theSpecialtySpecialityIsAddedToTheDatabase(String name) {
    List<Speciality> response=databaseStepImpl.getSpeciality(name);

    boolean found=false;
    for(Speciality element:response){
      if (element.getName().equals(name)) {
        found = true;
        break;
      }
    }
    assertTrue(found);
  }

  @And("the deleted specialty is not returned by the DB")
  public void theDeletedSpecialtyIsNotReturnedByTheDB() {
    List<Speciality> response=databaseStepImpl.getSpeciality(SpecialityStepDefinitions.lastId);

    boolean found=false;
    for(Speciality element:response){
      if (element.getId().equals(SpecialityStepDefinitions.lastId)) {
        found = true;
        break;
      }
    }
    assertFalse(found);
  }
}
