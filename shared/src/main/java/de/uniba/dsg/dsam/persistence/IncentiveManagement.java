package de.uniba.dsg.dsam.persistence;

import java.util.List;
import java.util.Optional;

import de.uniba.dsg.dsam.model.Incentive;

public interface IncentiveManagement {
	void addIncentive(Incentive incentive);
	Incentive updateIncentive(Incentive incentive);
	Optional<Incentive> getOneIncentive(int id);
	List<Incentive> getIncentivesByIds(List<Integer> ids);
	List<Incentive> getAllIncentives();
	void deleteOneIncentive(int id);
	void deleteAllIncentives();
}
