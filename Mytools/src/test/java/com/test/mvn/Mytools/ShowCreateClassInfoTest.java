package com.test.mvn.Mytools;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShowCreateClassInfoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		ShowCreateClassInfo show_class=ShowCreateClassInfo.getInstance("java.lang.reflect.Field");
		System.out.println(show_class.showEntireClass());
	}

}
