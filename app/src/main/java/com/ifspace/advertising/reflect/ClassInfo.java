package com.ifspace.advertising.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by sheng on 19/9/1.
 */
public class ClassInfo {
    public final Class<?> mClass;
    public final HashMap<String, Method> methods;
    public final HashMap<String, Field> fields;

    public ClassInfo(Class<?> clazz, String className) {
        methods = new HashMap<String, Method>();
        fields = new HashMap<String, Field>();
        mClass = clazz;
    }

    public void addCachedMethod(String key, Method method) {
        methods.put(key, method);
    }

    public Method getCachedMethod(String key) {
        return methods.get(key);
    }

    public void addCachedField(String key, Field field) {
        fields.put(key, field);
    }

    public Field getCachedField(String key) {
        return fields.get(key);
    }
}
