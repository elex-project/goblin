/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


import org.jetbrains.annotations.NotNull;

/**
 * 선은 두 개의 점으로 구성된다.
 * Created by Elex on 2016-05-13.
 */
public class Line implements IGeomObject {
	public Point p1, p2;
	private BoundingBox boundingBox;

	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}

	public Line(float x1, float y1, float x2, float y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}

	public Line(@NotNull Line line) {
		this(line.p1.x, line.p1.y, line.p2.x, line.p2.y);
	}

	/**
	 * 선분의 길이
	 *
	 * @return 항상 양수
	 */
	public float length() {
		return p1.distanceFrom(p2);
	}

	/**
	 * 기울기
	 * 12시 방향을 0으로 하는 시계방향의 절대 각도
	 *
	 * @return 0 ~ 360
	 */
	public float slope() {
		float dX = p1.distanceXFrom(p2.x);
		float dY = p1.distanceYFrom(p2.y);
		float theta = 0;
		if (p2.x == p1.x && p2.y > p1.y) {
			theta = 0;
		} else if (p2.x > p1.x && p2.y > p1.y) {
			theta = (float) Math.toDegrees(Math.atan(dX / dY));
		} else if (p2.x > p1.x && p2.y == p1.y) {
			theta = 90;
		} else if (p2.x > p1.x && p2.y < p1.y) {
			theta = (float) Math.toDegrees(Math.atan(dY / dX)) + 90;
		} else if (p2.x == p1.x && p2.y < p1.y) {
			theta = 180;
		} else if (p2.x < p1.x && p2.y < p1.y) {
			theta = 270 - (float) Math.toDegrees(Math.atan(dY / dX));
		} else if (p2.x < p1.x && p2.y == p1.y) {
			theta = 270;
		} else if (p2.x < p1.x && p2.y > p1.y) {
			theta = 360 - (float) Math.toDegrees(Math.atan(dX / dY));
		}

		return theta;
	}

	@Override
	public BoundingBox getBoundingBox() {
		if (null==boundingBox) {
			boundingBox = new BoundingBox(Geometry.minX(p1, p2), Geometry.minY(p1, p2), Geometry.maxX(p1, p2), Geometry.maxY(p1, p2));
		} else {
			boundingBox.set(Geometry.minX(p1, p2), Geometry.minY(p1, p2), Geometry.maxX(p1, p2), Geometry.maxY(p1, p2));
		}
		return boundingBox;
	}

	@Override
	public void translateBy(float dX, float dY) {
		p1.translateBy(dX, dY);
		p2.translateBy(dX, dY);
	}

	@Override
	public void translateTo(float x, float y) {
		float dX = x - getBoundingBox().centerX();
		float dY = y - getBoundingBox().centerY();
		translateBy(dX, dY);
	}

	@Override
	public void translateTo(@NotNull Point p) {
		translateTo(p.x, p.y);
	}

	@Override
	public void rotateBy(float theta, float x0, float y0) {
		p1.rotateBy(theta, x0, y0);
		p2.rotateBy(theta, x0, y0);
	}

	@Override
	public void rotateBy(float theta, @NotNull Point p0) {
		rotateBy(theta, p0.x, p0.y);
	}

	@Override
	public void scaleBy(float scale, float x0, float y0) {
		scaleBy(scale, scale, x0, y0);
	}

	@Override
	public void scaleBy(float dScale, @NotNull Point p0) {
		scaleBy(dScale, p0.x, p0.y);
	}

	@Override
	public void scaleBy(float scaleX, float scaleY, float x0, float y0) {
		p1.scaleBy(scaleX, scaleY, x0, y0);
		p2.scaleBy(scaleX, scaleY, x0, y0);
	}

	@Override
	public void scaleBy(float scaleX, float scaleY, @NotNull Point p0) {
		scaleBy(scaleX, scaleY, p0.x, p0.y);
	}

	@Override
	public void reflectTo(float x0, float y0) {
		p1.reflectTo(x0, y0);
		p2.reflectTo(x0, y0);
	}

	@Override
	public void reflectTo(@NotNull Point p) {
		reflectTo(p.x, p.y);
	}

	@Override
	public void reflectX(float y) {
		p1.reflectX(y);
		p2.reflectX(y);
	}

	@Override
	public void reflectY(float x) {
		p1.reflectY(x);
		p2.reflectY(x);
	}

	/**
	 * 선분의 분점
	 * m*n&gt;0이면 내분점, p1~p -&gt; m , p~p2 -&gt; n
	 * m*n&lt;0이면 외분점, p1~p -&gt; m, p2~p -&gt;n
	 * 주의! m-n이 0이 되도록 하지 마시오.
	 *
	 * @param m 시점에서 분점까지의 비율
	 * @param n 분점에서 종점까지의 비율
	 * @return 분점
	 */
	public Point getMidPoint(float m, float n) {
		return new Point(
				(n * p1.x + m * p2.x) / (m + n),
				(n * p1.y + m * p2.y) / (m + n)
		);
	}

	public float minX() {
		return Geometry.minX(p1, p2);
	}

	public float minY() {
		return Geometry.minY(p1, p2);
	}

	public float maxX() {
		return Geometry.maxX(p1, p2);
	}

	public float maxY() {
		return Geometry.maxY(p1, p2);
	}

	@Override
	public String toString() {
		return "Line: " + p1.toString() + "--" + p2.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Line) {
			return p1.equals(((Line) o).p1)
					&& p2.equals(((Line) o).p2);
		} else if (o instanceof IGeomObject) {
			return getBoundingBox().equals(((IGeomObject) o).getBoundingBox());
		} else {
			return super.equals(o);
		}

	}
}
