package tech.sidespell.prelimexam;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

        TextView txtSeekbar,txtTime;
        SeekBar seekbar;
        ToggleButton toggle;
        RadioButton r1,r2;
        int discrete=0;
        int start=0;
        int end=1000;
        int startPosition=0;
        int endPosition=0;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=-1;
        end=-1;

        txtSeekbar=(TextView)findViewById(R.id.textView);
        txtTime=(TextView)findViewById(R.id.textView2);
        seekbar=(SeekBar)findViewById(R.id.seekBar);
        toggle=(ToggleButton)findViewById(R.id.toggleButton);
        r1=(RadioButton)findViewById(R.id.radioButton);
        r2=(RadioButton)findViewById(R.id.radioButton2);


        seekbar.setProgress(startPosition);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int temp = startPosition;
                int dis = end - start;
                discrete = (start + ((temp / 1000) * dis));
            }
        });

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                AssetManager assetManager = getAssets();
                Typeface customFont   = Typeface.createFromAsset(assetManager, "fonts/SourceSansPro_Light.otf");
                txtTime.setTypeface(customFont);

                final Handler handler = new Handler();

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {

                        startPosition -= 1000;
                        txtTime.setText(startPosition + "");

                        if (startPosition > 0) {
                            handler.postDelayed(this, 1000);

                        }
                    }
                };
                handler.postDelayed(runnable,1000);
            }

        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
