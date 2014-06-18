package damian.eisenhowermatrix;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;


public class ElementView extends Button {

    private static final int GRID_SIZE = 100;
    //public ArrayList<Element> elementList;
    private Paint paint;

    public ElementView(Context context) {
        super(context);
        setText("Dupa");

        //setLayoutParams();

        //setBackgroundColor(Color.BLACK);
    }

    public void initSize(){
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = GRID_SIZE;
        params.width = 2 * GRID_SIZE;
    }
/*
    public ElementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    public ElementView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //init();
    }

    private void init() {
        //elementList = new ArrayList<Element>();
        paint = new Paint(Color.GRAY);
    }

    public void redraw(ArrayList<Element> e){
        elementList = e;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //if (elementList.isEmpty())
        //    return;
        //for(Element em: elementList){
            canvas.drawRect(em.rect,paint);
        }
    }*/

    double x,y;
    double dx, dy;
    double startX, startY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                dx = dy = 0;
                startX = getX();
                startY = getY();
                x = event.getX();
                y = event.getY();
                Log.d("ACTION DOWN",
                        " event.getX:"+event.getX()+
                                " event.getY:"+event.getY());
                Log.d("POS",
                        " event.getX:"+getX()+
                                " event.getY:"+getY());
                break;
            case MotionEvent.ACTION_UP:
                setX((int)(getX() + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE);
                setY((int) (getY() + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE);

                Log.d("UP NEW POS",
                        " event.getX:"+getX()+
                                " event.getY:"+getY());

                break;
            case MotionEvent.ACTION_MOVE:
                dx += event.getX() - x;
                dy += event.getY() - y;
                x = event.getX();
                y = event.getY();

                setX((int)(startX + dx));
                setY((int)(startY + dy));
                startX += dx;
                startY += dy;
                Log.d("ACTION MOVE",
                        " event.getX:"+event.getX()+
                        " event.getY:"+event.getY());

                Log.d("NEW POS",
                    " event.getX:"+getX()+
                            " event.getY:"+getY());
        }
        return true;
    }
}
