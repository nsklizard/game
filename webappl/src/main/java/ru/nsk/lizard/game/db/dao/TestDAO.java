package ru.nsk.lizard.game.db.dao;

import ru.nsk.lizard.game.db.entities.Test;
import org.springframework.stereotype.Component;


/**
 * Created by dkim on 09.11.2014.
 */
@Component
public class TestDAO extends GenericDaoImpl<Test> {
    public TestDAO() {
        super(Test.class);
    }
}
