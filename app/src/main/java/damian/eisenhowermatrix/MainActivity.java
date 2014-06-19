package damian.eisenhowermatrix;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    ArrayList<String> elementNames;
    ArrayList<ElementView> elementList;
    RelativeLayout rl;
    private static final String TAG = "MainActivity";
    String newName = new String("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rl = (RelativeLayout) findViewById(R.id.mainView);
        elementList = new ArrayList<ElementView>();
        elementNames = new ArrayList<String>();
        rl.setBackgroundResource(R.drawable.background);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "new task");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(this, "new task", Toast.LENGTH_SHORT).show();
                ElementView ev = new ElementView(this);
                ev.setId(elementList.size());
                ev.setOnClickListener(this);
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



    @Override
    public void onClick(View view) {
        ElementView ev = (ElementView) view;
        final int ID = view.getId();
        if (!ev.isClicked())
            return;
        Toast.makeText(this, "clicked:" + view.getId(), Toast.LENGTH_SHORT).show();
        ev.setClicked(false);

        AlertDialog.Builder newNameBox = new AlertDialog.Builder(this);
        newNameBox.setCancelable(true);
        newNameBox.setMessage("set name");
        final EditText et = new EditText(this);
        newNameBox.setView(et);
        newNameBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setElementViewText(et.getText().toString(), ID);
            }
        });
        newNameBox.show();

    }

    void setElementViewText(String s, int ID){
        elementList.get(ID).setText(s);
        }

}
