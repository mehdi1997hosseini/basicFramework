package com.mehdihosseini.framework.basicframework.service.dto;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;

import java.util.List;

public interface BasicDtoService<D> {
    D save(D dto);

    List<D> saveList(List<D> listDto);

    List<D> findAll();
}
