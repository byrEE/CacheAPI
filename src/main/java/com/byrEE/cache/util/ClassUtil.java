package com.byrEE.cache.util;

public class ClassUtil{

	public Class<?> getWrapperClassType(String className){
		switch(className){
			case "int":
				return Integer.TYPE;
			case "char":
				return Character.TYPE;
			case "long":
				return Long.TYPE;
			case "double":
				return Double.TYPE;
			case "float":
				return Float.TYPE;
			default:
				return null;
		}
	}
}