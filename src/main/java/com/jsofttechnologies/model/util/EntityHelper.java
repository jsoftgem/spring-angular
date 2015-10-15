package com.jsofttechnologies.model.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Jerico on 15/10/2015.
 */
public class EntityHelper {

    public static <S, T> List<T> convertListType(List<S> source, Class<T> type) {
        List<T> dest = new ArrayList<>();
        Iterator<S> sIterator = source.iterator();
        while (sIterator.hasNext()) {
            T t = (T) sIterator.next();
            dest.add(t);
        }
        return dest;
    }

}
