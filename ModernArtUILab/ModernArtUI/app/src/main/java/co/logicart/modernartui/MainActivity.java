package co.logicart.modernartui;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import java.util.ArrayList;
import java.util.List;
import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;

public class MainActivity extends ActionBarActivity {

    static private final int MORE_INFORMATION_REQUEST_CODE = 1;

    private SeekBar seekBar;
    private List<View> colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        listenForViewEvents();
    }

    private void initializeViews() {
        final RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
        final int count = mainLayout.getChildCount();

        seekBar = (SeekBar) findViewById(R.id.seekBar);
        colors = new ArrayList<View>();
        for(int i = 0; i < count; i++){
            final View view = mainLayout.getChildAt(i);
            if (view.getClass() == View.class){
                colors.add(view);
            }
        }
    }

    private void listenForViewEvents() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.v("Progress", String.format("progress=%d", progress));

                for(final View view : colors) {
                    final String tag = view.getTag().toString();

                    if(tag.equals("#FFFFFF")){continue;}

                    final int originalColor = Color.parseColor(tag);
                    final ColorDrawable colorDrawable = (ColorDrawable)view.getBackground();
                    colorDrawable.setColor( lightenColor(originalColor,progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.more_information) {
            //final Intent intent = new Intent(this, MoreInformationDialog.class);
            //startActivityForResult(intent, MORE_INFORMATION_REQUEST_CODE);
            final MoreInformationDialog dialog = new MoreInformationDialog();
            dialog.show(getFragmentManager(), "More Information");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static int lightenColor(int color,float factor){
        float r = Color.red(color)+factor;
        float g = Color.green(color)+factor*2;
        float b = Color.blue(color)+factor*3;
        int ir = Math.min(255,(int)r);
        int ig = Math.min(255,(int)g);
        int ib = Math.min(255,(int)b);
        int ia = Color.alpha(color);
        return(Color.argb(ia, ir, ig, ib));
    }
}
