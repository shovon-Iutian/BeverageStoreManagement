package com.example.appengine.java8.Service;

import com.google.appengine.api.datastore.Query;

import java.util.List;

public interface CrudManagement<DTO> {
    DTO create(DTO dto);
    DTO update(DTO dto);
    void delete(Query query);
    List<DTO> get(Query query);
}
