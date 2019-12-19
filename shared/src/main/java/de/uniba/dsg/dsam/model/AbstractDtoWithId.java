package de.uniba.dsg.dsam.model;

import java.io.Serializable;

public abstract class AbstractDtoWithId implements Serializable {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
