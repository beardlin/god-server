package net.lantrack.framework.sysbase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysRoleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysRoleExample() {
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
        public Criteria andIdIntegerIn(List<Integer> values) {
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

        public Criteria andRoleNameIsNull() {
            addCriterion("role_name is null");
            return (Criteria) this;
        }

        public Criteria andRoleNameIsNotNull() {
            addCriterion("role_name is not null");
            return (Criteria) this;
        }

        public Criteria andRoleNameEqualTo(String value) {
            addCriterion("role_name =", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotEqualTo(String value) {
            addCriterion("role_name <>", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThan(String value) {
            addCriterion("role_name >", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameGreaterThanOrEqualTo(String value) {
            addCriterion("role_name >=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThan(String value) {
            addCriterion("role_name <", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLessThanOrEqualTo(String value) {
            addCriterion("role_name <=", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameLike(String value) {
            addCriterion("role_name like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotLike(String value) {
            addCriterion("role_name not like", value, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameIn(List<String> values) {
            addCriterion("role_name in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotIn(List<String> values) {
            addCriterion("role_name not in", values, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameBetween(String value1, String value2) {
            addCriterion("role_name between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andRoleNameNotBetween(String value1, String value2) {
            addCriterion("role_name not between", value1, value2, "roleName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameIsNull() {
            addCriterion("office_name is null");
            return (Criteria) this;
        }

        public Criteria andOfficeNameIsNotNull() {
            addCriterion("office_name is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeNameEqualTo(String value) {
            addCriterion("office_name =", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotEqualTo(String value) {
            addCriterion("office_name <>", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameGreaterThan(String value) {
            addCriterion("office_name >", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameGreaterThanOrEqualTo(String value) {
            addCriterion("office_name >=", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLessThan(String value) {
            addCriterion("office_name <", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLessThanOrEqualTo(String value) {
            addCriterion("office_name <=", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameLike(String value) {
            addCriterion("office_name like", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotLike(String value) {
            addCriterion("office_name not like", value, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameIn(List<String> values) {
            addCriterion("office_name in", values, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotIn(List<String> values) {
            addCriterion("office_name not in", values, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameBetween(String value1, String value2) {
            addCriterion("office_name between", value1, value2, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeNameNotBetween(String value1, String value2) {
            addCriterion("office_name not between", value1, value2, "officeName");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNull() {
            addCriterion("office_id is null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIsNotNull() {
            addCriterion("office_id is not null");
            return (Criteria) this;
        }

        public Criteria andOfficeIdEqualTo(String value) {
            addCriterion("office_id =", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotEqualTo(String value) {
            addCriterion("office_id <>", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThan(String value) {
            addCriterion("office_id >", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdGreaterThanOrEqualTo(String value) {
            addCriterion("office_id >=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThan(String value) {
            addCriterion("office_id <", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLessThanOrEqualTo(String value) {
            addCriterion("office_id <=", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdLike(String value) {
            addCriterion("office_id like", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotLike(String value) {
            addCriterion("office_id not like", value, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdIn(List<String> values) {
            addCriterion("office_id in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotIn(List<String> values) {
            addCriterion("office_id not in", values, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdBetween(String value1, String value2) {
            addCriterion("office_id between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andOfficeIdNotBetween(String value1, String value2) {
            addCriterion("office_id not between", value1, value2, "officeId");
            return (Criteria) this;
        }

        public Criteria andDataScopeIsNull() {
            addCriterion("data_scope is null");
            return (Criteria) this;
        }

        public Criteria andDataScopeIsNotNull() {
            addCriterion("data_scope is not null");
            return (Criteria) this;
        }

        public Criteria andDataScopeEqualTo(String value) {
            addCriterion("data_scope =", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeNotEqualTo(String value) {
            addCriterion("data_scope <>", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeGreaterThan(String value) {
            addCriterion("data_scope >", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeGreaterThanOrEqualTo(String value) {
            addCriterion("data_scope >=", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeLessThan(String value) {
            addCriterion("data_scope <", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeLessThanOrEqualTo(String value) {
            addCriterion("data_scope <=", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeLike(String value) {
            addCriterion("data_scope like", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeNotLike(String value) {
            addCriterion("data_scope not like", value, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeIn(List<String> values) {
            addCriterion("data_scope in", values, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeNotIn(List<String> values) {
            addCriterion("data_scope not in", values, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeBetween(String value1, String value2) {
            addCriterion("data_scope between", value1, value2, "dataScope");
            return (Criteria) this;
        }

        public Criteria andDataScopeNotBetween(String value1, String value2) {
            addCriterion("data_scope not between", value1, value2, "dataScope");
            return (Criteria) this;
        }

        public Criteria andUseAbleIsNull() {
            addCriterion("use_able is null");
            return (Criteria) this;
        }

        public Criteria andUseAbleIsNotNull() {
            addCriterion("use_able is not null");
            return (Criteria) this;
        }

        public Criteria andUseAbleEqualTo(String value) {
            addCriterion("use_able =", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleNotEqualTo(String value) {
            addCriterion("use_able <>", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleGreaterThan(String value) {
            addCriterion("use_able >", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleGreaterThanOrEqualTo(String value) {
            addCriterion("use_able >=", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleLessThan(String value) {
            addCriterion("use_able <", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleLessThanOrEqualTo(String value) {
            addCriterion("use_able <=", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleLike(String value) {
            addCriterion("use_able like", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleNotLike(String value) {
            addCriterion("use_able not like", value, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleIn(List<String> values) {
            addCriterion("use_able in", values, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleNotIn(List<String> values) {
            addCriterion("use_able not in", values, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleBetween(String value1, String value2) {
            addCriterion("use_able between", value1, value2, "useAble");
            return (Criteria) this;
        }

        public Criteria andUseAbleNotBetween(String value1, String value2) {
            addCriterion("use_able not between", value1, value2, "useAble");
            return (Criteria) this;
        }

        public Criteria andIfAdminIsNull() {
            addCriterion("if_admin is null");
            return (Criteria) this;
        }

        public Criteria andIfAdminIsNotNull() {
            addCriterion("if_admin is not null");
            return (Criteria) this;
        }

        public Criteria andIfAdminEqualTo(String value) {
            addCriterion("if_admin =", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminNotEqualTo(String value) {
            addCriterion("if_admin <>", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminGreaterThan(String value) {
            addCriterion("if_admin >", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminGreaterThanOrEqualTo(String value) {
            addCriterion("if_admin >=", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminLessThan(String value) {
            addCriterion("if_admin <", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminLessThanOrEqualTo(String value) {
            addCriterion("if_admin <=", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminLike(String value) {
            addCriterion("if_admin like", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminNotLike(String value) {
            addCriterion("if_admin not like", value, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminIn(List<String> values) {
            addCriterion("if_admin in", values, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminNotIn(List<String> values) {
            addCriterion("if_admin not in", values, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminBetween(String value1, String value2) {
            addCriterion("if_admin between", value1, value2, "ifAdmin");
            return (Criteria) this;
        }

        public Criteria andIfAdminNotBetween(String value1, String value2) {
            addCriterion("if_admin not between", value1, value2, "ifAdmin");
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