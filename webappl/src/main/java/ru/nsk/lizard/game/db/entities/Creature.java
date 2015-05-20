package ru.nsk.lizard.game.db.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by dkim on 12.05.2015.
 */
@Entity
public class Creature {
    private long creatureId;
    private String name;
    private Integer isSys;
    private User user;
    private List<Creatureconfig> creatureConfigs;

    @Id
    @Column(name = "creatureId")
    public long getCreatureId() {
        return creatureId;
    }

    public void setCreatureId(long creatureId) {
        this.creatureId = creatureId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "isSys")
    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Creature creature = (Creature) o;

        if (creatureId != creature.creatureId) return false;
        if (isSys != null ? !isSys.equals(creature.isSys) : creature.isSys != null) return false;
        if (name != null ? !name.equals(creature.name) : creature.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (creatureId ^ (creatureId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (isSys != null ? isSys.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creature", cascade = CascadeType.ALL)
    @OrderBy("stepNumber")
    public List<Creatureconfig> getCreatureConfigs() {
        return creatureConfigs;
    }

    public void setCreatureConfigs(List<Creatureconfig> creatureConfigs) {
        this.creatureConfigs = creatureConfigs;
    }
}
