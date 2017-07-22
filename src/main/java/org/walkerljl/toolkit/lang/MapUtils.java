package org.walkerljl.toolkit.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MapUtils
 *
 * @author lijunlin
 */
public class MapUtils {

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> Map<K, V> newHashMap(K key, V value) {
        Map<K, V> map = new HashMap<K, V>();
        map.put(key, value);
        return map;
    }

    public static <K, V> V get(Map<K, V> map, K key) {
        return map == null ? null : map.get(key);
    }

    public static <K, V> boolean containsKey(Map<K, V> map, K key) {
        return map == null ? false : map.containsKey(key);
    }

    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.size() == 0;
    }

    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return map != null && map.size() != 0;
    }

    public static <K, V> int size(Map<K, V> map) {
        return map == null ? 0 : map.size();
    }

    public static <K, V> boolean isEquals(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == null && map2 == null) {
            return true;
        }
        if (map1 == null || map2 == null) {
            return false;
        }
        if (map1.size() != map2.size()) {
            return false;
        }
        for (Map.Entry<?, ?> entry : map1.entrySet()) {
            Object key = entry.getKey();
            Object value1 = entry.getValue();
            Object value2 = map2.get(key);
            if (!MapUtils.objectEquals(value1, value2)) {
                return false;
            }
        }
        return true;
    }

    private static boolean objectEquals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        }
        if (obj1 == null || obj2 == null) {
            return false;
        }
        return obj1.equals(obj2);
    }

    public static <K, V> void putIfAbsent(Map<K, V> map, K key, V value) {
        if (map == null) {
            return;
        }
        if (!map.containsKey(key)) {
            map.put(key, value);
        }
    }

    public static <K, V> void remove(Map<K, V> map, K... keys) {
        if (map == null || keys == null) {
            return;
        }
        for (K key : keys) {
            map.remove(key);
        }
    }

    /**
     * 移除map1中存在的map2元素
     * @param map1
     * @param map2
     */
    public static <K, V> void remove(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == null || map2 == null) {
            return;
        }
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            K key = entry.getKey();
            if (map1.containsKey(key)) {
                map1.remove(key);
            }
        }
    }

    /***
     * 合并
     * @param map1
     * @param map2
     */
    public static <K, V> void merge(Map<K, V> map1, Map<K, V> map2) {
        if (map1 == null || map2 == null) {
            return;
        }
        for (Map.Entry<K, V> entry : map2.entrySet()) {
            if (!map1.containsKey(entry.getKey())) {
                map1.put(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * list value list
     * @param map
     * @return
     */
    public static <K, V> List<V> listValues(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        List<V> values = new ArrayList<V>();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            V value = entry.getValue();
            if (!values.contains(value)) {
                values.add(value);
            }
        }
        return values;
    }

    /**
     * list key list
     * @param map
     * @return
     */
    public static <K, V> List<K> listKeys(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        List<K> list = new ArrayList<K>();
        for (K key : map.keySet()) {
            list.add(key);
        }
        return list;
    }

    /**
     * list key string
     * @param map
     * @param regex
     * @return
     */
    public static <K, V> String listKeys(Map<K, V> map, String regex) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(regex);
        }
        return sb.toString();
    }

    /**
     * 取出map1不存在于map2中的key
     * @param map1
     * @param map2
     * @return
     */
    public static <K, V> List<K> listDiffKeys(Map<K, V> map1, Map<K, V> map2) {
        if (MapUtils.isEmpty(map1)) {
            return null;
        }
        if (MapUtils.isEmpty(map2)) {
            return MapUtils.listKeys(map1);
        }
        List<K> diffKeyList = new ArrayList<K>();
        if (map1 != null && map2 != null) {
            for (Map.Entry<K, V> entry : map1.entrySet()) {
                K key = entry.getKey();
                if (!map2.containsKey(key)) {
                    diffKeyList.add(key);
                }
            }
        }
        return diffKeyList;
    }

    public static <K, V> Map<K, V> clone(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Map<K, V> clone = MapUtils.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            clone.put(entry.getKey(), entry.getValue());
        }
        return clone;
    }

    public static <K, V> Map<V, K> inverse(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        Map<V, K> resultMap = MapUtils.newHashMap();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            resultMap.put(entry.getValue(), entry.getKey());
        }
        return resultMap;
    }

    public static <K, V> K firstKey(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            return entry.getKey();
        }
        return null;
    }

    public static <K, V> V firstValue(Map<K, V> map) {
        if (MapUtils.isEmpty(map)) {
            return null;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            return entry.getValue();
        }
        return null;
    }
}