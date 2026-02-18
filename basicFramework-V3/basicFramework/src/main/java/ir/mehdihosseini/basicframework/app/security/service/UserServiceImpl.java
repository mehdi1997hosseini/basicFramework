//package ir.mehdihosseini.basicframework.app.security.service;
//
//import ir.fam.springcore.security.entity.UserEntity;
//import ir.fam.springcore.security.entity.dto.UserDto;
//import ir.fam.springcore.security.repository.UserRepository;
//import org.jspecify.annotations.NonNull;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//
//    private final JwtServiceImpl jwtService;
//
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
//
//    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtServiceImpl jwtService) {
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.jwtService = jwtService;
//    }
//
//    private void save(@NonNull UserEntity user) {
//        user.setPassword(encoder.encode(user.getPassword()));
//        userRepository.save(user);
//    }
//
//    @Override
//    public UserDto registry(UserDto user) {
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUsername(user.getUsername());
//        userEntity.setPassword(user.getPassword());
//        save(userEntity);
//        return user;
//    }
//
//    @Override
//    public String verify(UserDto user) {
//        Authentication authentication =
//                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
//        if (authentication.isAuthenticated()) {
//            return jwtService.generateToken(user.getUsername());
////            return "success";
//        }
//        return "failed";
//    }
//
//    @Override
//    public List<UserEntity> showAllUsers() {
//        return userRepository.findAll();
//    }
//
//}
