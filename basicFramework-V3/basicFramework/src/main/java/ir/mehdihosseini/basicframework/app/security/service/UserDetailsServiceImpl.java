//package ir.mehdihosseini.basicframework.app.security.service;
//
//import ir.fam.springcore.security.entity.UserEntity;
//import ir.fam.springcore.security.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserRepository userRepository;
//
//    public UserDetailsServiceImpl(UserRepository repository) {
//        this.userRepository = repository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity byUsername = userRepository.findByUsername(username);
//        if (byUsername == null) {
//            System.out.println("user not found");
//            throw new RuntimeException("user not found");
//        }
//        return byUsername;
//    }
//}
