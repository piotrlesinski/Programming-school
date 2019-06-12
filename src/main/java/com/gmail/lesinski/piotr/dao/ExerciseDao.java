package com.gmail.lesinski.piotr.dao;

import com.gmail.lesinski.piotr.model.Exercise;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDao {

    public static final Logger logger = Logger.getLogger(UserDao.class);



    private static final String CREATE_EXERCISE_QUERY =
            "INSERT INTO exercise (title, description) VALUES (?, ?)";
    private static final String READ_EXERCISE_QUERY =
            "SELECT * FROM exercise where id = ?";
    private static final String UPDATE_EXERCISE_QUERY =
            "UPDATE exercise SET title = ?, description = ? where id = ?";
    private static final String DELETE_EXERCISE_QUERY =
            "DELETE FROM exercise WHERE id = ?";
    private static final String FIND_ALL_EXERCISES_QUERY =
            "SELECT * FROM exercise";


    public static Exercise create(Exercise exercise) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_EXERCISE_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, exercise.getTitle());
            insertStm.setString(2, exercise.getDescription());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                RuntimeException ex = new RuntimeException("Nie udało się utworzyć nowego zadania");
                logger.error("Błąd tworzenia zadania", ex);
                throw ex;
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    exercise.setId(generatedKeys.getInt(1));
                    return exercise;
                } else {
                    RuntimeException ex = new RuntimeException("Nie utworzono klucza dla zadania");
                    logger.error("Błąd tworzenia zadania", ex);
                    throw ex;
                }

            }
        } catch (Exception e) {
            logger.error("Błąd zapisu zadania", e);
        }
        return null;
    }


    public static Exercise read(Integer exerciseId) {
        Exercise exercise = new Exercise();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_EXERCISE_QUERY)
        ) {
            statement.setInt(1, exerciseId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    exercise.setId(resultSet.getInt("id"));
                    exercise.setTitle(resultSet.getString("title"));
                    exercise.setDescription(resultSet.getString("description"));
                }
            }
        } catch (Exception e) {
            logger.error("Błąd odczytu zadania o id=" + exerciseId, e);
        }
        return exercise;

    }

    public static List<Exercise> findAll() {
        List<Exercise> exerciseList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_EXERCISES_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Exercise exerciseToAdd = new Exercise();
                exerciseToAdd.setId(resultSet.getInt("id"));
                exerciseToAdd.setTitle(resultSet.getString("title"));
                exerciseToAdd.setDescription(resultSet.getString("description"));
                exerciseList.add(exerciseToAdd);
            }

        } catch (SQLException e) {
            logger.error("Błąd odczytu wszystkich zadań", e);
        }
        return exerciseList;

    }


    public static boolean delete(Integer exerciseId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_EXERCISE_QUERY)) {

            statement.setInt(1, exerciseId);
            int resultsCount = statement.executeUpdate();
            return resultsCount == 1;
        } catch (Exception e) {
            logger.error("Błąd usunięcia zadania", e);
            return false;
        }
    }

    public static void update(Exercise exercise) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_EXERCISE_QUERY)) {
            statement.setString(1, exercise.getTitle());
            statement.setString(2, exercise.getDescription());
            statement.setInt(3, exercise.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Błąd aktualizacji zadania", e);
        }

    }
}
