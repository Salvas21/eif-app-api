package tech.salvas.eifapi.repositories;

import org.springframework.stereotype.Repository;
import tech.salvas.eifapi.models.Choice;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.models.User;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public UserRepository(ActivityRepository repository) {
        this.users.add(new User("Admin", "Name", "ADMIN12345678", true));
        this.users.add(new Student("Martin", "Sandwich", "SANM12345678", 2, new Choice[] {new Choice(repository.getActivities().get(4), 1)}));
        this.users.add(new Student("Bob", "Bob", "BOBB87654321", 1, new Choice[] {new Choice(repository.getActivities().get(5), 1), new Choice(repository.getActivities().get(6), 2), new Choice(repository.getActivities().get(2), 3)}));
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
