package com.byrEE.cache.impl;

/**
 * @author byrEE
 */
import org.springframework.util.SerializationUtils;
import com.byrEE.cache.ISerializer;

public class DefaultSerializer<T> implements ISerializer{
	@Override
	public byte[] serialize(T obj){
		return SerializationUtils.serialize(obj);
	}

	@Override
    public T deserialize(byte[] bytes, Class<T> type) {
        return (T)SerializationUtils.deserialize(bytes);
    }
}