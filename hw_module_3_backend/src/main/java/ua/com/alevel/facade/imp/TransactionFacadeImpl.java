package ua.com.alevel.facade.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.alevel.dto.TransactionCreatedDTO;
import ua.com.alevel.dto.TransactionDetailsDTO;
import ua.com.alevel.dto.TransactionInfoDTO;
import ua.com.alevel.facade.TransactionFacade;
import ua.com.alevel.service.TransactionService;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {


    private final TransactionService transactionService;

    @Override
    public void create(TransactionCreatedDTO transactionDTO) {
        transactionService.create(transactionDTO);
    }

    @Override
    public Collection<TransactionInfoDTO> findAll() {
        return transactionService.findAll()
                .stream()
                .map(TransactionInfoDTO::new)
                .toList();
    }

    @Override
    public TransactionDetailsDTO findById(Long id) {
        return new TransactionDetailsDTO(transactionService.findById(id));
    }

}
