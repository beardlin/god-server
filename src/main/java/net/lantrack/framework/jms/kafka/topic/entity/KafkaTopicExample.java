package net.lantrack.framework.jms.kafka.topic.entity;

import java.util.ArrayList;
import java.util.List;

public class KafkaTopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KafkaTopicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTopicIsNull() {
            addCriterion("topic is null");
            return (Criteria) this;
        }

        public Criteria andTopicIsNotNull() {
            addCriterion("topic is not null");
            return (Criteria) this;
        }

        public Criteria andTopicEqualTo(String value) {
            addCriterion("topic =", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotEqualTo(String value) {
            addCriterion("topic <>", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicGreaterThan(String value) {
            addCriterion("topic >", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicGreaterThanOrEqualTo(String value) {
            addCriterion("topic >=", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLessThan(String value) {
            addCriterion("topic <", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLessThanOrEqualTo(String value) {
            addCriterion("topic <=", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicLike(String value) {
            addCriterion("topic like", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotLike(String value) {
            addCriterion("topic not like", value, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicIn(List<String> values) {
            addCriterion("topic in", values, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotIn(List<String> values) {
            addCriterion("topic not in", values, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicBetween(String value1, String value2) {
            addCriterion("topic between", value1, value2, "topic");
            return (Criteria) this;
        }

        public Criteria andTopicNotBetween(String value1, String value2) {
            addCriterion("topic not between", value1, value2, "topic");
            return (Criteria) this;
        }

        public Criteria andObserverIsNull() {
            addCriterion("observer is null");
            return (Criteria) this;
        }

        public Criteria andObserverIsNotNull() {
            addCriterion("observer is not null");
            return (Criteria) this;
        }

        public Criteria andObserverEqualTo(String value) {
            addCriterion("observer =", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverNotEqualTo(String value) {
            addCriterion("observer <>", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverGreaterThan(String value) {
            addCriterion("observer >", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverGreaterThanOrEqualTo(String value) {
            addCriterion("observer >=", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverLessThan(String value) {
            addCriterion("observer <", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverLessThanOrEqualTo(String value) {
            addCriterion("observer <=", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverLike(String value) {
            addCriterion("observer like", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverNotLike(String value) {
            addCriterion("observer not like", value, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverIn(List<String> values) {
            addCriterion("observer in", values, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverNotIn(List<String> values) {
            addCriterion("observer not in", values, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverBetween(String value1, String value2) {
            addCriterion("observer between", value1, value2, "observer");
            return (Criteria) this;
        }

        public Criteria andObserverNotBetween(String value1, String value2) {
            addCriterion("observer not between", value1, value2, "observer");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andStand1IsNull() {
            addCriterion("stand1 is null");
            return (Criteria) this;
        }

        public Criteria andStand1IsNotNull() {
            addCriterion("stand1 is not null");
            return (Criteria) this;
        }

        public Criteria andStand1EqualTo(String value) {
            addCriterion("stand1 =", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1NotEqualTo(String value) {
            addCriterion("stand1 <>", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1GreaterThan(String value) {
            addCriterion("stand1 >", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1GreaterThanOrEqualTo(String value) {
            addCriterion("stand1 >=", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1LessThan(String value) {
            addCriterion("stand1 <", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1LessThanOrEqualTo(String value) {
            addCriterion("stand1 <=", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1Like(String value) {
            addCriterion("stand1 like", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1NotLike(String value) {
            addCriterion("stand1 not like", value, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1In(List<String> values) {
            addCriterion("stand1 in", values, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1NotIn(List<String> values) {
            addCriterion("stand1 not in", values, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1Between(String value1, String value2) {
            addCriterion("stand1 between", value1, value2, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand1NotBetween(String value1, String value2) {
            addCriterion("stand1 not between", value1, value2, "stand1");
            return (Criteria) this;
        }

        public Criteria andStand2IsNull() {
            addCriterion("stand2 is null");
            return (Criteria) this;
        }

        public Criteria andStand2IsNotNull() {
            addCriterion("stand2 is not null");
            return (Criteria) this;
        }

        public Criteria andStand2EqualTo(String value) {
            addCriterion("stand2 =", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2NotEqualTo(String value) {
            addCriterion("stand2 <>", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2GreaterThan(String value) {
            addCriterion("stand2 >", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2GreaterThanOrEqualTo(String value) {
            addCriterion("stand2 >=", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2LessThan(String value) {
            addCriterion("stand2 <", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2LessThanOrEqualTo(String value) {
            addCriterion("stand2 <=", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2Like(String value) {
            addCriterion("stand2 like", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2NotLike(String value) {
            addCriterion("stand2 not like", value, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2In(List<String> values) {
            addCriterion("stand2 in", values, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2NotIn(List<String> values) {
            addCriterion("stand2 not in", values, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2Between(String value1, String value2) {
            addCriterion("stand2 between", value1, value2, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand2NotBetween(String value1, String value2) {
            addCriterion("stand2 not between", value1, value2, "stand2");
            return (Criteria) this;
        }

        public Criteria andStand3IsNull() {
            addCriterion("stand3 is null");
            return (Criteria) this;
        }

        public Criteria andStand3IsNotNull() {
            addCriterion("stand3 is not null");
            return (Criteria) this;
        }

        public Criteria andStand3EqualTo(String value) {
            addCriterion("stand3 =", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3NotEqualTo(String value) {
            addCriterion("stand3 <>", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3GreaterThan(String value) {
            addCriterion("stand3 >", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3GreaterThanOrEqualTo(String value) {
            addCriterion("stand3 >=", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3LessThan(String value) {
            addCriterion("stand3 <", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3LessThanOrEqualTo(String value) {
            addCriterion("stand3 <=", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3Like(String value) {
            addCriterion("stand3 like", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3NotLike(String value) {
            addCriterion("stand3 not like", value, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3In(List<String> values) {
            addCriterion("stand3 in", values, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3NotIn(List<String> values) {
            addCriterion("stand3 not in", values, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3Between(String value1, String value2) {
            addCriterion("stand3 between", value1, value2, "stand3");
            return (Criteria) this;
        }

        public Criteria andStand3NotBetween(String value1, String value2) {
            addCriterion("stand3 not between", value1, value2, "stand3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}