package org.pojo.test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;


public class TestHelper {

    public static void setFinalStatic(final Object target, final String fieldName, final Object newValue) throws Exception {
        final Field declaredField = target.getClass()
                                          .getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        final Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(declaredField, declaredField.getModifiers() & ~Modifier.FINAL);
        declaredField.set(target, newValue);
    }
}
