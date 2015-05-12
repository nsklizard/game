package ru.nsk.lizard.game.db.entities;

import javax.persistence.*;

/**
 * Created by dkim on 12.05.2015.
 */
@Entity
public class Gamemap {
    private long gameMapId;
    private Integer x;
    private Integer y;
    private Creature creature;

    @Id
    @Column(name = "gameMapId")
    public long getGameMapId() {
        return gameMapId;
    }

    public void setGameMapId(long gameMapId) {
        this.gameMapId = gameMapId;
    }

    @Basic
    @Column(name = "x")
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y")
    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Gamemap gamemap = (Gamemap) o;

        if (gameMapId != gamemap.gameMapId) return false;
        if (x != null ? !x.equals(gamemap.x) : gamemap.x != null) return false;
        if (y != null ? !y.equals(gamemap.y) : gamemap.y != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (gameMapId ^ (gameMapId >>> 32));
        result = 31 * result + (x != null ? x.hashCode() : 0);
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "creatureId", referencedColumnName = "creatureId")
    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }
}
