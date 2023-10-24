package pl.benzo.enzo.kmserver.web;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.benzo.enzo.kmserver.resource.ProfileRestTemplate;
import pl.benzo.enzo.kmserver.web.dto.SendCrypto;


@RestController
@RequestMapping("/api/public/person")
@RequiredArgsConstructor
public class PersonController {
    private final ProfileRestTemplate profileRestTemplate;
    @GetMapping(value = "/join")
    @ResponseBody
    public ResponseEntity<?> join(){
        SendCrypto sendCrypto = profileRestTemplate.signUp();
        return ResponseEntity.ok()
                .body(sendCrypto);
    }
}
