package com.cony.data.jpa.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by wangk-p on 2017/4/24.
 */
public class QueryBuilder {


    protected final Logger logger = LoggerFactory.getLogger(QueryBuilder.class);

    private EntityManager entityManager;

    private CriteriaBuilder criteriaBuilder;

    private CriteriaQuery criteriaQuery;

    /**
     * 要查询的模型对象
     */
    private Class clazz;

    /**
     * 查询条件列表
     */
    private Root form;

    private List<Predicate> predicates = new ArrayList();

    private List<Join> joins = new ArrayList();

    /**
     * 排序方式列表
     */
    private List<Order> orders = new ArrayList<Order>();

    private QueryBuilder(Class clazz, EntityManager entityManager) {
        this.clazz = clazz;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.criteriaQuery = criteriaBuilder.createQuery(this.clazz);
        this.form = criteriaQuery.from(this.clazz);
    }

    private QueryBuilder() {

    }

    /**
     * 通过类创建查询条件
     */
    public static QueryBuilder forClass(Class clazz, EntityManager entityManager) {
        return new QueryBuilder(clazz, entityManager);
    }

    /**
     * 相等
     */
    public void eq(String propertyName, Object value) {
        if (isNullOrEmpty(value))
            return;
        this.predicates.add(criteriaBuilder.equal(form.get(propertyName), value));
    }

