package ir.mehdihosseini.basicframework.service.dto;

import java.util.List;

public interface BasicDtoService<D> {
    D save(D dto);

    List<D> saveList(List<D> listDto);

    List<D> findAll();
}
