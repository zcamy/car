package com.zhang.carservice.config.BeanMapper;

import com.api.carapi.annotations.Entity;
import com.api.carapi.annotations.ModelParam;
import com.zhang.carservice.config.handle.GlobalExceptionHandle;

import java.lang.reflect.Field;

/**
 * entity与model之间的映射
 */


public class BeanMapper {


    public static <T> T map(Object Entity, Class<T> modelClass)  {

        GlobalExceptionHandle globalExceptionHandle = new GlobalExceptionHandle();
        //通过反射创建对象
        T t = null;
        try {
            t = modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            globalExceptionHandle.mappingValueError(e,Entity.getClass().toString());
        }

        Class<?> entityClass = Entity.getClass();
        //获取所有字段
        Field[] entityParams = entityClass.getDeclaredFields();
        Field[] modelParams = modelClass.getDeclaredFields();

        for (Field entityParam : entityParams) {
            for (Field modelParam : modelParams) {
                //打开私有访问
                entityParam.setAccessible(true);
                modelParam.setAccessible(true);

                //获取属性
                String paramName = entityParam.getName();
                ModelParam annotation = modelParam.getAnnotation(ModelParam.class);

                //判断是否是有Entity注解
                if (entityClass.getAnnotation(Entity.class) == null){
                     paramName = modelParam.getName();
                     annotation = entityParam.getAnnotation(ModelParam.class);
                }

               if (annotation.value().equals(paramName)){
                   try {
                       modelParam.set(t,entityParam.get(Entity));
                   } catch (IllegalAccessException e) {
                       globalExceptionHandle.mappingValueError(e,Entity.getClass().toString());
                   }
               }
            }
        }
        return t;
    }
}
