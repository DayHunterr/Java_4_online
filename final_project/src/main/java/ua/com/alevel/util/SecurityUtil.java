package ua.com.alevel.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ua.com.alevel.persistance.type.Role;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class SecurityUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUsername() {
        Authentication authentication = SecurityUtil.getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getUsername();
    }

    public static Role getRole() {
        Authentication authentication = SecurityUtil.getAuthentication();
        User user = (User) authentication.getPrincipal();
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        GrantedAuthority authority = authorities.stream().findFirst().get();
        return Role.valueOf(authority.getAuthority());
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityUtil.getAuthentication();
        AtomicBoolean result = new AtomicBoolean(false);
        authentication.getAuthorities().forEach(auth -> result.set(auth.getAuthority().equalsIgnoreCase(role)));
        return result.get();
    }
}
