package net.lantrack.framework.sysbase.entity;

import java.util.ArrayList;
import java.util.List;

public class SysConfigExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysConfigExample() {
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

        public Criteria andConfNameIsNull() {
            addCriterion("conf_name is null");
            return (Criteria) this;
        }

        public Criteria andConfNameIsNotNull() {
            addCriterion("conf_name is not null");
            return (Criteria) this;
        }

        public Criteria andConfNameEqualTo(String value) {
            addCriterion("conf_name =", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotEqualTo(String value) {
            addCriterion("conf_name <>", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameGreaterThan(String value) {
            addCriterion("conf_name >", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameGreaterThanOrEqualTo(String value) {
            addCriterion("conf_name >=", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLessThan(String value) {
            addCriterion("conf_name <", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLessThanOrEqualTo(String value) {
            addCriterion("conf_name <=", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameLike(String value) {
            addCriterion("conf_name like", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotLike(String value) {
            addCriterion("conf_name not like", value, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameIn(List<String> values) {
            addCriterion("conf_name in", values, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotIn(List<String> values) {
            addCriterion("conf_name not in", values, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameBetween(String value1, String value2) {
            addCriterion("conf_name between", value1, value2, "confName");
            return (Criteria) this;
        }

        public Criteria andConfNameNotBetween(String value1, String value2) {
            addCriterion("conf_name not between", value1, value2, "confName");
            return (Criteria) this;
        }

        public Criteria andConfValueIsNull() {
            addCriterion("conf_value is null");
            return (Criteria) this;
        }

        public Criteria andConfValueIsNotNull() {
            addCriterion("conf_value is not null");
            return (Criteria) this;
        }

        public Criteria andConfValueEqualTo(String value) {
            addCriterion("conf_value =", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotEqualTo(String value) {
            addCriterion("conf_value <>", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueGreaterThan(String value) {
            addCriterion("conf_value >", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueGreaterThanOrEqualTo(String value) {
            addCriterion("conf_value >=", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueLessThan(String value) {
            addCriterion("conf_value <", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueLessThanOrEqualTo(String value) {
            addCriterion("conf_value <=", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueLike(String value) {
            addCriterion("conf_value like", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotLike(String value) {
            addCriterion("conf_value not like", value, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueIn(List<String> values) {
            addCriterion("conf_value in", values, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotIn(List<String> values) {
            addCriterion("conf_value not in", values, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueBetween(String value1, String value2) {
            addCriterion("conf_value between", value1, value2, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfValueNotBetween(String value1, String value2) {
            addCriterion("conf_value not between", value1, value2, "confValue");
            return (Criteria) this;
        }

        public Criteria andConfRemarksIsNull() {
            addCriterion("conf_remarks is null");
            return (Criteria) this;
        }

        public Criteria andConfRemarksIsNotNull() {
            addCriterion("conf_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andConfRemarksEqualTo(String value) {
            addCriterion("conf_remarks =", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksNotEqualTo(String value) {
            addCriterion("conf_remarks <>", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksGreaterThan(String value) {
            addCriterion("conf_remarks >", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("conf_remarks >=", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksLessThan(String value) {
            addCriterion("conf_remarks <", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksLessThanOrEqualTo(String value) {
            addCriterion("conf_remarks <=", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksLike(String value) {
            addCriterion("conf_remarks like", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksNotLike(String value) {
            addCriterion("conf_remarks not like", value, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksIn(List<String> values) {
            addCriterion("conf_remarks in", values, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksNotIn(List<String> values) {
            addCriterion("conf_remarks not in", values, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksBetween(String value1, String value2) {
            addCriterion("conf_remarks between", value1, value2, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andConfRemarksNotBetween(String value1, String value2) {
            addCriterion("conf_remarks not between", value1, value2, "confRemarks");
            return (Criteria) this;
        }

        public Criteria andLabelIsNull() {
            addCriterion("label is null");
            return (Criteria) this;
        }

        public Criteria andLabelIsNotNull() {
            addCriterion("label is not null");
            return (Criteria) this;
        }

        public Criteria andLabelEqualTo(String value) {
            addCriterion("label =", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotEqualTo(String value) {
            addCriterion("label <>", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThan(String value) {
            addCriterion("label >", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelGreaterThanOrEqualTo(String value) {
            addCriterion("label >=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThan(String value) {
            addCriterion("label <", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLessThanOrEqualTo(String value) {
            addCriterion("label <=", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelLike(String value) {
            addCriterion("label like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotLike(String value) {
            addCriterion("label not like", value, "label");
            return (Criteria) this;
        }

        public Criteria andLabelIn(List<String> values) {
            addCriterion("label in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotIn(List<String> values) {
            addCriterion("label not in", values, "label");
            return (Criteria) this;
        }

        public Criteria andLabelBetween(String value1, String value2) {
            addCriterion("label between", value1, value2, "label");
            return (Criteria) this;
        }

        public Criteria andLabelNotBetween(String value1, String value2) {
            addCriterion("label not between", value1, value2, "label");
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