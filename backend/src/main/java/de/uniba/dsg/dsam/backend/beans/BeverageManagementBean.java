package de.uniba.dsg.dsam.backend.beans;

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
public class BeverageManagementBean implements BeverageManagement<BeverageEntity, Beverage> {

    @EJB
    private CrudService<BeverageEntity> beverageService;

    @EJB
    private CrudService<IncentiveEntity> incentiveService;

    @EJB
    private IncentiveManagement<IncentiveEntity, Incentive> incentiveManagement;

    @Override
    public Beverage create(Beverage beverage) {
        BeverageEntity beverageEntity = this.convertDTOToEntity(beverage);
        beverageEntity = this.beverageService.addOne(beverageEntity);
        beverage = this.converEntityToDTO(beverageEntity);
        return beverage;
    }

    @Override
    public Beverage update(Beverage beverage) {
        BeverageEntity beverageEntity = this.convertDTOToEntity(beverage);
        beverageEntity = this.beverageService.updateOne(beverageEntity);
        beverage = this.converEntityToDTO(beverageEntity);
        return beverage;
    }

    @Override
    public Beverage converEntityToDTO(BeverageEntity beverageEntity) {
        Beverage beverage = new Beverage();
        beverage.setId(beverageEntity.getId());
        beverageEntity.getIncentive().map(this.incentiveManagement::converEntityToDTO).ifPresent(beverage::setIncentive);
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
            beverageEntity.setIncentive(incentiveEntity);
            return beverageEntity;
        });
        beverageEntity.setManufacturer(beverage.getManufacturer());
        beverageEntity.setName(beverage.getName());
        beverageEntity.setPrice(beverage.getPrice());
        beverageEntity.setQuantity(beverage.getQuantity());
        return beverageEntity;
    }
}
