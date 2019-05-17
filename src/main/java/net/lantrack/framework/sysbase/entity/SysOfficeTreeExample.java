package net.lantrack.framework.sysbase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysOfficeTreeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysOfficeTreeExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andPNameIsNull() {
            addCriterion("p_name is null");
            return (Criteria) this;
        }

        public Criteria andPNameIsNotNull() {
            addCriterion("p_name is not null");
            return (Criteria) this;
        }

        public Criteria andPNameEqualTo(String value) {
            addCriterion("p_name =", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotEqualTo(String value) {
            addCriterion("p_name <>", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThan(String value) {
            addCriterion("p_name >", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameGreaterThanOrEqualTo(String value) {
            addCriterion("p_name >=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThan(String value) {
            addCriterion("p_name <", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLessThanOrEqualTo(String value) {
            addCriterion("p_name <=", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameLike(String value) {
            addCriterion("p_name like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotLike(String value) {
            addCriterion("p_name not like", value, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameIn(List<String> values) {
            addCriterion("p_name in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotIn(List<String> values) {
            addCriterion("p_name not in", values, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameBetween(String value1, String value2) {
            addCriterion("p_name between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andPNameNotBetween(String value1, String value2) {
            addCriterion("p_name not between", value1, value2, "pName");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNull() {
            addCriterion("full_name is null");
            return (Criteria) this;
        }

        public Criteria andFullNameIsNotNull() {
            addCriterion("full_name is not null");
            return (Criteria) this;
        }

        public Criteria andFullNameEqualTo(String value) {
            addCriterion("full_name =", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotEqualTo(String value) {
            addCriterion("full_name <>", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThan(String value) {
            addCriterion("full_name >", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameGreaterThanOrEqualTo(String value) {
            addCriterion("full_name >=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThan(String value) {
            addCriterion("full_name <", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLessThanOrEqualTo(String value) {
            addCriterion("full_name <=", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameLike(String value) {
            addCriterion("full_name like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotLike(String value) {
            addCriterion("full_name not like", value, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameIn(List<String> values) {
            addCriterion("full_name in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotIn(List<String> values) {
            addCriterion("full_name not in", values, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameBetween(String value1, String value2) {
            addCriterion("full_name between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andFullNameNotBetween(String value1, String value2) {
            addCriterion("full_name not between", value1, value2, "fullName");
            return (Criteria) this;
        }

        public Criteria andPIdIsNull() {
            addCriterion("p_id is null");
            return (Criteria) this;
        }

        public Criteria andPIdIsNotNull() {
            addCriterion("p_id is not null");
            return (Criteria) this;
        }

        public Criteria andPIdEqualTo(String value) {
            addCriterion("p_id =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(String value) {
            addCriterion("p_id <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(String value) {
            addCriterion("p_id >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(String value) {
            addCriterion("p_id >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(String value) {
            addCriterion("p_id <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(String value) {
            addCriterion("p_id <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLike(String value) {
            addCriterion("p_id like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotLike(String value) {
            addCriterion("p_id not like", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<String> values) {
            addCriterion("p_id in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<String> values) {
            addCriterion("p_id not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(String value1, String value2) {
            addCriterion("p_id between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(String value1, String value2) {
            addCriterion("p_id not between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdsIsNull() {
            addCriterion("p_ids is null");
            return (Criteria) this;
        }

        public Criteria andPIdsIsNotNull() {
            addCriterion("p_ids is not null");
            return (Criteria) this;
        }

        public Criteria andPIdsEqualTo(String value) {
            addCriterion("p_ids =", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsNotEqualTo(String value) {
            addCriterion("p_ids <>", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsGreaterThan(String value) {
            addCriterion("p_ids >", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsGreaterThanOrEqualTo(String value) {
            addCriterion("p_ids >=", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsLessThan(String value) {
            addCriterion("p_ids <", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsLessThanOrEqualTo(String value) {
            addCriterion("p_ids <=", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsLike(String value) {
            addCriterion("p_ids like", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsNotLike(String value) {
            addCriterion("p_ids not like", value, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsIn(List<String> values) {
            addCriterion("p_ids in", values, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsNotIn(List<String> values) {
            addCriterion("p_ids not in", values, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsBetween(String value1, String value2) {
            addCriterion("p_ids between", value1, value2, "pIds");
            return (Criteria) this;
        }

        public Criteria andPIdsNotBetween(String value1, String value2) {
            addCriterion("p_ids not between", value1, value2, "pIds");
            return (Criteria) this;
        }

        public Criteria andTNameIsNull() {
            addCriterion("t_name is null");
            return (Criteria) this;
        }

        public Criteria andTNameIsNotNull() {
            addCriterion("t_name is not null");
            return (Criteria) this;
        }

        public Criteria andTNameEqualTo(String value) {
            addCriterion("t_name =", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameNotEqualTo(String value) {
            addCriterion("t_name <>", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameGreaterThan(String value) {
            addCriterion("t_name >", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameGreaterThanOrEqualTo(String value) {
            addCriterion("t_name >=", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameLessThan(String value) {
            addCriterion("t_name <", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameLessThanOrEqualTo(String value) {
            addCriterion("t_name <=", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameLike(String value) {
            addCriterion("t_name like", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameNotLike(String value) {
            addCriterion("t_name not like", value, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameIn(List<String> values) {
            addCriterion("t_name in", values, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameNotIn(List<String> values) {
            addCriterion("t_name not in", values, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameBetween(String value1, String value2) {
            addCriterion("t_name between", value1, value2, "tName");
            return (Criteria) this;
        }

        public Criteria andTNameNotBetween(String value1, String value2) {
            addCriterion("t_name not between", value1, value2, "tName");
            return (Criteria) this;
        }

        public Criteria andOSortIsNull() {
            addCriterion("o_sort is null");
            return (Criteria) this;
        }

        public Criteria andOSortIsNotNull() {
            addCriterion("o_sort is not null");
            return (Criteria) this;
        }

        public Criteria andOSortEqualTo(Long value) {
            addCriterion("o_sort =", value, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortNotEqualTo(Long value) {
            addCriterion("o_sort <>", value, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortGreaterThan(Long value) {
            addCriterion("o_sort >", value, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortGreaterThanOrEqualTo(Long value) {
            addCriterion("o_sort >=", value, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortLessThan(Long value) {
            addCriterion("o_sort <", value, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortLessThanOrEqualTo(Long value) {
            addCriterion("o_sort <=", value, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortIn(List<Long> values) {
            addCriterion("o_sort in", values, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortNotIn(List<Long> values) {
            addCriterion("o_sort not in", values, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortBetween(Long value1, Long value2) {
            addCriterion("o_sort between", value1, value2, "oSort");
            return (Criteria) this;
        }

        public Criteria andOSortNotBetween(Long value1, Long value2) {
            addCriterion("o_sort not between", value1, value2, "oSort");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(String value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(String value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(String value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(String value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(String value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLike(String value) {
            addCriterion("area_id like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotLike(String value) {
            addCriterion("area_id not like", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<String> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<String> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(String value1, String value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(String value1, String value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andOCodeIsNull() {
            addCriterion("o_code is null");
            return (Criteria) this;
        }

        public Criteria andOCodeIsNotNull() {
            addCriterion("o_code is not null");
            return (Criteria) this;
        }

        public Criteria andOCodeEqualTo(String value) {
            addCriterion("o_code =", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeNotEqualTo(String value) {
            addCriterion("o_code <>", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeGreaterThan(String value) {
            addCriterion("o_code >", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeGreaterThanOrEqualTo(String value) {
            addCriterion("o_code >=", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeLessThan(String value) {
            addCriterion("o_code <", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeLessThanOrEqualTo(String value) {
            addCriterion("o_code <=", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeLike(String value) {
            addCriterion("o_code like", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeNotLike(String value) {
            addCriterion("o_code not like", value, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeIn(List<String> values) {
            addCriterion("o_code in", values, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeNotIn(List<String> values) {
            addCriterion("o_code not in", values, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeBetween(String value1, String value2) {
            addCriterion("o_code between", value1, value2, "oCode");
            return (Criteria) this;
        }

        public Criteria andOCodeNotBetween(String value1, String value2) {
            addCriterion("o_code not between", value1, value2, "oCode");
            return (Criteria) this;
        }

        public Criteria andOTypeIsNull() {
            addCriterion("o_type is null");
            return (Criteria) this;
        }

        public Criteria andOTypeIsNotNull() {
            addCriterion("o_type is not null");
            return (Criteria) this;
        }

        public Criteria andOTypeEqualTo(String value) {
            addCriterion("o_type =", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeNotEqualTo(String value) {
            addCriterion("o_type <>", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeGreaterThan(String value) {
            addCriterion("o_type >", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeGreaterThanOrEqualTo(String value) {
            addCriterion("o_type >=", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeLessThan(String value) {
            addCriterion("o_type <", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeLessThanOrEqualTo(String value) {
            addCriterion("o_type <=", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeLike(String value) {
            addCriterion("o_type like", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeNotLike(String value) {
            addCriterion("o_type not like", value, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeIn(List<String> values) {
            addCriterion("o_type in", values, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeNotIn(List<String> values) {
            addCriterion("o_type not in", values, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeBetween(String value1, String value2) {
            addCriterion("o_type between", value1, value2, "oType");
            return (Criteria) this;
        }

        public Criteria andOTypeNotBetween(String value1, String value2) {
            addCriterion("o_type not between", value1, value2, "oType");
            return (Criteria) this;
        }

        public Criteria andOGradeIsNull() {
            addCriterion("o_grade is null");
            return (Criteria) this;
        }

        public Criteria andOGradeIsNotNull() {
            addCriterion("o_grade is not null");
            return (Criteria) this;
        }

        public Criteria andOGradeEqualTo(String value) {
            addCriterion("o_grade =", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeNotEqualTo(String value) {
            addCriterion("o_grade <>", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeGreaterThan(String value) {
            addCriterion("o_grade >", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeGreaterThanOrEqualTo(String value) {
            addCriterion("o_grade >=", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeLessThan(String value) {
            addCriterion("o_grade <", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeLessThanOrEqualTo(String value) {
            addCriterion("o_grade <=", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeLike(String value) {
            addCriterion("o_grade like", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeNotLike(String value) {
            addCriterion("o_grade not like", value, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeIn(List<String> values) {
            addCriterion("o_grade in", values, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeNotIn(List<String> values) {
            addCriterion("o_grade not in", values, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeBetween(String value1, String value2) {
            addCriterion("o_grade between", value1, value2, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOGradeNotBetween(String value1, String value2) {
            addCriterion("o_grade not between", value1, value2, "oGrade");
            return (Criteria) this;
        }

        public Criteria andOAddressIsNull() {
            addCriterion("o_address is null");
            return (Criteria) this;
        }

        public Criteria andOAddressIsNotNull() {
            addCriterion("o_address is not null");
            return (Criteria) this;
        }

        public Criteria andOAddressEqualTo(String value) {
            addCriterion("o_address =", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressNotEqualTo(String value) {
            addCriterion("o_address <>", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressGreaterThan(String value) {
            addCriterion("o_address >", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressGreaterThanOrEqualTo(String value) {
            addCriterion("o_address >=", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressLessThan(String value) {
            addCriterion("o_address <", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressLessThanOrEqualTo(String value) {
            addCriterion("o_address <=", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressLike(String value) {
            addCriterion("o_address like", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressNotLike(String value) {
            addCriterion("o_address not like", value, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressIn(List<String> values) {
            addCriterion("o_address in", values, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressNotIn(List<String> values) {
            addCriterion("o_address not in", values, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressBetween(String value1, String value2) {
            addCriterion("o_address between", value1, value2, "oAddress");
            return (Criteria) this;
        }

        public Criteria andOAddressNotBetween(String value1, String value2) {
            addCriterion("o_address not between", value1, value2, "oAddress");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNull() {
            addCriterion("zip_code is null");
            return (Criteria) this;
        }

        public Criteria andZipCodeIsNotNull() {
            addCriterion("zip_code is not null");
            return (Criteria) this;
        }

        public Criteria andZipCodeEqualTo(String value) {
            addCriterion("zip_code =", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotEqualTo(String value) {
            addCriterion("zip_code <>", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThan(String value) {
            addCriterion("zip_code >", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeGreaterThanOrEqualTo(String value) {
            addCriterion("zip_code >=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThan(String value) {
            addCriterion("zip_code <", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLessThanOrEqualTo(String value) {
            addCriterion("zip_code <=", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeLike(String value) {
            addCriterion("zip_code like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotLike(String value) {
            addCriterion("zip_code not like", value, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeIn(List<String> values) {
            addCriterion("zip_code in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotIn(List<String> values) {
            addCriterion("zip_code not in", values, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeBetween(String value1, String value2) {
            addCriterion("zip_code between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andZipCodeNotBetween(String value1, String value2) {
            addCriterion("zip_code not between", value1, value2, "zipCode");
            return (Criteria) this;
        }

        public Criteria andMasterIsNull() {
            addCriterion("master is null");
            return (Criteria) this;
        }

        public Criteria andMasterIsNotNull() {
            addCriterion("master is not null");
            return (Criteria) this;
        }

        public Criteria andMasterEqualTo(String value) {
            addCriterion("master =", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotEqualTo(String value) {
            addCriterion("master <>", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterGreaterThan(String value) {
            addCriterion("master >", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterGreaterThanOrEqualTo(String value) {
            addCriterion("master >=", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLessThan(String value) {
            addCriterion("master <", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLessThanOrEqualTo(String value) {
            addCriterion("master <=", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterLike(String value) {
            addCriterion("master like", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotLike(String value) {
            addCriterion("master not like", value, "master");
            return (Criteria) this;
        }

        public Criteria andMasterIn(List<String> values) {
            addCriterion("master in", values, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotIn(List<String> values) {
            addCriterion("master not in", values, "master");
            return (Criteria) this;
        }

        public Criteria andMasterBetween(String value1, String value2) {
            addCriterion("master between", value1, value2, "master");
            return (Criteria) this;
        }

        public Criteria andMasterNotBetween(String value1, String value2) {
            addCriterion("master not between", value1, value2, "master");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andFaxIsNull() {
            addCriterion("fax is null");
            return (Criteria) this;
        }

        public Criteria andFaxIsNotNull() {
            addCriterion("fax is not null");
            return (Criteria) this;
        }

        public Criteria andFaxEqualTo(String value) {
            addCriterion("fax =", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotEqualTo(String value) {
            addCriterion("fax <>", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThan(String value) {
            addCriterion("fax >", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxGreaterThanOrEqualTo(String value) {
            addCriterion("fax >=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThan(String value) {
            addCriterion("fax <", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLessThanOrEqualTo(String value) {
            addCriterion("fax <=", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxLike(String value) {
            addCriterion("fax like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotLike(String value) {
            addCriterion("fax not like", value, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxIn(List<String> values) {
            addCriterion("fax in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotIn(List<String> values) {
            addCriterion("fax not in", values, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxBetween(String value1, String value2) {
            addCriterion("fax between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andFaxNotBetween(String value1, String value2) {
            addCriterion("fax not between", value1, value2, "fax");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonIsNull() {
            addCriterion("primary_person is null");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonIsNotNull() {
            addCriterion("primary_person is not null");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonEqualTo(String value) {
            addCriterion("primary_person =", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotEqualTo(String value) {
            addCriterion("primary_person <>", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonGreaterThan(String value) {
            addCriterion("primary_person >", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonGreaterThanOrEqualTo(String value) {
            addCriterion("primary_person >=", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonLessThan(String value) {
            addCriterion("primary_person <", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonLessThanOrEqualTo(String value) {
            addCriterion("primary_person <=", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonLike(String value) {
            addCriterion("primary_person like", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotLike(String value) {
            addCriterion("primary_person not like", value, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonIn(List<String> values) {
            addCriterion("primary_person in", values, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotIn(List<String> values) {
            addCriterion("primary_person not in", values, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonBetween(String value1, String value2) {
            addCriterion("primary_person between", value1, value2, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andPrimaryPersonNotBetween(String value1, String value2) {
            addCriterion("primary_person not between", value1, value2, "primaryPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonIsNull() {
            addCriterion("deputy_person is null");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonIsNotNull() {
            addCriterion("deputy_person is not null");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonEqualTo(String value) {
            addCriterion("deputy_person =", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotEqualTo(String value) {
            addCriterion("deputy_person <>", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonGreaterThan(String value) {
            addCriterion("deputy_person >", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonGreaterThanOrEqualTo(String value) {
            addCriterion("deputy_person >=", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonLessThan(String value) {
            addCriterion("deputy_person <", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonLessThanOrEqualTo(String value) {
            addCriterion("deputy_person <=", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonLike(String value) {
            addCriterion("deputy_person like", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotLike(String value) {
            addCriterion("deputy_person not like", value, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonIn(List<String> values) {
            addCriterion("deputy_person in", values, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotIn(List<String> values) {
            addCriterion("deputy_person not in", values, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonBetween(String value1, String value2) {
            addCriterion("deputy_person between", value1, value2, "deputyPerson");
            return (Criteria) this;
        }

        public Criteria andDeputyPersonNotBetween(String value1, String value2) {
            addCriterion("deputy_person not between", value1, value2, "deputyPerson");
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