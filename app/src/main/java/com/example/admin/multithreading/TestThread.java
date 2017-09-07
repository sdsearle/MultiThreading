package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by admin on 9/7/2017.
 */

public class TestThread extends Thread {

    public static final String TAG = "TestThreadActivityTag";

    TextView tvCounter;
    Handler handler = new Handler(Looper.getMainLooper());

    public TestThread(TextView tvCounter) {
        this.tvCounter = tvCounter;
    }

    int i;
    @Override
    public void run() {
        super.run();

        for (i  = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    tvCounter.setText(String.valueOf(i));
                }
            });
            Log.d(TAG, "run: " + i + " " + Thread.currentThread());
        }
    }
}
