package com.example.admin.multithreading;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivityTag";
    TextView tvCounter, tvRunnable, tvAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: " + Thread.currentThread());
        tvCounter = (TextView) findViewById(R.id.tvCounter);
        tvRunnable = (TextView) findViewById(R.id.tvRunnable);
        tvAsyncTask = (TextView) findViewById(R.id.tvAsyncTask);

    }

    public void executeThreads(View view) {
        Log.d(TAG, "executeThreads: " + Thread.currentThread());
        switch (view.getId()){
            case R.id.btnThread:

                TestThread testThread = new TestThread(tvCounter);
                testThread.start();

                break;

            case R.id.btnRunnable:

                TestRunnable testRunnable = new TestRunnable(tvRunnable);
                Thread thread = new Thread(testRunnable);
                thread.start();


                break;

            case R.id.btnAsynchronous:

                TestAsynchTask testAsynchTask = new TestAsynchTask(tvAsyncTask);
                testAsynchTask.execute("Starting AsyncTask");

                break;

            case R.id.btnThreadHandelerMessage:
                Handler handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        tvCounter.setText(message.getData().getString("data"));
                        return false;
                    }
                });

                TestThreadHandlerMessage testThreadHandelerMessage = new TestThreadHandlerMessage(handler);
                testThreadHandelerMessage.start();
                        break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent){
        Log.d(TAG, "onMessageEvent: " + messageEvent.getMessage() + " " + Thread.currentThread());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
