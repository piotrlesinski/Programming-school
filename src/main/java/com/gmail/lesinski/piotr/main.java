package com.gmail.lesinski.piotr;

import com.gmail.lesinski.piotr.dao.UserDao;
import com.gmail.lesinski.piotr.model.User;

public class main {

    public static void main(String[] args) {

        User user1 = new User("Jan", "jankowalski@gmail.com", "tajne");
        User user2 = new User("Adam Kowalski", "adam.kowalski@gmail.com", "tajne");
        User user3 = new User("Robert Kowalski", "robert.kowalski@gmail.com", "tajne");

        UserDao userDao = new UserDao();
        userDao.create(user1);
        userDao.create(user2);
        userDao.create(user3);

        int idUser1 = user1.getId( );
        int idUser2 = user2.getId( );
        int idUser3 = user3.getId( );

        System.out.println("id1: "  + idUser1 + " id2: " + idUser2 +" id3+: " +idUser3 );
    }
}
