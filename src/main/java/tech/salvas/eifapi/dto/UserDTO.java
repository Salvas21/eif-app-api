package tech.salvas.eifapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import tech.salvas.eifapi.model.User;

@NoArgsConstructor
@Data
public class UserDTO {
    private  String fName;
    private String lName;
    private String cp;
    private boolean isAdmin;

    public UserDTO(User user) {
        this.fName = user.getFName();
        this.lName = user.getLName();
        this.cp = user.getCp();
        this.isAdmin = user.isAdmin();
    }

}
