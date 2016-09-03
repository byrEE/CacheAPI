package com.byrEE.cache.impl;

import com.byrEE.cache.ExpireType;
import com.byrEE.cache.ICache;
import com.byrEE.cache.ISerializer;
import com.byrEE.cache.Option;
import com.byrEE.util.ByteUtil;
import com.byrEE.util.ClassUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisCache implements ICache{
	/**
	 * jedis conenct pool
	 */
	private JedisPool jedisPool;	

	/**
	 * serializer
	 */
	private ISerializer serializer;

	/**
	 * option about expiretime or asynchronization
	 */
	private Option option;

	@Override
	public <K> boolean contains(K key){
		if(key==null)
			throw new IllegalArgumentException("key cannot be null");

		try(Jedis jedis=jedisPool.getResource()){
			byte[] kBytes=translateObjToBytes(key);
			return jedis.exists(kBytes);
		}
	}

	@Override
	public <K,V> boolean put(K key,V value){
		return put(key,value,option);
	}

	@Override
	public <K,V> boolean put(K key,V value,Option option){
		if(key==null || value==null)
			throw new IllegalArgumentException("key or value cannot be null");

		try(Jedis jedis=jedisPool.getResource()){
			byte[] kBytes=translateObjToBytes(key);
			byte[] vBytes=translateObjToBytes(value);
			
			String code=jedis.set(kBytes,vBytes);

			setExpire(kBytes,option,jedis);

			return "OK".equals(code);
		}
	}

	@Override
	public <K,V> V get(K key,Class<V> type){
		if(key==null || type==null)
			throw new IllegalArgumentException("key or type cannot be null");

		try(Jedis jedis=jedisPool.getResource()){
			byte[] kBytes=translateObjToBytes(key);
			byte[] vBytes=jedis.get(kBytes);

			if(vBytes==null)
				return null;
			return translateBytesToObj(vBytes,type);

		}

	}

	@Override
	public <K> boolean remove(K key){
		if(key==null)
			throw new IllegalArgumentException("key cannot be null");

		try(Jedis jedis=jedisPool.getResource()){
			byte[] kBytes=translateObjToBytes(key);
			jedis.del(kBytes);

			return true;
		}
	}

	private <T> byte[] translateObjToBytes(T val){
		byte[] valBytes;
		if(val instanceof String)
			valBytes=(String (val)).getBytes();
		else if(val.getClass().isArray() && val.getClass().getComponentType()==Byte.TYPE)
            valBytes = (byte[])val;
		else{
			Class<?> classType=ClassUtil.getWrapperClassType(val.getClass().getSimpleName());
			if(classType!=null){
				if (classType.equals(Integer.TYPE)) {
                    valBytes = ByteUtil.intToByte4((Integer) val);
                } 
                else if (classType.equals(Character.TYPE)) {
                    valBytes = ByteUtil.charToByte2((Character) val);
                } 
                else if (classType.equals(Long.TYPE)) {
                    valBytes = ByteUtil.longToByte8((Long) val);
                } 
                else if (classType.equals(Double.TYPE)) {
                    valBytes = ByteUtil.doubleToByte8((Double) val);
                } 
                else if (classType.equals(Float.TYPE)) {
                    valBytes = ByteUtil.floatToByte4((Float) val);
                }                 
                else {
                	throw new IllegalArgumentException("unsupported value type, classType is:" + classType);
                }
			}
			else{
				valBytes=serializer.serialize(val);
			}
		}
		return valBytes;
	}

	private <T> T translateBytesToObj(bytes[] bytes,Class<T> type){
		Object obj;
		if(type.equals(String.class))
			obj=new String(bytes);
		else{
			Class<?> classType=ClassUtil.getWrapperClassType(type.getSimpleName());
			if(classType!=null){
				if (classType.equals(Integer.TYPE)) {
    	            obj = ByteUtil.byte4ToInt(bytes);
        	    } 
            	else if (classType.equals(Character.TYPE)) {
                	obj = ByteUtil.byte2ToChar(bytes);
            	} 
	            else if (classType.equals(Long.TYPE)) {
    	            obj = ByteUtil.byte8ToLong(bytes);
        	    } 
            	else if (classType.equals(Double.TYPE)) {
                	obj = ByteUtil.byte8ToDouble(bytes);
	            } 
    	        else if (classType.equals(Float.TYPE)) {
     		        obj = ByteUtil.byte4ToFloat(bytes);
            	} 
            	else {
                    throw new IllegalArgumentException("unsupported value type, classType is:" + classType);
            	}
        	}
        	else{
        		obj=serializer.deserialize(bytes,type);
            	
			}
        }
        return (T) obj;

	}

	private void setExpire(byte[] kBytes,Option option,Jedis jedis){
		if(option.getExpireType().equals(ExpireType.SECONDS)){
			int seconds=(int) option.getExpireTime()/1000;
			if(seconds>0){
				jedis.expire(kBytes,seconds);
			}
		}
		else{
			jedis.expireAt(kBytes,option.getExpireTime());
		}
	}

	public void setPool(JedisPool pool) {
        this.pool = pool;
    }

    public void setSerializer(ISerializer serializer) {
        this.serializer = serializer;
    }	

    public void setOption(Option option) {
        this.option = option;
    }
}