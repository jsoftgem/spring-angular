package com.jsofttechnologies.services.util;

import com.jsofttechnologies.services.ds.ProjectEntityManager;
import com.jsofttechnologies.util.FieldHelper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jerico on 12/10/2015.
 */
public abstract class PageServiceImpl<T, ID> implements PageService<T, ID> {


    @Autowired
    private ProjectEntityManager projectEntityManager;


    private final Class<T> classType;

    public PageServiceImpl(Class<T> classType) {
        this.classType = classType;
    }

    @Override
    public T find(ID id) {
        return projectEntityManager.getEntityManager().find(classType, id);
    }

    @Override
    public List<T> list() {
        CriteriaBuilder cb = projectEntityManager.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = cb.createQuery(classType);
        Root<T> root = criteriaQuery.from(classType);
        criteriaQuery.select(root);
        TypedQuery<T> tTypedQuery = projectEntityManager.getEntityManager().createQuery(criteriaQuery);
        return tTypedQuery.getResultList();
    }

    @Override
    public Object list(String q, String fields, String sort, Integer size, Map<String, String[]> param) {

        CriteriaBuilder cb = projectEntityManager.getEntityManager().getCriteriaBuilder();

        CriteriaQuery criteriaQuery = cb.createQuery(classType);

        Root root = criteriaQuery.from(classType);


      /*  Field[] classFields = classType.getDeclaredFields();*/
        Method[] classMethods = classType.getDeclaredMethods();
        List<Predicate> predicates = new ArrayList<>();


        if (fields != null) {
            //TODO: create dynamic constructor arguments if possible.
            String[] fieldArray = fields.split(",");
            Path[] paths = new Path[fieldArray.length];
            for (int i = 0; i < fieldArray.length; i++) {
                paths[i] = root.get(fieldArray[i]);
            }
            criteriaQuery.multiselect(paths);
        } else {
            criteriaQuery.select(root);
        }

       /* for (Field field : classFields) {
            if (field.isAnnotationPresent(Column.class)) {
                if (q != null) {
                    q = q.toLowerCase();
                    Boolean number = false;
                    Number qNum = null;
                    try {
                        number = true;
                        if (field.getType() == Integer.class) {
                            qNum = Integer.parseInt(q);
                        } else if (field.getType() == Long.class) {
                            qNum = Long.parseLong(q);
                        } else if (field.getType() == Short.class) {
                            qNum = Short.parseShort(q);
                        } else if (field.getType() == Float.class) {
                            qNum = Float.parseFloat(q);
                        } else if (field.getType() == Double.class) {
                            qNum = Double.parseDouble(q);
                        }
                    } catch (Exception e) {
                        number = false;
                    }

                    if (field.getType() == Long.class
                            || field.getType() == Integer.class
                            || field.getType() == Byte.class
                            || field.getType() == Short.class
                            || field.getType() == Float.class
                            || field.getType() == Double.class) {

                        if (number) {
                            predicates.add(cb.equal(root.get(field.getName()), qNum));
                        }

                    } else if (field.getType() == String.class) {
                        predicates.add(cb.like(cb.lower(root.get(field.getName())), q + "%"));
                    } else if (field.getType() == Date.class) {
                        //TODO : support date search
                    } else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                        //TODO : support related object search
                    }
                }
            }
        }*/


        for (Method field : classMethods) {
            if (field.isAnnotationPresent(Column.class)) {
                if (q != null) {
                    q = q.toLowerCase();
                    Boolean number = false;
                    Number qNum = null;
                    try {
                        number = true;
                        if (field.getReturnType() == Integer.class) {
                            qNum = Integer.parseInt(q);
                        } else if (field.getReturnType() == Long.class) {
                            qNum = Long.parseLong(q);
                        } else if (field.getReturnType() == Short.class) {
                            qNum = Short.parseShort(q);
                        } else if (field.getReturnType() == Float.class) {
                            qNum = Float.parseFloat(q);
                        } else if (field.getReturnType() == Double.class) {
                            qNum = Double.parseDouble(q);
                        }
                    } catch (Exception e) {
                        number = false;
                    }

                    if (field.getReturnType() == Long.class
                            || field.getReturnType() == Integer.class
                            || field.getReturnType() == Byte.class
                            || field.getReturnType() == Short.class
                            || field.getReturnType() == Float.class
                            || field.getReturnType() == Double.class) {

                        if (number) {
                            predicates.add(cb.equal(root.get(FieldHelper.getFieldName(field.getName())), qNum));
                        }

                    } else if (field.getReturnType() == String.class) {
                        predicates.add(cb.like(cb.lower(root.get(FieldHelper.getFieldName(field.getName()))), q + "%"));
                    } else if (field.getReturnType() == Date.class) {
                        //TODO : support date search
                    } else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                        //TODO : support related object search
                    }
                }
            }
        }


