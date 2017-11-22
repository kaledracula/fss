//package com.cony.web.controller;
//
//import com.cony.data.common.validate.ValidateUtils;
//import com.cony.data.jpa.entity.BaseEntity;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// * Created by wangk-p on 2017/4/25.
// */
//@Aspect
//@Component
//public class ControllerValidatorInterceptor {
//
//    private static final Logger logger = LoggerFactory
//            .getLogger(ControllerValidatorInterceptor.class);
//
//    @Pointcut("execution(* com.glodon.coca..web.controller.*.add*(..))" +  "|| execution(* com.glodon.coca..web.controller.*.validate*(..))")
//    public void findValidatorController() {
//    }
//
//    @Before("findValidatorController()")
//    public void doBefore(JoinPoint joinPoint) throws IllegalAccessException {
//        Object[] args = joinPoint.getArgs(); //获取拦截方法的参数
//        for (Object arg : args) {
//            List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
//            if (arg != null) {
//                if (arg instanceof Collection) {
//                    Iterator it = ((Collection) arg).iterator();
//                    Object first = it.next();
//                    list.add(validateFiled(first));
//                    while (it.hasNext()) {
//                        list.add(validateFiled(it.next()));
//                    }
//                    ValidateUtils.validValues(first.getClass()
//                            , list);
//                } else if (arg.getClass().isArray()) {
//                    for (int i = 0; i < ((Object[]) arg).length; i++) {
//                        list.add(validateFiled(((Object[]) arg)[i]));
//                    }
//                    ValidateUtils.validValues(((Object[]) arg)[0].getClass()
//                            , list);
//                } else if (arg instanceof BaseEntity) {
//                    list.add(validateFiled(arg));
//                    ValidateUtils.validValues(arg.getClass()
//                            , list);
//                }
//            }
//        }
//    }
//
//
//    private Map<String, Object> validateFiled(Object arg) throws IllegalAccessException {
//        Map<String, Object> map = new HashMap<String, Object>();
//        Class<?> clazz = arg.getClass();//利用反射获取属性值
//        Field[] fields = clazz.getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            field.setAccessible(true);
//            map.put(field.getName(), field.get(arg));
//        }
//        return map;
//    }
//}
