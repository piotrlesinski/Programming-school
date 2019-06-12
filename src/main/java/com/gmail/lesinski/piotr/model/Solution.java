package com.gmail.lesinski.piotr.model;

import com.gmail.lesinski.piotr.dao.DbUtil;
import com.gmail.lesinski.piotr.dao.SolutionDao;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static final Logger logger = Logger.getLogger(SolutionDao.class);


    private int id;
    private String created;
    private String updated;
    private String description;
    private int exercise_id;
    private int user_id;

    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY =
            "SELECT * FROM solution JOIN users ON solution.user_id=users.id WHERE users.id=?;";

    private static final String FIND_ALL_SOLUTIONS_BY_USER_ID = "SELECT * FROM solution JOIN exercise ON " +
            "solution.exercise_id=exercise.id WHERE exercise_id = ? ORDER BY created DESC;";



    public static List<Solution> findAllByUserId (Integer userId) {

        List<Solution> solutionList = new ArrayList<>( );
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Solution solution = new Solution();
                    solution.setId(resultSet.getInt("id"));
                    solution.setCreated(resultSet.getString("created"));
                    solution.setUpdated(resultSet.getString("updated"));
                    solution.setDescription(resultSet.getString("description"));
                    solution.setExercise_id(resultSet.getInt("exercise_id"));
                    solution.setUser_id(resultSet.getInt("user_id"));
                    solutionList.add(solution);
                }
            }
        } catch (Exception e) {
            logger.error("Błąd odczytu rozwiązania o id=" + userId, e);
        }
        return solutionList;

    }

    public static List<Solution> findAllSolutionsByExerciseId (Integer userId) {

        List<Solution> solutionList = new ArrayList<>( );
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SOLUTIONS_BY_USER_ID_QUERY)
        ) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Solution solution = new Solution();
                    solution.setId(resultSet.getInt("id"));
                    solution.setCreated(resultSet.getString("created"));
                    solution.setUpdated(resultSet.getString("updated"));
                    solution.setDescription(resultSet.getString("description"));
                    solution.setExercise_id(resultSet.getInt("exercise_id"));
                    solution.setUser_id(resultSet.getInt("user_id"));
                    solutionList.add(solution);
                }
            }
        } catch (Exception e) {
            logger.error("Błąd odczytu rozwiązania użytkownika o id=" + userId, e);
        }
        return solutionList;

    }

    public Solution() {

    }

    public Solution(String created, String updated, String description, int exercise_id, int user_id) {
        this.created = created;
        this.updated = updated;
        this.description = description;
        this.exercise_id = exercise_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", description='" + description + '\'' +
                ", exercise_id=" + exercise_id +
                ", user_id=" + user_id +
                '}';
    }
}
