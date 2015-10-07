package ru.nsk.lizard.game.db.services;

import org.springframework.transaction.annotation.Transactional;
import ru.nsk.lizard.game.db.entities.Creature;

/**
 * Created by lizard on 21.05.2015.
 */
public interface GameMapService {
    @Transactional
    void setCreatureAt(int x, int y, Creature creature);
}
