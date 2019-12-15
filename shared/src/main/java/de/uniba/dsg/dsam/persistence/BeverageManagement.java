package de.uniba.dsg.dsam.persistence;

public interface BeverageManagement<ENTITY, DTO> extends CrudManagement<DTO>{
	DTO converEntityToDTO(ENTITY Entity);
}
