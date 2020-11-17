package br.eng.joaofaro.taskmanager.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author João Faro    contato@joaofaro.eng.br on 13/11/20
 * @version 1.0.0
 */
@SpringBootTest
public class UUIDGenerateTests {

    private String uuid;
    private static final String UUID_TRANSACTION_KEY = "UUID_TASK_MANAGER";

    @Before
    public void init() {
        this.uuid = UUIDImpl.createUUID();
    }

    @Test
    public void mustHave36Characters_thenReturnTrue() {
        int characters = StringUtils.length(this.uuid);
        assertTrue(valid(characters));
    }

    @Test
    public void whenCreateANewUUID_thenReturnAValidStringFormated() {
        String s = MDC.get(UUID_TRANSACTION_KEY);
        String expected = UUID_TRANSACTION_KEY.concat(" [").concat(uuid).concat("]");
        Assert.assertEquals(expected, s);

    }

    @Test
    public void whenRemoveUUID_thenReturnAEmptyObject() {
        UUIDImpl.removeUUID();
        String s = MDC.get(UUID_TRANSACTION_KEY);

        assertTrue(StringUtils.isBlank(s));
    }

    private boolean valid(int characters) {
        int numberOfCharacters = 36;
        return characters == numberOfCharacters;
    }
}
