package ua.com.alevel.api;

import ua.com.alevel.model.TransactionCheckModel;
import ua.com.alevel.model.TransactionDetailsModel;
import ua.com.alevel.model.TransactionModel;
import ua.com.alevel.model.TransactionPostModel;

import java.util.Collection;
import java.util.Optional;

public interface TransactionApiService {

    Boolean create(TransactionPostModel transactionPostModel);
    Optional<TransactionDetailsModel> findById(Long Id);
    Collection<TransactionModel> findAll();
    Collection<TransactionCheckModel> getCheck(Long id);

}
