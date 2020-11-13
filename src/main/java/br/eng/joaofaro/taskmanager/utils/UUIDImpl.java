package br.eng.joaofaro.taskmanager.utils;

import br.eng.joaofaro.taskmanager.factory.UUIDFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * Implementation from main class {@code UUIDFactory} to generate a unique key to log application
 *
 * @see UUIDFactory
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@Slf4j
public class UUIDImpl {

    private static final String UUID_TRANSACTION_KEY = "UUID_TASK_MANAGER";

    /**
     * Method responsible for create a {@code UUID code} and put into a {@code MDC} map.
     * @see UUIDFactory
     * @see MDC
     * @return code
     */
    public static String createUUID() {
        String uuid = UUIDFactory.generateCodeAsUUID();
        MDC.put(UUID_TRANSACTION_KEY, UUID_TRANSACTION_KEY.concat(" [").concat(uuid).concat("]"));
        log.info(MDC.get(UUID_TRANSACTION_KEY));
        return uuid;
    }

    /**
     * Method responsible for remove a {@code UUID code}.
     */
    public static void removeUUID() {
        MDC.remove(UUID_TRANSACTION_KEY);
    }
}
