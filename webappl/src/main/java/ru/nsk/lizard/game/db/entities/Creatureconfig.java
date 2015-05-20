package ru.nsk.lizard.game.db.entities;

import javax.persistence.*;

/**
 * Created by dkim on 12.05.2015.
 */
@Entity
public class Creatureconfig {
    private long creatureConfigId;
    private Long power;
    private Integer stepNumber;
    private Creature creature;
    private Skill skill;

    @Id
    @Column(name = "creatureConfigId")
    public long getCreatureConfigId() {
        return creatureConfigId;
    }

    public void setCreatureConfigId(long creatureConfigId) {
        this.creatureConfigId = creatureConfigId;
    }

    @Basic
    @Column(name = "power")
    public Long getPower() {
        return power;
    }

    public void setPower(Long power) {
        this.power = power;
    }

    @Basic
    @Column(name = "stepNumber")
    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Creatureconfig that = (Creatureconfig) o;

        if (creatureConfigId != that.creatureConfigId) return false;
        if (power != null ? !power.equals(that.power) : that.power != null) return false;
        if (stepNumber != null ? !stepNumber.equals(that.stepNumber) : that.stepNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (creatureConfigId ^ (creatureConfigId >>> 32));
        result = 31 * result + (power != null ? power.hashCode() : 0);
        result = 31 * result + (stepNumber != null ? stepNumber.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "creatureId", referencedColumnName = "creatureId")
    public Creature getCreature() {
        return creature;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    @OneToOne
    @JoinColumn(name = "skillId", referencedColumnName = "skillId")
    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}
