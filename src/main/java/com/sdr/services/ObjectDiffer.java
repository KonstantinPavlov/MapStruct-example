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
                                //Ссылки будем проверять рекурсивно
                                // 1) Надо понять ссылка на нужный нам объект или нет, для простоты по id
                                // 2) Если да, то просто запускаем рекурсию и проверяем внутри
                                // 3) Если нет сразу ставим флаг что отличия есть
                                boolean correctLink = false;
                                boolean idLink = checkLink(entry.getValue());
                                if (!idLink) {
                                    try {
                                        correctLink = isCorrectLink((EntityDTO) entry.getValue(), (Entity) method.invoke(record));
                                    } catch (ClassCastException e) {
                                        LOGGER.error("Not correct link in method " + entry.getKey() + " in DTO object!");
                                        isDifferent = true;
                                        break;
                                    }
                                } else
                                    correctLink = isCorrectDTOLink((Long) entry.getValue(), (Entity) method.invoke(record));//Ситуация когда в ДТО у нас id

                                if (correctLink && !idLink) {
                                    LOGGER.info("Recursion checking with correct link " + entry.getKey());
                                    boolean res = isDifferent((EntityDTO) entry.getValue(), (Entity) method.invoke(record));
                                    LOGGER.info("Recursion result for method " + entry.getKey() + " is different? " + res);
                                } else if (!idLink) {
                                    LOGGER.info("Link " + entry.getKey() + " is incorrect");
                                    isDifferent = true;
                                }
                                break;
                            }
                            case COLLECTION: {
                                // Для коллекций проверка похожа на REFERENCE, т.е. проверим количество записей и
                                // потом для каждой записи подряд будем выполнять такую же проверку как для REFERENCE

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

    private boolean checkLink(Object value) {
        return value instanceof Long;
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

    private boolean isCorrectLink(EntityDTO dtoLink, Entity recordLink) {
        return dtoLink.getId() != null && dtoLink.getId().equals(recordLink.getId());
    }

    private boolean isCorrectDTOLink(Long id, Entity recordLink) {
        return id != null && id.equals(recordLink.getId());
    }

}
