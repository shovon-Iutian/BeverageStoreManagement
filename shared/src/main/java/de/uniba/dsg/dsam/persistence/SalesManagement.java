package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;

public interface SalesManagement{
	void assignIncentiveToBeverage(Beverage beverage, Incentive incentive);
}
