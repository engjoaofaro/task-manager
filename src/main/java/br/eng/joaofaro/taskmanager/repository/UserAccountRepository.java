package br.eng.joaofaro.taskmanager.repository;

import br.eng.joaofaro.taskmanager.entity.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
public interface UserAccountRepository extends JpaRepository<AccountUser, Long> {
    Optional<AccountUser> findAccountUserByUsername(String username);
}
