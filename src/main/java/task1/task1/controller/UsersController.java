package task1.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import task1.task1.entity.Users;
import task1.task1.payload.Result;
import task1.task1.payload.UserDto;
import task1.task1.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    UserService userService;

    @PostMapping
    public Result addUser(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }
    @GetMapping
    public List<Users> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{id}")
    public Users getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }
    @PutMapping("/{id}")
    public Result editUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        return userService.editUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteUser(@PathVariable Integer id){
        return userService.deleteUser(id);
    }

}
