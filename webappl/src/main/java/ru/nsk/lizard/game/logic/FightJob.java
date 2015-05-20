package ru.nsk.lizard.game.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.nsk.lizard.game.common.GameConstants;
import ru.nsk.lizard.game.db.dao.GameMapDAO;
import ru.nsk.lizard.game.db.entities.Creature;
import ru.nsk.lizard.game.db.entities.Creatureconfig;
import ru.nsk.lizard.game.logic.exceptions.CorruptedCreatureException;

import java.util.List;

/**
 * Created by dkim on 12.05.2015.
 */
public class FightJob {
    private static Logger log = Logger.getLogger(FightJob.class);

    private GameMapDAO gameMapDAO;

    private int x;
    private int y;
    private Creature attackerCreature;

    public FightJob(int x, int y, Creature attacker, GameMapDAO gameMapDAO) {
        this.x = x;
        this.y = y;
        this.attackerCreature = attacker;
//        this.actionProcessor = actionProcessor;
//        this.gameMapDAO = gameMapDAO;
    }

    public static Logger getLog() {
        return log;
    }

    public static void setLog(Logger log) {
        FightJob.log = log;
    }

    public GameMapDAO getGameMapDAO() {
        return gameMapDAO;
    }

    public void setGameMapDAO(GameMapDAO gameMapDAO) {
        this.gameMapDAO = gameMapDAO;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Creature getAttackerCreature() {
        return attackerCreature;
    }

    public void setAttackerCreature(Creature attackerCreature) {
        this.attackerCreature = attackerCreature;
    }
}
