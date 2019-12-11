package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;

import java.util.List;
import java.util.Optional;

public interface BeverageManagement<ENTITY, DTO> extends IConverter<ENTITY, DTO>{

    Beverage create(Beverage beverage);
    Beverage update(Beverage beverage);
    List<Beverage> getAvailableBeverages();
    Beverage getOneBeverage(int id);
}
