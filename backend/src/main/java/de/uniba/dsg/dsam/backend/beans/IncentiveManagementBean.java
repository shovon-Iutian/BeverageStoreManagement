package de.uniba.dsg.dsam.backend.beans;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.backend.service.CrudServiceImpl;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.IncentiveType;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

@Stateless
@Remote(IncentiveManagement.class)
public class IncentiveManagementBean implements IncentiveManagement {	
	
	private CrudServiceImpl<IncentiveEntity> incentiveService = new CrudServiceImpl<>();
	
	private static final Logger logger = Logger.getLogger(IncentiveManagement.class.getName());

	@Override
	public void addIncentive(Incentive incentive) {
		System.out.println("something");
		logger.info("data in bean: "+incentive);
		IncentiveEntity incentiveEntity = convertDtoToEntity(incentive);
		incentiveEntity = incentiveService.addOne(incentiveEntity);
		System.out.println(incentiveEntity);
	}

	@Override
	public Incentive updateIncentive(Incentive incentive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Incentive> getOneIncentive(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incentive> getIncentivesByIds(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Incentive> getAllIncentives() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOneIncentive(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllIncentives() {
		this.incentiveService.deleteAll();
	}

	private IncentiveEntity convertDtoToEntity(Incentive incentive) {
		
		IncentiveEntity incentiveEntity = incentive.getIncentiveType().equals(IncentiveType.TrialPackage) ? new TrialPackageEntity() : new PromotionalGiftEntity();
		incentiveEntity.setName(incentive.getName());
		return incentiveEntity;
	}
}
