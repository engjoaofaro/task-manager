package br.eng.joaofaro.taskmanager.repository;

import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    Optional<Task> findByIdAndUser(Long id, AccountUser user);
    @Query("select t from Task t where t.status = :status and t.user = :user order by t.status asc")
    List<Task> findAllByStatusAndUser(@Param(value = "status") TaskStatus status, @Param(value = "user") AccountUser user);
    @Query("select t from Task t where t.status = :status order by t.status asc")
    List<Task> findAllByStatus(@Param(value = "status") TaskStatus status);
    @Query("select t from Task t where t.user = :user order by t.status asc")
    List<Task> findAllByUser(@Param(value = "user") AccountUser user);
    @Transactional
    void deleteByIdAndUser(Long id, AccountUser user);
}
