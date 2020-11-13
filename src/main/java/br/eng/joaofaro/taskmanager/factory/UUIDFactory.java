package br.eng.joaofaro.taskmanager.factory;

import br.eng.joaofaro.taskmanager.utils.UUIDImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

/**
 * This class is the main class to generate the <b>UUID</b> code for log application
 *
 * @see UUIDImpl
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
public class UUIDFactory {

    /**
     * Method responsible to generate a random {@code UUID code}.
     * @see UUIDImpl
     * @return code
     */
    public static String generateCodeAsUUID() {
        return StringUtils.lowerCase(UUID.randomUUID().toString());
    }
}
