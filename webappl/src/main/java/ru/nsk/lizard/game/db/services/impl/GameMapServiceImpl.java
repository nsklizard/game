package ru.nsk.lizard.game.db.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsk.lizard.game.db.dao.GameMapDAO;
import ru.nsk.lizard.game.db.entities.Creature;
import ru.nsk.lizard.game.db.entities.Gamemap;
import ru.nsk.lizard.game.db.services.GameMapService;

/**
 * Created by lizard on 21.05.2015.
 */
@Service("gameMapService")
public class GameMapServiceImpl implements GameMapService {

    @Autowired
    GameMapDAO gameMapDAO;

    @Override
    public void setCreatureAt(int x, int y, Creature creature) {
        Gamemap gm = gameMapDAO.getGamemap(x, y);
        gm.setCreature(creature);
        gameMapDAO.update(gm);
//        gameMapDAO.setCreatureAt(x, y, creature);
    }
}
