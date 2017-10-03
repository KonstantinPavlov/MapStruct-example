package com.sdr.services;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Reflection converter
 * <p>
 * Created by Konstantin on 03.10.2017.
 */
@Component
public class ObjectConverter {

    private static final Logger LOGGER = Logger.getLogger(ObjectConverter.class);

    private static final String GETTER = "get";
    private static final String GET_CLASS = "getClass";

    public Map<String, Object> parseToCollectionReflection(Object obj, Class cls) {

        Map<String, Object> resultMap = new HashMap<>();

        for (Method method : obj.getClass().getMethods()) {
            try {
                if ((!method.isBridge()) && method.getName().startsWith(GETTER) && !GET_CLASS.equals(method.getName())) {
                    method.setAccessible(true);
                    try {
                        Object propParam = getObjectData(obj, method);
                        if (propParam != null) {
                            resultMap.put(method.getName(), propParam);
                        }
                    } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException | java.lang.AbstractMethodError e) {
                        LOGGER.error("Class= " + cls + " " + method.getName() + "    error " + e.getMessage());
                        resultMap.put(method.getName(), null);
                    }
                }
            } catch (Exception e) {
                LOGGER.error("Class= " + cls + " " + method.getName() + "    error " + e.getMessage());
                resultMap.put(method.getName(), null);
            }
        }
        return resultMap;
    }

    private Object getObjectData(Object obj, AccessibleObject prop) throws IllegalAccessException, InvocationTargetException {
        if (prop instanceof Field) {
            return ((Field) prop).get(obj);
        } else if (prop instanceof Method) {
            return ((Method) prop).invoke(obj);
        }
        return null;
    }

}
