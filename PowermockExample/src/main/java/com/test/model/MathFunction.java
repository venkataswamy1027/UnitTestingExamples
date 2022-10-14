package com.test.model;

public class MathFunction {

	public int square(int n) {
		return n * n;
	}

	public int operation(int a, int b) {
		return multiplication(a, b);
	}

	private int multiplication(int a, int b) {
		return a * b;
	}
}
