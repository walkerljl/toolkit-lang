package org.walkerljl.toolkit.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Some collection utilities.
 *
 * @author lijunlin
 */
public class CollectionUtils {

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null ? true : collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return (collection != null && !collection.isEmpty());
    }

    public static <T> int size(Collection<T> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static <T> int size(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    public static <T extends Comparable<? super T>> void sort(List<T> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            Collections.sort(list);
        }
    }

    public static String wrap(Collection<?> collection, String prefixWrapper, String suffixWrapper, String sepeator) {
        if (collection == null) {
            return "";
        }
        return CollectionUtils.wrap(collection.iterator(), prefixWrapper, suffixWrapper, sepeator);
    }

    public static String wrap(Iterator<?> objects, String prefixWrapper, String suffixWrapper, String sepeator) {
        if (objects == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (objects.hasNext()) {
            sb.append(prefixWrapper).append(objects.next().toString()).append(suffixWrapper);
        }
        while (objects.hasNext()) {
            sb.append(sepeator).append(prefixWrapper).append(objects.next().toString()).append(suffixWrapper);
        }
        return sb.toString();
    }

    public static String wrap(Collection<?> collection, String sepeator) {
        return CollectionUtils.wrap(collection, "", "", sepeator);
    }

    /**
     * Adapt the specified <code>Iterator</code> to the <code>Enumeration</code>
     * interface.
     */
    public static <T> Enumeration<T> asEnumeration(final Iterator<T> iter) {
        return new Enumeration<T>() {
            public boolean hasMoreElements() {
                return iter.hasNext();
            }

            public T nextElement() {
                return iter.next();
            }
        };
    }

    /**
     * Adapt the specified <code>Enumeration</code> to the <code>Iterator</code>
     * interface.
     */
    public static <T> Iterator<T> asIterator(final Enumeration<T> e) {
        return new Iterator<T>() {
            public boolean hasNext() {
                return e.hasMoreElements();
            }

            public T next() {
                return e.nextElement();
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * Returns a collection containing all elements of the iterator.
     */
    public static <T> Collection<T> asCollection(final Iterator<? extends T> iterator) {
        List<T> list = new ArrayList<T>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    private static final Comparator<String> SIMPLE_NAME_COMPARATOR = new Comparator<String>() {
        public int compare(String s1, String s2) {
            if (s1 == null && s2 == null) {
                return 0;
            }
            if (s1 == null) {
                return -1;
            }
            if (s2 == null) {
                return 1;
            }
            int i1 = s1.lastIndexOf('.');
            if (i1 >= 0) {
                s1 = s1.substring(i1 + 1);
            }
            int i2 = s2.lastIndexOf('.');
            if (i2 >= 0) {
                s2 = s2.substring(i2 + 1);
            }
            return s1.compareToIgnoreCase(s2);
        }
    };

    public static List<String> sortSimpleName(List<String> list) {
        if (list != null && list.size() > 0) {
            Collections.sort(list, SIMPLE_NAME_COMPARATOR);
        }
        return list;
    }

    public static Map<String, Map<String, String>> splitAll(Map<String, List<String>> list, String separator) {
        if (list == null) {
            return null;
        }
        Map<String, Map<String, String>> result = new HashMap<String, Map<String, String>>();
        for (Map.Entry<String, List<String>> entry : list.entrySet()) {
            result.put(entry.getKey(), split(entry.getValue(), separator));
        }
        return result;
    }

    public static Map<String, List<String>> joinAll(Map<String, Map<String, String>> map, String separator) {
        if (map == null) {
            return null;
        }
        Map<String, List<String>> result = new HashMap<String, List<String>>();
        for (Map.Entry<String, Map<String, String>> entry : map.entrySet()) {
            result.put(entry.getKey(), join(entry.getValue(), separator));
        }
        return result;
    }

    public static Map<String, String> split(List<String> list, String separator) {
        if (list == null) {
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        if (list == null || list.size() == 0) {
            return map;
        }
        for (String item : list) {
            int index = item.indexOf(separator);
            if (index == -1) {
                map.put(item, "");
            } else {
                map.put(item.substring(0, index), item.substring(index + 1));
            }
        }
        return map;
    }

    public static List<String> join(Map<String, String> map, String separator) {
        if (map == null) {
            return null;
        }
        List<String> list = new ArrayList<String>();
        if (map == null || map.size() == 0) {
            return list;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null || value.length() == 0) {
                list.add(key);
            } else {
                list.add(key + separator + value);
            }
        }
        return list;
    }

    public static String join(List<String> list, String separator) {
        StringBuilder sb = new StringBuilder();
        for (String ele : list) {
            if (sb.length() > 0) {
                sb.append(separator);
            }
            sb.append(ele);
        }
        return sb.toString();
    }

    public static boolean mapEquals(Map<?, ?> map1, Map<?, ?> map2) {
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
            if (!objectEquals(value1, value2)) {
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

    public static Map<String, String> toStringMap(String... pairs) {
        Map<String, String> parameters = new HashMap<String, String>();
        if (pairs.length > 0) {
            if (pairs.length % 2 != 0) {
                throw new IllegalArgumentException("pairs must be even.");
            }
            for (int i = 0; i < pairs.length; i = i + 2) {
                parameters.put(pairs[i], pairs[i + 1]);
            }
        }
        return parameters;
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> toMap(Object... pairs) {
        Map<K, V> ret = new HashMap<K, V>();
        if (pairs == null || pairs.length == 0) { return ret; }

        if (pairs.length % 2 != 0) {
            throw new IllegalArgumentException("Map pairs can not be odd number.");
        }
        int len = pairs.length / 2;
        for (int i = 0; i < len; i++) {
            ret.put((K) pairs[2 * i], (V) pairs[2 * i + 1]);
        }
        return ret;
    }

    public static <E> void copy(Collection<E> src, Collection<E> dest) {
        if (CollectionUtils.isEmpty(src) || dest == null) {
            return;
        }
        for (E element : src) {
            dest.add(element);
        }
    }

    /**
     * To string array
     *
     * @param params
     * @return
     */
    public static <E> String[] toStringArray(Set<E> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        String[] stringArray = new String[params.size()];
        int index = 0;
        for (E ele : params) {
            if (ele == null) {
                continue;
            }
            stringArray[index] = String.valueOf(ele);
            index++;
        }
        return stringArray;
    }

    /**
     * 将Collection对象转换成Set对象
     *
     * @param collection 需要转换的集合
     * @param <T>
     * @return
     */
    public static <T> Set<T> toSet(Collection<T> collection) {

        if (collection == null || collection.isEmpty()) {
            return null;
        }

        Set<T> resultSet = new HashSet<>(collection.size());
        for (T item : collection) {
            resultSet.add(item);
        }
        return resultSet;
    }
}