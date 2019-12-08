package de.uniba.dsg.dsam.persistence;

public interface IncentiveManagement<ENTITY, DTO> extends CrudManagement<DTO> {
    DTO converEntityToDTO(ENTITY entity);
}
