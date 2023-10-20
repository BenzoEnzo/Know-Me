package pl.benzo.enzo.kmserver.token;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.service.UserRepository;

import java.util.ArrayList;

@Component
public class CryptoAuthenticationProvider implements AuthenticationProvider {
    private final UserRepository userRepository;

    public CryptoAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String crypto = (String) authentication.getPrincipal();
        User user = userRepository.findUserByCrypto(crypto);
        if (user == null) {
            throw new BadCredentialsException("Invalid crypto value");
        }
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

