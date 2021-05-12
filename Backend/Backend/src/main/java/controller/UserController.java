package controller;

import model.Patient;
import model.User;
import model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    //READ: get all patients
    @RequestMapping(path = "users", method = RequestMethod.GET)
    public List<User> getAllPatients(){
        return userService.getAllUsers();
    }

    //API to find users by name
    @RequestMapping(path = "users/search", method = RequestMethod.GET, params = "name")
    public List<User> getUsersByName(@RequestParam String name){
        return userService.findUsersByName(name);
    }

    @RequestMapping(path = "userroles/search", method = RequestMethod.GET, params = "username")
    public List<UserRole> getUserRolesByUsername(@RequestParam String username){
        return userService.getUserRoleByUser(username);
    }
}
