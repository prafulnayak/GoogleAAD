package com.anyemi.constraintl;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import android.widget.EditText;

public class NoteEditText extends android.support.v7.widget.AppCompatEditText {


    public NoteEditText(Context context) {
        super(context);
        init(null);
    }

    public NoteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }



    public NoteEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(@Nullable AttributeSet attrs) {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),"fonts/Quicksand-Regular.ttf");
        setTypeface(tf);

    }

    @Override
    public boolean onKeyUp (int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_2) {
//            currentValue--;
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
            return true;
        }
        return true;
    }

}
