/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


import org.jetbrains.annotations.NotNull;

/**
 * 다각형은 여러 개의 점으로 구성된다.
 * Created by Elex on 2016-05-13.
 */
public class Polygon implements IGeomObject {
	protected Point[] points;
	private BoundingBox boundingBox;

	public Polygon(Point... points) {
		this.points = points;
	}

	/**
	 * 정 다각형
	 *
	 * @param center        도형의 중심
	 * @param size          중심에서 꼭지점까지의 길이
	 * @param numOfVertices 꼭지점의 갯수
	 */
	public Polygon(Point center, float size, int numOfVertices) {
		points = new Point[numOfVertices];
		// 이웃하는 두 꼭지점과 중심점 사이의 각도
		float dA = 360.f / numOfVertices;
		double angle;
		for (int i = 0; i < points.length; i++) {
			angle = Math.toRadians(dA * i);
			points[i] = new Point(center.x + size * (float) Math.cos(angle), center.y + size * (float) Math.sin(angle));
		}
	}

	public Polygon(@NotNull Polygon polygon) {
		points = new Point[polygon.points.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = new Point(polygon.points[i]);
		}
	}

	@Override
	public BoundingBox getBoundingBox() {
		if (null==boundingBox) {
			boundingBox = new BoundingBox(minX(), minY(), maxX(), maxY());
		} else {
			boundingBox.set(minX(), minY(), maxX(), maxY());
		}

		return boundingBox;
	}


	public float minX() {
		return Geometry.minX(points);
	}

	public float minY() {
		return Geometry.minY(points);
	}

	public float maxX() {
		return Geometry.maxX(points);
	}

	public float maxY() {
		return Geometry.maxY(points);
	}

	/**
	 * 다각형의 꼭지점
	 *
	 * @return 점 배열
	 */
	public Point[] getVertices() {
		return points;
	}

	/**
	 * 다각형의 변
	 *
	 * @return 선분 배열
	 */
	public Line[] getEdges() {
		Line[] lines = new Line[getVertices().length];
		for (int i = 0; i < points.length; i++) {
			if (i == points.length - 1) {
				lines[i] = new Line(points[i], points[0]);
			} else {
				lines[i] = new Line(points[i], points[i + 1]);
			}

		}
		return lines;
	}

	@Override
	public void translateBy(float dX, float dY) {
		for (Point p : points) {
			p.translateBy(dX, dY);
		}
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
		for (Point p : points) {
			p.rotateBy(theta, x0, y0);
		}
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
		for (Point p : points) {
			p.scaleBy(scaleX, scaleY, x0, y0);
		}
	}

	@Override
	public void scaleBy(float scaleX, float scaleY, @NotNull Point p0) {
		scaleBy(scaleX, scaleY, p0.x, p0.y);
	}

	@Override
	public void reflectTo(float x0, float y0) {
		for (Point p : points) {
			p.reflectTo(x0, y0);
		}
	}

	@Override
	public void reflectTo(@NotNull Point p) {
		reflectTo(p.x, p.y);
	}

	@Override
	public void reflectX(float y) {
		for (Point p : points) {
			p.reflectX(y);
		}
	}

	@Override
	public void reflectY(float x) {
		for (Point p : points) {
			p.reflectY(x);
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Point p : points) {
			stringBuilder.append(p.toString());
			stringBuilder.append(",");
		}
		return "Polygon: " + points.length + "): " + stringBuilder.toString();
	}

	/**
	 * 다각형 끼리의 비교인 경우에는 모든 꼭지점 좌표가 같으면 같다.
	 * 다각형 외의 비교인 경우에는 바운딩 박스가 서로 같은 경우에 같다고 한다.
	 *
	 * @param o another
	 * @return t/f
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Polygon) {
			for (int i = 0; i < points.length; i++) {
				if (!points[i].equals(((Polygon) o).points[i])) return false;
			}
			return true;

		} else if (o instanceof IGeomObject) {
			return getBoundingBox().equals(((IGeomObject) o).getBoundingBox());

		} else {
			return super.equals(o);
		}

	}
}
