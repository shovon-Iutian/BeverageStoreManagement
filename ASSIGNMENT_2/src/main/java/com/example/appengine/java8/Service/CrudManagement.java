package com.example.appengine.java8.Service;

import com.google.appengine.api.datastore.Query;

import java.util.List;

public interface CrudManagement<DTO> {
    DTO create(DTO dto);
    List<DTO> getAll(Query query);
}
