//package net.lantrack.framework.core.cache.service;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//
//import net.lantrack.framework.core.cache.util.CacheUtils;
//
///**
// * ehcacheImpl      
// * @date 2019年3月19日
// */
//@Service("ehcacheImpl")
//public class EhCacheServiceImpl implements CacheService {
//
//	
//	@Override
//	public void save(String cacheName,String key, Object obj) {
//		CacheUtils.put(SYS_CACHE_NAME+key, obj);
//	}
//
//	@Override
//	public void delete(String cacheName,String key) {
//		CacheUtils.remove(SYS_CACHE_NAME+key);
//	}
//
//	@Override
//	public void update(String cacheName,String key, Object obj) {
//		CacheUtils.remove(SYS_CACHE_NAME+key);
//		CacheUtils.put(SYS_CACHE_NAME+key, obj);
//	}
//
//	@Override
//	public Object get(String cacheName,String key) {
//		return CacheUtils.get(SYS_CACHE_NAME+key);
//	}
//	
//	private String getCacheName(String cacheName) {
//		if(StringUtils.isBlank(cacheName)) {
//			cacheName = SYS_CACHE_NAME;
//		}
//		return cacheName;
//	}
//
//}
