package de.uniba.dsg.dsam.persistence;

import de.uniba.dsg.dsam.model.Incentive;

import java.util.List;
import java.util.Optional;

public interface IncentiveManagement<ENTITY, DTO> extends IConverter<ENTITY, DTO>{
	Incentive addIncentive(Incentive incentive);
	Incentive updateIncentive(Incentive incentive);
	Optional<Incentive> getOneIncentive(int id);
	List<Incentive> getIncentivesByIds(List<Integer> ids);
	List<Incentive> getAllIncentives();
	void deleteOneIncentive(Incentive id);
	void deleteAllIncentives();
}
