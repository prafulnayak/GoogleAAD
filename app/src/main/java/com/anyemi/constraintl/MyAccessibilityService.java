package com.anyemi.constraintl;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MyAccessibilityService extends AccessibilityService {

    @Override
    protected void onServiceConnected() {

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();

        info.eventTypes = AccessibilityEvent.TYPE_VIEW_CLICKED |
                AccessibilityEvent.TYPE_VIEW_FOCUSED;

        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.notificationTimeout = 30;


        this.setServiceInfo(info);

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        String test = null;
        switch (accessibilityEvent.getEventType()){
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                test = "Clicked";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                test = "Focused";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                test = "Changed";
                break;
        }

        test = test+ accessibilityEvent.getContentDescription();
        Toast.makeText(this, ""+test, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onInterrupt() {

    }
}
