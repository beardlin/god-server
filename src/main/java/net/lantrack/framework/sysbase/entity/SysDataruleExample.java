package net.lantrack.framework.sysbase.entity;

import java.util.ArrayList;
import java.util.List;

public class SysDataruleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysDataruleExample() {
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

        public Criteria andIdIn(List<String> values) {
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

        public Criteria andMenuIdIsNull() {
            addCriterion("menu_id is null");
            return (Criteria) this;
        }

        public Criteria andMenuIdIsNotNull() {
            addCriterion("menu_id is not null");
            return (Criteria) this;
        }

        public Criteria andMenuIdEqualTo(Integer value) {
            addCriterion("menu_id =", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotEqualTo(Integer value) {
            addCriterion("menu_id <>", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThan(Integer value) {
            addCriterion("menu_id >", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("menu_id >=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThan(Integer value) {
            addCriterion("menu_id <", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
            addCriterion("menu_id <=", value, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdIn(List<Integer> values) {
            addCriterion("menu_id in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotIn(List<Integer> values) {
            addCriterion("menu_id not in", values, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdBetween(Integer value1, Integer value2) {
            addCriterion("menu_id between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
            addCriterion("menu_id not between", value1, value2, "menuId");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNull() {
            addCriterion("rule_name is null");
            return (Criteria) this;
        }

        public Criteria andRuleNameIsNotNull() {
            addCriterion("rule_name is not null");
            return (Criteria) this;
        }

        public Criteria andRuleNameEqualTo(String value) {
            addCriterion("rule_name =", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotEqualTo(String value) {
            addCriterion("rule_name <>", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThan(String value) {
            addCriterion("rule_name >", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameGreaterThanOrEqualTo(String value) {
            addCriterion("rule_name >=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThan(String value) {
            addCriterion("rule_name <", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLessThanOrEqualTo(String value) {
            addCriterion("rule_name <=", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameLike(String value) {
            addCriterion("rule_name like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotLike(String value) {
            addCriterion("rule_name not like", value, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameIn(List<String> values) {
            addCriterion("rule_name in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotIn(List<String> values) {
            addCriterion("rule_name not in", values, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameBetween(String value1, String value2) {
            addCriterion("rule_name between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andRuleNameNotBetween(String value1, String value2) {
            addCriterion("rule_name not between", value1, value2, "ruleName");
            return (Criteria) this;
        }

        public Criteria andTFieldIsNull() {
            addCriterion("t_field is null");
            return (Criteria) this;
        }

        public Criteria andTFieldIsNotNull() {
            addCriterion("t_field is not null");
            return (Criteria) this;
        }

        public Criteria andTFieldEqualTo(String value) {
            addCriterion("t_field =", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldNotEqualTo(String value) {
            addCriterion("t_field <>", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldGreaterThan(String value) {
            addCriterion("t_field >", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldGreaterThanOrEqualTo(String value) {
            addCriterion("t_field >=", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldLessThan(String value) {
            addCriterion("t_field <", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldLessThanOrEqualTo(String value) {
            addCriterion("t_field <=", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldLike(String value) {
            addCriterion("t_field like", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldNotLike(String value) {
            addCriterion("t_field not like", value, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldIn(List<String> values) {
            addCriterion("t_field in", values, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldNotIn(List<String> values) {
            addCriterion("t_field not in", values, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldBetween(String value1, String value2) {
            addCriterion("t_field between", value1, value2, "tField");
            return (Criteria) this;
        }

        public Criteria andTFieldNotBetween(String value1, String value2) {
            addCriterion("t_field not between", value1, value2, "tField");
            return (Criteria) this;
        }

        public Criteria andTExpressIsNull() {
            addCriterion("t_express is null");
            return (Criteria) this;
        }

        public Criteria andTExpressIsNotNull() {
            addCriterion("t_express is not null");
            return (Criteria) this;
        }

        public Criteria andTExpressEqualTo(String value) {
            addCriterion("t_express =", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressNotEqualTo(String value) {
            addCriterion("t_express <>", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressGreaterThan(String value) {
            addCriterion("t_express >", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressGreaterThanOrEqualTo(String value) {
            addCriterion("t_express >=", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressLessThan(String value) {
            addCriterion("t_express <", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressLessThanOrEqualTo(String value) {
            addCriterion("t_express <=", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressLike(String value) {
            addCriterion("t_express like", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressNotLike(String value) {
            addCriterion("t_express not like", value, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressIn(List<String> values) {
            addCriterion("t_express in", values, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressNotIn(List<String> values) {
            addCriterion("t_express not in", values, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressBetween(String value1, String value2) {
            addCriterion("t_express between", value1, value2, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTExpressNotBetween(String value1, String value2) {
            addCriterion("t_express not between", value1, value2, "tExpress");
            return (Criteria) this;
        }

        public Criteria andTValueIsNull() {
            addCriterion("t_value is null");
            return (Criteria) this;
        }

        public Criteria andTValueIsNotNull() {
            addCriterion("t_value is not null");
            return (Criteria) this;
        }

        public Criteria andTValueEqualTo(String value) {
            addCriterion("t_value =", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueNotEqualTo(String value) {
            addCriterion("t_value <>", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueGreaterThan(String value) {
            addCriterion("t_value >", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueGreaterThanOrEqualTo(String value) {
            addCriterion("t_value >=", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueLessThan(String value) {
            addCriterion("t_value <", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueLessThanOrEqualTo(String value) {
            addCriterion("t_value <=", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueLike(String value) {
            addCriterion("t_value like", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueNotLike(String value) {
            addCriterion("t_value not like", value, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueIn(List<String> values) {
            addCriterion("t_value in", values, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueNotIn(List<String> values) {
            addCriterion("t_value not in", values, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueBetween(String value1, String value2) {
            addCriterion("t_value between", value1, value2, "tValue");
            return (Criteria) this;
        }

        public Criteria andTValueNotBetween(String value1, String value2) {
            addCriterion("t_value not between", value1, value2, "tValue");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentIsNull() {
            addCriterion("sql_segment is null");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentIsNotNull() {
            addCriterion("sql_segment is not null");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentEqualTo(String value) {
            addCriterion("sql_segment =", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentNotEqualTo(String value) {
            addCriterion("sql_segment <>", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentGreaterThan(String value) {
            addCriterion("sql_segment >", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentGreaterThanOrEqualTo(String value) {
            addCriterion("sql_segment >=", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentLessThan(String value) {
            addCriterion("sql_segment <", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentLessThanOrEqualTo(String value) {
            addCriterion("sql_segment <=", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentLike(String value) {
            addCriterion("sql_segment like", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentNotLike(String value) {
            addCriterion("sql_segment not like", value, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentIn(List<String> values) {
            addCriterion("sql_segment in", values, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentNotIn(List<String> values) {
            addCriterion("sql_segment not in", values, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentBetween(String value1, String value2) {
            addCriterion("sql_segment between", value1, value2, "sqlSegment");
            return (Criteria) this;
        }

        public Criteria andSqlSegmentNotBetween(String value1, String value2) {
            addCriterion("sql_segment not between", value1, value2, "sqlSegment");
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

        public Criteria andDelFlagIsNull() {
            addCriterion("del_flag is null");
            return (Criteria) this;
        }

        public Criteria andDelFlagIsNotNull() {
            addCriterion("del_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDelFlagEqualTo(String value) {
            addCriterion("del_flag =", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotEqualTo(String value) {
            addCriterion("del_flag <>", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThan(String value) {
            addCriterion("del_flag >", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagGreaterThanOrEqualTo(String value) {
            addCriterion("del_flag >=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThan(String value) {
            addCriterion("del_flag <", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLessThanOrEqualTo(String value) {
            addCriterion("del_flag <=", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagLike(String value) {
            addCriterion("del_flag like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotLike(String value) {
            addCriterion("del_flag not like", value, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagIn(List<String> values) {
            addCriterion("del_flag in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotIn(List<String> values) {
            addCriterion("del_flag not in", values, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagBetween(String value1, String value2) {
            addCriterion("del_flag between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andDelFlagNotBetween(String value1, String value2) {
            addCriterion("del_flag not between", value1, value2, "delFlag");
            return (Criteria) this;
        }

        public Criteria andClassnameIsNull() {
            addCriterion("className is null");
            return (Criteria) this;
        }

        public Criteria andClassnameIsNotNull() {
            addCriterion("className is not null");
            return (Criteria) this;
        }

        public Criteria andClassnameEqualTo(String value) {
            addCriterion("className =", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameNotEqualTo(String value) {
            addCriterion("className <>", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameGreaterThan(String value) {
            addCriterion("className >", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameGreaterThanOrEqualTo(String value) {
            addCriterion("className >=", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameLessThan(String value) {
            addCriterion("className <", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameLessThanOrEqualTo(String value) {
            addCriterion("className <=", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameLike(String value) {
            addCriterion("className like", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameNotLike(String value) {
            addCriterion("className not like", value, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameIn(List<String> values) {
            addCriterion("className in", values, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameNotIn(List<String> values) {
            addCriterion("className not in", values, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameBetween(String value1, String value2) {
            addCriterion("className between", value1, value2, "classname");
            return (Criteria) this;
        }

        public Criteria andClassnameNotBetween(String value1, String value2) {
            addCriterion("className not between", value1, value2, "classname");
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