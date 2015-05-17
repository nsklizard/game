package ru.nsk.lizard.game.logic;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import ru.nsk.lizard.game.common.GameConstants;
import ru.nsk.lizard.game.db.dao.GameMapDAO;
import ru.nsk.lizard.game.db.entities.Creature;
import ru.nsk.lizard.game.db.entities.Creatureconfig;
import ru.nsk.lizard.game.db.entities.Skill;
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

        try {
            if (isAttackerWins(attacker, defender)){
                log.debug("attacker won");
                gameMapDAO.setCreatureAt(x,y,attacker);
                actionProcessor.queue.add(new FightJob(x,y-1,attacker, actionProcessor));
                actionProcessor.queue.add(new FightJob(x,y+1,attacker, actionProcessor));
                actionProcessor.queue.add(new FightJob(x-1,y,attacker, actionProcessor));
                actionProcessor.queue.add(new FightJob(x+1,y,attacker, actionProcessor));
            }
            else{
                gameMapDAO.setCreatureAt(x,y,defender);
                actionProcessor.queue.add(new FightJob(x,y-1,defender, actionProcessor));
                actionProcessor.queue.add(new FightJob(x,y+1,defender, actionProcessor));
                actionProcessor.queue.add(new FightJob(x-1,y,defender, actionProcessor));
                actionProcessor.queue.add(new FightJob(x+1,y,defender, actionProcessor));
            }
        } catch (CorruptedCreatureException e) {
            log.error(e);
        }

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

        log.debug("FIGHT: "+attacker.getName()+" vs "+defender.getName());
        int attackerWins= 0;
        int defenderWins = 0;


        for (int i = 0; i< GameConstants.CREATURE_CONFIG_SIZE; i++){
            Creatureconfig attackerCC = attackerCreatureConfigs.get(i);
            Creatureconfig defenderCC = defenderCreatureConfigs.get(i);
            if (attackerCC == null){
                throw new CorruptedCreatureException("Creature config is null", attacker);
            }
            if (defenderCC == null){
                throw new CorruptedCreatureException("Creature config is null", defender);
            }

            if (isFirstWin(attackerCC, defenderCC)){
                attackerWins++;
            }else{
                defenderWins++;
            }
        }


        return attackerWins>defenderWins;
    }

    private boolean isFirstWin(Creatureconfig first, Creatureconfig second) throws CorruptedCreatureException {
        if (first==null || first.getSkill() == null){
            throw new CorruptedCreatureException("CreatureConfig or Skill is null", first.getCreature());
        }
        if (second==null || second.getSkill() == null){
            throw new CorruptedCreatureException("CreatureConfig or Skill is null", second.getCreature());
        }
        if (first.getSkill().getSkillId()!=GameConstants.STONE
                &&first.getSkill().getSkillId()!=GameConstants.SCISSORS
                &&first.getSkill().getSkillId()!=GameConstants.PAPER){
            throw new CorruptedCreatureException("Wrong skillId = "+first.getSkill().getSkillId(), first.getCreature());
        }
        if (second.getSkill().getSkillId()!=GameConstants.STONE
                &&second.getSkill().getSkillId()!=GameConstants.SCISSORS
                &&second.getSkill().getSkillId()!=GameConstants.PAPER){
            throw new CorruptedCreatureException("Wrong skillId = "+second.getSkill().getSkillId(), second.getCreature());
        }

        switch((int) first.getSkill().getSkillId()){
            case GameConstants.STONE:
                switch ((int)second.getSkill().getSkillId()){
                    case GameConstants.STONE:
                        return compareEquals(first, second);
                    case GameConstants.SCISSORS:
                        return compareFirstWin(first, second);
                    case GameConstants.PAPER:
                        return compareFirstWin(second, first);
                }
                
            case GameConstants.SCISSORS:
                switch ((int)second.getSkill().getSkillId()){
                    case GameConstants.STONE:
                        return compareFirstWin(second, first);
                    case GameConstants.SCISSORS:
                        return compareEquals(first, second);
                    case GameConstants.PAPER:
                        return compareFirstWin(first, second);
                }
                
            case GameConstants.PAPER:
                switch ((int)second.getSkill().getSkillId()){
                    case GameConstants.STONE:
                        return compareFirstWin(first, second);
                    case GameConstants.SCISSORS:
                        return compareFirstWin(second, first);
                    case GameConstants.PAPER:
                        return compareEquals(first, second);
                }
        }
        return false;
    }

    /**
     * First wins by default logic. This function examines if this to change
     * @param first
     * @param second
     * @return
     */
    private boolean compareFirstWin(Creatureconfig first, Creatureconfig second) {
        return first.getPower().longValue() + GameConstants.POWER_ADVANTAGE <= second.getPower().longValue();
    }

    private boolean compareEquals(Creatureconfig first, Creatureconfig second) {
        return first.getPower().longValue()>=second.getPower().longValue();
    }
}
