package tech.salvas.eifapi.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthDTO {
    private String token;
    private UserDTO userDTO;
}
