package com.flowductivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class DiagrammarActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.main);
        setContentView(new DiagrammarView(this));
    }

    public class DiagrammarView extends View {
    	
        public DiagrammarView(Context context) {
            super(context);
        }

    	public void onDraw(Canvas canvas) {
    		canvas.drawColor(Color.WHITE);
    		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    		paint.setColor(Color.GRAY);
    		paint.setStyle(Paint.Style.STROKE);
    		canvas.restore();
    		canvas.save();
    		canvas.drawLine(0, 0, 64, 64, paint);
    	}
    }
}