package ua.com.alevel.api.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.api.AccountApiService;
import ua.com.alevel.model.AccountInfoModel;
import ua.com.alevel.model.AccountModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class AccountApiServiceImpl implements AccountApiService {

    @Value("${bank.backend.api.url}")
    private String apiUrl;

    @Override
    public Optional<AccountInfoModel> findById(Long Id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<AccountInfoModel> responseEntity = restTemplate.exchange(
                    apiUrl + "/accounts/" + Id,
                    HttpMethod.GET,
                    null,
                    AccountInfoModel.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                AccountInfoModel accountInfoModel = responseEntity.getBody();
                if (accountInfoModel != null) {
                    return Optional.of(accountInfoModel);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<AccountModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AccountModel[]> responseEntity = restTemplate.exchange(
                apiUrl + "/accounts",
                HttpMethod.GET,
                null,
                AccountModel[].class
        );
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            AccountModel[] accountModels = responseEntity.getBody();
            if (accountModels != null) {
                return List.of(accountModels);
            }
        }
        return Collections.emptyList();
    }
}
