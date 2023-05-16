package ua.com.alevel.api.imp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.alevel.api.UserApiService;
import ua.com.alevel.model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserApiServiceImpl implements UserApiService {

    @Value("${bank.backend.api.url}")
    private String apiUrl;

    @Override
    public Boolean createAccount(AccountPostModel account, Long id) {
        RestTemplate restTemplate = new RestTemplate();
        //try {
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                    apiUrl + "/users/" + id,
                    HttpMethod.POST,
                    ResponseEntity.ok(account),
                    Boolean.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
            return false;
        } //catch (Exception e) {
            //return false;


    @Override
    public Boolean create(UserModel user) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<Boolean> responseEntity = restTemplate.exchange(
                    apiUrl + "/users",
                    HttpMethod.POST,
                    ResponseEntity.ok(user),
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
    public Optional<UserInfoModel> findById(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<UserInfoModel> responseEntity = restTemplate.exchange(
                    apiUrl + "/users/" + id,
                    HttpMethod.GET,
                    null,
                    UserInfoModel.class
            );
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                UserInfoModel userInfoModel = responseEntity.getBody();
                if (userInfoModel != null) {
                    return Optional.of(userInfoModel);
                }
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Collection<UserAccountsNumberModel> findAll() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserAccountsNumberModel[]> responseEntity = restTemplate.exchange(
                apiUrl + "/users",
                HttpMethod.GET,
                null,
                UserAccountsNumberModel[].class
        );
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            UserAccountsNumberModel[] userModels = responseEntity.getBody();
            if (userModels != null) {
                return List.of(userModels);
            }
        }
        return Collections.emptyList();
    }
}
