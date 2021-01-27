/*
 * Copyright (c) 2019. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CircleTest {
	private Circle circle;

	@BeforeEach
	public void setup() {
		circle = new Circle(10, 10, 50);
	}

	@Test
	public void boundingTest() {
		Circle circleExpected = new Circle(20, 20, 20);
		//circle.
	}

	@Test
	public void translateBy() {
		circle.translateBy(10, 10);
		Circle circleExpected = new Circle(20, 20, 50);

		assertEquals(circleExpected, circle);
	}
}
