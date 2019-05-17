package net.lantrack.framework.jms.kafka.topic.entity;

import java.io.Serializable;

public class KafkaTopic implements Serializable {
    private Integer id;
    /**
     * topic名称
     */
    private String topic;
    /**
     * 对应数据处理的全类名
     */
    private String observer;
    /**
     * 备注
     */
    private String remarks;

    private String stand1;

    private String stand2;

    private String stand3;

    private static final long serialVersionUID = 1L;

    public KafkaTopic(Integer id, String topic, String observer, String remarks, String stand1, String stand2, String stand3) {
        this.id = id;
        this.topic = topic;
        this.observer = observer;
        this.remarks = remarks;
        this.stand1 = stand1;
        this.stand2 = stand2;
        this.stand3 = stand3;
    }

    public KafkaTopic() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic == null ? null : topic.trim();
    }

    public String getObserver() {
        return observer==null?"":observer;
    }

    public void setObserver(String observer) {
        this.observer = observer == null ? null : observer.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public String getStand1() {
        return stand1;
    }

    public void setStand1(String stand1) {
        this.stand1 = stand1 == null ? null : stand1.trim();
    }

    public String getStand2() {
        return stand2;
    }

    public void setStand2(String stand2) {
        this.stand2 = stand2 == null ? null : stand2.trim();
    }

    public String getStand3() {
        return stand3;
    }

    public void setStand3(String stand3) {
        this.stand3 = stand3 == null ? null : stand3.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", topic=").append(topic);
        sb.append(", observer=").append(observer);
        sb.append(", remarks=").append(remarks);
        sb.append(", stand1=").append(stand1);
        sb.append(", stand2=").append(stand2);
        sb.append(", stand3=").append(stand3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}