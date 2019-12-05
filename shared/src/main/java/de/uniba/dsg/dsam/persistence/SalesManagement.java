package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;

public interface SalesManagement<ENTITY, DTO> extends IConverter<ENTITY, DTO>{
	void assignIncentiveToBeverage(Beverage beverage, Incentive incentive);
}
