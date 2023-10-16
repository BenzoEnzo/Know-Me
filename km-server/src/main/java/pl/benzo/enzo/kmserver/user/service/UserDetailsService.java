package pl.benzo.enzo.kmserver.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.benzo.enzo.kmserver.user.model.ImplUserDetails;
import pl.benzo.enzo.kmserver.user.model.User;
import pl.benzo.enzo.kmserver.user.model.dto.UploadImage;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    public void uploadImageOnServ(MultipartFile file) throws IOException {
        String uploadDir = "/home/devk/Pulpit/IdeaProjects/know-me/km-server/src/main/resources/static";
        filename = file.getOriginalFilename();
        uploadService.storeFile(file, filename, uploadDir);
    }

    public Resource loadFile(UploadImage uploadImage) throws FileNotFoundException {
        this.filename = uploadImage.filename();
        return uploadService.loadFile(filename,uploadImage.uploadDirectory());
    }

}
