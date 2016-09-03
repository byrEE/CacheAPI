package com.byrEE.cache.util;

public class ByteUtil{
	/**
	 * int to 4 bytes
	 * @param  i [int]
	 * @return   
	 */
	public byte[] intToByte4(Integer num){
		byte[] result=new byte[4];
		result[0]=(byte)(num & 0xff);
		result[1]=(byte)((num>>8) & 0xff);
		result[2]=(byte)((num>>16) & 0xff);
		result[3]=(byte)((num>>24) & 0xff);

		return result;
	}

	/**
	 * char to 2 bytes
	 * @param  ch [char]
	 * @return    
	 */
	public byte[] charToByte2(Character ch){
		byte[] result=new byte[2];
		result[0]=(byte)(ch & 0xff);
		result[1]=(byte)((ch>>8) & 0xff);

		return result;
	}

	/**
	 * long to 8 bytes
	 * @param  num [long]
	 * @return     
	 */
	public byte[] longToByte8(Long num){
		byte[] result=new byte[8];
		result[0]=(byte)(num & 0xff);
		result[1]=(byte)((num>>8) & 0xff);
		result[2]=(byte)((num>>16) & 0xff);
		result[3]=(byte)((num>>24) & 0xff);
		result[4]=(byte)((num>>32) & 0xff);
		result[5]=(byte)((num>>40) & 0xff);
		result[6]=(byte)((num>>48) & 0xff);
		result[7]=(byte)((num>>56) & 0xff);

		return result;
	}

	/**
	 * double to 8 bytes
	 * @param  num [double]
	 * @return     
	 */
	public byte[] doubleToByte8(Double num){
		byte[] result=new byte[8];
		result[0]=(byte)(num & 0xff);
		result[1]=(byte)((num>>8) & 0xff);
		result[2]=(byte)((num>>16) & 0xff);
		result[3]=(byte)((num>>24) & 0xff);
		result[4]=(byte)((num>>32) & 0xff);
		result[5]=(byte)((num>>40) & 0xff);
		result[6]=(byte)((num>>48) & 0xff);
		result[7]=(byte)((num>>56) & 0xff);

		return result;
	}

	/**
	 * float to 4 bytes
	 * @param  num [float]
	 * @return     
	 */
	public byte[] floatToByte4(Float num){
		byte[] result=new byte[4];
		result[0]=(byte)(num & 0xff);
		result[1]=(byte)((num>>8) & 0xff);
		result[2]=(byte)((num>>16) & 0xff);
		result[3]=(byte)((num>>24) & 0xff);

		return result;
	}

	/**
	 * byte4 to int
	 * @param  bytes []
	 * @return       
	 */
	public Integer byte4ToInt(byte[] bytes){
		int result;
		for(int i=bytes.length-1;i>=0;i--){
			result |=(bytes[i] & 0xff);
			if(i!=0)
				result<<=8;
		}
		return result;
	}

	/**
	 * byte2 to char
	 * @param  bytes []
	 * @return       
	 */
	public Character byte2ToChar(byte[] bytes){
		char result;
		for(int i=bytes.length-1;i>=0;i--){
			result |=(bytes[i] & 0xff);
			if(i!=0)
				result<<=8;
		}
		return result;
	}

	/**
	 * byte8 to long
	 * @param  bytes []
	 * @return       
	 */
	public Long byte8ToLong(byte[] bytes){
		long result;
		for(int i=bytes.length-1;i>=0;i--){
			result |=(bytes[i] & 0xff);
			if(i!=0)
				result<<=8;
		}
		return result;
	}

	/**
	 * byte8 to double
	 * @param  bytes []
	 * @return       
	 */
	public Double byte8ToDouble(byte[] bytes){
		double result;
		for(int i=bytes.length-1;i>=0;i--){
			result |=(bytes[i] & 0xff);
			if(i!=0)
				result<<=8;
		}
		return result;
	}

	/**
	 * byte4 to float
	 * @param  bytes []
	 * @return       
	 */
	public Float byte4ToFloat(byte[] bytes){
		float result;
		for(int i=bytes.length-1;i>=0;i--){
			result |=(bytes[i] & 0xff);
			if(i!=0)
				result<<=8;
		}
		return result;
	}
}