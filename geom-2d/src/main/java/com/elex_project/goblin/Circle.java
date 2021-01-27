/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


import org.jetbrains.annotations.NotNull;

/**
 * 원은 중심점과 반지름으로 정의된다.
 * Created by Elex on 2016-05-13.
 */
public class Circle implements IGeomObject {
	public Point center;
	public float radius;
	private BoundingBox boundingBox;

	public Circle(Point center, float radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(float centerX, float centerY, float radius) {
		this.center = new Point(centerX, centerY);
		this.radius = radius;
	}

	public Circle(@NotNull Circle circle) {
		this(circle.center.x, circle.center.y, circle.radius);
	}

	@Override
	public BoundingBox getBoundingBox() {
		if (null==boundingBox) {
			boundingBox = new BoundingBox(center.x - radius, center.y - radius, center.x + radius, center.y + radius);
		} else {
			boundingBox.set(center.x - radius, center.y - radius, center.x + radius, center.y + radius);
		}
		return boundingBox;
	}

	/**
	 * 두 원의 중심점 사이의 거리
	 *
	 * @param another 원
	 * @return 항상 양수
	 */
	public float distanceFrom(@NotNull Circle another) {
		return distanceFrom(another.center);
	}

	public float distanceFrom(Point point) {
		return center.distanceFrom(point);
	}

	@Override
	public void translateBy(float dX, float dY) {
		center.translateBy(dX, dY);
	}

	@Override
	public void translateTo(float x, float y) {
		center.translateTo(x, y);
	}

	@Override
	public void translateTo(@NotNull Point p) {
		translateTo(p.x, p.y);
	}

	@Override
	public void rotateBy(float theta, float x0, float y0) {
		center.rotateBy(theta, x0, y0);
	}

	@Override
	public void rotateBy(float theta, @NotNull Point p0) {
		rotateBy(theta, p0.x, p0.y);
	}

	@Override
	public void scaleBy(float scale, float x0, float y0) {
		center.scaleBy(scale, x0, y0);
		radius *= scale;
	}

	@Override
	public void scaleBy(float dScale, @NotNull Point p0) {
		scaleBy(dScale, p0.x, p0.y);
	}

	/**
	 * 둘 중 큰 값을 사용한다.
	 * 타원이 아니라, 원이다. 웬만하면 쓰지말자.
	 *
	 * @param scaleX 배율
	 * @param scaleY 배율
	 */
	@Override
	@Deprecated
	public void scaleBy(float scaleX, float scaleY, float x0, float y0) {
		scaleBy(scaleX > scaleY ? scaleX : scaleY, x0, y0);
	}

	@Override
	@Deprecated
	public void scaleBy(float scaleX, float scaleY, @NotNull Point p0) {
		scaleBy(scaleX, scaleY, p0.x, p0.y);
	}

	@Override
	public void reflectTo(float x0, float y0) {
		center.reflectTo(x0, y0);
	}

	@Override
	public void reflectTo(@NotNull Point p) {
		reflectTo(p.x, p.y);
	}

	@Override
	public void reflectX(float y) {
		center.reflectX(y);
	}

	@Override
	public void reflectY(float x) {
		center.reflectY(x);
	}

	@Override
	public String toString() {
		return "Circle: " + center.toString() + "@R" + radius;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Circle) {
			return center.equals(((Circle) o).center)
					&& radius == ((Circle) o).radius;
		} else if (o instanceof IGeomObject) {
			return getBoundingBox().equals(((IGeomObject) o).getBoundingBox());
		} else {
			return super.equals(o);
		}

	}
}
