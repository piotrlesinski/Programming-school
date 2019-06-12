package com.gmail.lesinski.piotr.dao;

import com.gmail.lesinski.piotr.model.Solution;
import com.gmail.lesinski.piotr.model.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SolutionDao {

    public static final Logger logger = Logger.getLogger(SolutionDao.class);



    private static final String CREATE_SOLUTION_QUERY =
            "INSERT INTO solution (created, updated, description, exercise_id, user_id) VALUES (?, ?, ?, ?, ?)";
    private static final String READ_SOLUTION_QUERY =
            "SELECT * FROM solution where id = ?";
    private static final String UPDATE_SOLUTION_QUERY =
            "UPDATE solution SET created = ?, updated = ?, description = ?, exercise_id = ?, user_id = ? where id = ?";
    private static final String DELETE_SOLUTION_QUERY =
            "DELETE FROM solution WHERE id = ?";
    private static final String FIND_ALL_SOLUTIONS_QUERY =
            "SELECT * FROM solution";


    public static Solution create(Solution solution) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_SOLUTION_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, solution.getCreated());
            insertStm.setString(2, solution.getUpdated());
            insertStm.setString(3, solution.getDescription());
            insertStm.setInt(4, solution.getExercise_id());
            insertStm.setInt(5, solution.getUser_id());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                RuntimeException ex = new RuntimeException("Nie udało się utworzyć nowego rozwiązania");
                logger.error("Błąd tworzenia rozwiązania", ex);
                throw ex;
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    solution.setId(generatedKeys.getInt(1));
                    return solution;
                } else {
                    RuntimeException ex = new RuntimeException("Nie utworzono klucza dla rozwiązania");
                    logger.error("Błąd tworzenia rozwiązania", ex);
                    throw ex;
                }

            }
        } catch (Exception e) {
            logger.error("Błąd zapisu rozwiązania", e);
        }
        return null;
    }


    public static Solution read(Integer solutionId) {
        Solution solution = new Solution();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_SOLUTION_QUERY)
        ) {
            statement.setInt(1, solutionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    solution.setId(resultSet.getInt("id"));
                    solution.setCreated(resultSet.getString("created"));
                    solution.setUpdated(resultSet.getString("updated"));
                    solution.setDescription(resultSet.getString("description"));
                    solution.setExercise_id(resultSet.getInt("exercise_id"));
                    solution.setUser_id(resultSet.getInt("user_id"));
                }
            }
        } catch (Exception e) {
            logger.error("Błąd odczytu rozwiązania o id=" + solutionId, e);
        }
        return solution;

    }

    public static List<Solution> findAll() {
        List<Solution> solutionList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Solution solutionToAdd = new Solution();
                solutionToAdd.setId(resultSet.getInt("id"));
                solutionToAdd.setCreated(resultSet.getString("created"));
                solutionToAdd.setUpdated(resultSet.getString("updated"));
                solutionToAdd.setDescription(resultSet.getString("description"));
                solutionToAdd.setExercise_id(resultSet.getInt("exercise_id"));
                solutionToAdd.setUser_id(resultSet.getInt("user_id"));
                solutionList.add(solutionToAdd);
            }

        } catch (SQLException e) {
            logger.error("Błąd odczytu wszystkich rozwiązań", e);
        }
        return solutionList;

    }


    public static boolean delete(Integer solutionId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SOLUTION_QUERY)) {

            statement.setInt(1, solutionId);
            int resultsCount = statement.executeUpdate();
            return resultsCount == 1;
        } catch (Exception e) {
            logger.error("Błąd usunięcia rozwiązania", e);
            return false;
        }
    }

    public static void update(Solution solution) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_SOLUTION_QUERY)) {
            statement.setString(1, solution.getCreated());
            statement.setString(2, solution.getUpdated());
            statement.setString(3, solution.getDescription());
            statement.setInt(4, solution.getExercise_id());
            statement.setInt(5, solution.getUser_id());
            statement.setInt(6, solution.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Błąd aktualizacji rozwiązania", e);
        }

    }
}
