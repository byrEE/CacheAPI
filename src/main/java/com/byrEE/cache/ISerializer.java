package com.byrEE.cache;

/**
 * @author byrEE
 */

public interface ISerializer<T>{
	/**
	 * serialize method
	 * @param  obj [object to be serialized]
	 * @return     [serialized data]
	 */
	byte[] serialize(T obj);

	/**
	 *deserialize method
	 * @param byte[] [bytes to deserialize]
	 * @param type [type of deserialized object]
	 */
	T deserialize(byte[] bytes,Class<T> type);
}