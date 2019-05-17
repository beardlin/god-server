package net.lantrack.framework.sysbase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysFileExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysFileExample() {
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

        public Criteria andNewNameIsNull() {
            addCriterion("new_name is null");
            return (Criteria) this;
        }

        public Criteria andNewNameIsNotNull() {
            addCriterion("new_name is not null");
            return (Criteria) this;
        }

        public Criteria andNewNameEqualTo(String value) {
            addCriterion("new_name =", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotEqualTo(String value) {
            addCriterion("new_name <>", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameGreaterThan(String value) {
            addCriterion("new_name >", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameGreaterThanOrEqualTo(String value) {
            addCriterion("new_name >=", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameLessThan(String value) {
            addCriterion("new_name <", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameLessThanOrEqualTo(String value) {
            addCriterion("new_name <=", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameLike(String value) {
            addCriterion("new_name like", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotLike(String value) {
            addCriterion("new_name not like", value, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameIn(List<String> values) {
            addCriterion("new_name in", values, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotIn(List<String> values) {
            addCriterion("new_name not in", values, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameBetween(String value1, String value2) {
            addCriterion("new_name between", value1, value2, "newName");
            return (Criteria) this;
        }

        public Criteria andNewNameNotBetween(String value1, String value2) {
            addCriterion("new_name not between", value1, value2, "newName");
            return (Criteria) this;
        }

        public Criteria andOldNameIsNull() {
            addCriterion("old_name is null");
            return (Criteria) this;
        }

        public Criteria andOldNameIsNotNull() {
            addCriterion("old_name is not null");
            return (Criteria) this;
        }

        public Criteria andOldNameEqualTo(String value) {
            addCriterion("old_name =", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameNotEqualTo(String value) {
            addCriterion("old_name <>", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameGreaterThan(String value) {
            addCriterion("old_name >", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameGreaterThanOrEqualTo(String value) {
            addCriterion("old_name >=", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameLessThan(String value) {
            addCriterion("old_name <", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameLessThanOrEqualTo(String value) {
            addCriterion("old_name <=", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameLike(String value) {
            addCriterion("old_name like", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameNotLike(String value) {
            addCriterion("old_name not like", value, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameIn(List<String> values) {
            addCriterion("old_name in", values, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameNotIn(List<String> values) {
            addCriterion("old_name not in", values, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameBetween(String value1, String value2) {
            addCriterion("old_name between", value1, value2, "oldName");
            return (Criteria) this;
        }

        public Criteria andOldNameNotBetween(String value1, String value2) {
            addCriterion("old_name not between", value1, value2, "oldName");
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

        public Criteria andPIdEqualTo(Integer value) {
            addCriterion("p_id =", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotEqualTo(Integer value) {
            addCriterion("p_id <>", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThan(Integer value) {
            addCriterion("p_id >", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("p_id >=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThan(Integer value) {
            addCriterion("p_id <", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdLessThanOrEqualTo(Integer value) {
            addCriterion("p_id <=", value, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdIn(List<Integer> values) {
            addCriterion("p_id in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotIn(List<Integer> values) {
            addCriterion("p_id not in", values, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdBetween(Integer value1, Integer value2) {
            addCriterion("p_id between", value1, value2, "pId");
            return (Criteria) this;
        }

        public Criteria andPIdNotBetween(Integer value1, Integer value2) {
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

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(String value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(String value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(String value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(String value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(String value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(String value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLike(String value) {
            addCriterion("file_type like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotLike(String value) {
            addCriterion("file_type not like", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<String> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<String> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(String value1, String value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(String value1, String value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andIfDirectIsNull() {
            addCriterion("if_direct is null");
            return (Criteria) this;
        }

        public Criteria andIfDirectIsNotNull() {
            addCriterion("if_direct is not null");
            return (Criteria) this;
        }

        public Criteria andIfDirectEqualTo(Integer value) {
            addCriterion("if_direct =", value, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectNotEqualTo(Integer value) {
            addCriterion("if_direct <>", value, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectGreaterThan(Integer value) {
            addCriterion("if_direct >", value, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectGreaterThanOrEqualTo(Integer value) {
            addCriterion("if_direct >=", value, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectLessThan(Integer value) {
            addCriterion("if_direct <", value, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectLessThanOrEqualTo(Integer value) {
            addCriterion("if_direct <=", value, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectIn(List<Integer> values) {
            addCriterion("if_direct in", values, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectNotIn(List<Integer> values) {
            addCriterion("if_direct not in", values, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectBetween(Integer value1, Integer value2) {
            addCriterion("if_direct between", value1, value2, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andIfDirectNotBetween(Integer value1, Integer value2) {
            addCriterion("if_direct not between", value1, value2, "ifDirect");
            return (Criteria) this;
        }

        public Criteria andDirectLevelIsNull() {
            addCriterion("direct_level is null");
            return (Criteria) this;
        }

        public Criteria andDirectLevelIsNotNull() {
            addCriterion("direct_level is not null");
            return (Criteria) this;
        }

        public Criteria andDirectLevelEqualTo(Integer value) {
            addCriterion("direct_level =", value, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelNotEqualTo(Integer value) {
            addCriterion("direct_level <>", value, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelGreaterThan(Integer value) {
            addCriterion("direct_level >", value, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("direct_level >=", value, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelLessThan(Integer value) {
            addCriterion("direct_level <", value, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelLessThanOrEqualTo(Integer value) {
            addCriterion("direct_level <=", value, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelIn(List<Integer> values) {
            addCriterion("direct_level in", values, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelNotIn(List<Integer> values) {
            addCriterion("direct_level not in", values, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelBetween(Integer value1, Integer value2) {
            addCriterion("direct_level between", value1, value2, "directLevel");
            return (Criteria) this;
        }

        public Criteria andDirectLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("direct_level not between", value1, value2, "directLevel");
            return (Criteria) this;
        }

        public Criteria andTargetIsNull() {
            addCriterion("target is null");
            return (Criteria) this;
        }

        public Criteria andTargetIsNotNull() {
            addCriterion("target is not null");
            return (Criteria) this;
        }

        public Criteria andTargetEqualTo(String value) {
            addCriterion("target =", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotEqualTo(String value) {
            addCriterion("target <>", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThan(String value) {
            addCriterion("target >", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetGreaterThanOrEqualTo(String value) {
            addCriterion("target >=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThan(String value) {
            addCriterion("target <", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLessThanOrEqualTo(String value) {
            addCriterion("target <=", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetLike(String value) {
            addCriterion("target like", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotLike(String value) {
            addCriterion("target not like", value, "target");
            return (Criteria) this;
        }

        public Criteria andTargetIn(List<String> values) {
            addCriterion("target in", values, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotIn(List<String> values) {
            addCriterion("target not in", values, "target");
            return (Criteria) this;
        }

        public Criteria andTargetBetween(String value1, String value2) {
            addCriterion("target between", value1, value2, "target");
            return (Criteria) this;
        }

        public Criteria andTargetNotBetween(String value1, String value2) {
            addCriterion("target not between", value1, value2, "target");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNull() {
            addCriterion("is_show is null");
            return (Criteria) this;
        }

        public Criteria andIsShowIsNotNull() {
            addCriterion("is_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowEqualTo(Integer value) {
            addCriterion("is_show =", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotEqualTo(Integer value) {
            addCriterion("is_show <>", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThan(Integer value) {
            addCriterion("is_show >", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_show >=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThan(Integer value) {
            addCriterion("is_show <", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowLessThanOrEqualTo(Integer value) {
            addCriterion("is_show <=", value, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowIn(List<Integer> values) {
            addCriterion("is_show in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotIn(List<Integer> values) {
            addCriterion("is_show not in", values, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowBetween(Integer value1, Integer value2) {
            addCriterion("is_show between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andIsShowNotBetween(Integer value1, Integer value2) {
            addCriterion("is_show not between", value1, value2, "isShow");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadIsNull() {
            addCriterion("allow_download is null");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadIsNotNull() {
            addCriterion("allow_download is not null");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadEqualTo(Integer value) {
            addCriterion("allow_download =", value, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadNotEqualTo(Integer value) {
            addCriterion("allow_download <>", value, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadGreaterThan(Integer value) {
            addCriterion("allow_download >", value, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadGreaterThanOrEqualTo(Integer value) {
            addCriterion("allow_download >=", value, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadLessThan(Integer value) {
            addCriterion("allow_download <", value, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadLessThanOrEqualTo(Integer value) {
            addCriterion("allow_download <=", value, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadIn(List<Integer> values) {
            addCriterion("allow_download in", values, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadNotIn(List<Integer> values) {
            addCriterion("allow_download not in", values, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadBetween(Integer value1, Integer value2) {
            addCriterion("allow_download between", value1, value2, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andAllowDownloadNotBetween(Integer value1, Integer value2) {
            addCriterion("allow_download not between", value1, value2, "allowDownload");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNull() {
            addCriterion("file_url is null");
            return (Criteria) this;
        }

        public Criteria andFileUrlIsNotNull() {
            addCriterion("file_url is not null");
            return (Criteria) this;
        }

        public Criteria andFileUrlEqualTo(String value) {
            addCriterion("file_url =", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotEqualTo(String value) {
            addCriterion("file_url <>", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThan(String value) {
            addCriterion("file_url >", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlGreaterThanOrEqualTo(String value) {
            addCriterion("file_url >=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThan(String value) {
            addCriterion("file_url <", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLessThanOrEqualTo(String value) {
            addCriterion("file_url <=", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlLike(String value) {
            addCriterion("file_url like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotLike(String value) {
            addCriterion("file_url not like", value, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlIn(List<String> values) {
            addCriterion("file_url in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotIn(List<String> values) {
            addCriterion("file_url not in", values, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlBetween(String value1, String value2) {
            addCriterion("file_url between", value1, value2, "fileUrl");
            return (Criteria) this;
        }

        public Criteria andFileUrlNotBetween(String value1, String value2) {
            addCriterion("file_url not between", value1, value2, "fileUrl");
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

        public Criteria andStand4IsNull() {
            addCriterion("stand4 is null");
            return (Criteria) this;
        }

        public Criteria andStand4IsNotNull() {
            addCriterion("stand4 is not null");
            return (Criteria) this;
        }

        public Criteria andStand4EqualTo(String value) {
            addCriterion("stand4 =", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4NotEqualTo(String value) {
            addCriterion("stand4 <>", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4GreaterThan(String value) {
            addCriterion("stand4 >", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4GreaterThanOrEqualTo(String value) {
            addCriterion("stand4 >=", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4LessThan(String value) {
            addCriterion("stand4 <", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4LessThanOrEqualTo(String value) {
            addCriterion("stand4 <=", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4Like(String value) {
            addCriterion("stand4 like", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4NotLike(String value) {
            addCriterion("stand4 not like", value, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4In(List<String> values) {
            addCriterion("stand4 in", values, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4NotIn(List<String> values) {
            addCriterion("stand4 not in", values, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4Between(String value1, String value2) {
            addCriterion("stand4 between", value1, value2, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand4NotBetween(String value1, String value2) {
            addCriterion("stand4 not between", value1, value2, "stand4");
            return (Criteria) this;
        }

        public Criteria andStand5IsNull() {
            addCriterion("stand5 is null");
            return (Criteria) this;
        }

        public Criteria andStand5IsNotNull() {
            addCriterion("stand5 is not null");
            return (Criteria) this;
        }

        public Criteria andStand5EqualTo(String value) {
            addCriterion("stand5 =", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5NotEqualTo(String value) {
            addCriterion("stand5 <>", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5GreaterThan(String value) {
            addCriterion("stand5 >", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5GreaterThanOrEqualTo(String value) {
            addCriterion("stand5 >=", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5LessThan(String value) {
            addCriterion("stand5 <", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5LessThanOrEqualTo(String value) {
            addCriterion("stand5 <=", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5Like(String value) {
            addCriterion("stand5 like", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5NotLike(String value) {
            addCriterion("stand5 not like", value, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5In(List<String> values) {
            addCriterion("stand5 in", values, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5NotIn(List<String> values) {
            addCriterion("stand5 not in", values, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5Between(String value1, String value2) {
            addCriterion("stand5 between", value1, value2, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand5NotBetween(String value1, String value2) {
            addCriterion("stand5 not between", value1, value2, "stand5");
            return (Criteria) this;
        }

        public Criteria andStand6IsNull() {
            addCriterion("stand6 is null");
            return (Criteria) this;
        }

        public Criteria andStand6IsNotNull() {
            addCriterion("stand6 is not null");
            return (Criteria) this;
        }

        public Criteria andStand6EqualTo(String value) {
            addCriterion("stand6 =", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6NotEqualTo(String value) {
            addCriterion("stand6 <>", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6GreaterThan(String value) {
            addCriterion("stand6 >", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6GreaterThanOrEqualTo(String value) {
            addCriterion("stand6 >=", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6LessThan(String value) {
            addCriterion("stand6 <", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6LessThanOrEqualTo(String value) {
            addCriterion("stand6 <=", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6Like(String value) {
            addCriterion("stand6 like", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6NotLike(String value) {
            addCriterion("stand6 not like", value, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6In(List<String> values) {
            addCriterion("stand6 in", values, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6NotIn(List<String> values) {
            addCriterion("stand6 not in", values, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6Between(String value1, String value2) {
            addCriterion("stand6 between", value1, value2, "stand6");
            return (Criteria) this;
        }

        public Criteria andStand6NotBetween(String value1, String value2) {
            addCriterion("stand6 not between", value1, value2, "stand6");
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