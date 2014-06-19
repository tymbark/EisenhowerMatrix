package damian.eisenhowermatrix;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class ElementView extends Button {

    private static final int GRID_SIZE = 50;
    private static final int HEIGHT = 50;
    private static final int WIDTH = 120;
    private static final long MAX_CLICK_DURATION = 170;
    private static long START_CLICK_TIME = 0;
    private static long CLICK_DURATION = 0;
    //public ArrayList<Element> elementList;
    private Paint paint;

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    private boolean clicked = false;
    double x, y;
    double dx, dy;
    double startX, startY;

    public ElementView(Context context) {
        super(context);
        setText("tap to edit");
        setTextColor(Color.WHITE);
        setBackgroundColor(Color.BLACK);
        setTextSize(12);
//        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT);
//        setLayoutParams(buttonParams);

    }

    public void initSize() {
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = HEIGHT;
        params.width = WIDTH;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                START_CLICK_TIME = Calendar.getInstance().getTimeInMillis();
                dx = dy = 0;
                startX = getX();
                startY = getY();
                x = event.getX();
                y = event.getY();
//                Log.d("ACTION.DOWN", "@startclick:" + START_CLICK_TIME);
//                Log.d("ACTION DOWN"," event.getX:"+event.getX()+" event.getY:"+event.getY());
//                Log.d("POS"," event.getX:"+getX()+" event.getY:"+getY());

                break;
            case MotionEvent.ACTION_UP:
                setX((int) (getX() + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE);
                setY((int) (getY() + GRID_SIZE / 2) / GRID_SIZE * GRID_SIZE);

//                Log.d("UP NEW POS", " event.getX:" + getX() + " event.getY:" + getY());


                if (CLICK_DURATION < MAX_CLICK_DURATION) {
                    clicked = true;
                    break;
                }

//                if (clicked) {
//                    clicked = false;
//                    Log.d("ACTION.UP", "clicked" + getId());
//                }

                break;
            case MotionEvent.ACTION_MOVE:
                CLICK_DURATION = Calendar.getInstance().getTimeInMillis() - START_CLICK_TIME;

//                Log.d("ACTION.MOVE", "@startCLICK:" + START_CLICK_TIME);
//                Log.d("ACTION.MOVE", "@clickDUR:" + CLICK_DURATION);



                dx += event.getX() - x;
                dy += event.getY() - y;
                x = event.getX();
                y = event.getY();

                setX((int) (startX + dx));
                setY((int) (startY + dy));
                startX += dx;
                startY += dy;

//                Log.d("ACTION MOVE", " event.getX:"+event.getX()+" event.getY:"+event.getY());
//                Log.d("NEW POS"," event.getX:"+getX()+ " event.getY:"+getY());
        }
        return true;
    }
}
