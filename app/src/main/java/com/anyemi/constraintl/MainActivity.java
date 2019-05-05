package com.anyemi.constraintl;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String DAYNIGHTMODE = "day_night";
    CustomeView customeView;

    FrameLayout mLayout;

//    @Override
//    protected void onServiceConnected() {
//        // Create an overlay and display the action bar
//        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
//        mLayout = new FrameLayout(this);
//        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
//        lp.type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY;
//        lp.format = PixelFormat.TRANSLUCENT;
//        lp.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
//        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        lp.gravity = Gravity.TOP;
//        LayoutInflater inflater = LayoutInflater.from(this);
//        inflater.inflate(R.layout.activity_main, mLayout);
//        wm.addView(mLayout, lp);
//
//        configurePowerButton();
//    }
//
//    private void configurePowerButton() {
//        Button powerButton = (Button) mLayout.findViewById(R.id.power);
//        powerButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                performGlobalAction(GLOBAL_ACTION_POWER_DIALOG);
//            }
//        });
//    }

    private EditText number1, number2;
    private Button add, sub;
    static final String STATE_SCORE_1 = "55";
    static final String STATE_SCORE_2 = "88";

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "org.sairaa.android.hellosharedprefs";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customeView = findViewById(R.id.c_view);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        number1 = findViewById(R.id.editText1);
        number2 = findViewById(R.id.editText2);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);

        //shared preference
        int nightMode = mPreferences.getInt(DAYNIGHTMODE,1);
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_YES);



        } else {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_NO);

        }

        if (savedInstanceState != null) {
            int mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            int mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

            //Set the score text views
            number1.setText(String.valueOf(mScore1));
            number2.setText(String.valueOf(mScore2));
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Double a = Double.parseDouble(number1.getText().toString().trim());
               Double b = Double.parseDouble(number2.getText().toString().trim());

               Double c = a+b;
               number2.setText(String.valueOf(c));
            }
        });

        customeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "hello", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        PreferenceManager.setDefaultValues(this,R.xml.preference,false);

        SharedPreferences sharedPref =
                android.support.v7.preference.PreferenceManager
                        .getDefaultSharedPreferences(this);
        Boolean switchPref = sharedPref.getBoolean
                (SettingActivity.KEY_PREF_EXAMPLE_SWITCH, false);
        Toast.makeText(this, switchPref.toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // Save the scores.
//        outState.putInt(STATE_SCORE_1, Integer.parseInt(number1.getText().toString().trim()));
//        outState.putInt(STATE_SCORE_2, Integer.parseInt(number2.getText().toString().trim()));
        super.onSaveInstanceState(outState);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

//        int nightMode = AppCompatDelegate.getDefaultNightMode();
        int nightMode = mPreferences.getInt(DAYNIGHTMODE,AppCompatDelegate.MODE_NIGHT_YES);

//        preferencesEditor.putInt(COLOR_KEY, mColor);

        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }


// Recreate the activity for the theme change to take effect.
//        recreate();

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.night_mode) {
            // Get the night mode state of the app.
//            int nightMode = AppCompatDelegate.getDefaultNightMode();

            int nightMode = mPreferences.getInt(DAYNIGHTMODE,AppCompatDelegate.MODE_NIGHT_YES);

            SharedPreferences.Editor preferencesEditor = mPreferences.edit();

            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);

                preferencesEditor.putInt(DAYNIGHTMODE, AppCompatDelegate.MODE_NIGHT_NO);
                preferencesEditor.apply();

            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
                preferencesEditor.putInt(DAYNIGHTMODE, AppCompatDelegate.MODE_NIGHT_YES);
                preferencesEditor.apply();
            }
// Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }

    //    @Override
//    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
//
//    }
//
//    @Override
//    public void onInterrupt() {
//
//    }
}
