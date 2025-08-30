package ir.mehdihosseini.basicframework.mapper;

import java.util.List;

/**
 * @Param E is Entity that extend BasicEntity
 * @Param D id dto class that implements Serializable
 */
public interface BasicMapper<E, D> {

    E toEntity(D dto);

    List<E> toEntity(List<D> listDto);

    D toDto(E entity);

    List<D> toDto(List<E> listEntity);

}
