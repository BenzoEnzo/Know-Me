package pl.benzo.enzo.kmkafkacentrala.core;



import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Collation("KmLogsEntities")
public class KmLogEntity {
    @Id
    private Long id;
    private String message;
    private LocalDateTime timestamp;
}
