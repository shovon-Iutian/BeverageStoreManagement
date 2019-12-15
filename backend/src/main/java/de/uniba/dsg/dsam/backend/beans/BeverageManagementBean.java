package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.abstractbean.AbstractCrudBean;
import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.backend.entities.IncentiveEntity;
import de.uniba.dsg.dsam.backend.service.CrudService;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.model.Incentive;
import de.uniba.dsg.dsam.persistence.BeverageManagement;
import de.uniba.dsg.dsam.persistence.IncentiveManagement;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Stateless
@Remote(BeverageManagement.class)
public class BeverageManagementBean extends AbstractCrudBean<BeverageEntity, Beverage> 
	implements BeverageManagement<BeverageEntity, Beverage> {

    @EJB
    private CrudService<IncentiveEntity> incentiveService;

    @EJB
    private IncentiveManagement<IncentiveEntity, Incentive> incentiveManagement;

    public BeverageManagementBean() {
        this.persistentClass = BeverageEntity.class;
    }

    @Override
    public Beverage converEntityToDTO(BeverageEntity beverageEntity) {
        Beverage beverage = new Beverage();
        beverage.setId(beverageEntity.getId());
        beverage.setVersion(beverageEntity.getVersion());
        IncentiveEntity incentiveEntity = beverageEntity.getIncentiveEntity();
        if(incentiveEntity != null){
            Incentive incentive = this.incentiveManagement.converEntityToDTO(incentiveEntity);
            beverage.setIncentive(incentive);
        }
        beverage.setManufacturer(beverageEntity.getManufacturer());
        beverage.setName(beverageEntity.getName());
        beverage.setPrice(beverageEntity.getPrice());
        beverage.setQuantity(beverageEntity.getQuantity());
        return beverage;
    }

    @Override
    public BeverageEntity convertDTOToEntity(Beverage beverage) {
        BeverageEntity beverageEntity = new BeverageEntity();
        beverage.getIncentive().map(incentive -> {
            IncentiveEntity incentiveEntity = this.incentiveService.getOne(IncentiveEntity.class, incentive.getId());
            beverageEntity.setIncentiveEntity(incentiveEntity);
            return beverageEntity;
        });
        if(beverage.getId() != -1){
            beverageEntity.setId(beverage.getId());
            beverageEntity.setVersion(beverage.getVersion());
        }
        beverageEntity.setManufacturer(beverage.getManufacturer());
        beverageEntity.setName(beverage.getName());
        beverageEntity.setPrice(beverage.getPrice());
        beverageEntity.setQuantity(beverage.getQuantity());
        return beverageEntity;
    }
}
