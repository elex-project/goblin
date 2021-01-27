/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * Axis-Aligned Bounding Box
 * Created by Elex on 2016-05-17.
 */
public final class BoundingBox implements Serializable {
	private Rectangle rectangle;

	public BoundingBox(float left, float top, float right, float bottom) {
		rectangle = new Rectangle(left, top, right, bottom);
	}

	public float width() {
		return rectangle.maxX() - rectangle.minX();
	}

	public float height() {
		return rectangle.maxY() - rectangle.minY();
	}

	public float centerX() {
		return rectangle.minX() + width() / 2f;
	}

	public float centerY() {
		return rectangle.minY() + height() / 2f;
	}

	public void offsetTo(float x, float y) {
		rectangle.translateTo(x, y);
	}

	public void scaleTo(float scale, float x, float y) {
		rectangle.scaleBy(scale, x, y);
	}

	public void flipX() {
		flipX(centerY());
	}

	public void flipX(float y) {
		rectangle.reflectX(y);
	}

	public void flipY() {
		flipY(centerX());
	}

	public void flipY(float x) {
		rectangle.reflectY(x);
	}

	public void set(float left, float top, float right, float bottom) {
		rectangle.points[0].x = left;
		rectangle.points[0].y = top;
		rectangle.points[1].x = right;
		rectangle.points[1].y = top;
		rectangle.points[2].x = right;
		rectangle.points[2].y = bottom;
		rectangle.points[3].x = left;
		rectangle.points[3].y = bottom;
	}

	public void set(float width, float height) {
		float dW = width - width();
		float dH = height - height();
		set(left() + dW / 2, top() + dH / 2, right() + dW / 2, bottom() + dH / 2);
	}


	public float left() {
		return rectangle.minX();
	}

	public float right() {
		return rectangle.maxX();
	}

	public float top() {
		return rectangle.maxY();
	}

	public float bottom() {
		return rectangle.minY();
	}


	@Override
	public boolean equals(Object o) {
		if (o instanceof BoundingBox) {
			return rectangle.equals(((BoundingBox) o).rectangle);
		} else {
			return super.equals(o);
		}
	}

	public boolean contains(float x, float y) {
		return x >= left() && x <= right()
				&& y >= bottom() && y < top();
	}

	public boolean contains(@NotNull Point p) {
		return contains(p.x, p.y);
	}

	@NotNull
	@Contract(" -> new")
	public Rectangle toRectangle() {
		return new Rectangle(rectangle);
	}
}
