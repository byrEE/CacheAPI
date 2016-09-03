package com.byrEE.cache;

/**
 * @author byrEE
 */

import java.util.List;
import java.util.Map;


public interface ICache{

	/**
	 * cache hit
	 * @param  key <K>
	 * @return     [cache is hitted]
	 */
	<K> boolean contains(K key);

	/**
	 * cache put
	 * @param  key   [<K>]
	 * @param  value [<V>]
	 * @return       [put action successed]
	 */
	<K,V> boolean put(K key,V value);

	/**
	 * cache put with option
	 * @param  key    [<K>]
	 * @param  value  [<V>]
	 * @param  option [<option>]
	 * @return        [put action successed]
	 */
	<K,V> boolean put(K key,V value,Option option);

	/**
	 * get value by key
	 * @param  key  [<K>]
	 * @param  type [<V>]
	 * @return      [return hit key]
	 */
	<K,V> V get(K key,Class<V> type);

	/**
	 * remove value of key
	 * @param  key [<K>]
	 * @return     [remove action successed]
	 */
	<K> boolean remove(K key);

}