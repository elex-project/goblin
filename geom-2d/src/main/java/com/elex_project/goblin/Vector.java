/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

/**
 * 벡터는 크기와 방향을 가지고 있다.
 * 시점과 종점으로 구성된다.
 * 벡터의 방향은 12시 방향을 0으로 하며, 시계 방향으로 0~360의 값으로 나타낸다.
 * Created by Elex on 2016-05-15.
 */
public final class Vector implements Serializable {
	/**
	 * X 방향 단위 벡터
	 */
	public static final Vector UNIT_VECTOR_X = new Vector(0, 0, 1, 0);
	/**
	 * Y 방향 단위 벡터
	 */
	public static final Vector UNIT_VECTOR_Y = new Vector(0, 0, 0, 1);
	/**
	 * 0 벡터
	 */
	public static final Vector ZERO_VECTOR = new Vector(0, 0, 0, 0);

	private Line segment;

	/**
	 * @param startPoint 시점
	 * @param endPoint   종점
	 */
	public Vector(Point startPoint, Point endPoint) {
		segment = new Line(startPoint, endPoint);
	}

	public Vector(float startX, float startY, float endX, float endY) {
		segment = new Line(new Point(startX, startY), new Point(endX, endY));
	}

	/**
	 * 주어진 벡터를 복제한다.
	 *
	 * @param vector 벡터
	 */
	public Vector(@NotNull Vector vector) {
		this(new Point(vector.segment.p1), new Point(vector.segment.p2));
	}

	/**
	 * (0, 0) 위치에 시점이 설정되고, 크기와 방향에 맞게 종점이 설정된다.
	 *
	 * @param size      크기
	 * @param direction 방향
	 */
	public Vector(float size, float direction) {
		this(0, 0, 0, size);
		this.segment.rotateBy(direction, segment.p1);
	}


	public Point getStartPoint() {
		return segment.p1;
	}

	public void setStartPoint(Point startPoint) {
		segment.p1 = startPoint;
	}

	public float getStartPointX() {
		return segment.p1.x;
	}

	public float getStartPointY() {
		return segment.p1.y;
	}

	public Point getEndPoint() {
		return segment.p2;
	}

	public void setEndPoint(Point endPoint) {
		segment.p2 = endPoint;
	}

	public float getEndPointX() {
		return segment.p2.x;
	}

	public float getEndPointY() {
		return segment.p2.y;
	}

	public void setStartPoint(float startX, float startY) {
		segment.p1.x = startX;
		segment.p1.y = startY;
	}

	public void setEndPoint(float endX, float endY) {
		segment.p2.x = endX;
		segment.p2.y = endY;
	}

	/**
	 * 벡터의 크기
	 *
	 * @return 항상 양수
	 */
	public float size() {
		return segment.length();
	}

	/**
	 * 벡터의 크기를 설정
	 * 벡터의 방향은 고정이다.
	 * 시점은 고정이며, 종점이 변한다.
	 *
	 * @param length 양수를 입력해야 한다. 아니면 방향이 뒤집힐껄?
	 */
	public void setSize(float length) {
		// y축에 나란한 벡터를 만든 다음 회전시킨다.
		Line l = new Line(segment.p1, new Point(segment.p1.x, segment.p1.y + length));
		l.rotateBy(direction(), l.p1);

		setEndPoint(l.p2);
	}

	/**
	 * 이동 진행 방향에 따른 방향각을 계산
	 *
	 * @return 12시 방향을 0으로 하는 시계방향의 방향각
	 */
	public float direction() {
		return segment.slope();
	}

	/**
	 * 벡터를 회전시킨다.
	 * 벡터의 시점이 회전 중심
	 *
	 * @param theta 각도
	 */
	public void rotate(float theta) {
		segment.rotateBy(theta, segment.p1);
	}

