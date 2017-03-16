package kld.tumanov.aggregator.repository.mongo;

import kld.tumanov.aggregator.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * Created by Admin on 12.03.2017.
 */
public interface CrudNewsRepository extends MongoRepository<News, Long> {

    List<News> findByIdGreaterThan(Long id);

    List<News> findByIdLessThan(Long id);

    List<News> findByUrlOrderByIdDesc(String url);

}
