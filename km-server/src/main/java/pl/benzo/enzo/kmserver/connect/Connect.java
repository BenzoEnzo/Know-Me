package pl.benzo.enzo.kmserver.connect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Pair;
import pl.benzo.enzo.kmserver.user.model.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Connect {
   private Long person1;
   private Long person2;

}
