package jp.ac.titech.itpro.sdl.hilbert;

import java.util.Random;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class HilbertView extends View {
    public HilbertView(Context context) {
        this(context, null);
    }

    public HilbertView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HilbertView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();
    private Canvas canvas;
    private HilbertTurtle turtle = new HilbertTurtle(new Turtle.Drawer() {
        @Override
        public void drawLine(double x0, double y0, double x1, double y1) {
            canvas.drawLine((float)x0, (float)y0, (float)x1, (float)y1, paint);
        }
    });

    @Override
    protected void onDraw(Canvas canvas) {
        Random rnd = new Random();
        int r = rnd.nextInt(255);
        int g = rnd.nextInt(255);
        int b = rnd.nextInt(255);
        super.onDraw(canvas);
        this.canvas = canvas;

        int w = canvas.getWidth();
        int h = canvas.getHeight();
        paint.setColor(Color.BLACK);
        canvas.drawRect(0, 0, w, h, paint);
        paint.setARGB(255,r,g,b);
        paint.setStrokeWidth(1);
        int size = w < h ? w : h;
        double step = (double) size / (1 << order);
        turtle.setPos((w - size + step) / 2, (h + size - step) / 2);
        turtle.setDir(HilbertTurtle.E);
        turtle.draw(order, step, HilbertTurtle.R);
    }

    private int order = 1;

    public void setOrder(int n) {
        order = n;
        invalidate();
    }
}
