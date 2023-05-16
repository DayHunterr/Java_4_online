package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dto.AccountInfoDTO;
import ua.com.alevel.dto.AccountTransactionInfoDTO;
import ua.com.alevel.entity.Account;
import ua.com.alevel.facade.AccountFacade;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountFacade accountFacade;

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> update (@RequestBody Account entity, @PathVariable Long id){
        accountFacade.update(entity,id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete (@PathVariable Long id){
        accountFacade.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountTransactionInfoDTO> findByID(@PathVariable Long id){
        return ResponseEntity.ok(accountFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<AccountInfoDTO>> findAll(){
        return ResponseEntity.ok(accountFacade.findAll());
    }
}
