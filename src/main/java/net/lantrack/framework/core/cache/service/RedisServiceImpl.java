package net.lantrack.framework.core.cache.service;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import net.lantrack.framework.core.cache.util.JedisUtils;
/**
 * redisImpl      
 * @date 2019年3月19日
 */
//@Service("redisImpl")
@Service
public class RedisServiceImpl implements CacheService {

	
	private String getCacheName(String cacheName) {
		if(StringUtils.isBlank(cacheName)) {
			cacheName = SYS_CACHE_NAME;
		}
		return cacheName;
	}
	
	private String getKey(String cacheName,String key) {
		return getCacheName(cacheName)+":"+key;
	}
	
	
	@Override
	public void save(String cacheName,String key, Object obj) {
		String k = getKey(cacheName,key);
		//将缓存的子集合单独存起来，方便后边使用
		JedisUtils.setSetAdd(getCacheName(cacheName), k);
		JedisUtils.setObject(k, obj, 0);
	}

	@Override
	public void delete(String cacheName,String key) {
		JedisUtils.delObject(getKey(cacheName,key));
	}

	@Override
	public void update(String cacheName,String key, Object obj) {
		String k = getKey(cacheName,key);
		JedisUtils.delObject(k);
		//将缓存的子集合单独存起来，方便后边使用
		JedisUtils.setSetAdd(getCacheName(cacheName), k);
		JedisUtils.setObject(k, obj, 0);
	}

	@Override
	public Object get(String cacheName,String key) {
		return JedisUtils.getObject(getKey(cacheName,key));
	}

	@Override
	public void clear(String cacheName) {
		Set<String> keys = JedisUtils.getSet(cacheName);
		if(keys!=null&&keys.size()>0) {
			String[] array = keys.toArray(new String[keys.size()]);
			JedisUtils.delkeys(array);
		}
		
	}

}