        List<Predicate> andSetList = new ArrayList<>();

        if (param != null) {
            for (String key : param.keySet()) {
                if (!key.equalsIgnoreCase("sort") && !key.equalsIgnoreCase("size") && !key.equalsIgnoreCase("fields") && !key.equalsIgnoreCase("q")) {
                    try {
                      /*  if (classType.getDeclaredFields().length > 0) {
                            Field field = classType.getDeclaredField(key);
                            String value = param.get(key)[0];
                            if (field.isAnnotationPresent(Column.class)) {
                                if (value != null) {
                                    value = value.toLowerCase();
                                    if (field.getType() == Long.class
                                            || field.getType() == Integer.class
                                            || field.getType() == Byte.class
                                            || field.getType() == Short.class
                                            || field.getType() == Float.class
                                            || field.getType() == Double.class) {

                                        Boolean number = false;
                                        Number valueNum = null;
                                        Boolean gt = Boolean.FALSE;
                                        Boolean lt = Boolean.FALSE;
                                        try {
                                            number = true;

                                            if (value.charAt(0) == '>') {
                                                value = value.substring(1, value.length());
                                                gt = Boolean.TRUE;
                                            } else if (value.charAt(0) == '<') {
                                                value = value.substring(1, value.length());
                                                lt = Boolean.TRUE;
                                            }

                                            if (field.getType() == Integer.class) {
                                                valueNum = Integer.parseInt(value);
                                            } else if (field.getType() == Long.class) {
                                                valueNum = Long.parseLong(value);
                                            } else if (field.getType() == Short.class) {
                                                valueNum = Short.parseShort(value);
                                            } else if (field.getType() == Float.class) {
                                                valueNum = Float.parseFloat(value);
                                            } else if (field.getType() == Double.class) {
                                                valueNum = Double.parseDouble(value);
                                            }
                                        } catch (Exception e) {
                                            number = false;
                                        }

                                        if (number) {
                                            if (gt) {
                                                andSetList.add(cb.gt(root.get(field.getName()), valueNum));
                                            } else if (lt) {
                                                andSetList.add(cb.lt(root.get(field.getName()), valueNum));
                                            } else {
                                                andSetList.add(cb.equal(root.get(field.getName()), valueNum));
                                            }
                                        }

                                    } else if (field.getType() == String.class) {
                                        andSetList.add(cb.like(cb.lower(root.get(field.getName())), value + "%"));
                                    } else if (field.getType() == Date.class) {
                                        //TODO : support date search
                                    } else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                                        //TODO : support related object search
                                    }
                                }
                            }
                        }*/

                        if (classType.getDeclaredMethods().length > 0) {
                            Method field = classType.getDeclaredMethod(FieldHelper.toMethodGet(key));
                            String value = param.get(key)[0];
                            if (field.isAnnotationPresent(Column.class)) {
                                if (value != null) {
                                    value = value.toLowerCase();
                                    if (field.getReturnType() == Long.class
                                            || field.getReturnType() == Integer.class
                                            || field.getReturnType() == Byte.class
                                            || field.getReturnType() == Short.class
                                            || field.getReturnType() == Float.class
                                            || field.getReturnType() == Double.class) {

                                        Boolean number = false;
                                        Number valueNum = null;
                                        Boolean gt = Boolean.FALSE;
                                        Boolean lt = Boolean.FALSE;
                                        try {
                                            number = true;

                                            if (value.charAt(0) == '>') {
                                                value = value.substring(1, value.length());
                                                gt = Boolean.TRUE;
                                            } else if (value.charAt(0) == '<') {
                                                value = value.substring(1, value.length());
                                                lt = Boolean.TRUE;
                                            }

                                            if (field.getReturnType() == Integer.class) {
                                                valueNum = Integer.parseInt(value);
                                            } else if (field.getReturnType() == Long.class) {
                                                valueNum = Long.parseLong(value);
                                            } else if (field.getReturnType() == Short.class) {
                                                valueNum = Short.parseShort(value);
                                            } else if (field.getReturnType() == Float.class) {
                                                valueNum = Float.parseFloat(value);
                                            } else if (field.getReturnType() == Double.class) {
                                                valueNum = Double.parseDouble(value);
                                            }
                                        } catch (Exception e) {
                                            number = false;
                                        }

                                        if (number) {
                                            if (gt) {
                                                andSetList.add(cb.gt(root.get(key), valueNum));
                                            } else if (lt) {
                                                andSetList.add(cb.lt(root.get(key), valueNum));
                                            } else {
                                                andSetList.add(cb.equal(root.get(key), valueNum));
                                            }
                                        }

                                    } else if (field.getReturnType() == String.class) {
                                        andSetList.add(cb.like(cb.lower(root.get(key)), value + "%"));
                                    } else if (field.getReturnType() == Date.class) {
                                        //TODO : support date search
                                    } else if (field.isAnnotationPresent(ManyToOne.class) || field.isAnnotationPresent(OneToOne.class)) {
                                        //TODO : support related object search
                                    }
                                }
                            }

                        }


                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        List<Order> orderList = new ArrayList<>();

        if (sort != null) {
            String[] sortArray = sort.split(",");
            for (String s : sortArray) {
                if (s.charAt(0) == '-') {
                    String ss = s.substring(1, s.length());
                    orderList.add(cb.desc(root.get(ss)));
                } else {
                    orderList.add(cb.asc(root.get(s)));
                }
            }
        }

        if (!predicates.isEmpty()) {
            criteriaQuery.where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
        }

        if (!andSetList.isEmpty()) {
            criteriaQuery.where(cb.and(andSetList.toArray(new Predicate[andSetList.size()])));
        }

        if (!orderList.isEmpty()) {
            criteriaQuery.orderBy(orderList);
        }


        TypedQuery tTypedQuery = projectEntityManager.getEntityManager().createQuery(criteriaQuery);

        if (size != null) {
            tTypedQuery.setMaxResults(size);
        }

        return tTypedQuery.getResultList();
    }

    @Override
    public T update(T t) throws Exception {
        preUpdate(t);

        EntityTransaction transaction = projectEntityManager.getEntityManager().getTransaction();

        if (!transaction.isActive()) transaction.begin();

        t = projectEntityManager.getEntityManager().merge(t);

        if (transaction.isActive()) transaction.commit();

        return t;
    }

    @Override
    public T update(ID id, T t) throws Exception {
        return update(t);
    }


    @Override
    public void delete(ID id) {
        T t = find(id);
        projectEntityManager.getEntityManager().remove(t);
    }

    @Override
    public void create(T t) throws Exception {
        prePersist(t);

        EntityTransaction transaction = projectEntityManager.getEntityManager().getTransaction();

        if (!transaction.isActive()) {
            transaction.begin();
        }

        projectEntityManager.getEntityManager().persist(t);

        if (transaction.isActive()) {
            transaction.commit();
        }

    }

    @Override
    public void preUpdate(T t) throws Exception {

    }

    @Override
    public void prePersist(T t) throws Exception {

    }

    @Override
    public T patch(ID id, Map<String, String[]> param) {
        T t = find(id);
        try {
            for (String field : param.keySet()) {
                String value = param.get(field)[0];
                try {
                    Method field1 = t.getClass().getDeclaredMethod(FieldHelper.toMethodGet(field));
                    if (field1 != null) {
                        t.getClass().getMethod(FieldHelper.toMethodSet(field), field1.getReturnType()).invoke(t, value);
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            update(t);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return t;
    }
}
