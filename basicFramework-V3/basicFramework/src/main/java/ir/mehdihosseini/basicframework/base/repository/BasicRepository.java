package ir.mehdihosseini.basicframework.base.repository;

import ir.mehdihosseini.basicframework.base.entity.BasicEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

/**
 * این اینترفیس پایه‌ای برای تمام Repositoryهای پروژه است که از JpaRepository ارث‌بری می‌کند.
 * متدهای خاص هر Entity می‌توانند در Repositoryهای فرزند اضافه شوند.
 *<p></p>
 * Base interface for all repositories in the project extending JpaRepository.
 * Entity-specific methods can be added in child repositories.
 *
 * @param <ENTITY> نوع موجودیت
 * @param <ID> نوع شناسه موجودیت
 */

@NoRepositoryBean
public interface BasicRepository<ENTITY extends BasicEntity<ID>, ID> extends JpaRepository<ENTITY, ID> {
    @Modifying
    @Transactional
    @Query("update #{#entityName} e set e.isDelete = true where e.id = ?1 ")
    Integer softDeleteById(ID id);

    @Override
    @Query("select e from #{#entityName} e where e.isDelete = false")
    List<ENTITY> findAll();

    @Override
    @Query("select e from #{#entityName} e where e.id=?1 and e.isDelete = false")
    Optional<ENTITY> findById(ID id);
}
