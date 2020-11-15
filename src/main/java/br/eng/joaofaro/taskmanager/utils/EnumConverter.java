package br.eng.joaofaro.taskmanager.utils;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Field;
import java.util.EnumSet;

/**
 * @author João Faro    contato@joaofaro.eng.br on 14/11/20
 * @version 1.0.0
 */
public abstract class EnumConverter<E extends Enum<E>, T extends Comparable<T>> implements AttributeConverter<E, T> {

    private final Class<E> enumClass;
    private final String fieldName;

    private Field field;

    public EnumConverter(Class<E> clazz, String fieldName){
        this.enumClass = clazz;
        this.fieldName = fieldName;
    }

    @Override
    public T convertToDatabaseColumn(E attribute) {
        try {
            return attribute == null ? null : (T) getMappingField().get(attribute);
        } catch (NoSuchFieldException|IllegalAccessException e) {
            throw new IllegalArgumentException(String.format("Enum %s doesn't have a field named %s", enumClass, fieldName), e);
        }

    }

    @Override
    public E convertToEntityAttribute(T dbData) {
        if(dbData == null){
            return null;
        }
        for(E en: EnumSet.allOf(enumClass)){
            T val = convertToDatabaseColumn(en);
            if(val != null && val.compareTo(dbData) == 0){
                return en;
            }
        }
        throw new IllegalArgumentException(String.format("There is no Enum of type %s for the db data %s", enumClass, dbData));
    }

    private Field getMappingField() throws NoSuchFieldException{
        if(field == null) {
            field = enumClass.getDeclaredField(fieldName);
            field.setAccessible(true);
        }
        return field;
    }


}
