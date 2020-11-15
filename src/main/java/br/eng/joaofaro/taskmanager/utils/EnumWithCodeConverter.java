package br.eng.joaofaro.taskmanager.utils;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public abstract class EnumWithCodeConverter<E extends Enum<E>, T extends Comparable<T>> extends EnumConverter<E, T> {

    protected EnumWithCodeConverter(Class<E> clazz) {
        super(clazz, "code");
    }
}
