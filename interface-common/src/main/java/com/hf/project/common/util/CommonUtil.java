package com.hf.project.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.PropDesc;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;

import java.util.*;

/**
 * @Description 通用工具
 * @Author fyq
 * @Date 2021/8/27 11:43
 **/

public class CommonUtil {

    /**
     * @Author fyq
     * @Description 属性复制 父类 到 子类
     * @Date 11:49 2021/8/27
     * @Param [object, parentFieldName, childFieldName]
     * @return void
     **/
    public static void copyPropertySingleObject(Object object, String parentFieldName, String childFieldName) {

        if (ObjectUtil.isNotNull(object)) {

            Object parentValue = null;
            Class<?> parentClass = null;
            List<List<?>> lists = new ArrayList<>();

            for (PropDesc propDesc : BeanUtil.getBeanDesc(object.getClass()).getProps()) {

                Object o1 = propDesc.getValue(object);
                Class<?> c1 = propDesc.getFieldClass();
                // 8种基本类型 + String
                if (parentFieldName.equals(propDesc.getFieldName())) {
                    if (c1.isPrimitive() || c1 == String.class) {
                        parentValue = o1;
                        parentClass = c1;
                    }
                }
                else if (c1 == List.class) {
                    lists.add((List)o1);
                }
            }

            if (ObjectUtil.isNotNull(parentValue) && ObjectUtil.isNotNull(parentClass)){

                final Object pv = parentValue;
                final Class<?> pc = parentClass;

                lists.forEach(list -> {
                    list.forEach(o -> {
                        for (PropDesc propDesc : BeanUtil.getBeanDesc(o.getClass()).getProps()) {

                            Class<?> c1 = propDesc.getFieldClass();

                            if (childFieldName.equals(propDesc.getFieldName())) {
                                if ( c1 == pc) {
                                    propDesc.setValue(o, pv);
                                }
                                break;
                            }
                        }
                    });
                });
            }
        }
    }

    /**
     * @Author fyq
     * @Description 去除前缀
     * @Date 10:53 2021/9/3
     * @Param [target, prefix]
     * @return java.lang.String
     **/
    public static String removePrefix(String target, String prefix) {

        if (ObjectUtil.isNull(prefix) || ObjectUtil.isNull(target)) {
            return target;
        }

        return target.replaceAll(prefix, "");
    }

    /**
     * @Author fyq
     * @Description 获取前缀
     * @Date 9:45 2021/9/6
     * @Param [sysCode]
     * @return java.lang.String
     **/
    public static String getPrefix(String sysCode) {

        if (StrUtil.isBlank(sysCode)) {
            return "";
        }
        else {
            return sysCode + "_";
        }
    }

    /**
     * @Author fyq
     * @Description 加前缀
     * @Date 9:57 2021/9/6
     * @Param [object, fieldName, prefix]
     * @return void
     **/
    public static void addPrefix(Object object, String prefix, String... fieldNames) {

        List<String> fieldList = Arrays.asList(fieldNames);

        if (ObjectUtil.isNotNull(object)) {

            for (PropDesc propDesc : BeanUtil.getBeanDesc(object.getClass()).getProps()) {

                Object o1 = propDesc.getValue(object);
                Class<?> c1 = propDesc.getFieldClass();
                if (c1.equals(String.class) && fieldList.contains(propDesc.getFieldName())) {

                    String value = Convert.toStr(propDesc.getValue(object), "");
                    if (StrUtil.isNotBlank(value)) {
                        propDesc.setValue(object, prefix + value);
                    }

                }
                else if (o1 instanceof Collection) {
                    Collection<?> collection = (Collection<?>)o1;
                    collection.forEach(o -> addPrefix(o, prefix, fieldNames));
                }

            }
        }
    }

    public static void prefix(Object object, String prefix, Integer action, String... fieldNames) {

        List<String> fieldList = Arrays.asList(fieldNames);

        if (ObjectUtil.isNotNull(object)) {

            for (PropDesc propDesc : BeanUtil.getBeanDesc(object.getClass()).getProps()) {

                Object o1 = propDesc.getValue(object);
                Class<?> c1 = propDesc.getFieldClass();
                if (c1.equals(String.class) && fieldList.contains(propDesc.getFieldName())) {

                    String value = Convert.toStr(propDesc.getValue(object), "");
                    if (StrUtil.isNotBlank(value)) {
                        if (action == 0) {
                            propDesc.setValue(object, prefix + value);
                        }
                        else {
                            propDesc.setValue(object, StrUtil.removePrefix(value, prefix));
                        }

                    }

                }
                else if (o1 instanceof Collection) {
                    Collection<?> collection = (Collection<?>)o1;
                    collection.forEach(o -> prefix(o, prefix, action, fieldNames));
                }

            }
        }
    }

    public static void getValue(Object object, List<String> list, String... fieldNames) {

        List<String> fieldList = Arrays.asList(fieldNames);

        if (ObjectUtil.isNotNull(object)) {

            for (PropDesc propDesc : BeanUtil.getBeanDesc(object.getClass()).getProps()) {

                Object o1 = propDesc.getValue(object);
                Class<?> c1 = propDesc.getFieldClass();
                if (c1.equals(String.class) && fieldList.contains(propDesc.getFieldName())) {

                    String value = Convert.toStr(propDesc.getValue(object), "");
                    if (StrUtil.isNotBlank(value)) {
                        list.add(value);
                    }

                }
                else if (o1 instanceof Collection) {
                    Collection<?> collection = (Collection<?>)o1;
                    collection.forEach(o -> getValue(o, list, fieldNames));
                }

            }
        }
    }
}
