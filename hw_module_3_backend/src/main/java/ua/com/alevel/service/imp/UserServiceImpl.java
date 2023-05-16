package ua.com.alevel.service.imp;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dto.UserCreatedDTO;
import ua.com.alevel.entity.User;
import ua.com.alevel.exeption.CustomException;
import ua.com.alevel.repository.UserRepository;
import ua.com.alevel.service.UserService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public void create(UserCreatedDTO entity) {
        User user = new User();
        user.setName(entity.getName());
        validateUserName(user);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Entity doesn't exist"));
    }

    @Transactional
    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public void update(User userUpdated, Long id) {
        validateUserId(id);
        validateUserName(userUpdated);
        userUpdated.setId(id);
        userRepository.save(userUpdated);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        validateUserId(id);
        userRepository.deleteById(id);
    }

    private void validateUserId(Long id) {
        if (userRepository.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Entity doesn't exist");
        }
    }

    private void validateUserName(User entity) {
        if (entity.getName() == null || entity.getName().equals("")) {
            throw new CustomException("Invalid name, try again");
        }
    }
}
