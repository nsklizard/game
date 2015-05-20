package ru.nsk.lizard.game.db.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.nsk.lizard.game.common.GameConstants;
import ru.nsk.lizard.game.db.entities.Creature;
import ru.nsk.lizard.game.db.entities.Gamemap;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dkim on 12.05.2015.
 */
@Component
public class GameMapDAO extends GenericDaoImpl<Gamemap> {
    public GameMapDAO() {
        super(Gamemap.class);
    }

    public Creature getCreatureAt(int x, int y) {
        Gamemap gm = getGamemap(x, y);
        return (gm != null) ? gm.getCreature() : null;
    }

    public void setCreatureAt(int x, int y, Creature creature){
        Gamemap gm = getGamemap(x, y);
        gm.setCreature(creature);
        update(gm);
    }

    public Gamemap getGamemap(int x, int y) {
        return (Gamemap) em.createQuery("from Gamemap where x=" + x + " and y=" + y).getSingleResult();
    }

    public Creature[][] getMap(){
        Creature[][] ret = new Creature[GameConstants.WORLD_SIZE][GameConstants.WORLD_SIZE];
        for (Gamemap g : findAll()){
            ret[g.getX()][g.getY()] = g.getCreature();
        }
        return ret;
    }
}