package com.gmail.lesinski.piotr;

import com.gmail.lesinski.piotr.dao.ExerciseDao;
import com.gmail.lesinski.piotr.dao.GroupDao;
import com.gmail.lesinski.piotr.dao.SolutionDao;
import com.gmail.lesinski.piotr.dao.UserDao;
import com.gmail.lesinski.piotr.model.Exercise;
import com.gmail.lesinski.piotr.model.Group;
import com.gmail.lesinski.piotr.model.Solution;
import com.gmail.lesinski.piotr.model.User;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

public class main {

    public static void main(String[] args) {

//==============================  USER  ===================================================

//        User user1 = new User("Jan Kowalski", "jankowalski@gmail.com", "tajne");
//        UserDao.create(user1);
//        UserDao.create(new User("Adam Kowalski", "adam.kowalski@gmail.com", "tajne"));
//        UserDao.create(new User("Robert Kowalski", "robert.kowalski@gmail.com", "tajne"));
//        UserDao.create(new User("Jan Nowak", "jan.nowak@gmail.com", "tajne"));
//        UserDao.create(new User("Adam Nowak", "adam.nowak@gmail.com", "tajne"));
//        UserDao.create(new User("Robert Nowak", "robert.nowak@gmail.com", "tajne"));
//
//        int idUser1 = user1.getId( );
//        System.out.println("id1: "  + idUser1);
//
//        System.out.println("READ Użtykownik z id 2: " + UserDao.read(2));
//
//
//        user2 = new User("Pan Kleks", "kseksik@gmail.com", "tajne");
//        UserDao.update(user2);
//        System.out.println("UPDATE Użtykownik z id 2: " + UserDao.read(2));
//
//
//        System.out.println("DELETE usunięcie uytkownika z id 2 " + UserDao.delete(2));
//
//
//        System.out.println("FIND ALL" );
//        System.out.println(UserDao.findAll());

// ============================  EXERCISE  ================================================

//        Exercise exercise1 = new Exercise("Zakupy", "2 mleka");
//        ExerciseDao.create(exercise1);
//
//        ExerciseDao.create(new Exercise("Zakupy", "3 masła"));
//        ExerciseDao.create(new Exercise("Zakupy", "4 piwa"));
//        ExerciseDao.create(new Exercise("Poczta", "odebrać paczkę na poczcie"));
//        ExerciseDao.create(new Exercise("Auto", "zrobić przegląd okresowy auta"));
//
//        exercise1 = new Exercise("Zakupy", "4 mleka");
//        ExerciseDao.update(exercise1);
//
//        System.out.println(ExerciseDao.read(1));
//
//        System.out.println("DELETE " + ExerciseDao.delete(1));
//
//        System.out.println("All: " + ExerciseDao.findAll());

// =================================  GROUP  ==========================================

//        Group group = new Group("misie");
//        GroupDao.create(group);
//        GroupDao.create(new Group("kotki"));
//        GroupDao.create(new Group("pieski"));
//        GroupDao.create(new Group("kucyki"));
//        GroupDao.create(new Group("rybki"));
//
//        group.setName("super misie");
//        GroupDao.update(group);
//
//        System.out.println("READ " + GroupDao.read(1));
//
//        System.out.println("DELETE " + GroupDao.delete(1));

//        List<Group> groupList = GroupDao.findAll();
//        for (Group grupa : groupList) {
//            System.out.println(grupa);
//        }

//====================================  SOLUTION  ========================================

        Solution solution = new Solution("2019-06-11 15:15:15", "2019-06-12 15:16:17",
                "łatwe i przyjemne", 2, 1);
        SolutionDao.create(solution);

        SolutionDao.create(new Solution("2019-05-10 14:20:25", "2019-06-11 20:15:20",
                                        "fajne", 3, 3));

        SolutionDao.create(new Solution("2019-05-11 23:00:00", "2019-06-11 21:20:30",
                                        "super", 3, 7));

        SolutionDao.create(new Solution("2019-04-20 15:23:44", "2019-06-11 10:22:00",
                                        "mega", 5, 8));

        SolutionDao.create(new Solution("2019-01-10 21:10:00", "2019-06-11 21:00:00",
                                         "ok", 4, 9));

        solution.setDescription("nie łatwe acz mega trudne");
        SolutionDao.update(solution);
        System.out.println("READ: " + SolutionDao.read(1));

        System.out.println("DELETE: " + SolutionDao.delete(1));

        List<Solution> solutionList = SolutionDao.findAll( );
        for (Solution rozw : solutionList) {
            System.out.println(rozw);
        }


    }
}