    public void or(List<String> propertyName, Object value) {
        if (isNullOrEmpty(value))
            return;
        if ((propertyName == null) || (propertyName.size() == 0))
            return;
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.equal(form.get(propertyName.get(0)), value));
        for (int i = 1; i < propertyName.size(); ++i)
            predicate = criteriaBuilder.or(predicate, criteriaBuilder.equal(form.get(propertyName.get(i)), value));
        this.predicates.add(predicate);
    }

    public void orLike(List<String> propertyName, String value) {
        if (isNullOrEmpty(value) || (propertyName.size() == 0))
            return;
        if (value.indexOf("%") < 0)
            value = "%" + value + "%";
        Predicate predicate = criteriaBuilder.or(criteriaBuilder.like(form.get(propertyName.get(0)), value.toString()));
        for (int i = 1; i < propertyName.size(); ++i)
            predicate = criteriaBuilder.or(predicate, criteriaBuilder.like(form.get(propertyName.get(i)), value));
        this.predicates.add(predicate);
    }

    /**
     * 空
     */
    public void isNull(String propertyName) {
        this.predicates.add(criteriaBuilder.isNull(form.get(propertyName)));
    }

    /**
     * 非空
     */
    public void isNotNull(String propertyName) {
        this.predicates.add(criteriaBuilder.isNotNull(form.get(propertyName)));
    }

    /**
     * 不相等
     */
    public void notEq(String propertyName, Object value) {
        if (isNullOrEmpty(value)) {
            return;
        }
        this.predicates.add(criteriaBuilder.notEqual(form.get(propertyName), value));
    }

    /**
     * not in
     *
     * @param propertyName 属性名称
     * @param value        值集合
     */
    public void notIn(String propertyName, Collection value) {
        if ((value == null) || (value.size() == 0)) {
            return;
        }
        Iterator iterator = value.iterator();
        CriteriaBuilder.In in = criteriaBuilder.in(form.get(propertyName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        this.predicates.add(criteriaBuilder.not(in));
    }

    /**
     * not in
     *
     * @param propertyName 属性名称
     * @param value        值集合
     */
    public void notIn(String propertyName, Serializable[] value) {
        if ((value == null) || (value.length == 0)) {
            return;
        }
        CriteriaBuilder.In in = criteriaBuilder.in(form.get(propertyName));
        for (int i = 0; i < value.length; i++) {
            in.value(value[i]);
        }
        this.predicates.add(criteriaBuilder.not(in));
    }

    /**
     * 模糊匹配
     *
     * @param propertyName 属性名称
     * @param value        属性值
     */
    public void like(String propertyName, String value) {
        if (isNullOrEmpty(value))
            return;
        this.predicates.add(criteriaBuilder.like(form.get(propertyName), value));
    }

    /**
     * 时间区间查询
     *
     * @param propertyName 属性名称
     * @param lo           属性起始值
     * @param go           属性结束值
     */
    public void between(String propertyName, Date lo, Date go) {
        if (!isNullOrEmpty(lo) && !isNullOrEmpty(go)) {
            this.predicates.add(criteriaBuilder.between(form.get(propertyName), lo, go));
        }
    }

    public void between(String propertyName, Number lo, Number go) {
        if (!(isNullOrEmpty(lo)))
            ge(propertyName, lo);

        if (!(isNullOrEmpty(go)))
            le(propertyName, go);
    }

    /**
     * 小于等于
     *
     * @param propertyName 属性名称
     * @param value        属性值
     */
    public void le(String propertyName, Number value) {
        if (isNullOrEmpty(value)) {
            return;
        }
        this.predicates.add(criteriaBuilder.le(form.get(propertyName), value));
    }

    /**
     * 小于
     *
     * @param propertyName 属性名称
     * @param value        属性值
     */
    public void lt(String propertyName, Number value) {
        if (isNullOrEmpty(value)) {
            return;
        }
        this.predicates.add(criteriaBuilder.lt(form.get(propertyName), value));
    }

    /**
     * 大于等于
     *
     * @param propertyName 属性名称
     * @param value        属性值
     */
    public void ge(String propertyName, Number value) {
        if (isNullOrEmpty(value)) {
            return;
        }
        this.predicates.add(criteriaBuilder.ge(form.get(propertyName), value));
    }

    /**
     * 大于
     *
     * @param propertyName 属性名称
     * @param value        属性值
     */
    public void gt(String propertyName, Number value) {
        if (isNullOrEmpty(value)) {
            return;
        }
        this.predicates.add(criteriaBuilder.gt(form.get(propertyName), value));
    }

    /**
     * in
     *
     * @param propertyName 属性名称
     * @param value        值集合
     */
    public void in(String propertyName, Collection value) {
        if ((value == null) || (value.size() == 0)) {
            return;
        }
        Iterator iterator = value.iterator();
        CriteriaBuilder.In in = criteriaBuilder.in(form.get(propertyName));
        while (iterator.hasNext()) {
            in.value(iterator.next());
        }
        this.predicates.add(in);
    }

    /**
     * in
     *
     * @param propertyName 属性名称
     * @param value        值集合
     */
    public void in(String propertyName, Serializable[] value) {
        if ((value == null) || (value.length == 0)) {
            return;
        }
        CriteriaBuilder.In in = criteriaBuilder.in(form.get(propertyName));
        for (int i = 0; i < value.length; i++) {
            in.value(value[i]);
        }
        this.predicates.add(in);
    }

    /**
     * 直接添加JPA内部的查询条件,用于应付一些复杂查询的情况,例如或
     */
    public void addCriterions(Predicate predicate) {
        this.predicates.add(predicate);
    }

    /**
     * @return JPA查询
     */
    public CriteriaQuery newCriteriaQuery() {

        if (predicates.size() > 0) {
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
        }
        if (orders.size() > 0) {
            criteriaQuery.orderBy(orders);
        }
        return criteriaQuery;
    }

    public void addOrder(String propertyName, String order) {
        if (order == null || propertyName == null)
            return;
        if (order.equalsIgnoreCase("asc"))
            this.orders.add(criteriaBuilder.asc(form.get(propertyName)));
        else if (order.equalsIgnoreCase("desc"))
            this.orders.add(criteriaBuilder.desc(form.get(propertyName)));
    }

    public Class getClazz() {
        return this.clazz;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Root getform() {
        return form;
    }

    public List<Predicate> getPredicates() {
        return predicates;
    }

    public void setPredicates(List<Predicate> predicates) {
        this.predicates = predicates;
    }

    public CriteriaQuery getCriteriaQuery() {
        return criteriaQuery;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    private boolean isNullOrEmpty(Object value) {
        if (value instanceof String) {
            return value == null || "".equals(value);
        }
        return value == null;
    }

}
