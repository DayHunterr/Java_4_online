package ua.com.alevel.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.alevel.dao.TransactionCheckDao;
import ua.com.alevel.dto.TransactionCheckDTO;
import ua.com.alevel.dto.TransactionCreatedDTO;
import ua.com.alevel.dto.TransactionDetailsDTO;
import ua.com.alevel.dto.TransactionInfoDTO;
import ua.com.alevel.facade.TransactionFacade;

import java.util.Collection;

@RestController
@AllArgsConstructor
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionFacade transactionFacade;


    @PostMapping
    public ResponseEntity<Boolean> create(@RequestBody TransactionCreatedDTO transactionCreatedDTO) {
        transactionFacade.create(transactionCreatedDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDetailsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(transactionFacade.findById(id));
    }

    @GetMapping
    public ResponseEntity<Collection<TransactionInfoDTO>> findAll() {
        return ResponseEntity.ok(transactionFacade.findAll());
    }
}
