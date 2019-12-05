package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;

public interface BeverageManagement<ENTITY, DTO> extends IConverter<ENTITY, DTO>{

    public void create(Beverage beverage);
}
