package ru.nsk.lizard.game.db;

import hello.Test;
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
