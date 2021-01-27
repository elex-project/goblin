package com.elex_project.goblin;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PointTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void getBoundingBox() throws Exception {

	}

	@Test
	public void translateBy() throws Exception {
		Point p = new Point(0, 0);
		p.translateBy(5, 5);
		assertEquals(new Point(5, 5), p);

		p.translateBy(-10, 0);
		assertEquals(new Point(-5, 5), p);
	}

	@Test
	public void translateTo() throws Exception {
		Point p = new Point(0, 0);
		p.translateTo(5, 5);
		assertEquals(new Point(5, 5), p);

		p.translateTo(-10, 0);
		assertEquals(new Point(-10, 0), p);
	}

	@Test
	public void rotateBy() throws Exception {
		Point p = new Point(0, 0);
		p.rotateBy(90, 10, 0);
		assertEquals(new Point(10, 10), p);

		p.rotateBy(-45, 0, 0);
		assertEquals(new Point(0, (float) (Math.sqrt(2) * 10)), p);
	}

	@Test
	public void scaleBy() throws Exception {
		Point p = new Point(1, 1);
		p.scaleBy(10, 0, 0);
		assertEquals(new Point(10, 10), p);

		p.scaleBy(-10, 10, 10);
		assertEquals(new Point(10, 10), p);
	}


	@Test
	public void reflectTo() throws Exception {
		Point p = new Point(1, 1);
		p.reflectTo(0, 0);
		assertEquals(new Point(-1, -1), p);

		p.reflectTo(0, -1);
		assertEquals(new Point(1, -1), p);
	}

	@Test
	public void distanceFrom() throws Exception {
		Point p = new Point(0, 0);
		float d = p.distanceFrom(new Point(10, 10));
		assertEquals(10 * Math.sqrt(2), d, 0.000001);
	}


}
