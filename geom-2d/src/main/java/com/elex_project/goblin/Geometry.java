/*
 * Copyright (c) 2017. Elex. All Rights Reserved.
 *
 */

package com.elex_project.goblin;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * 단순하지만 반복적인 연산을 처리하는 유틸리티 클래스
 * <p>
 * # 12시 방향이 절대 각도 0.
 * # 시계 방향은 +, 반시계방향은 -.
 * Created by Elex on 2016-05-11.
 */
public final class Geometry {
	@Contract(pure = true)
	public static float min(@NotNull float... values) {
		float min = values[0];
		for (float v : values) {
			min = Math.min(min, v);
		}
		return min;
	}

	@Contract(pure = true)
	public static float minX(@NotNull Point... points) {
		float min = points[0].x;
		for (Point p : points) {
			min = Math.min(min, p.x);
		}
		return min;
	}

	@Contract(pure = true)
	public static float minY(@NotNull Point... points) {
		float min = points[0].y;
		for (Point p : points) {
			min = Math.min(min, p.y);
		}
		return min;
	}

	@Contract(pure = true)
	public static float max(@NotNull float... values) {
		float max = values[0];
		for (float v : values) {
			max = Math.max(max, v);
		}
		return max;
	}

	@Contract(pure = true)
	public static float maxX(@NotNull Point... points) {
		float max = points[0].x;
		for (Point p : points) {
			max = Math.max(max, p.x);
		}
		return max;
	}

	@Contract(pure = true)
	public static float maxY(@NotNull Point... points) {
		float max = points[0].y;
		for (Point p : points) {
			max = Math.max(max, p.y);
		}
		return max;
	}

	@Contract(pure = true)
	public static float sum(@NotNull float... nums) {
		float sum = 0;
		for (float num : nums) {
			sum += num;
		}
		return sum;
	}

	@Contract(pure = true)
	public static float sumX(@NotNull Point... points) {
		float sum = 0;
		for (Point point : points) {
			sum += point.x;
		}
		return sum;
	}

	@Contract(pure = true)
	public static float sumY(@NotNull Point... points) {
		float sum = 0;
		for (Point point : points) {
			sum += point.y;
		}
		return sum;
	}

	public static float average(float... nums) {
		return sum(nums) / nums.length;
	}

	public static float averageX(Point... points) {
		return sumX(points) / points.length;
	}

	public static float averageY(Point... points) {
		return sumY(points) / points.length;
	}
}
