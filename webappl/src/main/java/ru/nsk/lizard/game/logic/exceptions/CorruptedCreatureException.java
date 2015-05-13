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
        this.creature = creature;

    }
}
