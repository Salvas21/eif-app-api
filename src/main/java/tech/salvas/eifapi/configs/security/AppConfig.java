package tech.salvas.eifapi.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tech.salvas.eifapi.models.Admin;
import tech.salvas.eifapi.models.Student;
import tech.salvas.eifapi.repositories.AdminRepository;
import tech.salvas.eifapi.repositories.StudentRepository;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class AppConfig {

    private final StudentRepository studentRepository;
    private final AdminRepository adminRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // here subject (username) can be either email or CP / CE and of admin or student
            Optional<Student> studentOpt = studentRepository.findByEmailOrCp(username, username);

            if (studentOpt.isPresent()) {
                Student student = studentOpt.get();
                AppUserDetails details = new AppUserDetails();
                details.setRole("student");
                details.setUsername(student.getCp());
                details.setPassword(student.getPassword());
                return details;
            } else {
                Optional<Admin> adminOpt = adminRepository.findByEmailOrCe(username, username);

                if (adminOpt.isPresent()) {
                    Admin admin = adminOpt.get();
                    AppUserDetails details = new AppUserDetails();
                    details.setRole("admin");
                    details.setUsername(admin.getEmail());
                    details.setPassword(admin.getPassword());
                    return details;
                } else {
                    return null;
                }
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
