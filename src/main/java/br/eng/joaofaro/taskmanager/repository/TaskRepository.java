package br.eng.joaofaro.taskmanager.repository;

import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.entity.Task;
import br.eng.joaofaro.taskmanager.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findById(Long id);
    Optional<Task> findByIdAndUser(Long id, AccountUser user);
    List<Task> findAllByStatusAndUser(TaskStatus status, AccountUser user);
    List<Task> findAllByStatus(TaskStatus status);
    List<Task> findAllByUser(AccountUser user);
    void deleteByIdAndUser(Long id, AccountUser user);
}
