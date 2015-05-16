package ru.nsk.lizard.game.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    GameMapDAO gameMapDAO;

    private int x;
    private int y;
    private Creature attacker;
    private ActionProcessor actionProcessor; //нужен для добавления новых задач по итогам выполнения предыдущих

    public FightJob(int x, int y, Creature attacker, ActionProcessor actionProcessor) {
        this.x = x;
        this.y = y;
        this.attacker = attacker;
        this.actionProcessor = actionProcessor;
    }

    public void execute() {
        if (x<0 || x>= GameConstants.WORLD_SIZE || y<0 || y>=GameConstants.WORLD_SIZE){
            log.error("Invalid coords. x="+x+", y="+y);
            return;
        }
        Creature defender = gameMapDAO.getCreatureAt(x,y);
        if (defender==null){
            gameMapDAO.setCreatureAt(x,y,attacker);
        }else if (defender.equals(attacker)){
            log.info("Attacker already has this cell");
            return;
        }

        //TODO: fight

        return;
    }

    private boolean isAttackerWins(Creature attacker, Creature defender) throws CorruptedCreatureException {
        List<Creatureconfig> attackerCreatureConfigs = attacker.getCreatureConfigs();
        List<Creatureconfig> defenderCreatureConfigs = defender.getCreatureConfigs();

        if (attackerCreatureConfigs==null){
            throw new CorruptedCreatureException("attackerCreatureConfigs is null", attacker);
        }
        if (defenderCreatureConfigs==null){
            throw new CorruptedCreatureException("defenderCreatureConfigs is null", defender);
        }
        if (attackerCreatureConfigs.size()!=GameConstants.CREATURE_CONFIG_SIZE){
            throw new CorruptedCreatureException("Wrong creature config size", attacker);
        }
        if (defenderCreatureConfigs.size()!=GameConstants.CREATURE_CONFIG_SIZE){
            throw new CorruptedCreatureException("Wrong creature config size", defender);
        }

        for (int i = 0; i< GameConstants.CREATURE_CONFIG_SIZE; i++){
            attackerCreatureConfigs.get(i);
        }


        return false;
    }
}
