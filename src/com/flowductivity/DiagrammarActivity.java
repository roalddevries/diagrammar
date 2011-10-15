package com.flowductivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

public class DiagrammarActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DiagrammarView(this));
    }

    public class DiagrammarView extends View {
    	
    	public Diagram diagram; // TODO: this should probably be an Activity-property
    	
        public DiagrammarView(Context context) {
            super(context);
            diagram = new Diagram();
            Pair<Point, Point> line1 = new Pair(new Point(0, 0), new Point(64, 64));
            diagram.elements.add(line1);
            Pair<Point, Point> line2 = new Pair(new Point(64, 0), new Point(0, 64));
            diagram.elements.add(line2);
        }

    	public void onDraw(Canvas canvas) {
    		canvas.drawColor(Color.WHITE);
    		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    		paint.setColor(Color.GRAY);
    		paint.setStyle(Paint.Style.STROKE);
    		for (Object o : diagram.elements) {
    			Pair<Point, Point> p = (Pair<Point, Point>) o;
        		canvas.drawLine(p.first.x, p.first.y, p.second.x, p.second.y, paint);
    		}
    	}
    }
}