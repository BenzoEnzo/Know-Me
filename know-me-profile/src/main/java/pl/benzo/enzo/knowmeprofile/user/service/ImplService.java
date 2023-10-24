package pl.benzo.enzo.knowmeprofile.user.service;

import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.UserFacadeApi;
import pl.benzo.enzo.knowmeprofile.user.database.User;
import pl.benzo.enzo.knowmeprofile.user.dto.SendCrypto;
import pl.benzo.enzo.knowmeprofile.user.dto.ValidateCrypto;

import java.util.List;

@Service
public class ImplService implements UserFacadeApi {
   private final SignService signService;

    public ImplService(SignService signService) {
        this.signService = signService;
    }

    public void createAccount(SendCrypto sendCrypto) {
            signService.createAccount(sendCrypto);
    }


    public ValidateCrypto validateAccount(SendCrypto sendCrypto) {
        return signService.validateCrypto(sendCrypto);
    }

    @Override
    public List<User> findAllUsers() {
        return signService.findAllUsers();
    }
}
