package br.eng.joaofaro.taskmanager.repository.service;

import br.eng.joaofaro.taskmanager.entity.AccountUser;
import br.eng.joaofaro.taskmanager.exception.TaskManagerException;
import br.eng.joaofaro.taskmanager.repository.UserAccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <p>Class responsible to process all <code>users</code> services</p>
 *
 * @see UserAccountRepository
 * @author João Faro    contato@joaofaro.eng.br on 15/11/20
 * @version 1.0.0
 */
@Service
@Slf4j
public class UserAccountService {

    private final UserAccountRepository repository;

    public UserAccountService(UserAccountRepository repository) {
        this.repository = repository;
    }

    /**
     * <p>Method responsible to get a <code>user</code> by username</p>
     * @param username User username
     * @return Account
     * @throws TaskManagerException throws a exception to handle result
     */
    public AccountUser getBy(String username) throws TaskManagerException {
        log.info("Getting user by username: {}", username);
        Optional<AccountUser> user;
        try {
            user = repository.findAccountUserByUsername(username);
            if (user.isPresent()) {
                log.info("User returned successfully with Id: {}", user.get().getId());
                return user.get();
            }else {
                log.warn("User not found in database");
                throw new TaskManagerException("User not found in database");
            }
        } catch (Exception e) {
            log.error("Error when trying to get User from Database: {}", e.getMessage());
            throw new TaskManagerException("Error when trying to get User from Database: "+ e.getMessage());
        }
    }
}
