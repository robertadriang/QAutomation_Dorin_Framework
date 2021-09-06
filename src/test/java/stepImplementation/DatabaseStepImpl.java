package stepImplementation;


import lombok.extern.slf4j.Slf4j;
import models.Speciality;
import util.database.DbConnection;
import util.database.DbTables;
import util.database.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class DatabaseStepImpl {

    DbConnection database = new DbConnection();


    public List<Speciality> getAllSpecialities() {

        String table = DbTables.SPECIALITIES.getTableName();

        List<Speciality> specialities = new ArrayList<>();

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.SELECT_ALL_FROM_TABLE + table)
        ) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                Speciality speciality = Speciality.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();

                specialities.add(speciality);
            }
        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

        return specialities;
    }


    public void deleteSpecialities(List<String> specialities) {

        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.DELETE_RECORDS_FROM_SPECIALITY)
        ) {

            preparedStatement.setString(1, specialities.get(0));
            preparedStatement.setString(2, specialities.get(1));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }

    }

    public void addSpecialities(List<String> asList) {
        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.ADD_RECORDS_TO_SPECIALITY)
        ) {
            for (String element : asList) {
                preparedStatement.setString(1, element);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
    }

    public List<Speciality> getSpeciality(String nam) {
        List<Speciality> specialities = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.GET_SPECIFIC_SPECIALITY_BY_ID)
        ) {
            preparedStatement.setString(1, nam);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Speciality speciality = Speciality.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();

                specialities.add(speciality);
            }

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return specialities;
    }

    public List<Speciality> getSpeciality(int id) {
        List<Speciality> specialities = new ArrayList<>();
        try (
                PreparedStatement preparedStatement = database.connectToDatabase().prepareStatement(
                        Queries.GET_SPECIFIC_SPECIALITY_BY_NAME)
        ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Speciality speciality = Speciality.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .build();

                specialities.add(speciality);
            }

        } catch (SQLException e) {
            log.error("Database error: ", e);
        }
        return specialities;
    }
}

