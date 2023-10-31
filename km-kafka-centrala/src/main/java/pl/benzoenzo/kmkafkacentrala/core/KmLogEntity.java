package pl.benzoenzo.kmkafkacentrala.core;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KmLogEntity {
    @Id
    private Long id;
    private String message;
    private LocalDateTime timestamp;
}
