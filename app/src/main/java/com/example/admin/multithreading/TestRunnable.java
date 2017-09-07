package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by admin on 9/7/2017.
 */

public class TestRunnable implements Runnable {

    public static final String TAG = "TestRunnableActivityTag";
    TextView textView;
    Handler handler = new Handler(Looper.getMainLooper());

    public TestRunnable(TextView textView) {
        this.textView = textView;
    }

    int i;
    @Override
    public void run() {
        for (i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(i));
                }
            }, 1000);
            Log.d(TAG, "run: " + i + " " + Thread.currentThread());
        }
    }
}
