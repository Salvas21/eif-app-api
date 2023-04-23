package tech.salvas.eifapi.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    private long id;
    private  String first_name;
    private String last_name;
    private String cp;
    private boolean isAdmin;
}
