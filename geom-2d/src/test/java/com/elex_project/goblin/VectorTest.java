/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VectorTest {

	@Test
	public void plus() throws Exception {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(1, 0);

		v1.plus(v2);
		assertEquals(2, v1.size(), 0.000001);
		assertEquals(0, v1.direction(), 0.000001);
	}

	@Test
	public void minus() throws Exception {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(2, 0);

		v1.minus(v2);
		assertEquals(1, v1.size(), 0.000001);
		assertEquals(180, v1.direction(), 0.000001);
	}

	@Test
	public void inverse() throws Exception {
		Vector v1 = new Vector(1, 0);
		v1.inverse();
		assertEquals(1, v1.size(), 0.000001);
		assertEquals(180, v1.direction(), 0.000001);
	}

	@Test
	public void innerProduct() throws Exception {
		Vector v1 = new Vector(1, 0);
		Vector v2 = new Vector(1, 90);

		assertEquals(0, v1.innerProduct(v2), 0.000001);
	}


}
