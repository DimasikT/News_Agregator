package kld.tumanov.aggregator.repository.mongo;

import kld.tumanov.aggregator.exceptions.SequenceException;
import kld.tumanov.aggregator.model.Sequence;
import kld.tumanov.aggregator.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * Класс реализующий целочисленный AUTO_INCREMENT.
 * В коллекции sequence храняться пары: "имя коллекции" - "последний ID"
 */
@Repository
public class SequenceRepositoryImpl implements SequenceRepository {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Long getNextSequenceId(String key) {
        //получаем объект sequence по наименованию коллекции
        Query query = new Query(Criteria.where("id").is(key));

        //увеличиваем поле sequence на единицу
        Update update = new Update();
        update.inc("sequence", 1);

        //указываем опцию, что нужно возвращать измененный объект
        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        //магия
        Sequence sequence = mongoOperations.findAndModify(query, update, options, Sequence.class);

        if(sequence == null) throw new SequenceException("Unable to get sequence for key: " + key);

        return sequence.getSequence();
    }
}
