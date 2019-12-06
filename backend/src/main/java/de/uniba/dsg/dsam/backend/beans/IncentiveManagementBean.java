package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.abstractbean.AbstractCrudBean;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(IncentiveManagement.class)
public class IncentiveManagementBean extends AbstractCrudBean<IncentiveEntity, Incentive>
		implements IncentiveManagement<IncentiveEntity, Incentive>{

	public IncentiveManagementBean() {
		this.persistentClass = IncentiveEntity.class;
	}

	@Override
	public Incentive converEntityToDTO(IncentiveEntity entity) {
		Incentive incentive;
		if(entity instanceof TrialPackageEntity){
			incentive = new TrialPackage();
		}
		else {
			incentive = new PromotionalGift();
		}
		incentive.setId(entity.getId());
		incentive.setName(entity.getName());
		return incentive;
	}

	@Override
	public IncentiveEntity convertDTOToEntity(Incentive incentive) {
		IncentiveEntity incentiveEntity;
		if(incentive instanceof TrialPackage){
			incentiveEntity = new TrialPackageEntity();
		}
		else {
			incentiveEntity = new PromotionalGiftEntity();
		}
		incentiveEntity.setName(incentive.getName());
		return incentiveEntity;
	}
}
