//package ir.mehdihosseini.basicframework.app.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    private final UserDetailsService userDetailsService;
//    private final JwtFilterConfig jwtFilterConfig;
//
//    public SecurityConfig(UserDetailsService userDetailsService, JwtFilterConfig jwtFilterConfig) {
//        this.userDetailsService = userDetailsService;
//        this.jwtFilterConfig = jwtFilterConfig;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity security) {
//        return security.csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(req ->
//                        req.requestMatchers("/user/registry", "/user/login" , "/logout").permitAll()
//                                .anyRequest().authenticated())
////                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                // in this section : based on any request show login page because generate new session for any request
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(jwtFilterConfig , UsernamePasswordAuthenticationFilter.class)
//                .build();
//
//    }
////
////    private void csrfCustom(HttpSecurity security) {
////        Customizer<CsrfConfigurer<HttpSecurity>> csrfCustom = new Customizer<CsrfConfigurer<HttpSecurity>>() {
////            @Override
////            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
////                httpSecurityCsrfConfigurer.disable();
////            }
////        };
////
////        security.csrf(csrfCustom);
////    }
//
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails userDetails1 = User
////                .withDefaultPasswordEncoder()
////                .username("mehdi")
////                .password("mehdi")
////                .roles("USER")
////                .build();
////        UserDetails userDetails2 = User
////                .withDefaultPasswordEncoder()
////                .username("hosseini")
////                .password("hosseini")
////                .roles("ADMIN")
////                .build();
////        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
////    }
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//
//        return provider;
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) {
//        return configuration.getAuthenticationManager();
//    }
//
//}
