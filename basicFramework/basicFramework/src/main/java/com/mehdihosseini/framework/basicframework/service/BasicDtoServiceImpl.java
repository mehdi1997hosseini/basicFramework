package com.mehdihosseini.framework.basicframework.service;

import com.mehdihosseini.framework.basicframework.entity.BasicEntity;
import com.mehdihosseini.framework.basicframework.mapper.BasicMapper;
import com.mehdihosseini.framework.basicframework.service.dto.BasicDtoService;

import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@MappedSuperclass
public abstract class BasicDtoServiceImpl<E extends BasicEntity<ID>, ID, D, I extends BasicInfrastructureService<E, ID>, M extends BasicMapper<E, D>> implements BasicDtoService<D> {

    protected M mapper;
    protected I infrastructureService;

    public BasicDtoServiceImpl(M mapper, I infrastructureService) {
        this.mapper = mapper;
        this.infrastructureService = infrastructureService;
    }

    @Override
    public D save(D dto) {
        return mapper.toDto(infrastructureService.save(mapper.toEntity(dto)));
    }

    @Override
    public List<D> saveList(List<D> listDto) {
        if (listDto == null || listDto.isEmpty())
            return null;

        return listDto.stream()
                .map(this::save)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<D> findAll() {
        List<E> allEntity = infrastructureService.findAll();
        return (allEntity == null || allEntity.isEmpty()) ? null : mapper.toDto(allEntity);
    }
}
