package net.lantrack.framework.jms.kafka.topic.dao;

import java.util.List;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopic;
import net.lantrack.framework.jms.kafka.topic.entity.KafkaTopicExample;
import org.apache.ibatis.annotations.Param;

public interface KafkaTopicMapper {
    long countByExample(KafkaTopicExample example);

    int deleteByExample(KafkaTopicExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(KafkaTopic record);

    int insertSelective(KafkaTopic record);

    List<KafkaTopic> selectByExample(KafkaTopicExample example);

    KafkaTopic selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") KafkaTopic record, @Param("example") KafkaTopicExample example);

    int updateByExample(@Param("record") KafkaTopic record, @Param("example") KafkaTopicExample example);

    int updateByPrimaryKeySelective(KafkaTopic record);

    int updateByPrimaryKey(KafkaTopic record);
}