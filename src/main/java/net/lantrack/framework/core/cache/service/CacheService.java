package net.lantrack.framework.core.cache.service;


public interface CacheService {
	/**
	 * 系统缓存名称
	 */
	static final String SYS_CACHE_NAME = "lantrack_sys_cache_"; 
	
	void save(String cacheName,String key,Object obj);
	
	void delete(String cacheName,String key);
	
	void update(String cacheName,String key,Object obj);
	
	Object get(String cacheName,String key);
	
	void clear(String cacheName);
	
	

}
