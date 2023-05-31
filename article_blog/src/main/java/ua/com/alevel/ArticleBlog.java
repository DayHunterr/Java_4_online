package ua.com.alevel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.entity.user.BaseUser;
import ua.com.alevel.persistence.entity.user.User;
import ua.com.alevel.persistence.repository.user.AdminRepository;
import ua.com.alevel.persistence.repository.user.UserRepository;

import java.io.IOException;
import java.util.Optional;


@SpringBootApplication
public class ArticleBlog {

    @Value("${initUsers}")
    private boolean initUsers;
    private final AdminRepository adminRepository;
    private final UserRepository personalRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ArticleBlog(
            AdminRepository adminRepository,
            UserRepository personalRepository,
            BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.personalRepository = personalRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public static void main(String[] args) {
        SpringApplication.run(ArticleBlog.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() throws IOException {
        String email = "admin@gmail.com";
        String password = "12345678";
        Optional<BaseUser> optionalAdmin = adminRepository.findByEmail(email);
        if (optionalAdmin.isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail(email);
            admin.setPassword(bCryptPasswordEncoder.encode(password));
            adminRepository.save(admin);
        }
        if (initUsers) {
            System.out.println("start init");
            for (int i = 0; i < 10; i++) {
                User user = new User();
                user.setPassword(bCryptPasswordEncoder.encode(password));
                user.setEmail("user" + i + "@gmail.com");
                personalRepository.save(user);
            }
            System.out.println("finish init");
        }
    }
}