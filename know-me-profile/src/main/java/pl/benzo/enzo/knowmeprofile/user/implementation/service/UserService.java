package pl.benzo.enzo.knowmeprofile.user.implementation.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.User;
import pl.benzo.enzo.knowmeprofile.user.implementation.database.UserRepository;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.ReadUserRequest;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.ReadUserResponse;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.UpdateUserRequest;
import pl.benzo.enzo.knowmeprofile.user.implementation.dto.UpdateUserResponse;

import java.util.Optional;

@Service
@Slf4j
public class UserService extends BasicService{
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest) {
            final User user = findUserById(updateUserRequest.id());

            Optional.ofNullable(updateUserRequest.name()).ifPresent(user::setName);
            Optional.ofNullable(updateUserRequest.describe()).ifPresent(user::setDescribe);
            Optional.ofNullable(updateUserRequest.gender()).ifPresent(user::setGender);

            createOrUpdateUser(user);

            return new UpdateUserResponse(
                    user.getId(),
                    user.getName(),
                    user.getDescribe(),
                    user.getGender()
            );
    }

    public ReadUserResponse readUser(ReadUserRequest readUserRequest){
            final User user = findUserById(readUserRequest.id());

            return new ReadUserResponse(user.getName(), user.getDescribe(),user.getGender());

    }
}
