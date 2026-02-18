//package ir.mehdihosseini.basicframework.app.security.controller;
//
//import ir.fam.springcore.security.entity.dto.UserDto;
//import ir.fam.springcore.security.service.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/user/")
//public class UserController {
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("registry")
//    public ResponseEntity<?> registry(@RequestBody UserDto user) {
//        return new ResponseEntity<>(userService.registry(user), HttpStatus.CREATED);
//    }
//
//    @PostMapping("login")
//    public String login(@RequestBody UserDto user) {
//        return userService.verify(user);
//    }
//
//    @GetMapping("show-all")
//    public ResponseEntity<?> showAll() {
//        return new ResponseEntity<>(userService.showAllUsers(), HttpStatus.OK);
//    }
//
//}
