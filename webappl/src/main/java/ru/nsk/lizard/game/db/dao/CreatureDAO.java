package ru.nsk.lizard.game.db.dao;

import org.springframework.stereotype.Component;
import ru.nsk.lizard.game.db.entities.Creature;

/**
 * Created by dkim on 12.05.2015.
 */
@Component
public class CreatureDAO extends GenericDaoImpl<Creature> {
    public CreatureDAO() {
        super(Creature.class);
    }
}
