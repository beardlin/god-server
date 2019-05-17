package net.lantrack.framework.core.service;

import java.util.List;

import net.lantrack.framework.core.entity.MapEntity;

/**
 * 专门用来存放key-value的，一般给下拉，单选，复选框用
 * @date 2019年3月7日
 */
public interface MapEntityService {
	/**
	 * 获取key-value集合
	 * @return
	 * @date 2019年3月7日
	 */
	public List<MapEntity> getSelectMap();
}
