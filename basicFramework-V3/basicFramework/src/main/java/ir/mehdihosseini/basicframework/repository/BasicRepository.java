package ir.mehdihosseini.basicframework.repository;

import ir.mehdihosseini.basicframework.entity.BasicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface BasicRepository<E extends BasicEntity<ID>, ID> extends JpaRepository<E, ID>
        , PagingAndSortingRepository<E, ID>, CrudRepository<E, ID> {

}
