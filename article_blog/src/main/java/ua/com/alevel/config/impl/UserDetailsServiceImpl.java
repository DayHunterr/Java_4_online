package ua.com.alevel.config.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.entity.user.BaseUser;
import ua.com.alevel.persistence.repository.user.BaseUserRepository;

import java.util.HashSet;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final BaseUserRepository<BaseUser> baseUserRepository;

    public UserDetailsServiceImpl(BaseUserRepository<BaseUser> baseUserRepository) {
        this.baseUserRepository = baseUserRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BaseUser baseUser = baseUserRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(baseUser.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                baseUser.getEmail(),
                baseUser.getPassword(),
                baseUser.getEnabled(),
                true,
                true,
                true,
                grantedAuthorities);
    }
}
