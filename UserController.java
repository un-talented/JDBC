package com.example.userrestapiapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.example.userrestapiapplication.UserRepository.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserRepository userRepository;
    @PostMapping("/addUser")
    public User addUser(@RequestBody User user)
    {
        User u = new User();
        u.setUserId(user.getUserId());
        u.setUserName(user.getUserName());
        u.setUserEmail(user.getUserEmail());
        u.setAge(user.getAge());
        u.setGender(user.getGender());
        u.setNationality(user.getNationality());
        userRepository.save(u);
        return u;
    }
    @GetMapping("/viewAll")
    public @ResponseBody Iterable<User> getAllUsers()
    {
        return userRepository.findAll();
    }
    @GetMapping("/viewAll/{id}")
    public Optional<User> getUser(@PathVariable Integer id)
    {
        System.out.println("Goutham"+userRepository.findById(id));
        return userRepository.findById(id);
    }
    @PutMapping("/edit/{id}")
    public String update(@RequestBody User updateUser,@PathVariable Integer id)
    {
        return userRepository.findById(id)
                .map(User ->{
                    User.setUserName(updateUser.getUserName());
                    User.setUserEmail(updateUser.getUserEmail());
                    User.setAge(updateUser.getAge());
                    User.setGender(updateUser.getGender());
                    User.setNationality(updateUser.getNationality());
                    userRepository.save(User);
                    return "User details updated successfully";
                }).orElseGet(()->{
                    return "No such user exist!";
                });
    }
}
