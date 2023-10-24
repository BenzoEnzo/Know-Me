package pl.benzo.enzo.knowmeprofile.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.authentication.ImplUserDetails;
import pl.benzo.enzo.knowmeprofile.user.database.User;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SecurityService {
    private final BasicService basicService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userDetail = Optional.ofNullable(basicService.findUser(username));

        return userDetail.map(ImplUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
