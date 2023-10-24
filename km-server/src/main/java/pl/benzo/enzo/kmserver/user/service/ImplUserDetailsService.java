package pl.benzo.enzo.kmserver.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.model.ImplUserDetails;
import pl.benzo.enzo.kmserver.user.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImplUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    public UserDetails loadUserByCrypto(String crypto) throws UsernameNotFoundException {

        Optional<User> userDetail = Optional.ofNullable(userRepository.findUserByCrypto(crypto));

        return userDetail.map(ImplUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + crypto));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = Optional.ofNullable(userRepository.findUserByCrypto(username));

        return userDetail.map(ImplUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}