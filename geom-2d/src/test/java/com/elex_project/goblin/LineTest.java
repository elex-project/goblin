package com.elex_project.goblin;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest {
	@Test
	public void length() throws Exception {
		Line line = new Line(0, 0, 1, 1);

		assertEquals(Math.sqrt(2), line.length(), 0.000001);
	}

	@Test
	public void slope() throws Exception {
		Line line = new Line(0, 0, 1, 1);

		assertEquals(45, line.slope(), 0.000001);

		line.rotateBy(45, 0, 0);
		assertEquals(90, line.slope(), 0.000001);
	}

	@Test
	public void getMidPoint() throws Exception {
		Line line = new Line(0, 0, 10, 10);
		Point p = line.getMidPoint(1, 1);
		assertEquals(new Point(5, 5), p);
	}

}
