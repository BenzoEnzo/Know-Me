package pl.benzoenzo.kmkafkacentrala.core;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface KmLogRepository extends MongoRepository<KmLogEntity,String> {
}
