package util.database;


public class Queries {

  public final static String SELECT_ALL_FROM_TABLE = "SELECT * FROM  ";

  public final static String DELETE_RECORDS_FROM_SPECIALITY = "DELETE FROM specialties where name in (?,?)";

  public final static String ADD_RECORDS_TO_SPECIALITY = " INSERT INTO specialties (name) VALUES (?)";

  public final static String GET_SPECIFIC_SPECIALITY_BY_ID ="SELECT * FROM specialties WHERE name=?";
  public final static String GET_SPECIFIC_SPECIALITY_BY_NAME ="SELECT * FROM specialties WHERE id=?";
}
