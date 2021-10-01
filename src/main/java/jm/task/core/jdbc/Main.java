package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();
        userService.createUsersTable();
        userService.saveUser("Oleg", "Petrov", (byte) 20);
        userService.saveUser("Pavel", "Volkov", (byte) 32);
        userService.saveUser("Maksim", "Ivanov", (byte) 29);
        userService.saveUser("Evgeniy", "Krasov", (byte) 18);
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
