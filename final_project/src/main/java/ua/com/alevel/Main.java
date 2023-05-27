package ua.com.alevel;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistance.entity.BaseUser;
import ua.com.alevel.persistance.entity.users.Admin;
import ua.com.alevel.persistance.entity.users.User;
import ua.com.alevel.persistance.repository.user.AdminRepository;
import ua.com.alevel.persistance.repository.user.UserRepository;

import java.io.IOException;
import java.util.Optional;


@SpringBootApplication
public class Main {

    private final AdminRepository adminRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;

    public Main(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){

        String email = "admin@gmail.com";
        String password = "12345678";
        String userEmail = "user@gmail.com";
        String userPassword = "1234567";

        Optional<BaseUser> optionalBaseUser = userRepository.findByEmail(userEmail);
        if(optionalBaseUser.isEmpty()){
            User user = new User();
            user.setEmail(userEmail);
            user.setPassword(bCryptPasswordEncoder.encode(userPassword));
            userRepository.save(user);
        }

        Optional<BaseUser> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(bCryptPasswordEncoder.encode(password));
            adminRepository.save(admin);
        }
    }
}