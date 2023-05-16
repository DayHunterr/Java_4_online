package ua.com.alevel.service.imp;


import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dto.UserCreatedDTO;
import ua.com.alevel.entity.User;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.UserService;
import java.util.Collection;


@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate() {
        UserCreatedDTO userDTO = new UserCreatedDTO();
        userDTO.setName("John");
        userService.create(userDTO);
        Collection<User> users = userRepository.findAll();
        UserCreatedDTO user = userDTO;
        Assertions.assertEquals("John",user.getName());
        Assertions.assertEquals(56, users.size());

    }

    @Test
    public void testFindById() {
        // Given
        User user = new User();
        user.setName("John");
        userRepository.save(user);

        // When
        User foundUser = userService.findById(user.getId());

        // Then
        Assertions.assertEquals(user.getId(), foundUser.getId());
        Assertions.assertEquals("John", foundUser.getName());
    }

    @Test
    public void testFindAll() {
        // Given
        User user1 = new User();
        user1.setName("John");
        User user2 = new User();
        user2.setName("Jane");
        userRepository.save(user1);
        userRepository.save(user2);

        // When
        Collection<User> users = userService.findAll();

        // Then
        Assertions.assertEquals(57, users.size());
    }

    @Test
    public void testUpdate() {
        // Given
        User user = new User();
        user.setName("John Doe");
        userRepository.save(user);

        User updatedUser = new User();
        updatedUser.setName("Jane Smith");

        // When
        userService.update(updatedUser, user.getId());

        // Then
        User foundUser = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNotNull(foundUser);
        Assertions.assertEquals("Jane Smith", foundUser.getName());
    }

    @Test
    public void testDelete() {
        // Given
        User user = new User();
        user.setName("John Doe");
        userRepository.save(user);

        // When
        userService.delete(user.getId());

        // Then
        User foundUser = userRepository.findById(user.getId()).orElse(null);
        Assertions.assertNull(foundUser);
    }
}
