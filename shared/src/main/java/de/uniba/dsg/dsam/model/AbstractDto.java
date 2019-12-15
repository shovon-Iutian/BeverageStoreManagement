package de.uniba.dsg.dsam.model;

import java.io.Serializable;

public abstract class AbstractDto implements Serializable {
    protected int id;
    protected int version;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
