package tech.salvas.eifapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private  String fName;
    private String lName;
    private String cp;
    private boolean isAdmin;
}