package com.anyemi.constraintl;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customeView = findViewById(R.id.c_view);

        number1 = findViewById(R.id.editText1);
        number2 = findViewById(R.id.editText2);
        add = findViewById(R.id.add);
        sub = findViewById(R.id.sub);

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
