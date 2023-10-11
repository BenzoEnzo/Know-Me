package pl.benzo.enzo.kmserver.connect;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.util.Pair;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Connect {
   private Pair<Long,Long> conversation;
}
