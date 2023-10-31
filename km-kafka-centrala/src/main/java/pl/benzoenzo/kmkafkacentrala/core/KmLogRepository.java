package pl.benzoenzo.kmkafkacentrala.core;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface KmLogRepository extends MongoRepository<KmLogEntity,String> {
}
