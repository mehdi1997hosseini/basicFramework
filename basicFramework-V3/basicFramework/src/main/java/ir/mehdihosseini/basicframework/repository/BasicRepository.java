package ir.mehdihosseini.basicframework.repository;

import ir.mehdihosseini.basicframework.entity.BasicEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BasicRepository<E extends BasicEntity<ID>, ID> extends JpaRepository<E, ID>
        , PagingAndSortingRepository<E, ID>, CrudRepository<E, ID> {
    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.isDelete = true where e.id = ?1 ")
    Integer softDeleteById(ID id);

//    default Class<E> getEntityClass() {
//        ParameterizedType type = (ParameterizedType) getClass().getGenericInterfaces()[0];
//        return (Class<E>) type.getActualTypeArguments()[0];
//    }

}
