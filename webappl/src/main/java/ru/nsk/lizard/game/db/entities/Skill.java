package ru.nsk.lizard.game.db.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by dkim on 12.05.2015.
 */
@Entity
public class Skill {
    private long skillId;
    private String name;

    @Id
    @Column(name = "skillId")
    public long getSkillId() {
        return skillId;
    }

    public void setSkillId(long skillId) {
        this.skillId = skillId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Skill skill = (Skill) o;

        if (skillId != skill.skillId) return false;
        if (name != null ? !name.equals(skill.name) : skill.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (skillId ^ (skillId >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
