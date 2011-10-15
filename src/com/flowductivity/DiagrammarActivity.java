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
    	public Diagram.Element currentElement = null;
    	
        public DiagrammarView(Context context) {
            super(context);
            diagram = new Diagram();
            Diagram.Line line1 = new Diagram.Line(new Point(0, 0), new Point(64, 64));
            diagram.elements.add(line1);
            Diagram.Line line2 = new Diagram.Line(new Point(64, 0), new Point(0, 64));
            diagram.elements.add(line2);
            Diagram.Rectangle rect1 = new Diagram.Rectangle(new Point(20, 10), new Point(44, 54));
            diagram.elements.add(rect1);
        }

    	@Override
		public void onDraw(Canvas canvas) {
    		canvas.drawColor(Color.WHITE);
    		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    		paint.setColor(Color.GRAY);
    		paint.setStyle(Paint.Style.STROKE);
    		for (Diagram.Element e : diagram.elements) {
    			// TODO: for all possible shapes, like Diagram.Line:
    			if (e.getClass() == Diagram.Line.class) {
    				Diagram.Line l = (Diagram.Line) e;
    				canvas.drawLine(l.start.x, l.start.y, l.end.x, l.end.y, paint);
    			}
    			else if (e.getClass() == Diagram.Rectangle.class) {
    				Diagram.Rectangle r = (Diagram.Rectangle) e;
    				canvas.drawRect(r.start.x, r.start.y, r.end.x, r.end.y, paint);
    			}
    		}
			// TODO: do the same as above
    		if (currentElement != null) {
				Diagram.Line l = (Diagram.Line) currentElement;
	    		canvas.drawLine(l.start.x, l.start.y, l.end.x, l.end.y, paint);
    		}
    	}
    	
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            Point point;
            
            float x = event.getX();
            float y = event.getY();

			switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	point = new Point((int) x, (int) y);
                	currentElement = new Diagram.Line(point, point);
                    invalidate();
                    break;
                case MotionEvent.ACTION_MOVE:
    				Diagram.Line l = (Diagram.Line) currentElement;
                	point = new Point((int) x, (int) y);
                	currentElement = new Diagram.Line(l.start, point);
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                	diagram.elements.add(currentElement);
                    invalidate();
                    break;
            }
            return true;
        }
    }
}