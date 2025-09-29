package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.User;
import com.example.demo.service.IUserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"https://gmao-app.netlify.app", "http://localhost:4200"})
public class userController {
@Autowired
	
	IUserService userService;



	@PostMapping("/addUser")
public ResponseEntity<?> addUser(@RequestBody User user) {
    try {
        User savedUser = userService.addUser(user);
        return ResponseEntity.ok(savedUser);
    } catch (Exception e) {
        e.printStackTrace(); // Affiche l’erreur exacte dans les logs Render
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
    }
}


@PostMapping("/addTestEmail")
public String addTestEmail (@RequestBody User user) {
	return userService.addUserTestEmail(user);
}


@PutMapping("/updateUser/{id}")
public User updateUser(@RequestBody User user,@PathVariable("id") Long idUser) {
	return userService.updateUser(user, idUser);
}

@DeleteMapping("/deleteUser/{id}")
public String deleteUser(@PathVariable("id") Long idUser) {
	String msg="";
	userService.deleteUser(idUser);
	return msg;
}

@GetMapping("/findAllUser")
public List<User> findAll(){
	return userService.findAll();
}

@PostMapping("/addTestPwd")
public String addTestPwd(@RequestBody User user) {
	return userService.addUserTestPwd(user);
}
}
