package com.example.admin.multithreading;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by admin on 9/7/2017.
 */

public class TestAsynchTask extends AsyncTask<String, Integer, String> {

    TextView tvAsyncTask;

    public static final String TAG = "AsyncTaskTag";

    public TestAsynchTask(TextView tvAsyncTask) {
        this.tvAsyncTask = tvAsyncTask;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread());
        tvAsyncTask.setText("Preparing the AsyncTask");
    }

    @Override
    protected String doInBackground(String... strings) {
        Log.d(TAG, "doInBackground: " + Thread.currentThread());
        Log.d(TAG, "doInBackground: " + strings[0]);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            publishProgress(i);
        }
        String result = "Task Completed";
        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        tvAsyncTask.setText(String.valueOf(values[0]));
        Log.d(TAG, "onProgressUpdate: " + values[0] + Thread.currentThread());
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.d(TAG, "onPostExecute: " + Thread.currentThread());
        tvAsyncTask.setText(s);
    }
}
