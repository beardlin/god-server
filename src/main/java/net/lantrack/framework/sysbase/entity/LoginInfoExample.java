package net.lantrack.framework.sysbase.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoginInfoExample() {
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

        public Criteria andLoginNameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("login_name =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("login_name >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("login_name <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("login_name like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("login_name not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("login_name in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenIsNull() {
            addCriterion("if_forbidden is null");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenIsNotNull() {
            addCriterion("if_forbidden is not null");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenEqualTo(String value) {
            addCriterion("if_forbidden =", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenNotEqualTo(String value) {
            addCriterion("if_forbidden <>", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenGreaterThan(String value) {
            addCriterion("if_forbidden >", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenGreaterThanOrEqualTo(String value) {
            addCriterion("if_forbidden >=", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenLessThan(String value) {
            addCriterion("if_forbidden <", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenLessThanOrEqualTo(String value) {
            addCriterion("if_forbidden <=", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenLike(String value) {
            addCriterion("if_forbidden like", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenNotLike(String value) {
            addCriterion("if_forbidden not like", value, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenIn(List<String> values) {
            addCriterion("if_forbidden in", values, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenNotIn(List<String> values) {
            addCriterion("if_forbidden not in", values, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenBetween(String value1, String value2) {
            addCriterion("if_forbidden between", value1, value2, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfForbiddenNotBetween(String value1, String value2) {
            addCriterion("if_forbidden not between", value1, value2, "ifForbidden");
            return (Criteria) this;
        }

        public Criteria andIfLockIsNull() {
            addCriterion("if_lock is null");
            return (Criteria) this;
        }

        public Criteria andIfLockIsNotNull() {
            addCriterion("if_lock is not null");
            return (Criteria) this;
        }

        public Criteria andIfLockEqualTo(String value) {
            addCriterion("if_lock =", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockNotEqualTo(String value) {
            addCriterion("if_lock <>", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockGreaterThan(String value) {
            addCriterion("if_lock >", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockGreaterThanOrEqualTo(String value) {
            addCriterion("if_lock >=", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockLessThan(String value) {
            addCriterion("if_lock <", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockLessThanOrEqualTo(String value) {
            addCriterion("if_lock <=", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockLike(String value) {
            addCriterion("if_lock like", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockNotLike(String value) {
            addCriterion("if_lock not like", value, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockIn(List<String> values) {
            addCriterion("if_lock in", values, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockNotIn(List<String> values) {
            addCriterion("if_lock not in", values, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockBetween(String value1, String value2) {
            addCriterion("if_lock between", value1, value2, "ifLock");
            return (Criteria) this;
        }

        public Criteria andIfLockNotBetween(String value1, String value2) {
            addCriterion("if_lock not between", value1, value2, "ifLock");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeIsNull() {
            addCriterion("lock_start_time is null");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeIsNotNull() {
            addCriterion("lock_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeEqualTo(Date value) {
            addCriterion("lock_start_time =", value, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeNotEqualTo(Date value) {
            addCriterion("lock_start_time <>", value, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeGreaterThan(Date value) {
            addCriterion("lock_start_time >", value, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lock_start_time >=", value, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeLessThan(Date value) {
            addCriterion("lock_start_time <", value, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("lock_start_time <=", value, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeIn(List<Date> values) {
            addCriterion("lock_start_time in", values, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeNotIn(List<Date> values) {
            addCriterion("lock_start_time not in", values, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeBetween(Date value1, Date value2) {
            addCriterion("lock_start_time between", value1, value2, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("lock_start_time not between", value1, value2, "lockStartTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeIsNull() {
            addCriterion("lock_end_time is null");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeIsNotNull() {
            addCriterion("lock_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeEqualTo(Date value) {
            addCriterion("lock_end_time =", value, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeNotEqualTo(Date value) {
            addCriterion("lock_end_time <>", value, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeGreaterThan(Date value) {
            addCriterion("lock_end_time >", value, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lock_end_time >=", value, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeLessThan(Date value) {
            addCriterion("lock_end_time <", value, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("lock_end_time <=", value, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeIn(List<Date> values) {
            addCriterion("lock_end_time in", values, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeNotIn(List<Date> values) {
            addCriterion("lock_end_time not in", values, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeBetween(Date value1, Date value2) {
            addCriterion("lock_end_time between", value1, value2, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andLockEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("lock_end_time not between", value1, value2, "lockEndTime");
            return (Criteria) this;
        }

        public Criteria andErrCountIsNull() {
            addCriterion("err_count is null");
            return (Criteria) this;
        }

        public Criteria andErrCountIsNotNull() {
            addCriterion("err_count is not null");
            return (Criteria) this;
        }

        public Criteria andErrCountEqualTo(Integer value) {
            addCriterion("err_count =", value, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountNotEqualTo(Integer value) {
            addCriterion("err_count <>", value, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountGreaterThan(Integer value) {
            addCriterion("err_count >", value, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("err_count >=", value, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountLessThan(Integer value) {
            addCriterion("err_count <", value, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountLessThanOrEqualTo(Integer value) {
            addCriterion("err_count <=", value, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountIn(List<Integer> values) {
            addCriterion("err_count in", values, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountNotIn(List<Integer> values) {
            addCriterion("err_count not in", values, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountBetween(Integer value1, Integer value2) {
            addCriterion("err_count between", value1, value2, "errCount");
            return (Criteria) this;
        }

        public Criteria andErrCountNotBetween(Integer value1, Integer value2) {
            addCriterion("err_count not between", value1, value2, "errCount");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeIsNull() {
            addCriterion("last_suc_time is null");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeIsNotNull() {
            addCriterion("last_suc_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeEqualTo(Date value) {
            addCriterion("last_suc_time =", value, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeNotEqualTo(Date value) {
            addCriterion("last_suc_time <>", value, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeGreaterThan(Date value) {
            addCriterion("last_suc_time >", value, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_suc_time >=", value, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeLessThan(Date value) {
            addCriterion("last_suc_time <", value, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_suc_time <=", value, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeIn(List<Date> values) {
            addCriterion("last_suc_time in", values, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeNotIn(List<Date> values) {
            addCriterion("last_suc_time not in", values, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeBetween(Date value1, Date value2) {
            addCriterion("last_suc_time between", value1, value2, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_suc_time not between", value1, value2, "lastSucTime");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressIsNull() {
            addCriterion("last_suc_address is null");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressIsNotNull() {
            addCriterion("last_suc_address is not null");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressEqualTo(String value) {
            addCriterion("last_suc_address =", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressNotEqualTo(String value) {
            addCriterion("last_suc_address <>", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressGreaterThan(String value) {
            addCriterion("last_suc_address >", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressGreaterThanOrEqualTo(String value) {
            addCriterion("last_suc_address >=", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressLessThan(String value) {
            addCriterion("last_suc_address <", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressLessThanOrEqualTo(String value) {
            addCriterion("last_suc_address <=", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressLike(String value) {
            addCriterion("last_suc_address like", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressNotLike(String value) {
            addCriterion("last_suc_address not like", value, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressIn(List<String> values) {
            addCriterion("last_suc_address in", values, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressNotIn(List<String> values) {
            addCriterion("last_suc_address not in", values, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressBetween(String value1, String value2) {
            addCriterion("last_suc_address between", value1, value2, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucAddressNotBetween(String value1, String value2) {
            addCriterion("last_suc_address not between", value1, value2, "lastSucAddress");
            return (Criteria) this;
        }

        public Criteria andLastSucIpIsNull() {
            addCriterion("last_suc_ip is null");
            return (Criteria) this;
        }

        public Criteria andLastSucIpIsNotNull() {
            addCriterion("last_suc_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastSucIpEqualTo(String value) {
            addCriterion("last_suc_ip =", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpNotEqualTo(String value) {
            addCriterion("last_suc_ip <>", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpGreaterThan(String value) {
            addCriterion("last_suc_ip >", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpGreaterThanOrEqualTo(String value) {
            addCriterion("last_suc_ip >=", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpLessThan(String value) {
            addCriterion("last_suc_ip <", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpLessThanOrEqualTo(String value) {
            addCriterion("last_suc_ip <=", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpLike(String value) {
            addCriterion("last_suc_ip like", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpNotLike(String value) {
            addCriterion("last_suc_ip not like", value, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpIn(List<String> values) {
            addCriterion("last_suc_ip in", values, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpNotIn(List<String> values) {
            addCriterion("last_suc_ip not in", values, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpBetween(String value1, String value2) {
            addCriterion("last_suc_ip between", value1, value2, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastSucIpNotBetween(String value1, String value2) {
            addCriterion("last_suc_ip not between", value1, value2, "lastSucIp");
            return (Criteria) this;
        }

        public Criteria andLastDeviceIsNull() {
            addCriterion("last_device is null");
            return (Criteria) this;
        }

        public Criteria andLastDeviceIsNotNull() {
            addCriterion("last_device is not null");
            return (Criteria) this;
        }

        public Criteria andLastDeviceEqualTo(String value) {
            addCriterion("last_device =", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceNotEqualTo(String value) {
            addCriterion("last_device <>", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceGreaterThan(String value) {
            addCriterion("last_device >", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceGreaterThanOrEqualTo(String value) {
            addCriterion("last_device >=", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceLessThan(String value) {
            addCriterion("last_device <", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceLessThanOrEqualTo(String value) {
            addCriterion("last_device <=", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceLike(String value) {
            addCriterion("last_device like", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceNotLike(String value) {
            addCriterion("last_device not like", value, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceIn(List<String> values) {
            addCriterion("last_device in", values, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceNotIn(List<String> values) {
            addCriterion("last_device not in", values, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceBetween(String value1, String value2) {
            addCriterion("last_device between", value1, value2, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andLastDeviceNotBetween(String value1, String value2) {
            addCriterion("last_device not between", value1, value2, "lastDevice");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeIsNull() {
            addCriterion("sendmsg_time is null");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeIsNotNull() {
            addCriterion("sendmsg_time is not null");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeEqualTo(Date value) {
            addCriterion("sendmsg_time =", value, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeNotEqualTo(Date value) {
            addCriterion("sendmsg_time <>", value, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeGreaterThan(Date value) {
            addCriterion("sendmsg_time >", value, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sendmsg_time >=", value, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeLessThan(Date value) {
            addCriterion("sendmsg_time <", value, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeLessThanOrEqualTo(Date value) {
            addCriterion("sendmsg_time <=", value, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeIn(List<Date> values) {
            addCriterion("sendmsg_time in", values, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeNotIn(List<Date> values) {
            addCriterion("sendmsg_time not in", values, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeBetween(Date value1, Date value2) {
            addCriterion("sendmsg_time between", value1, value2, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andSendmsgTimeNotBetween(Date value1, Date value2) {
            addCriterion("sendmsg_time not between", value1, value2, "sendmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeIsNull() {
            addCriterion("endmsg_time is null");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeIsNotNull() {
            addCriterion("endmsg_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeEqualTo(Date value) {
            addCriterion("endmsg_time =", value, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeNotEqualTo(Date value) {
            addCriterion("endmsg_time <>", value, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeGreaterThan(Date value) {
            addCriterion("endmsg_time >", value, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endmsg_time >=", value, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeLessThan(Date value) {
            addCriterion("endmsg_time <", value, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeLessThanOrEqualTo(Date value) {
            addCriterion("endmsg_time <=", value, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeIn(List<Date> values) {
            addCriterion("endmsg_time in", values, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeNotIn(List<Date> values) {
            addCriterion("endmsg_time not in", values, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeBetween(Date value1, Date value2) {
            addCriterion("endmsg_time between", value1, value2, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andEndmsgTimeNotBetween(Date value1, Date value2) {
            addCriterion("endmsg_time not between", value1, value2, "endmsgTime");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNull() {
            addCriterion("msg_code is null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIsNotNull() {
            addCriterion("msg_code is not null");
            return (Criteria) this;
        }

        public Criteria andMsgCodeEqualTo(String value) {
            addCriterion("msg_code =", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotEqualTo(String value) {
            addCriterion("msg_code <>", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThan(String value) {
            addCriterion("msg_code >", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("msg_code >=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThan(String value) {
            addCriterion("msg_code <", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLessThanOrEqualTo(String value) {
            addCriterion("msg_code <=", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeLike(String value) {
            addCriterion("msg_code like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotLike(String value) {
            addCriterion("msg_code not like", value, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeIn(List<String> values) {
            addCriterion("msg_code in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotIn(List<String> values) {
            addCriterion("msg_code not in", values, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeBetween(String value1, String value2) {
            addCriterion("msg_code between", value1, value2, "msgCode");
            return (Criteria) this;
        }

        public Criteria andMsgCodeNotBetween(String value1, String value2) {
            addCriterion("msg_code not between", value1, value2, "msgCode");
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