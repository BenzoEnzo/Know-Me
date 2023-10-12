package pl.benzo.enzo.kmserver.key;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class KeyService {
    private final KeyRepository keyRepository;

    public List<Key> getAll(){
        return keyRepository.findAll();
    }
    public boolean saveKey(String name){
        final Key key = keyRepository.findKeyByName(name);
        if(Objects.isNull(key)){
            keyRepository.save(new Key(name));
            return true;
        } else return false;
    }
}
