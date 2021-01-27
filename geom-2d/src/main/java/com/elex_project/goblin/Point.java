/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


import org.jetbrains.annotations.NotNull;

/**
 * 점은 x, y의 좌표이다.
 * Created by Elex on 2016-05-13.
 */
public class Point implements IGeomObject {
	public static final float EPSILON = 0.001f;
	public float x, y;
	private BoundingBox boundingBox;

	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 점을 복제한다.
	 * copy by values
	 *
	 * @param p 점
	 */
	public Point(@NotNull Point p) {
		this.x = p.x;
		this.y = p.y;
	}

	@Override
	public BoundingBox getBoundingBox() {
		if (null==boundingBox) {
			boundingBox = new BoundingBox(x - EPSILON / 2, y - EPSILON / 2, x + EPSILON / 2, y + EPSILON / 2);
		} else {
			boundingBox.set(x - EPSILON / 2, y - EPSILON / 2, x + EPSILON / 2, y + EPSILON / 2);
		}
		return boundingBox;
	}

	@Override
	public void translateBy(float dX, float dY) {
		this.x += dX;
		this.y += dY;
	}

	@Override
	public void translateTo(float x, float y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void translateTo(@NotNull Point to) {
		translateTo(to.x, to.y);
	}

	/**
	 * 점의 회전 이동
	 * https://en.wikipedia.org/wiki/Rotation_matrix
	 *
	 * @param theta 양수이면 시계방향, 음수는 반시계방향
	 * @param x0    회전 중심
	 * @param y0    회전 중심
	 */
	@Override
	public void rotateBy(float theta, float x0, float y0) {

		float dX = x - x0;
		float dY = y - y0;
		double rad = Math.abs(Math.toRadians(theta));
		float cosTheta = (float) Math.cos(rad);
		float sinTheta = (float) Math.sin(rad);

		if (theta >= 0) {
			x = dX * cosTheta + dY * sinTheta;
			y = dY * cosTheta - dX * sinTheta;
		} else {
			x = dX * cosTheta - dY * sinTheta;
			y = dY * cosTheta + dX * sinTheta;
		}

		x += x0;
		y += y0;
	}

	@Override
	public void rotateBy(float theta, @NotNull Point p0) {
		rotateBy(theta, p0.x, p0.y);
	}

	@Override
	public void scaleBy(float scale, float x0, float y0) {
		Vector vector = new Vector(x0, y0, x, y);
		vector.setSize(Math.abs(vector.size()) * scale);
		this.x = vector.getEndPointX();
		this.y = vector.getEndPointY();
	}

	@Override
	public void scaleBy(float scale, @NotNull Point p0) {
		scaleBy(scale, p0.x, p0.y);
	}

	@Override
	public void scaleBy(float scaleX, float scaleY, float x0, float y0) {
		Vector vector = new Vector(x0, y0, x, y);
		Vector vectorX = vector.getVectorX();
		Vector vectorY = vector.getVectorY();
		vectorX.setSize(vectorX.size() * scaleX);
		vectorY.setSize(vectorY.size() * scaleY);
		Point p = vectorX.plus(vectorY).getEndPoint();
		this.x = p.x;
		this.y = p.y;
		//float dX = -1*diffXFrom(x0);
		//float dY = -1*diffYFrom(y0);
		//translateBy(dX * scaleX, dY * scaleY);
	}

	@Override
	public void scaleBy(float scaleX, float scaleY, @NotNull Point p0) {
		scaleBy(scaleX, scaleY, p0.x, p0.y);
	}

	@Override
	public void reflectTo(float x0, float y0) {
		float dX = diffXFrom(x0);
		float dY = diffYFrom(y0);
		translateBy(dX * 2, dY * 2);
	}

	@Override
	public void reflectTo(@NotNull Point p) {
		reflectTo(p.x, p.y);
	}

	@Override
	public void reflectX(float y) {
		reflectTo(this.x, y);
	}

	@Override
	public void reflectY(float x) {
		reflectTo(x, this.y);
	}

	/**
	 * 두 점 사이의 거리
	 *
	 * @param p 다른 점
	 * @return 항상 양수
	 */
	public float distanceFrom(@NotNull final Point p) {
		float dX = distanceXFrom(p.x);
		float dY = distanceYFrom(p.y);
		return (float) Math.sqrt(dX * dX + dY * dY);
	}

	/**
	 * 점과 직선 사이의 거리
	 *
	 * @param line 선분
	 * @return 항상 양수
	 */
	public float distanceFrom(final Line line) {
		// y축에 나란하게 회전시킨 후, x거리를 계산한다.
		Line line1 = new Line(line);
		Point point1 = new Point(this);
		float angle = line.slope();
		line1.rotateBy(-angle, point1.x, point1.y);
		point1.rotateBy(-angle, point1.x, point1.y);

		return point1.distanceXFrom(line1.p1.x);
	}

	public float distanceXFrom(float x1) {
		return Math.abs(diffXFrom(x1));
	}

	public float distanceYFrom(float y1) {
		return Math.abs(diffYFrom(y1));
	}

	/**
	 * 주어진 x에서 현재 x값을 뺀다.
	 *
	 * @param x x
	 * @return x - this.x
	 */
	public float diffXFrom(float x) {
		return x - this.x;
	}

	/**
	 * @param y y
	 * @return y - this.x
	 */
	public float diffYFrom(float y) {
		return y - this.y;
	}

	@Override
	public String toString() {
		return "Point: (" + x + ", " + y + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			return x == ((Point) o).x
					&& y == ((Point) o).y;
		} else if (o instanceof IGeomObject) {
			return getBoundingBox().equals(((IGeomObject) o).getBoundingBox());
		} else {
			return super.equals(o);
		}

	}
}
