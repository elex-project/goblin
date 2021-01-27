/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;

import java.io.Serializable;

/**
 * Created by Elex on 2016-05-13.
 */
public interface IGeomObject extends Serializable {
	/**
	 * 도형의 전체를 포함하는 최소 크기의 사각형
	 * 즉, AABB
	 *
	 * @return 바운딩 박스
	 */
	BoundingBox getBoundingBox();

	/**
	 * 도형의 상대 이동
	 *
	 * @param dX 상대 거리
	 * @param dY 상대 거리
	 */
	void translateBy(float dX, float dY);

	/**
	 * 도형의 절대 이동
	 * 이동시 기준 위치는 바운딩 박스의 중심이다.
	 *
	 * @param x 절대 위치
	 * @param y 절대 위치
	 */
	void translateTo(float x, float y);

	/**
	 * 도형의 절대 이동
	 * 이동시 기준 위치는 바운딩 박스의 중심이다.
	 *
	 * @param p 절대 위치
	 */
	void translateTo(Point p);

	/**
	 * 도형의 회전
	 *
	 * @param theta 회전 각. 양수면 시계방향, 음수면 반시계방향
	 * @param x0    회전 중심.
	 * @param y0    회전 중심
	 */
	void rotateBy(float theta, float x0, float y0);

	/**
	 * 도형의 회전
	 *
	 * @param theta 회전 각. 양수면 시계방향, 음수면 반시계방향
	 * @param p0    회전 중심.
	 */
	void rotateBy(float theta, Point p0);

	/**
	 * 도형의 크기 변환
	 * 중심점은 크기 변환시 고정된 점이다.
	 *
	 * @param scale 배율
	 * @param x0    중심점
	 * @param y0    중심점
	 */
	void scaleBy(float scale, float x0, float y0);

	/**
	 * 도형의 크기 변환
	 * 중심점은 크기 변환시 고정된 점이다.
	 *
	 * @param scale 배율
	 * @param p0    중심점
	 */
	void scaleBy(float scale, Point p0);

	/**
	 * 도형의 크기 변환
	 * 중심점은 크기 변환시 고정된 점이다.
	 *
	 * @param scaleX 배율
	 * @param scaleY 배율
	 * @param x0     중심점
	 * @param y0     중심점
	 */
	void scaleBy(float scaleX, float scaleY, float x0, float y0);

	/**
	 * 도형의 크기 변환
	 * 중심점은 크기 변환시 고정된 점이다.
	 *
	 * @param scaleX 배율
	 * @param scaleY 배율
	 * @param p0     중심점
	 */
	void scaleBy(float scaleX, float scaleY, Point p0);

	/**
	 * 점 대칭
	 *
	 * @param x0 기준점
	 * @param y0 기준점
	 */
	void reflectTo(float x0, float y0);

	/**
	 * 점 대칭
	 *
	 * @param p 기준점
	 */
	void reflectTo(Point p);

	/**
	 * X축에 나란한 축에 대한 대칭
	 *
	 * @param y 기준축
	 */
	void reflectX(float y);

	/**
	 * Y 축에 나란한 축에 대한 대칭
	 *
	 * @param x 기준축
	 */
	void reflectY(float x);
}
