package de.uniba.dsg.dsam.model;

public abstract class AbstractDtoWithId {
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
