package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.ReportEntity;

import java.util.List;

public interface SalesManagement{
	void assignIncentiveToBeverage(Beverage beverage, Incentive incentive);

	List<ReportEntity> getSummaryReport();

	List<ReportEntity> getReportOnIncentive();
}