	/**
	 * x축 방향 벡터
	 *
	 * @return 벡터
	 */
	@NotNull
	@Contract(" -> new")
	public Vector getVectorX() {
		return new Vector(new Point(segment.p1), new Point(segment.p2.x, segment.p1.y));
	}

	/**
	 * X 성분의 크기
	 *
	 * @return 부호 있음
	 */
	public float getFactorX() {
		float s = segment.p1.distanceXFrom(segment.p2.x);
		s *= (direction() <= 180) ? 1 : -1;
		return s;
	}

	/**
	 * Y축 방향 벡터
	 *
	 * @return 벡터
	 */
	@NotNull
	@Contract(" -> new")
	public Vector getVectorY() {
		return new Vector(new Point(segment.p1), new Point(segment.p1.x, segment.p2.y));
	}

	/**
	 * Y 성분의 크기
	 *
	 * @return 부호 있음
	 */
	public float getFactorY() {
		float s = segment.p1.distanceYFrom(segment.p2.y);
		s *= (direction() >= 270 || direction() <= 90) ? 1 : -1;
		return s;
	}

	/**
	 * 벡터의 덧셈
	 *
	 * @param another 벡터
	 * @return 현재 벡터
	 */
	public Vector plus(final Vector another) {
		Vector v = new Vector(another);
		v.offsetTo(segment.p2);

		this.segment.p2 = v.getEndPoint();

		return this;
	}

	/**
	 * 벡터의 뺄셈
	 *
	 * @param another 벡터
	 * @return 현재 벡터
	 */
	@Contract("_ -> this")
	public Vector minus(@NotNull final Vector another) {
		segment.p1 = another.segment.p2;
		return this;
	}

	/**
	 * 역벡터
	 * 시점과 종점의 좌표를 서로 교환한다.
	 *
	 * @return 현재 벡터
	 */
	public Vector inverse() {
		Point tmp = segment.p1;
		segment.p1 = segment.p2;
		segment.p2 = tmp;

		return this;
	}

	/**
	 * 평행 이동
	 *
	 * @param point 벡터의 시점을 이 곳으로 이동
	 * @return 현재 벡터
	 */
	@Contract("_ -> this")
	public Vector offsetTo(@NotNull final Point point) {
		return offsetTo(point.x, point.y);
	}

	/**
	 * 평행 이동
	 *
	 * @param x 벡터의 시점을 이 곳으로 이동
	 * @param y 벡터의 시점을 이 곳으로 이동
	 * @return 현재 벡터
	 */
	public Vector offsetTo(final float x, final float y) {
		float dX = segment.p1.diffXFrom(x);
		float dY = segment.p1.diffYFrom(y);
		offsetBy(dX, dY);

		return this;
	}

	public Vector offsetBy(final float dX, final float dY) {
		this.segment.translateBy(dX, dY);
		return this;
	}

	/**
	 * 벡터의 내적
	 *
	 * @param another 벡터
	 * @return 벡터의 내적
	 */
	public float innerProduct2(@NotNull Vector another) {
		float a1 = this.direction();
		float a2 = another.direction();
		float a = (a1 >= a2) ? a1 - a2 : a2 - a1;
		return (float) (this.size() * another.size() * Math.cos(Math.toRadians(a)));
	}

	/**
	 * 벡터의 내적
	 *
	 * @param another 벡터
	 * @return 벡터의 내적
	 */
	public float innerProduct(@NotNull Vector another) {
		return this.getFactorX() * another.getFactorX() + this.getFactorY() * another.getFactorY();
	}

	@NotNull
	@Contract(pure = true)
	@Override
	public String toString() {
		return "[" + segment.p1 + "->" + segment.p2 + "]";
	}

	/**
	 * 크기와 방향이 같은 벡터는 서로 같다.
	 *
	 * @param o 벡터
	 * @return t/f
	 */
	public boolean equals(@NotNull Vector o) {
		return this.size() == o.size()
				&& this.direction() == o.direction();
	}
}
