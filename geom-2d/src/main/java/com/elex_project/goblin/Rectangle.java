/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


import org.jetbrains.annotations.NotNull;

/**
 * 사각형
 * Created by Elex on 2016-05-13.
 */
public class Rectangle extends Polygon {

	public Rectangle(float left, float top, float right, float bottom) {
		super(new Point(left, top), new Point(right, top), new Point(right, bottom), new Point(left, bottom));
	}

	public Rectangle(Point... points) {
		super(points);
	}

	public Rectangle(Rectangle rectangle) {
		super(rectangle);
	}

	public Rectangle(@NotNull BoundingBox rect) {
		this(rect.left(), rect.top(), rect.right(), rect.bottom());
	}

	public void set(float left, float top, float right, float bottom) {
		points[0].x = left;
		points[0].y = top;
		points[1].x = right;
		points[1].y = top;
		points[2].x = right;
		points[2].y = bottom;
		points[3].x = left;
		points[3].y = bottom;
	}

	@Override
	public String toString() {
		return "Rectangle: " + points[0] + ", " + points[1] + ", " + points[2] + ", " + points[3];
	}
}
