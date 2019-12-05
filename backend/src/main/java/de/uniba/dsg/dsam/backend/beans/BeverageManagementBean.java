package de.uniba.dsg.dsam.backend.beans;

import de.uniba.dsg.dsam.backend.entities.BeverageEntity;
import de.uniba.dsg.dsam.model.Beverage;
import de.uniba.dsg.dsam.persistence.BeverageManagement;

import javax.ejb.Stateless;

@Stateless
public class BeverageManagementBean implements BeverageManagement<BeverageEntity, Beverage> {

    @Override
    public void create(Beverage beverage) {

    }

    @Override
    public Beverage converEntityToDTO(BeverageEntity beverageEntity) {
        return null;
    }

    @Override
    public BeverageEntity convertDTOToEntity(Beverage beverage) {
        return null;
    }
}
