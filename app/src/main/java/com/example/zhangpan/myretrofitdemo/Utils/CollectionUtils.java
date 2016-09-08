package com.example.zhangpan.myretrofitdemo.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * 容器的一些常用操作
 *
 * @author mashengchao 2012-2-27 下午3:27:36
 */
public final class CollectionUtils {

    public static <T> List<T> asList(T... arr) {
        return new ArrayList<T>(Arrays.asList(arr));
    }

    /**
     * 判断一个容器是否为空或是否无值
     *
     * @author mashengchao 2012-2-27 下午5:59:47
     * @param list
     * @return
     */
    public static <T> boolean isEmpty(Collection<T> list) {
        if (list == null || list.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 判断一个容器是否为空或是否无值
     *
     * @author mashengchao 2012-2-27 下午5:59:47
     * @param list
     * @return
     */
    public static <T> boolean isNotEmpty(Collection<T> list) {
        return !isEmpty(list);
    }

    /**
     * 判断一个容器是否为空或是否无值
     *
     * @author mashengchao 2012-2-27 下午5:59:47
     * @param list
     * @return
     */
    public static boolean isEmpty(Object[] list) {
        return list == null || list.length == 0;
    }

    /**
     * 判断一个容器是否为空或是否无值
     *
     * @author mashengchao 2012-2-27 下午5:59:47
     * @param list
     * @return
     */
    public static boolean isNotEmpty(Object[] list) {
        return !isEmpty(list);
    }

    /**
     * 判断一个容器是否为空或是否无值
     *
     * @author zhaohaifeng 2013-09-27 下午5:59:47
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        if (map == null || map.isEmpty()) {
            return true;
        }

        return false;
    }

    /**
     * 判断一个容器是否为空或是否无值
     *
     * @author zhaohaifeng 2013-09-27 下午5:59:47
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return !isEmpty(map);
    }

    /**
     * 检测一个对象是否在列表中
     *
     * @author mashengchao 2012-4-9 下午3:51:27
     * @param t
     * @param list
     * @return
     */
    public static <T> boolean inArray(T t, List<T> list) {
        if (t == null || isEmpty(list)) {
            return false;
        }

        int count = Collections.frequency(list, t);

        return count > 0;
    }

    /**
     * 检测一个对象是否在一个数组中
     *
     * @author mashengchao 2012-4-9 下午4:15:04
     * @param t
     * @param ts
     *            当传入基本数据类型的数组时，会出现小问题，会把传入的数组整个当作返回的List中的第一个元素
     * @return
     */
    public static <T> boolean inArray(T t, T... ts) {

        List<T> list = Arrays.asList(ts);
        return inArray(t, list);
    }

    public static <T> void addAll(List<T> list, T... ts) {
        List<T> newList = Arrays.asList(ts);
        list.addAll(newList);
    }


    public static <T> ArrayList copyArray(List<T> list){
        if (list==null){
            return null;
        }
        if(list.size()==0){
            return new ArrayList<T>();
        }
        ArrayList<T> arrayList = new ArrayList<>();
        for(T t: list){
            arrayList.add(t);
        }
        return arrayList;
    }
}
