package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;

public interface BeverageManagement<ENTITY, DTO> extends IConverter<ENTITY, DTO>{

    Beverage create(Beverage beverage);
    Beverage update(Beverage beverage);
}
