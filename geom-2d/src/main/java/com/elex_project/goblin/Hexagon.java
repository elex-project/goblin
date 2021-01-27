/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;


/**
 * 육각형
 * Created by Elex on 2016-05-17.
 */
public class Hexagon extends Polygon {
	public static final byte SHAPE_POINTY_TOPPED = 0;
	public static final byte SHAPE_FLAT_TOPPED = 1;


	/**
	 * 정육각형
	 *
	 * @param center 줌심
	 * @param size   중심점에서 꼭지점 까지의 길이 = 한 변의 길이
	 * @param shape  SHAPE_POINTY_TOPPED 또는 SHAPE_FLAT_TOPPED
	 */
	public Hexagon(Point center, float size, byte shape) {
		super(center, size, 6);
		if (shape == SHAPE_FLAT_TOPPED) {
			this.rotateBy(30, center);
		}
	}

	public Hexagon(Point... points) throws InvalidGeometricParameterException {
		super(points);
		if (points.length != 6) throw new InvalidGeometricParameterException();
	}

	public Hexagon(Hexagon hexagon) {
		super(hexagon);
	}


}
