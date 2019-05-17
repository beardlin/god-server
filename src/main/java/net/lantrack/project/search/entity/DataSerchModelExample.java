package net.lantrack.project.search.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSerchModelExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DataSerchModelExample() {
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

        public Criteria andModelNameIsNull() {
            addCriterion("model_name is null");
            return (Criteria) this;
        }

        public Criteria andModelNameIsNotNull() {
            addCriterion("model_name is not null");
            return (Criteria) this;
        }

        public Criteria andModelNameEqualTo(String value) {
            addCriterion("model_name =", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotEqualTo(String value) {
            addCriterion("model_name <>", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThan(String value) {
            addCriterion("model_name >", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameGreaterThanOrEqualTo(String value) {
            addCriterion("model_name >=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThan(String value) {
            addCriterion("model_name <", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLessThanOrEqualTo(String value) {
            addCriterion("model_name <=", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameLike(String value) {
            addCriterion("model_name like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotLike(String value) {
            addCriterion("model_name not like", value, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameIn(List<String> values) {
            addCriterion("model_name in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotIn(List<String> values) {
            addCriterion("model_name not in", values, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameBetween(String value1, String value2) {
            addCriterion("model_name between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelNameNotBetween(String value1, String value2) {
            addCriterion("model_name not between", value1, value2, "modelName");
            return (Criteria) this;
        }

        public Criteria andModelCondIsNull() {
            addCriterion("model_cond is null");
            return (Criteria) this;
        }

        public Criteria andModelCondIsNotNull() {
            addCriterion("model_cond is not null");
            return (Criteria) this;
        }

        public Criteria andModelCondEqualTo(String value) {
            addCriterion("model_cond =", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondNotEqualTo(String value) {
            addCriterion("model_cond <>", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondGreaterThan(String value) {
            addCriterion("model_cond >", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondGreaterThanOrEqualTo(String value) {
            addCriterion("model_cond >=", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondLessThan(String value) {
            addCriterion("model_cond <", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondLessThanOrEqualTo(String value) {
            addCriterion("model_cond <=", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondLike(String value) {
            addCriterion("model_cond like", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondNotLike(String value) {
            addCriterion("model_cond not like", value, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondIn(List<String> values) {
            addCriterion("model_cond in", values, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondNotIn(List<String> values) {
            addCriterion("model_cond not in", values, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondBetween(String value1, String value2) {
            addCriterion("model_cond between", value1, value2, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelCondNotBetween(String value1, String value2) {
            addCriterion("model_cond not between", value1, value2, "modelCond");
            return (Criteria) this;
        }

        public Criteria andModelFieldIsNull() {
            addCriterion("model_field is null");
            return (Criteria) this;
        }

        public Criteria andModelFieldIsNotNull() {
            addCriterion("model_field is not null");
            return (Criteria) this;
        }

        public Criteria andModelFieldEqualTo(String value) {
            addCriterion("model_field =", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldNotEqualTo(String value) {
            addCriterion("model_field <>", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldGreaterThan(String value) {
            addCriterion("model_field >", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldGreaterThanOrEqualTo(String value) {
            addCriterion("model_field >=", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldLessThan(String value) {
            addCriterion("model_field <", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldLessThanOrEqualTo(String value) {
            addCriterion("model_field <=", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldLike(String value) {
            addCriterion("model_field like", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldNotLike(String value) {
            addCriterion("model_field not like", value, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldIn(List<String> values) {
            addCriterion("model_field in", values, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldNotIn(List<String> values) {
            addCriterion("model_field not in", values, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldBetween(String value1, String value2) {
            addCriterion("model_field between", value1, value2, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelFieldNotBetween(String value1, String value2) {
            addCriterion("model_field not between", value1, value2, "modelField");
            return (Criteria) this;
        }

        public Criteria andModelCondZhIsNull() {
            addCriterion("model_cond_zh is null");
            return (Criteria) this;
        }

        public Criteria andModelCondZhIsNotNull() {
            addCriterion("model_cond_zh is not null");
            return (Criteria) this;
        }

        public Criteria andModelCondZhEqualTo(String value) {
            addCriterion("model_cond_zh =", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhNotEqualTo(String value) {
            addCriterion("model_cond_zh <>", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhGreaterThan(String value) {
            addCriterion("model_cond_zh >", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhGreaterThanOrEqualTo(String value) {
            addCriterion("model_cond_zh >=", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhLessThan(String value) {
            addCriterion("model_cond_zh <", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhLessThanOrEqualTo(String value) {
            addCriterion("model_cond_zh <=", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhLike(String value) {
            addCriterion("model_cond_zh like", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhNotLike(String value) {
            addCriterion("model_cond_zh not like", value, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhIn(List<String> values) {
            addCriterion("model_cond_zh in", values, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhNotIn(List<String> values) {
            addCriterion("model_cond_zh not in", values, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhBetween(String value1, String value2) {
            addCriterion("model_cond_zh between", value1, value2, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelCondZhNotBetween(String value1, String value2) {
            addCriterion("model_cond_zh not between", value1, value2, "modelCondZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhIsNull() {
            addCriterion("model_field_zh is null");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhIsNotNull() {
            addCriterion("model_field_zh is not null");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhEqualTo(String value) {
            addCriterion("model_field_zh =", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhNotEqualTo(String value) {
            addCriterion("model_field_zh <>", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhGreaterThan(String value) {
            addCriterion("model_field_zh >", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhGreaterThanOrEqualTo(String value) {
            addCriterion("model_field_zh >=", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhLessThan(String value) {
            addCriterion("model_field_zh <", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhLessThanOrEqualTo(String value) {
            addCriterion("model_field_zh <=", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhLike(String value) {
            addCriterion("model_field_zh like", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhNotLike(String value) {
            addCriterion("model_field_zh not like", value, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhIn(List<String> values) {
            addCriterion("model_field_zh in", values, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhNotIn(List<String> values) {
            addCriterion("model_field_zh not in", values, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhBetween(String value1, String value2) {
            addCriterion("model_field_zh between", value1, value2, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andModelFieldZhNotBetween(String value1, String value2) {
            addCriterion("model_field_zh not between", value1, value2, "modelFieldZh");
            return (Criteria) this;
        }

        public Criteria andShowWhoIsNull() {
            addCriterion("show_who is null");
            return (Criteria) this;
        }

        public Criteria andShowWhoIsNotNull() {
            addCriterion("show_who is not null");
            return (Criteria) this;
        }

        public Criteria andShowWhoEqualTo(String value) {
            addCriterion("show_who =", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoNotEqualTo(String value) {
            addCriterion("show_who <>", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoGreaterThan(String value) {
            addCriterion("show_who >", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoGreaterThanOrEqualTo(String value) {
            addCriterion("show_who >=", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoLessThan(String value) {
            addCriterion("show_who <", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoLessThanOrEqualTo(String value) {
            addCriterion("show_who <=", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoLike(String value) {
            addCriterion("show_who like", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoNotLike(String value) {
            addCriterion("show_who not like", value, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoIn(List<String> values) {
            addCriterion("show_who in", values, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoNotIn(List<String> values) {
            addCriterion("show_who not in", values, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoBetween(String value1, String value2) {
            addCriterion("show_who between", value1, value2, "showWho");
            return (Criteria) this;
        }

        public Criteria andShowWhoNotBetween(String value1, String value2) {
            addCriterion("show_who not between", value1, value2, "showWho");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeIsNull() {
            addCriterion("office_code is null");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeIsNotNull() {
            addCriterion("office_code is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeEqualTo(String value) {
            addCriterion("office_code =", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeNotEqualTo(String value) {
            addCriterion("office_code <>", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeGreaterThan(String value) {
            addCriterion("office_code >", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeGreaterThanOrEqualTo(String value) {
            addCriterion("office_code >=", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeLessThan(String value) {
            addCriterion("office_code <", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeLessThanOrEqualTo(String value) {
            addCriterion("office_code <=", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeLike(String value) {
            addCriterion("office_code like", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeNotLike(String value) {
            addCriterion("office_code not like", value, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeIn(List<String> values) {
            addCriterion("office_code in", values, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeNotIn(List<String> values) {
            addCriterion("office_code not in", values, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeBetween(String value1, String value2) {
            addCriterion("office_code between", value1, value2, "officeCode");
            return (Criteria) this;
        }

        public Criteria andOfficeCodeNotBetween(String value1, String value2) {
            addCriterion("office_code not between", value1, value2, "officeCode");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("update_date is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("update_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("update_date =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("update_date <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("update_date >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("update_date >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("update_date <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("update_date <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("update_date in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("update_date not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("update_date between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("update_date not between", value1, value2, "updateDate");
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