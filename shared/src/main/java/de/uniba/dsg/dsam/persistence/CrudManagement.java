package de.uniba.dsg.dsam.persistence;

import java.util.List;
import java.util.Optional;

public interface CrudManagement<DTO>{
    DTO create(DTO dto);
    DTO update(DTO dto);
    Optional<DTO> getOne(int id);
    List<DTO> getAll();
    void deleteOne(DTO dto);
    void deleteAll();
}
