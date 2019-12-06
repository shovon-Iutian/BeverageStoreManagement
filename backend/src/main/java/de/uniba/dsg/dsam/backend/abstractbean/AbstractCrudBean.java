package de.uniba.dsg.dsam.backend.abstractbean;

import de.uniba.dsg.dsam.backend.service.CrudService;
import de.uniba.dsg.dsam.persistence.CrudManagement;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public abstract class AbstractCrudBean<ENTITY, DTO> implements CrudManagement<DTO> {

    @EJB
    private CrudService<ENTITY> crudService;

    protected Class<ENTITY> persistentClass;

    @Override
    public DTO create(DTO dto) {
        ENTITY entity = convertDTOToEntity(dto);
        entity = crudService.addOne(entity);
        dto = converEntityToDTO(entity);
        return dto;
    }

    @Override
    public DTO update(DTO dto) {
        ENTITY entity = convertDTOToEntity(dto);
        entity = crudService.updateOne(entity);
        dto = converEntityToDTO(entity);
        return dto;
    }

    @Override
    public Optional<DTO> getOne(int id) {
        ENTITY entity = crudService.getOne(persistentClass, id);
        if(entity == null)return null;
        return Optional.ofNullable(this.converEntityToDTO(entity));
    }

    @Override
    public List<DTO> getAll() {
        List<ENTITY> entities = crudService.getAll(persistentClass);
        return entities.stream().map(this::converEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOne(DTO dto) {
        ENTITY entity = convertDTOToEntity(dto);
        crudService.deleteOne(entity);
    }

    @Override
    public void deleteAll() {
        crudService.deleteAll();
    }

    protected abstract DTO converEntityToDTO(ENTITY entity);
    protected abstract ENTITY convertDTOToEntity(DTO dto);
}
