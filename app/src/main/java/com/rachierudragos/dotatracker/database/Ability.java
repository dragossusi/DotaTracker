package com.rachierudragos.dotatracker.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Dragos on 08-Nov-17.
 */
@Entity
public class Ability {
    @Id
    Long id;

    String name;

    @Generated(hash = 4499119)
    public Ability(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Generated(hash = 323309464)
    public Ability() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
