/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;

import java.io.Serializable;

/**
 * Created by Elex on 2016-05-10.
 */
public class Interpolator implements Serializable {
	private float p0, p1, p2, p3;
	private int numOfPoints;

	/**
	 * 선형 보간
	 *
	 * @param p0 시점
	 * @param p1 종점
	 */
	public Interpolator(float p0, float p1) {
		this.p0 = p0;
		this.p1 = p1;
		numOfPoints = 2;
	}

	/**
	 * 2차원 베지어 보간
	 *
	 * @param p0 시점
	 * @param p1 중간점
	 * @param p2 종점
	 */
	public Interpolator(float p0, float p1, float p2) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		numOfPoints = 3;
	}

	/**
	 * 3차원 베지어 보간
	 *
	 * @param p0 시점
	 * @param p1 중간점
	 * @param p2 중간점
	 * @param p3 종점
	 */
	public Interpolator(float p0, float p1, float p2, float p3) {
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		numOfPoints = 4;
	}

	/**
	 * 선형 보간
	 *
	 * @param t  0.0~1.0
	 * @param p0 시점
	 * @param p1 종점
	 * @return
	 */
	static float linear(float t, float p0, float p1) {
		//_x = x0 + ((x1 - x0) * elapsed / duration);
		//_y = y0 + ((y1 - y0) * elapsed / duration);
		return (1 - t) * p0 + t * p1;
	}

	/**
	 * 2차 베지어 곡선
	 *
	 * @param t  0.0~1.0
	 * @param p0 시점
	 * @param p1 중간점
	 * @param p2 종점
	 * @return
	 */
	static float bezier(float t, float p0, float p1, float p2) {
		return (1 - t) * (1 - t) * p0
				+ 2 * t * (1 - t) * p1
				+ t * t * p2;
	}

	/**
	 * 3차 베지어 보간
	 *
	 * @param t  0.0 ~ 1.0
	 * @param p0 시점
	 * @param p1 중간점 1
	 * @param p2 중간점 2
	 * @param p3 종점
	 * @return
	 */
	static float bezier(float t, float p0, float p1, float p2, float p3) {
		return (1 - t) * (1 - t) * (1 - t) * p0
				+ 3 * p1 * t * (1 - t) * (1 - t)
				+ 3 * p2 * t * t * (1 - t)
				+ t * t * t * p3;
	}

	/**
	 * @param t 0.0~1.0
	 * @return 중간 점
	 */
	public float getMidPoint(float t) {
		switch (numOfPoints) {
			case 4:
				return bezier(t, p0, p1, p2, p3);
			case 3:
				return bezier(t, p0, p1, p2);
			case 2:
			default:
				return linear(t, p0, p1);
		}
	}

	public float getStartPoint() {
		return this.p0;
	}

	public float getEndPoint() {
		switch (numOfPoints) {
			case 4:
				return p3;
			case 3:
				return p2;
			case 2:
			default:
				return p1;
		}
	}

}
