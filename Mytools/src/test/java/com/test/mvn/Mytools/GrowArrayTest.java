package com.test.mvn.Mytools;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GrowArrayTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		int[] a = { 1, 2, 3,5,6,7,8,0 };
		GrowArray grow =GrowArray.getInstance(a);
		int[] newArray = (int[])grow.growArray();
		System.out.println(newArray.length);
	}

}
