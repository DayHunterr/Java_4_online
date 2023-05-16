package ua.com.alevel.facade;

import ua.com.alevel.dto.TransactionCreatedDTO;
import ua.com.alevel.dto.TransactionDetailsDTO;
import ua.com.alevel.dto.TransactionInfoDTO;

import java.util.Collection;

public interface TransactionFacade {

    void create(TransactionCreatedDTO transactionDTO);

    Collection<TransactionInfoDTO> findAll();

    TransactionDetailsDTO findById(Long id);



}
