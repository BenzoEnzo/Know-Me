package pl.benzo.enzo.kmserver.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.kmserver.user.model.ImplUserDetails;
import pl.benzo.enzo.kmserver.user.model.User;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final UploadService uploadService = new UploadService();
    private final UserRepository userRepository;
    private String filename;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = Optional.ofNullable(userRepository.findUserByCrypto(username));

        return userDetail.map(ImplUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }

}
