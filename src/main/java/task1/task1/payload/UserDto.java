package task1.task1.payload;

import lombok.Data;

@Data
public class UserDto {
private String firstName;
private String lastName;
private String phoneNumber;
private String password;
private boolean active=true;

}
