package com.example.appengine.java8.Management;

import com.example.appengine.java8.Service.CrudManagement;
import com.google.appengine.api.datastore.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractCrudManagement<DTO> implements CrudManagement<DTO> {
    private DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    private Transaction transaction;

    @Override
    public DTO create(DTO dto) {
        Entity entity = convertDtoToEntity(dto);
        transaction = datastoreService.beginTransaction();
        datastoreService.put(transaction, entity);
        transaction.commit();
        return dto;
    }

    @Override
    public List<DTO> getAll(Query query) {
        PreparedQuery preparedQuery = datastoreService.prepare(query);
        List<Entity> candidatesList = preparedQuery.asList(FetchOptions.Builder.withDefaults());
        return candidatesList.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    protected abstract Entity convertDtoToEntity(DTO dto);
    protected abstract DTO convertEntityToDto(Entity entity);
}
