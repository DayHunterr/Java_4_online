package ua.com.alevel.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.concurrent.atomic.AtomicBoolean;

public class SecurityUtil {

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static String getUsername() {
        Authentication authentication = SecurityUtil.getAuthentication();
        User principal = (User) authentication.getPrincipal();
        return principal.getUsername();
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityUtil.getAuthentication();
        AtomicBoolean result = new AtomicBoolean(false);
        authentication.getAuthorities().forEach(authority -> result.set(authority.getAuthority().equalsIgnoreCase(role)));
        return result.get();
    }
}
