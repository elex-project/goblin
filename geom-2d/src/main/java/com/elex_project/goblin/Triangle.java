/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


/**
 * 삼각형
 * Created by Elex on 2016-05-24.
 */
public class Triangle extends Polygon {

	/**
	 * 정삼각형
	 *
	 * @param center 정삼각형의 무게 중심
	 * @param size   중심에서 꼭지점 까지의 길이
	 */
	public Triangle(Point center, float size) {
		super(center, size, 3);
	}

	/**
	 * @param points 삼각형의 꼭지점
	 * @throws InvalidGeometricParameterException 꼭지점이 3개가 아니면 삼각형이 아니다.
	 */
	public Triangle(Point... points) throws InvalidGeometricParameterException {
		super(points);
		if (points.length != 3) throw new InvalidGeometricParameterException();
	}

	public Triangle(Triangle triangle) {
		super(triangle);
	}

	/**
	 * 삼각형의 무게중심
	 *
	 * @return 무게중심
	 */
	public Point getCentroid() {
		return new Point(Geometry.averageX(points), Geometry.averageY(points));
	}

	@Override
	public String toString() {
		return "Triangle: " + points[0] + ", " + points[1] + ", " + points[2];
	}
}
