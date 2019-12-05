package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.entities.PromotionalGiftEntity;
import de.uniba.dsg.dsam.backend.entities.TrialPackageEntity;
import de.uniba.dsg.dsam.backend.service.CrudService;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.model.PromotionalGift;
import de.uniba.dsg.dsam.model.TrialPackage;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
@Remote(IncentiveManagement.class)
public class IncentiveManagementBean implements IncentiveManagement<IncentiveEntity, Incentive> {

	@EJB
	private CrudService<IncentiveEntity> incentiveService;

	@Override
	public Incentive addIncentive(Incentive incentive) {
		IncentiveEntity incentiveEntity = convertDTOToEntity(incentive);
		incentiveEntity = incentiveService.addOne(incentiveEntity);
		incentive = converEntityToDTO(incentiveEntity);
		return incentive;
	}

	@Override
	public Incentive updateIncentive(Incentive incentive) {
		IncentiveEntity incentiveEntity = convertDTOToEntity(incentive);
		incentiveEntity = incentiveService.updateOne(incentiveEntity);
		incentive = converEntityToDTO(incentiveEntity);
		return incentive;
	}

	@Override
	public Optional<Incentive> getOneIncentive(int id) {
		Optional<IncentiveEntity> optionalIncentiveEntity = incentiveService.getOne(TrialPackageEntity.class, id);
		if(!optionalIncentiveEntity.isPresent()){
			optionalIncentiveEntity = incentiveService.getOne(PromotionalGiftEntity.class, id);
		}
		return optionalIncentiveEntity.map(this::converEntityToDTO);
	}

	@Override
	public List<Incentive> getIncentivesByIds(List<Integer> ids) {
		List<Incentive> incentiveList = new ArrayList<>();
		for(int id : ids){
			Optional<Incentive> incentive = this.getOneIncentive(id);
			if(incentive.isPresent()){
				incentiveList.add(incentive.get());
			}
		}
		return incentiveList;
	}

	@Override
	public List<Incentive> getAllIncentives() {
		List<IncentiveEntity> incentiveEntities = incentiveService.getAll(TrialPackageEntity.class);
		incentiveEntities.addAll(incentiveService.getAll(PromotionalGiftEntity.class));
		return incentiveEntities.stream().map(this::converEntityToDTO).collect(Collectors.toList());
	}

	@Override
	public void deleteOneIncentive(Incentive incentive) {
		IncentiveEntity incentiveEntity = convertDTOToEntity(incentive);
		incentiveService.deleteOne(incentiveEntity);
	}

	@Override
	public void deleteAllIncentives() {
		this.incentiveService.deleteAll();
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
