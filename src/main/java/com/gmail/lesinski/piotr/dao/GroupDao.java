package com.gmail.lesinski.piotr.dao;

import com.gmail.lesinski.piotr.model.Exercise;
import com.gmail.lesinski.piotr.model.Group;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupDao {

    public static final Logger logger = Logger.getLogger(GroupDao.class);



    private static final String CREATE_USER_GROUP_QUERY =
            "INSERT INTO user_group (name) VALUES (?)";
    private static final String READ_USER_GROUP_QUERY =
            "SELECT * FROM user_group where id = ?";
    private static final String UPDATE_USER_GROUP_QUERY =
            "UPDATE user_group SET name = ? where id = ?";
    private static final String DELETE_USER_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USER_GROUP_QUERY =
            "SELECT * FROM user_group";


    public static Group create(Group group) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement insertStm = connection.prepareStatement(CREATE_USER_GROUP_QUERY,
                     PreparedStatement.RETURN_GENERATED_KEYS)) {
            insertStm.setString(1, group.getName());
            int result = insertStm.executeUpdate();

            if (result != 1) {
                RuntimeException ex = new RuntimeException("Nie udało się utworzyć nowej grupy");
                logger.error("Błąd tworzenia grupy", ex);
                throw ex;
            }

            try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
                if (generatedKeys.first()) {
                    group.setId(generatedKeys.getInt(1));
                    return group;
                } else {
                    RuntimeException ex = new RuntimeException("Nie utworzono klucza dla grupy");
                    logger.error("Błąd tworzenia grupy", ex);
                    throw ex;
                }

            }
        } catch (Exception e) {
            logger.error("Błąd zapisu grupy", e);
        }
        return null;
    }


    public static Group read(Integer groupId) {
        Group group = new Group();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(READ_USER_GROUP_QUERY)
        ) {
            statement.setInt(1, groupId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    group.setId(resultSet.getInt("id"));
                    group.setName(resultSet.getString("name"));
                }
            }
        } catch (Exception e) {
            logger.error("Błąd odczytu grupy o id=" + groupId, e);
        }
        return group;

    }

    public static List<Group> findAll() {
        List<Group> groupList = new ArrayList<>();
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_USER_GROUP_QUERY);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Group groupToAdd = new Group();
                groupToAdd.setId(resultSet.getInt("id"));
                groupToAdd.setName(resultSet.getString("name"));
                groupList.add(groupToAdd);
            }

        } catch (SQLException e) {
            logger.error("Błąd odczytu wszystkich grup", e);
        }
        return groupList;

    }


    public static boolean delete(Integer groupId) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_GROUP_QUERY)) {

            statement.setInt(1, groupId);
            int resultsCount = statement.executeUpdate();
            return resultsCount == 1;
        } catch (Exception e) {
            logger.error("Błąd usunięcia grupy", e);
            return false;
        }
    }

    public static void update(Group group) {
        try (Connection connection = DbUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_GROUP_QUERY)) {
            statement.setString(1, group.getName());
            statement.setInt(2, group.getId());

            statement.executeUpdate();
        } catch (Exception e) {
            logger.error("Błąd aktualizacji grupy", e);
        }

    }
}
