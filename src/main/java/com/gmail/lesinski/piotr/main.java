package com.gmail.lesinski.piotr;

import com.gmail.lesinski.piotr.dao.UserDao;
import com.gmail.lesinski.piotr.model.User;

public class main {

    public static void main(String[] args) {

        User user1 = new User("Jan Kowalski", "jankowalski@gmail.com", "tajne");
        User user2 = new User("Adam Kowalski", "adam.kowalski@gmail.com", "tajne");
        User user3 = new User("Robert Kowalski", "robert.kowalski@gmail.com", "tajne");
        User user4 = new User("Jan Nowak", "jan.nowak@gmail.com", "tajne");
        User user5 = new User("Adam Nowak", "adam.nowak@gmail.com", "tajne");
        User user6 = new User("Robert Nowak", "robert.nowak@gmail.com", "tajne");

 //       UserDao userDao = new UserDao();
        UserDao.create(user1);
        UserDao.create(user2);
        UserDao.create(user3);
        UserDao.create(user4);
        UserDao.create(user5);
        UserDao.create(user6);

        int idUser1 = user1.getId( );
        int idUser2 = user2.getId( );
        int idUser3 = user3.getId( );
        int idUser4 = user4.getId( );
        int idUser5 = user5.getId( );
        int idUser6 = user6.getId( );

        System.out.println("id1: "  + idUser1 + " id2: " + idUser2 + " id3+: " +idUser3 + " id4+: " +idUser4
                + " id5+: " +idUser5 + " id6+: " +idUser6);

        System.out.println("READ Użtykownik z id 2: " + UserDao.read(2));


        user2 = new User("Pan Kleks", "kseksik@gmail.com", "tajne");
        UserDao.update(user2);
        System.out.println("UPDATE Użtykownik z id 2: " + UserDao.read(2));


        System.out.println("DELETE usunięcie uytkownika z id 2 " + UserDao.delete(2));


        System.out.println("FIND ALL" );
        System.out.println(UserDao.findAll());

    }
}
