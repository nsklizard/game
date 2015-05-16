package ru.nsk.lizard.game.logic.exceptions;

import ru.nsk.lizard.game.db.entities.Creature;

/**
 * Created by dkim on 13.05.2015.
 */
public class CorruptedCreatureException extends Exception{

    private Creature creature;

    public CorruptedCreatureException(String s) {
        super(s);
    }

    public CorruptedCreatureException(Creature creature) {
        super("");
        this.creature = creature;
    }

    public CorruptedCreatureException(String error, Creature creature) {
        super(error);
        this.creature = creature;
    }

    public Creature getCreature() {
        return creature;
    }
}
