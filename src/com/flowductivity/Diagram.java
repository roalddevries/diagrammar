package com.flowductivity;

import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Point;

public class Diagram {
	public static interface Element {

	}
	
	public static class Line implements Element {
		public Point start, end;
		
		public Line(Point start, Point end) {
			this.start = start;
			this.end = end;
		}
	}

	public Collection<Element> elements = new ArrayList<Element>();
}
