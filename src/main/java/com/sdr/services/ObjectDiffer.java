package com.sdr.services;


import com.sdr.dto.base.EntityDTO;
import com.sdr.entity.base.Entity;
import com.sdr.services.enums.MethodType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class ObjectDiffer {

    private static final Logger LOGGER = Logger.getLogger(ObjectDiffer.class);

    @Autowired
    private ObjectConverter converter;

    public <D extends EntityDTO, R extends Entity> boolean isDifferent(D dto, R record) {
        boolean isDifferent = false;

        for (Map.Entry<String, Object> entry : converter.parseToCollectionReflection(dto, dto.getClass()).entrySet()) {
            for (Method method : record.getClass().getMethods()) {
                if (method.getName().equals(entry.getKey())) {
                    // Определим тип возвращаемого значения
                    method.setAccessible(true);
                    try {
                        switch (getMethodReturnType(method)) {
                            case VALUE: {
                                if (entry.getValue() != null) {
                                    if (!entry.getValue().equals(method.invoke(record))) {
                                        isDifferent = true;
                                        LOGGER.info(" Record " + record + " method " + method + " isDifferent ? " + isDifferent);
                                    }
                                }
                                break;
                            }
                            case REFERENCE: {
                                break;
                            }
                            case COLLECTION: {
                                break;
                            }
                            default: {
                                LOGGER.info("Smth going wrong!");
                                break;
                            }
                        }

                    } catch (IllegalAccessException e) {
                        LOGGER.error("IllegalAccessException : " + e);
                    } catch (InvocationTargetException e) {
                        LOGGER.error("InvocationTargetException : " + e);
                    }
                }
            }
        }
        return isDifferent;
    }

    private MethodType getMethodReturnType(AccessibleObject method) {
        if (isValueMethod((Method) method)) {
            return MethodType.VALUE;
        }
        if (isCollectionMethod((Method) method))
            return MethodType.COLLECTION;
        else
            return MethodType.REFERENCE;
    }

    private boolean isCollectionMethod(Method prop) {
        return prop.getReturnType().isAssignableFrom(Collection.class) || prop.getReturnType().isAssignableFrom(List.class);
    }

    private boolean isValueMethod(Method method) {
        return (method.getReturnType().isAssignableFrom(Long.class) || method.getReturnType().isAssignableFrom(String.class) || method.getReturnType().isAssignableFrom(Date.class));

    }

}
