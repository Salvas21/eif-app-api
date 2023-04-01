package tech.salvas.eifapi.repository;

import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.model.Activity;
import tech.salvas.eifapi.model.Student;
import tech.salvas.eifapi.model.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository() {
        this.users.add(new User("Admin", "Name", "ADMIN12345678", true));
        this.users.add(new Student("Martin", "Sandwich", "SANM12345678", 2, new Activity[] {new Activity("ACT12", "Test", "Description", "2")}));
        this.users.add(new Student("Bob", "Bob", "BOBB87654321", 1, new Activity[] {new Activity("BEG11", "Beginner", "A beginner task", "1"), new Activity("BEG22", "Another", "An other beginner task", "1")}));
    }

    public List<User> getUsers() {
        return users;
    }

    public User userAt(int index) {
        return this.users.get(index);
    }

    public Student getStudent() {
        return (Student) this.users.stream().filter(user -> user instanceof Student).findAny().orElse(null);
    }

    public User getAdmin() {
        return this.users.stream().filter(User::isAdmin).findAny().orElse(null);
    }
}
