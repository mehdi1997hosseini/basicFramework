package ir.mehdihosseini.basicframework.base.mapper;

import java.util.List;

/**
 * @Param E is Entity that extend BasicEntity
 * @Param D id dto class that implements Serializable
 */
public interface BasicMapper<ENTITY, DTO> {

    ENTITY toEntity(DTO dto);

    List<ENTITY> toEntity(List<DTO> listDto);

    DTO toDto(ENTITY entity);

    List<DTO> toDto(List<ENTITY> listEntity);

}
