package damian.eisenhowermatrix;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity {

    //ArrayList<Element> elementList;
    ArrayList<ElementView> elementList;
    RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout) findViewById(R.id.mainView);
        elementList = new ArrayList<ElementView>();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "new task");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case 0:
                Toast.makeText(this,"new task",Toast.LENGTH_SHORT).show();
                ElementView ev = new ElementView(this);

                elementList.add(ev);

                rl.addView(ev);

                ev.initSize();
                //Element e = new Element(new Rect(0,0,100,50));
                //elementList.add(e);
                return true;

            case 1:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
