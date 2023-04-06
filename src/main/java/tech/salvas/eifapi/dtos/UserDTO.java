package tech.salvas.eifapi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.salvas.eifapi.models.User;

@NoArgsConstructor
@Data
public class UserDTO {
    private long id;
    private  String first_name;
    private String last_name;
    private String cp;
    private boolean isAdmin;

    public UserDTO(User user) {
        this.first_name = user.getFName();
        this.last_name = user.getLName();
        this.cp = user.getCp();
        this.isAdmin = user.isAdmin();
    }

}
