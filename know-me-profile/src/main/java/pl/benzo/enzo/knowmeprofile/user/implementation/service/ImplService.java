package pl.benzo.enzo.knowmeprofile.user.implementation.service;

import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.implementation.UserFacadeApi;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.*;

import java.util.List;

@Service
public class ImplService implements UserFacadeApi {
   private final SignService signService;
   private final UserService userService;

    public ImplService(SignService signService, UserService userService) {
        this.signService = signService;
        this.userService = userService;
    }


    @Override
    public SendCrypto generateCrypto() {
        return signService.generateCrypto();
    }

    @Override
    public ReadUserResponse readUser(ReadUserRequest readUserRequest) {
        return userService.readUser(readUserRequest);
    }
    @Override
    public ValidateCrypto validateAccount(SendCrypto sendCrypto) {
        return signService.validateCrypto(sendCrypto);
    }

    @Override
    public List<User> findAllUsers() {
        return signService.findAllUsers();
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest);
    }
}
