package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);


        userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

        Car mercedes = new Car("mercedes", 500);
        User Petya = new User("Petya", "Petrov", "petya@mail.ru");
        mercedes.setUser(Petya);
        Petya.setCar(mercedes);
        userService.add(Petya);

        User vasya = new User("Vasya", "Ivanov", "vasya@mail.ru");
        Car bmw = new Car("bmw", 2);
        vasya.setCar(bmw);
        userService.add(vasya);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
        }

        for(User user : userService.listUsers()){
            System.out.println("Car = " + user.getCar());
        }

        System.out.println(" Поиск человека по машине = " + userService.getUserByCar("bmw", 2));

        context.close();
    }
}
