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
    	public Class<? extends Diagram.Element> currentElementClass = Diagram.Rectangle.class;
    	public Diagram.Element currentElement = null;
		private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    	
        public DiagrammarView(Context context) {
            super(context);
    		paint.setColor(Color.GRAY);
    		paint.setStyle(Paint.Style.STROKE);
            diagram = new Diagram();
            Diagram.Line line1 = new Diagram.Line(new Point(0, 0), new Point(64, 64));
            diagram.elements.add(line1);
            Diagram.Line line2 = new Diagram.Line(new Point(64, 0), new Point(0, 64));
            diagram.elements.add(line2);
            Diagram.Rectangle rect1 = new Diagram.Rectangle(new Point(20, 10), new Point(44, 54));
            diagram.elements.add(rect1);
        }
        
        public void drawDiagramElement(Canvas canvas, Diagram.Element element) {
			if (element.getClass() == Diagram.Line.class) {
				Diagram.Line l = (Diagram.Line) element;
				canvas.drawLine(l.start.x, l.start.y, l.end.x, l.end.y, paint);
			}
			else if (element.getClass() == Diagram.Rectangle.class) {
				Diagram.Rectangle r = (Diagram.Rectangle) element;
				canvas.drawRect(r.start.x, r.start.y, r.end.x, r.end.y, paint);
			}
        }

    	@Override
		public void onDraw(Canvas canvas) {
    		canvas.drawColor(Color.WHITE);
    		for (Diagram.Element element : diagram.elements) {
    			drawDiagramElement(canvas, element);
    		}
    		if (currentElement != null) {
    			drawDiagramElement(canvas, currentElement);
    		}
    	}
    	
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            Point point;
            
            float x = event.getX();
            float y = event.getY();
        	point = new Point((int) x, (int) y);

			switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                	if (currentElementClass == Diagram.Line.class) {
                    	currentElement = new Diagram.Line(point, point);
                	}
                	else if (currentElementClass == Diagram.Rectangle.class) {
                    	currentElement = new Diagram.Rectangle(point, point);
                	}
                    break;
                case MotionEvent.ACTION_MOVE:
                	if (currentElementClass == Diagram.Line.class) {
        				Diagram.Line l = (Diagram.Line) currentElement;
                    	currentElement = new Diagram.Line(l.start, point);
                	}
                	else if (currentElementClass == Diagram.Rectangle.class) {
        				Diagram.Rectangle r = (Diagram.Rectangle) currentElement;
                    	currentElement = new Diagram.Rectangle(r.start, point);
                	}
                    break;
                case MotionEvent.ACTION_UP:
                	diagram.elements.add(currentElement);
                    break;
            }
            invalidate();
            return true;
        }
    }
}
