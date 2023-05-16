package ua.com.alevel.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.UserAccountsNumberDTO;
import ua.com.alevel.dto.UserCreatedDTO;
import ua.com.alevel.dto.UserInfoDTO;
import ua.com.alevel.entity.Account;
import ua.com.alevel.entity.User;
import ua.com.alevel.facade.AccountFacade;
import ua.com.alevel.facade.UserFacade;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserFacade userFacade;
    private final AccountFacade accountFacade;

    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody UserCreatedDTO entity) {
        userFacade.create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Boolean> createAccount(@RequestBody Account entity, @PathVariable Long id) {
        accountFacade.create(entity, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDTO> findByID(@PathVariable Long id) {
        return ResponseEntity.ok(userFacade.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update(@RequestBody User entity, @PathVariable Long id) {
        userFacade.update(entity, id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        userFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping
    public ResponseEntity<Collection<UserAccountsNumberDTO>> findAllUsersWithNumberOfAccount() {
        return ResponseEntity.ok(userFacade.findAllUsersWithNumberOfAccount());
    }
}
