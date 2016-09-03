package com.byrEE.cache;

/**
 * @author byrEE
 */

public class Option{
	/**
	 * expire time
	 */
	private long expireTime;

	/**
	 * expire type
	 */
	private ExpireType expireType;

	/**
	 * synchronize or asynchronize mode: syn default
	 */
	private boolean async;

	public Option(){
		expireType=ExpireType.SECONDS;
	}

	public long getExpireTime(){
		return expireTime;
	}

	public void setExpireTime(long expireTime){
		this.expireTime=expireTime;
	}

	public boolean isAsync(){
		return async;
	}

	public void setAsync(boolean async){
		this.async=async;
	}

	public ExpireType getExpireType(){
		return expireType;
	}

	public void setExpireType(ExpireType expireType){
		this.expireType=expireType;
	}
}