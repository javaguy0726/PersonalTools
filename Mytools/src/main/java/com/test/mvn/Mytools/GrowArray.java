package com.test.mvn.Mytools;

import java.lang.reflect.Array;

public class GrowArray {
	private static GrowArray instance;
	private Object obj;
	
	private GrowArray(Object object) {
		this.obj=object;
	}

	public static GrowArray getInstance(Object object) {
		if (instance == null) {
			instance = new GrowArray(object);
		}
		return instance;
	}
	
	public Object growArray() {
		Class cls = gotClass();
		if (!cls.isArray())
			return null;
		int newLength = Array.getLength(this.obj) * 11 / 10 + 10;
		Object newArray = Array.newInstance(cls.getComponentType(), newLength);
		System.arraycopy(this.obj, 0, newArray, 0, Array.getLength(this.obj));
		return newArray;
	}
	
	private Class<?> gotClass() {
		return this.obj.getClass();
	}
}
