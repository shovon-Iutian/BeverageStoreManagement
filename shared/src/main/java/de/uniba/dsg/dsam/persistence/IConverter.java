package de.uniba.dsg.dsam.persistence;

public interface IConverter<ENTITY, DTO> {
    DTO converEntityToDTO(ENTITY entity);
    ENTITY convertDTOToEntity(DTO dto);
}
