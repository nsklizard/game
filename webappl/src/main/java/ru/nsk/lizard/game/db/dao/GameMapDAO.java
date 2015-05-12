package ru.nsk.lizard.game.db.dao;

import org.springframework.stereotype.Component;
import ru.nsk.lizard.game.db.entities.Creature;
import ru.nsk.lizard.game.db.entities.Gamemap;

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

    private Gamemap getGamemap(int x, int y) {
        return (Gamemap) em.createQuery("from Gamemap where x=" + x + " and y=" + y).getSingleResult();
    }
}