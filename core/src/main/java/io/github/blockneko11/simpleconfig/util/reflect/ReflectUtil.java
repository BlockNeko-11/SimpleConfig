package io.github.blockneko11.simpleconfig.util.reflect;

public final class ReflectUtil {
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }
}
