package ua.com.alevel.api.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.api.TransactionApiService;
import ua.com.alevel.model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class TransactionApiServiceImpl implements TransactionApiService {

    @Value("${bank.backend.api.url}")
    private String apiUrl;

    @Override
    public Boolean create(TransactionPostModel transactionPostModel) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                    apiUrl + "/transactions",
                    HttpMethod.POST,
                    ResponseEntity.ok(transactionPostModel),
                    Boolean.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<TransactionDetailsModel> findById(Long Id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<TransactionDetailsModel> responseEntity = restTemplate.exchange(
                    apiUrl + "/transactions/" + Id,
                    HttpMethod.GET,
                    null,
                    TransactionDetailsModel.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                TransactionDetailsModel transactionDetailsModel = responseEntity.getBody();
                if (transactionDetailsModel != null) {
                    return Optional.of(transactionDetailsModel);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<TransactionModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionModel[]> responseEntity = restTemplate.exchange(
                apiUrl + "/transactions",
                HttpMethod.GET,
                null,
                TransactionModel[].class
        );
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TransactionModel[] transactionModels = responseEntity.getBody();
            if (transactionModels != null) {
                return List.of(transactionModels);
            }
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<TransactionCheckModel> getCheck(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<TransactionCheckModel[]> responseEntity = restTemplate.exchange(
                apiUrl + "/transactions",
                HttpMethod.GET,
                null,
                TransactionCheckModel[].class
        );
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            TransactionCheckModel[] transactionCheckModels = responseEntity.getBody();
            if (transactionCheckModels != null) {
                return List.of(transactionCheckModels);
            }
        }
        return Collections.emptyList();
    }
}