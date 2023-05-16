package ua.com.alevel.dto;

import ua.com.alevel.entity.User;
import java.util.Collection;

public record UserInfoDTO(User user, Collection<AccountInfoDTO> accounts) {
}
